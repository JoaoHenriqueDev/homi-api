package app.Homi.HomiApp.service;

import app.Homi.HomiApp.dto.userRequestDto;
import app.Homi.HomiApp.dto.userResponseDto;
import app.Homi.HomiApp.mapper.userMapper;
import app.Homi.HomiApp.model.userModel;
import app.Homi.HomiApp.repository.userRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class userService {
    private final userRepository repository;
    private final userMapper mapper;

    public userResponseDto cadastrarUsuario(userRequestDto userRequestDto){
        userModel user = mapper.toEntity(userRequestDto);
        userModel userSalvo = repository.save(user);
        return mapper.toDto(userSalvo);
    }

    public userResponseDto atualizarUsuario(BigInteger id, userRequestDto userRequestDto){
        userModel user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
            user.setName(userRequestDto.name());
            user.setEmail(userRequestDto.email());
            user.setPassword(userRequestDto.password());
            user.setCelular(userRequestDto.celular());
            user.setFotoURL(userRequestDto.fotoUrl());
        userModel userSave = repository.save(user);
        return mapper.toDto(userSave);
    }

    public userResponseDto buscarUsuario(BigInteger id){
        userModel user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        return mapper.toDto(user);
    }

    public void deletarUsuario(BigInteger id){
        userModel user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        repository.deleteById(id);
    }
}
