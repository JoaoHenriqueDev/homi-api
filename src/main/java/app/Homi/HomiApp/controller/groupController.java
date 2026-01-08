package app.Homi.HomiApp.controller;

import app.Homi.HomiApp.dto.groupRequestDto;
import app.Homi.HomiApp.dto.groupRequestUpdateDto;
import app.Homi.HomiApp.dto.groupResponseDto;
import app.Homi.HomiApp.security.userDetailsImpl;
import app.Homi.HomiApp.service.groupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class groupController {
    private final groupService groupService;

    @PostMapping("/create")
    public ResponseEntity<groupResponseDto> criarGrupo(@AuthenticationPrincipal userDetailsImpl user, @RequestBody @Valid groupRequestDto groupRequestDto){
        groupResponseDto group = groupService.criarGrupo(user.getId(),groupRequestDto);
        return ResponseEntity.ok(group);
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
