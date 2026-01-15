package app.Homi.HomiApp.service;

import app.Homi.HomiApp.Enum.userEnum;
import app.Homi.HomiApp.dto.loginRequestDto;
import app.Homi.HomiApp.dto.userRequestDto;
import app.Homi.HomiApp.dto.userResponseDto;
import app.Homi.HomiApp.mapper.userMapper;
import app.Homi.HomiApp.model.userModel;
import app.Homi.HomiApp.repository.userRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Service
public class authService {
    private final userRepository repository;
    private final imageStoreService imageStorageService;
    private final PasswordEncoder passwordEncoder;
    @Value("${app.default-avatar-url}")
    private String defaultAvatarUrl;

    public userResponseDto loginUsuario(loginRequestDto loginRequestDto){
        if(loginRequestDto.email() == null || loginRequestDto.email().isEmpty()){
            throw new EntityNotFoundException("Preencha o campo de email");
        }
        if(loginRequestDto.password() == null || loginRequestDto.password().isEmpty()){
            throw new EntityNotFoundException("Preencha o campo de senha");
        }
        userModel user = repository.findByEmail(loginRequestDto.email()).orElseThrow(() -> new EntityNotFoundException("Email ou senha incorretos"));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(loginRequestDto.password(),user.getPassword())){
            throw new RuntimeException("Email ou senha incorretos");
        }
        return userMapper.toDto(user);
    }

    @Transactional
    public userResponseDto cadastrarUsuario(
            userRequestDto userRequestDto,
            MultipartFile photo
    ) {
        if (repository.findByEmail(userRequestDto.email()).isPresent()) {
            throw new IllegalArgumentException("Usuário já existente");
        }

        userModel user = userMapper.toEntity(userRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(userEnum.USER);

        // salva para gerar ID
        repository.save(user);

        if (photo != null && !photo.isEmpty()) {
            validateImage(photo);

            String photoUrl = imageStorageService.upload(
                    photo,
                    "users",
                    user.getId().toString()
            );
            user.setFotoURL(photoUrl);
        } else {
            user.setFotoURL(defaultAvatarUrl);
        }

        // JPA faz dirty checking, save é opcional
        userModel userSalvo = repository.save(user);
        return userMapper.toDto(userSalvo);
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
