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

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class userService {
    private final userRepository repository;

    public userResponseDto cadastrarUsuario(userRequestDto userRequestDto){
        userModel user = userMapper.toEntity(userRequestDto);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(userEnum.USER);
        userModel userSalvo = repository.save(user);
        return userMapper.toDto(userSalvo);
    }

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

    public userResponseDto atualizarUsuario(UUID id, userRequestUpdateDto userRequestUpdateDto){
        userModel user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
            user.setName(userRequestUpdateDto.name());
            user.setEmail(userRequestUpdateDto.email());
            user.setCelular(userRequestUpdateDto.celular());
            user.setFotoURL(userRequestUpdateDto.fotoUrl());
        userModel userSave = repository.save(user);
        return userMapper.toDto(userSave);
    }

    public userResponseDto buscarUsuario(UUID id){
        userModel user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        return userMapper.toDto(user);
    }

    public void deletarUsuario(UUID id){
        userModel user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        repository.deleteById(id);
    }
}
