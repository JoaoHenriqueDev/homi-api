package app.Homi.HomiApp.mapper;

import app.Homi.HomiApp.dto.familyRequestDto;
import app.Homi.HomiApp.dto.familyResponseDto;
import app.Homi.HomiApp.model.familyModel;

public class familyMapper {

    public static familyModel toEntity(familyRequestDto familyRequestDto){
        familyModel family = new familyModel();
        family.setName(familyRequestDto.name());
        family.setDescription(familyRequestDto.description());
        family.setIdUserAdmin(familyRequestDto.idUser());
        return family;
    }

    public static familyResponseDto toDto(familyModel familyModel){
        return new familyResponseDto(familyModel.getId(), familyModel.getName(),familyModel.getDescription(),familyModel.getConvite());
    }
}
