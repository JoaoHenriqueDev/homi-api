package app.Homi.HomiApp.controller;

import app.Homi.HomiApp.dto.familyRequestDto;
import app.Homi.HomiApp.dto.familyRequestUpdateDto;
import app.Homi.HomiApp.dto.familyResponseDto;
import app.Homi.HomiApp.dto.familyResponseListDto;
import app.Homi.HomiApp.security.userDetailsImpl;
import app.Homi.HomiApp.service.familyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class familyController  {
    private final familyService familyService;

    @PostMapping("/create")
    public ResponseEntity<familyResponseDto> criarGrupo(@AuthenticationPrincipal userDetailsImpl user, @RequestBody familyRequestDto familyRequestDto){
        familyResponseDto group = familyService.criarGrupo(user.getId(),familyRequestDto);
        return ResponseEntity.ok(group);
    }

    @PostMapping("/enter")
    public ResponseEntity<familyResponseDto> entrarNoGrupo(@RequestParam UUID invite, @AuthenticationPrincipal userDetailsImpl user) throws Exception {
        familyService.entrarNoGrupo(invite, user.getId());
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/update/{idGroup}")
    public ResponseEntity<familyResponseDto> atualizarGroup(@AuthenticationPrincipal userDetailsImpl user, @PathVariable UUID idGroup, @RequestBody familyRequestUpdateDto familyRequestDto){
        familyResponseDto group = familyService.atualizarDadosGrupo(user.getId(), idGroup,familyRequestDto);
        return ResponseEntity.ok(group);
    }

    @GetMapping
    public ResponseEntity<List<familyResponseListDto>> listarGrupos(@AuthenticationPrincipal userDetailsImpl user){
        List<familyResponseListDto> dadosGrupos = familyService.listarGrupos(user.getId());
        return ResponseEntity.ok(dadosGrupos);
    }

    @DeleteMapping("/delete/{idGroup}/user/{idUser}")
    public ResponseEntity<familyResponseDto> deleteUserGroup(@AuthenticationPrincipal userDetailsImpl user, @PathVariable UUID idUser, @PathVariable UUID idGroup){
        familyService.deletarUsuario(user.getId(),idUser,idGroup);
        return ResponseEntity.accepted().build();
    }
}
