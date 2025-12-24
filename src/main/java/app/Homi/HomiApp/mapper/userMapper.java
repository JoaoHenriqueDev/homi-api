package app.Homi.HomiApp.mapper;

import app.Homi.HomiApp.dto.userRequestDto;
import app.Homi.HomiApp.dto.userResponseDto;
import app.Homi.HomiApp.model.userModel;

public class userMapper {

    public static userModel toEntity(userRequestDto userRequestDto){
        userModel user = new userModel();
        user.setEmail(userRequestDto.email());
        user.setName(userRequestDto.name());
        user.setCelular(userRequestDto.celular());
        user.setPassword(userRequestDto.password());
        user.setFotoURL(userRequestDto.fotoUrl());
        return user;
    };
    public static userResponseDto toDto(userModel user){
        return new userResponseDto(user.getId(),user.getEmail(),user.getName(),user.getFotoURL(),user.getCelular());
    };
}