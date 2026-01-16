package app.Homi.HomiApp.controller;

import app.Homi.HomiApp.dto.groupRequestDto;
import app.Homi.HomiApp.dto.groupRequestUpdateDto;
import app.Homi.HomiApp.dto.groupResponseDto;
import app.Homi.HomiApp.dto.userRequestDto;
import app.Homi.HomiApp.security.userDetailsImpl;
import app.Homi.HomiApp.service.groupService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class groupController {
    private final groupService groupService;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<groupResponseDto> criarGrupo(@AuthenticationPrincipal userDetailsImpl user,
                                                       @RequestPart("data") String data,
                                                       @RequestPart(value = "photo", required = false) MultipartFile photo) throws JsonProcessingException {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        ObjectMapper mapper = new ObjectMapper();
        groupRequestDto groupRequestDto = mapper.readValue(data, groupRequestDto.class);
        groupResponseDto group = groupService.criarGrupo(user.getId(), groupRequestDto, photo);
        return ResponseEntity.status(HttpStatus.CREATED).body(group);
    }

    @PostMapping("/enter")
    public ResponseEntity<groupResponseDto> entrarNoGrupo(@RequestParam UUID invite, @AuthenticationPrincipal userDetailsImpl user) throws Exception {
        groupService.entrarNoGrupo(invite, user.getId());
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/update/{idGroup}")
    public ResponseEntity<groupResponseDto> atualizarGroup(@AuthenticationPrincipal userDetailsImpl user, @PathVariable UUID idGroup, @RequestBody @Valid groupRequestUpdateDto groupRequestDto){
        groupResponseDto group = groupService.atualizarDadosGrupo(user.getId(), idGroup,groupRequestDto);
        return ResponseEntity.ok(group);
    }

    @DeleteMapping("/delete/{idGroup}/user/{idUser}")
    public ResponseEntity<groupResponseDto> deleteUserGroup(@AuthenticationPrincipal userDetailsImpl user, @PathVariable UUID idUser, @PathVariable UUID idGroup){
        groupService.deletarUsuario(user.getId(),idUser,idGroup);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/details")
    public ResponseEntity<groupResponseDto> groupDetails(@AuthenticationPrincipal userDetailsImpl user, @PathVariable UUID idGroup){
        groupResponseDto group = groupService.detalhesDeGrupo(idGroup, user.getId());
        return ResponseEntity.ok(group);
    }
}
