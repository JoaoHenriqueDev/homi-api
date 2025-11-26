package app.Homi.HomiApp.mapper;

import app.Homi.HomiApp.dto.userRequestDto;
import app.Homi.HomiApp.dto.userResponseDto;
import app.Homi.HomiApp.model.userModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface userMapper {
    @Mapping(target = "id", ignore = true)
    userModel toEntity(userRequestDto userRequestDto);
    userResponseDto toDto(userModel user);
}