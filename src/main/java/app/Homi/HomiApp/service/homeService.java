package app.Homi.HomiApp.service;

import app.Homi.HomiApp.dto.homeResponseDto;
import app.Homi.HomiApp.mapper.groupMapper;
import app.Homi.HomiApp.model.groupModel;
import app.Homi.HomiApp.model.userModel;
import app.Homi.HomiApp.repository.groupMemberRepository;
import app.Homi.HomiApp.repository.groupRepository;
import app.Homi.HomiApp.repository.userRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class homeService {
    private final userRepository userRepository;
    private final groupMemberRepository groupMemberRepository;
    private final groupRepository groupRepository;


    public homeResponseDto Home(UUID idUser){
        userModel user =userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        List<groupModel> group =
                groupMemberRepository.findFamiliesByUserId(idUser);

        return new homeResponseDto(
                user.getId(),
                user.getFotoURL(),
                group.stream()
                        .map(groupMapper::toDto)
                        .toList()
        );
    }
}
