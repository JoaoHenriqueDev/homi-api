package app.Homi.HomiApp.controller;

import app.Homi.HomiApp.dto.loginRequestDto;
import app.Homi.HomiApp.dto.userRequestDto;
import app.Homi.HomiApp.dto.userRequestUpdateDto;
import app.Homi.HomiApp.dto.userResponseDto;
import app.Homi.HomiApp.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class userController {
    private final userService service;

    @PostMapping("/register")
    public ResponseEntity<userResponseDto> registrarUsuario(@RequestBody userRequestDto userRequestDto){
        userResponseDto user = service.cadastrarUsuario(userRequestDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<userResponseDto> loginUsuario(@RequestBody loginRequestDto loginRequestDto){
        userResponseDto user = service.loginUsuario(loginRequestDto);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update")
    public ResponseEntity<userResponseDto> updateUsuario(@RequestParam UUID id, @RequestBody userRequestUpdateDto userRequestUpdateDto){
        userResponseDto user = service.atualizarUsuario(id, userRequestUpdateDto);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletarUsuario(@RequestParam UUID id){
        service.deletarUsuario(id);
        return ResponseEntity.ok("Usuario apagado");
    }
}
