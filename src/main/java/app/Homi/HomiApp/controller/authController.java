package app.Homi.HomiApp.controller;

import app.Homi.HomiApp.dto.*;
import app.Homi.HomiApp.dto.login.loginRequestDto;
import app.Homi.HomiApp.dto.login.loginResponseDto;
import app.Homi.HomiApp.dto.user.userRequestDto;
import app.Homi.HomiApp.dto.user.userResponseDto;
import app.Homi.HomiApp.model.userModel;
import app.Homi.HomiApp.repository.userRepository;
import app.Homi.HomiApp.security.jwtService;
import app.Homi.HomiApp.service.authService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authController {
    private final AuthenticationManager authenticationManager;
    private final jwtService jwtService;
    private final userRepository userRepository;
    private final authService service;

    @PostMapping("/login")
    public ResponseEntity<apiResponseGeneric<loginResponseDto>> login(@RequestBody @Valid loginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        userModel user = userRepository
                .findByEmail(request.email())
                .orElseThrow();
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new apiResponseGeneric<>(
                true,
                "Usuario logado com sucesso",
                new loginResponseDto(token)
        ));
    }
    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> registrarUsuario(
            @RequestPart("data") String data,
            @RequestPart(value = "photo", required = false) MultipartFile photo
    ) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        userRequestDto userRequestDto =
                mapper.readValue(data, userRequestDto.class);

        userResponseDto user = service.cadastrarUsuario(userRequestDto,photo);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "message", "Usuário cadastrado com sucesso",
                        "redirect", "/auth/login"
                )
        );
    }

}
