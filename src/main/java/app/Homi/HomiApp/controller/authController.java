package app.Homi.HomiApp.controller;

import app.Homi.HomiApp.dto.loginRequestDto;
import app.Homi.HomiApp.dto.loginResponseDto;
import app.Homi.HomiApp.dto.userRequestDto;
import app.Homi.HomiApp.dto.userResponseDto;
import app.Homi.HomiApp.model.userModel;
import app.Homi.HomiApp.repository.userRepository;
import app.Homi.HomiApp.security.jwtService;
import app.Homi.HomiApp.service.userService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class authController {
    private final AuthenticationManager authenticationManager;
    private final jwtService jwtService;
    private final userRepository userRepository;
    private final userService service;

    @PostMapping("/login")
    public ResponseEntity<loginResponseDto> login(@RequestBody loginRequestDto request) {
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
        return ResponseEntity.ok(new loginResponseDto(token));
    }
    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@RequestBody userRequestDto userRequestDto){
        userResponseDto user = service.cadastrarUsuario(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                        "message", "Usuário cadastrado com sucesso",
                        "redirect", "/auth/login"
                )
        );
    }


}
