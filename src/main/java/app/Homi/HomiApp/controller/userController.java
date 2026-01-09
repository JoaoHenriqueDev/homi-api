package app.Homi.HomiApp.controller;

import app.Homi.HomiApp.dto.*;
import app.Homi.HomiApp.security.userDetailsImpl;
import app.Homi.HomiApp.service.userService;
import jakarta.validation.Valid;
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
    public ResponseEntity<apiResponseGeneric<userResponseDto>> updateUsuario(@AuthenticationPrincipal userDetailsImpl user, @RequestBody @Valid userRequestUpdateDto userRequestUpdateDto){
        userResponseDto response = service.atualizarUsuario(user.getId(), userRequestUpdateDto);
        return ResponseEntity.ok(new apiResponseGeneric<>(
                true,
                "Dados do usuario atualizados com sucesso",
                response
        ));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletarUsuario(@AuthenticationPrincipal userDetailsImpl user){
        service.deletarUsuario(user.getId());
        return ResponseEntity
                .noContent()
                .build();
    }
}
