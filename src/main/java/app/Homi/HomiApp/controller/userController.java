package app.Homi.HomiApp.controller;

import app.Homi.HomiApp.dto.*;
import app.Homi.HomiApp.security.userDetailsImpl;
import app.Homi.HomiApp.service.userService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class userController {
    private final userService service;

    @PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<userResponseDto> atualizarUsuario(
            @AuthenticationPrincipal userDetailsImpl user,
            @RequestPart("dados") String userDados,
            @RequestPart(value = "foto", required = false) MultipartFile foto
    )  throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        userRequestUpdateDto userRequestUpdateDto =
                mapper.readValue(userDados, userRequestUpdateDto.class);
        return ResponseEntity.ok(
                service.atualizarUsuario(user.getId(), userRequestUpdateDto, foto)
        );
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deletarUsuario(@AuthenticationPrincipal userDetailsImpl user){
        service.deletarUsuario(user.getId());
        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/details")
    public ResponseEntity<apiResponseGeneric<userResponseDto>> userDetails(@AuthenticationPrincipal userDetailsImpl user){
        userResponseDto response = service.userDetails(user.getId());
        return ResponseEntity.ok(new apiResponseGeneric<>(
                true,
                "Detalhes do usuario obtidos com sucesso",
                response
        )
        );
    }

}
