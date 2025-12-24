package app.Homi.HomiApp.service;

import app.Homi.HomiApp.dto.familyRequestDto;
import app.Homi.HomiApp.dto.familyResponseDto;
import app.Homi.HomiApp.mapper.familyMapper;
import app.Homi.HomiApp.model.familyMemberModel;
import app.Homi.HomiApp.model.familyModel;
import app.Homi.HomiApp.repository.familyMemberRepository;
import app.Homi.HomiApp.repository.familyRepository;
import app.Homi.HomiApp.repository.userRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class familyService {
    private final familyRepository familyRepository;
    private final familyMemberRepository familyMemberRepository;
    private final userRepository userRepository;

    @Transactional
    public familyResponseDto criarGrupo(familyRequestDto familyRequestDto){
        familyModel family = familyMapper.toEntity(familyRequestDto);
        family.setConvite(UUID.randomUUID());
        familyRepository.save(family);

        familyMemberModel member = new familyMemberModel();
        member.setIdFamily(family.getId());
        member.setCriador(true);
        member.setIdUser(family.getIdUserAdmin());
        member.setRoleUser("ADMIN");
        familyMemberRepository.save(member);

        return familyMapper.toDto(family);
    }

    public void entrarNoGrupo(UUID invite, UUID idUser) throws Exception {
        userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

        familyModel family = familyRepository.findByConvite(invite)
                .orElseThrow(() -> new EntityNotFoundException("Grupo não encontrado"));

        Optional<familyMemberModel> membroGrupoOpt =
                familyMemberRepository.findByIdFamilyAndIdUser(
                        family.getId(),
                        idUser
                );

        if (membroGrupoOpt.isPresent()) {

            familyMemberModel membroGrupo = membroGrupoOpt.get();

            if ("ADMIN".equals(membroGrupo.getRoleUser())) {
                throw new IllegalStateException("Você já é ADMIN desse grupo");
            }

            throw new IllegalStateException("Usuário já pertence a esse grupo");
        }

        familyMemberModel member = new familyMemberModel();
        member.setIdFamily(family.getId());
        member.setIdUser(idUser);
        member.setRoleUser("MENBER");
        member.setCriador(false);

        familyMemberRepository.save(member);
    }
    public familyResponseDto atualizarDadosGrupo(UUID idUser,UUID id,familyRequestDto familyRequestDto){
        familyMemberModel user = familyMemberRepository
                .findByIdUser(idUser)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        if(!user.getRoleUser()
                .equals("ADMIN")){
            throw new IllegalArgumentException("Apenas o adminstrador do grupo pode fazer mudanças nele");
        }
        familyModel family = familyRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grupo não encontrado"));
        family.setName(familyRequestDto.name());
        family.setDescription(familyRequestDto.description());
        return familyMapper.toDto(familyRepository.save(family));
    }

    public familyResponseDto buscarPorNome(String name){
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome da família é obrigatório");
        }
        familyModel family = familyRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Grupo não encontrado"));
        return familyMapper.toDto(family);
    }

    public void deletarUsuario(UUID idAdmin, UUID idUser, UUID idGrupo){
        familyMemberModel admin = familyMemberRepository
                .findByIdFamilyAndIdUser(idGrupo, idAdmin)
                .orElseThrow(() ->
                        new EntityNotFoundException("Admin não pertence a esse grupo")
                );

        if (!"ADMIN".equals(admin.getRoleUser())) {
            throw new IllegalArgumentException(
                    "Usuário não tem permissão para esta operação"
            );
        }

        if (idAdmin.equals(idUser)) {
            throw new IllegalStateException(
                    "O ADMIN não pode remover a si mesmo do grupo"
            );
        }

        familyMemberModel usuario = familyMemberRepository
                .findByIdFamilyAndIdUser(idGrupo, idUser)
                .orElseThrow(() ->
                        new EntityNotFoundException("Usuário não pertence a esse grupo")
                );

        if (usuario.getCriador()) {
            throw new IllegalStateException(
                    "Não é possível remover o criador do grupo"
            );
        }

        familyMemberRepository.deleteByIdFamilyAndIdUser(
                idGrupo,
                idUser
        );
    }

    @Transactional
    public void deletarFamilia(UUID idAdmin, UUID id){
        familyModel family = familyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grupo não encontrado"));

        familyMemberModel admin = familyMemberRepository.findByIdUser(idAdmin).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        if(!admin.getRoleUser().equals("ADMIN")){
            throw new IllegalArgumentException("Apenas o administrador pode realizar essa ação dentro do grupo");
        }

        List<familyMemberModel> membros = familyMemberRepository.findByIdFamily(id);
        if (membros != null && !membros.isEmpty()) {
            familyMemberRepository.deleteAll(membros);
        }

        familyRepository.deleteById(id);
    }

}
