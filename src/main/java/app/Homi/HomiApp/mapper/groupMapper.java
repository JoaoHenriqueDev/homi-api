package app.Homi.HomiApp.mapper;

import app.Homi.HomiApp.dto.groupRequestDto;
import app.Homi.HomiApp.dto.groupResponseDto;
import app.Homi.HomiApp.dto.groupResponseListDto;
import app.Homi.HomiApp.model.groupModel;

public class groupMapper {

    public static groupModel toEntity(groupRequestDto familyRequestDto){
        groupModel family = new groupModel();
        family.setName(familyRequestDto.name());
        family.setDescription(familyRequestDto.description());
        return family;
    }

    public static groupResponseDto toDto(groupModel familyModel){
        return new groupResponseDto(familyModel.getId(), familyModel.getName(),familyModel.getDescription(),familyModel.getConvite(), familyModel.getFotoUrl());
    }

    public static groupResponseListDto toDtolist(groupModel familyModel){
        return new groupResponseListDto(familyModel.getId(), familyModel.getName(),familyModel.getDescription(), familyModel.getFotoUrl());
    }

}
