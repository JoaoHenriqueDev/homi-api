package app.Homi.HomiApp.service;

import app.Homi.HomiApp.dto.familyRequestDto;
import app.Homi.HomiApp.dto.familyRequestUpdateDto;
import app.Homi.HomiApp.dto.familyResponseDto;
import app.Homi.HomiApp.dto.familyResponseListDto;
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
    public familyResponseDto criarGrupo(UUID id, familyRequestDto familyRequestDto){
        familyModel family = familyMapper.toEntity(familyRequestDto);
        family.setConvite(UUID.randomUUID());
        family.setIdUserAdmin(id);
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
    public familyResponseDto atualizarDadosGrupo(UUID idUser, UUID id, familyRequestUpdateDto familyRequestUpdateDto){
        familyMemberModel group = familyMemberRepository
                .findByIdFamily(id)
                .stream()
                .filter(f -> f.getIdUser().equals(idUser))
                .filter(r -> r.getRoleUser().equals("ADMIN"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("\"Usuario não encontrado ou não o usuario é admin desse grupo\""));
        familyModel family = familyRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grupo não encontrado"));
        family.setName(familyRequestUpdateDto.name());
        family.setDescription(familyRequestUpdateDto.description());
        return familyMapper.toDto(familyRepository.save(family));
    }

//    public List<familyResponseDto> buscarPorNome(String name){
//        if (name == null || name.isBlank()) {
//            throw new IllegalArgumentException("Nome da família é obrigatório");
//        }
//        List<familyModel> families = familyRepository.findByName(name);
//        if(families.isEmpty()){
//                throw new EntityNotFoundException("Grupo não encontrado");
//        }
//        return families.stream()
//                .map(familyMapper::toDto)
//                .toList();
//    }

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
        familyMemberModel admin = familyMemberRepository
                .findByIdFamily(id)
                .stream()
                .filter(f -> f.getIdUser().equals(idAdmin))
                .filter(r -> r.getRoleUser().equals("ADMIN"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("\"Usuario não encontrado ou não o usuario é admin desse grupo\""));

        List<familyMemberModel> membros = familyMemberRepository.findByIdFamily(id);
        if (!membros.isEmpty()) {
            familyMemberRepository.deleteAll(membros);
        }

        familyRepository.deleteById(id);
    }

    public List<familyResponseListDto> listarGrupos(UUID idUser){
        userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        List<familyMemberModel> memberships =
                familyMemberRepository.findByIdUser(idUser);

        if (memberships.isEmpty()) {
            return List.of();
        }
        return memberships.stream()
                .map(fm -> familyRepository.findById(fm.getIdFamily())
                        .orElseThrow(() ->
                                new EntityNotFoundException("Grupo não encontrado")))
                .map(familyMapper::toDtolist)
                .toList();
    }

}
