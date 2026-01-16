package app.Homi.HomiApp.service;

import app.Homi.HomiApp.Enum.userEnum;
import app.Homi.HomiApp.dto.loginRequestDto;
import app.Homi.HomiApp.dto.userRequestDto;
import app.Homi.HomiApp.dto.userRequestUpdateDto;
import app.Homi.HomiApp.dto.userResponseDto;
import app.Homi.HomiApp.mapper.userMapper;
import app.Homi.HomiApp.model.userModel;
import app.Homi.HomiApp.repository.userRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class userService {
    private final userRepository repository;
    private final CloudinaryImageStorageService uploadService;

    public userResponseDto atualizarUsuario(
            UUID id,
            userRequestUpdateDto userRequestUpdateDto,
            MultipartFile foto
    ) {

        userModel user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

        user.setName(userRequestUpdateDto.name());
        user.setEmail(userRequestUpdateDto.email());
        user.setCelular(userRequestUpdateDto.celular());

        // 🔥 PARTE DA FOTO
        if (foto != null && !foto.isEmpty()) {

            String fotoUrl = uploadService.upload(foto, "users", user.getId().toString());
            user.setFotoURL(fotoUrl);

        }

        userModel userSave = repository.save(user);
        return userMapper.toDto(userSave);
    }

    public void deletarUsuario(UUID id){
        userModel user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        repository.deleteById(id);
    }

    public userResponseDto userDetails(UUID id){
        userModel user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        return userMapper.toDto(user);
    }

}
