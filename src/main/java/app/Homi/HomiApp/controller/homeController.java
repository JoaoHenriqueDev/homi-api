package app.Homi.HomiApp.controller;

import app.Homi.HomiApp.dto.homeResponseDto;
import app.Homi.HomiApp.security.userDetailsImpl;
import app.Homi.HomiApp.service.homeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class homeController {
    private final homeService homeService;

    @GetMapping("/me")
    public ResponseEntity<homeResponseDto> home(@AuthenticationPrincipal userDetailsImpl user){
        homeResponseDto dadosHome = homeService.Home(user.getId());
        return ResponseEntity.ok(dadosHome);
    }
}
