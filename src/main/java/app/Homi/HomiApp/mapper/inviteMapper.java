package app.Homi.HomiApp.mapper;

import app.Homi.HomiApp.dto.inviteRequestDto;
import app.Homi.HomiApp.dto.inviteResponseDto;
import app.Homi.HomiApp.model.invitesModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface inviteMapper{

    @Mapping(target = "id", ignore = true)
    invitesModel toEntity(inviteRequestDto inviteRequestDto);

    inviteResponseDto toDto(invitesModel invitesModel);
}
