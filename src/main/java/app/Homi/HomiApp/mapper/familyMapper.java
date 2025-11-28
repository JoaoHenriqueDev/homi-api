package app.Homi.HomiApp.mapper;

import app.Homi.HomiApp.dto.familyRequestDto;
import app.Homi.HomiApp.dto.familyResponseDto;
import app.Homi.HomiApp.model.familyModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface familyMapper {

    @Mapping(target = "id", ignore = true)
    familyModel toEntity(familyRequestDto familyRequestDto);

    familyResponseDto toDto(familyModel familyModel);
}
