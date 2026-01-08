package app.Homi.HomiApp.service;

import app.Homi.HomiApp.dto.*;
import app.Homi.HomiApp.mapper.groupMapper;
import app.Homi.HomiApp.model.groupMemberModel;
import app.Homi.HomiApp.model.groupModel;
import app.Homi.HomiApp.repository.groupMemberRepository;
import app.Homi.HomiApp.repository.groupRepository;
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
public class groupService {
    private final groupRepository groupRepository;
    private final groupMemberRepository groupMemberRepository;
    private final userRepository userRepository;

    @Transactional
    public groupResponseDto criarGrupo(UUID id, groupRequestDto groupRequestDto){
        groupModel group = groupMapper.toEntity(groupRequestDto);
        group.setConvite(UUID.randomUUID());
        group.setIdUserAdmin(id);
        groupRepository.save(group);

        groupMemberModel member = new groupMemberModel();
        member.setIdGroup(group.getId());
        member.setCriador(true);
        member.setIdUser(group.getIdUserAdmin());
        member.setRoleUser("ADMIN");
        groupMemberRepository.save(member);

        return groupMapper.toDto(group);
    }

    public void entrarNoGrupo(UUID invite, UUID idUser) throws Exception {
        userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

        groupModel group = groupRepository.findByConvite(invite)
                .orElseThrow(() -> new EntityNotFoundException("Grupo não encontrado"));

        Optional<groupMemberModel> membroGrupoOpt =
                groupMemberRepository.findByIdgroupAndIdUser(
                        group.getId(),
                        idUser
                );

        if (membroGrupoOpt.isPresent()) {

            groupMemberModel membroGrupo = membroGrupoOpt.get();

            if ("ADMIN".equals(membroGrupo.getRoleUser())) {
                throw new IllegalStateException("Você já é ADMIN desse grupo");
            }

            throw new IllegalStateException("Usuário já pertence a esse grupo");
        }

        groupMemberModel member = new groupMemberModel();
        member.setIdGroup(group.getId());
        member.setIdUser(idUser);
        member.setRoleUser("MENBER");
        member.setCriador(false);

        groupMemberRepository.save(member);
    }
    public groupResponseDto atualizarDadosGrupo(UUID idUser, UUID id, groupRequestUpdateDto groupRequestUpdateDto){
        groupMemberModel group = groupMemberRepository
                .findByIdgroup(id)
                .stream()
                .filter(f -> f.getIdUser().equals(idUser))
                .filter(r -> r.getRoleUser().equals("ADMIN"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("\"Usuario não encontrado ou não o usuario é admin desse grupo\""));
        groupModel groupEntidade = groupRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grupo não encontrado"));
        groupEntidade.setName(groupRequestUpdateDto.name());
        groupEntidade.setDescription(groupRequestUpdateDto.description());
        return groupMapper.toDto(groupRepository.save(groupEntidade));
    }

    public void deletarUsuario(UUID idAdmin, UUID idUser, UUID idGrupo){
        groupMemberModel admin = groupMemberRepository
                .findByIdgroupAndIdUser(idGrupo, idAdmin)
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

        groupMemberModel usuario = groupMemberRepository
                .findByIdgroupAndIdUser(idGrupo, idUser)
                .orElseThrow(() ->
                        new EntityNotFoundException("Usuário não pertence a esse grupo")
                );

        if (usuario.getCriador()) {
            throw new IllegalStateException(
                    "Não é possível remover o criador do grupo"
            );
        }

        groupMemberRepository.deleteByIdgroupAndIdUser(
                idGrupo,
                idUser
        );
    }

    @Transactional
    public void deletarFamilia(UUID idAdmin, UUID id){
        groupMemberModel admin = groupMemberRepository
                .findByIdgroup(id)
                .stream()
                .filter(f -> f.getIdUser().equals(idAdmin))
                .filter(r -> r.getRoleUser().equals("ADMIN"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("\"Usuario não encontrado ou não o usuario é admin desse grupo\""));

        List<groupMemberModel> membros = groupMemberRepository.findByIdgroup(id);
        if (!membros.isEmpty()) {
            groupMemberRepository.deleteAll(membros);
        }

        groupRepository.deleteById(id);
    }

    public groupResponseDto detalhesDeGrupo(UUID idGroup, UUID idUser){
        groupMemberModel admin = groupMemberRepository
                .findByIdgroup(idGroup)
                .stream()
                .filter(f -> f.getIdUser().equals(idUser))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("\"Usuario ou grupo não encontrados\""));
        groupModel group = groupRepository.findById(idGroup).orElseThrow(() -> new EntityNotFoundException("Usuario ou grupo não encontrados"));
        return groupMapper.toDto(group);
    }
}
