package app.Homi.HomiApp.service;

import app.Homi.HomiApp.dto.group.groupRequestDto;
import app.Homi.HomiApp.dto.group.groupRequestUpdateDto;
import app.Homi.HomiApp.dto.group.groupResponseDto;
import app.Homi.HomiApp.exceptions.exceptions;
import app.Homi.HomiApp.mapper.groupMapper;
import app.Homi.HomiApp.model.groupMemberModel;
import app.Homi.HomiApp.model.groupModel;
import app.Homi.HomiApp.repository.groupMemberRepository;
import app.Homi.HomiApp.repository.groupRepository;
import app.Homi.HomiApp.repository.userRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class groupService {
    private final groupRepository groupRepository;
    private final groupMemberRepository groupMemberRepository;
    private final userRepository userRepository;
    private final imageStoreService imageStorageService;
    private final CloudinaryImageStorageService uploadService;
    @Value("${app.default-avatar-url}")
    private String defaultAvatarUrl;

    @Transactional
    public groupResponseDto criarGrupo(UUID id, groupRequestDto groupRequestDto, MultipartFile photo){
        groupModel group = groupMapper.toEntity(groupRequestDto);
        group.setConvite(UUID.randomUUID());
        group.setIdUserAdmin(id);
        groupRepository.save(group);

        groupMemberModel member = new groupMemberModel();
        member.setIdGroup(group.getId());
        member.setCriador(true);
        member.setIdUser(group.getIdUserAdmin());
        member.setRoleUser("ADMIN");

        if (photo != null && !photo.isEmpty()) {
            validateImage(photo);

            String photoUrl = imageStorageService.upload(
                    photo,
                    "groups",
                    group.getId().toString()
            );
            group.setFotoUrl(photoUrl);
        } else {
            group.setFotoUrl(defaultAvatarUrl);
        }
        groupMemberRepository.save(member);

        return groupMapper.toDto(group);
    }

    public void entrarNoGrupo(UUID invite, UUID idUser) {

        // 1. Valida usuário
        userRepository.findById(idUser)
                .orElseThrow(() -> new exceptions.NotFoundException("Usuário não encontrado"));

        // 2. Busca grupo pelo convite
        groupModel group = groupRepository.findByConvite(invite)
                .orElseThrow(() -> new exceptions.NotFoundException("Grupo não encontrado"));

        // 3. Verifica se já é membro
        boolean jaMembro = groupMemberRepository
                .existsByIdGroupAndIdUser(group.getId(), idUser);

        if (jaMembro) {
            throw new exceptions.BusinessException("Usuário já faz parte do grupo");
        }

        // 4. Cria vínculo
        groupMemberModel membro = new groupMemberModel();
        membro.setIdGroup(group.getId());
        membro.setRoleUser("MEMBER");
        membro.setCriador(false);
        membro.setIdUser(idUser);
        membro.setDataEntrada(LocalDateTime.now());

        groupMemberRepository.save(membro);
    }


    public groupResponseDto atualizarDadosGrupo(
            UUID idUser,
            UUID id,
            groupRequestUpdateDto groupRequestUpdateDto,
            MultipartFile foto
    ) {

        groupMemberModel group = groupMemberRepository
                .findByIdGroup(id)
                .stream()
                .filter(f -> f.getIdUser().equals(idUser))
                .filter(r -> r.getRoleUser().equals("ADMIN"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Usuario não encontrado ou não o usuario é admin desse grupo"
                ));

        groupModel groupEntidade = groupRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grupo não encontrado"));

        groupEntidade.setName(groupRequestUpdateDto.name());
        groupEntidade.setDescription(groupRequestUpdateDto.description());

        // 🔥 PARTE DA FOTO
        if (foto != null && !foto.isEmpty()) {

            String fotoUrl = uploadService.upload(foto, "groups", groupEntidade.getId().toString());

            groupEntidade.setFotoUrl(fotoUrl);
        }

        return groupMapper.toDto(groupRepository.save(groupEntidade));
    }


    public void deletarUsuario(UUID idAdmin, UUID idUser, UUID idGrupo){
        groupMemberModel admin = groupMemberRepository
                .findByIdGroupAndIdUser(idGrupo, idAdmin)
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
                .findByIdGroupAndIdUser(idGrupo, idUser)
                .orElseThrow(() ->
                        new EntityNotFoundException("Usuário não pertence a esse grupo")
                );

        if (usuario.getCriador()) {
            throw new IllegalStateException(
                    "Não é possível remover o criador do grupo"
            );
        }

        groupMemberRepository.deleteByIdGroupAndIdUser(
                idGrupo,
                idUser
        );
    }

    @Transactional
    public void deletarFamilia(UUID idAdmin, UUID id){
        groupMemberModel admin = groupMemberRepository
                .findByIdGroup(id)
                .stream()
                .filter(f -> f.getIdUser().equals(idAdmin))
                .filter(r -> r.getRoleUser().equals("ADMIN"))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("\"Usuario não encontrado ou não o usuario é admin desse grupo\""));

        List<groupMemberModel> membros = groupMemberRepository.findByIdGroup(id);
        if (!membros.isEmpty()) {
            groupMemberRepository.deleteAll(membros);
        }

        groupRepository.deleteById(id);
    }

    public groupResponseDto detalhesDeGrupo(UUID idGroup, UUID idUser){
        groupMemberModel admin = groupMemberRepository
                .findByIdGroup(idGroup)
                .stream()
                .filter(f -> f.getIdUser().equals(idUser))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("\"Usuario ou grupo não encontrados\""));
        groupModel group = groupRepository.findById(idGroup).orElseThrow(() -> new EntityNotFoundException("Usuario ou grupo não encontrados"));
        return groupMapper.toDto(group);
    }

    private void validateImage(MultipartFile file) {
        if (!List.of("image/jpeg", "image/png").contains(file.getContentType())) {
            throw new RuntimeException("Formato de imagem inválido");
        }

        if (file.getSize() > 2 * 1024 * 1024) {
            throw new RuntimeException("Imagem maior que 2MB");
        }
    }
}
