package app.Homi.HomiApp.controller;

import app.Homi.HomiApp.dto.loginRequestDto;
import app.Homi.HomiApp.dto.userRequestDto;
import app.Homi.HomiApp.dto.userRequestUpdateDto;
import app.Homi.HomiApp.dto.userResponseDto;
import app.Homi.HomiApp.security.userDetailsImpl;
import app.Homi.HomiApp.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class userController {
    private final userService service;

    @PutMapping("/update")
    public ResponseEntity<userResponseDto> updateUsuario(@AuthenticationPrincipal userDetailsImpl user, @RequestBody userRequestUpdateDto userRequestUpdateDto){
        userResponseDto response = service.atualizarUsuario(user.getId(), userRequestUpdateDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletarUsuario(@AuthenticationPrincipal userDetailsImpl user){
        service.deletarUsuario(user.getId());
        return ResponseEntity.ok("Usuario apagado");
    }
}
