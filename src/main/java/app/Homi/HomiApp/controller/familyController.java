package app.Homi.HomiApp.controller;

import app.Homi.HomiApp.dto.familyRequestDto;
import app.Homi.HomiApp.dto.familyResponseDto;
import app.Homi.HomiApp.service.familyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class familyController  {
    private final familyService familyService;

    @PostMapping("/create")
    public ResponseEntity<familyResponseDto> criarGrupo(@RequestBody familyRequestDto familyRequestDto){
        familyResponseDto group = familyService.criarGrupo(familyRequestDto);
        return ResponseEntity.ok(group);
    }

    @PostMapping("/enter")
    public ResponseEntity<familyResponseDto> entrarNoGrupo(@RequestParam UUID invite, @RequestBody UUID idUser) throws Exception {
        familyService.entrarNoGrupo(invite, idUser);
        return ResponseEntity.accepted().build();
    }

    @PutMapping("/update")
    public ResponseEntity<familyResponseDto> atualizarGroup(@RequestBody UUID idAdmin, @RequestParam UUID id, @RequestParam familyRequestDto familyRequestDto){
        familyResponseDto group = familyService.atualizarDadosGrupo(idAdmin, id,familyRequestDto);
        return ResponseEntity.ok(group);
    }

    @GetMapping("/search")
    public ResponseEntity<familyResponseDto> buscarGroup(@RequestParam String name){
        familyResponseDto group = familyService.buscarPorNome(name);
        return ResponseEntity.ok(group);
    }

    @DeleteMapping("/delete/user")
    public ResponseEntity<familyResponseDto> deleteUserGroup(@RequestBody UUID idAdmin, @RequestParam UUID idUser, @RequestParam UUID idGroup){
        familyService.deletarUsuario(idAdmin,idUser,idGroup);
        return ResponseEntity.accepted().build();
    }
}
