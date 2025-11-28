package app.Homi.HomiApp.model;

import app.Homi.HomiApp.dto.userRequestDto;
import app.Homi.HomiApp.repository.userRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
public class userModelTest {
    userRepository repository;
    userRequestDto user;

    @Test
    void salvarUsuario(){
        user.name();

    }

}
