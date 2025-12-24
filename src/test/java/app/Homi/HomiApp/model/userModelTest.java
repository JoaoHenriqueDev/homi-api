package app.Homi.HomiApp.model;

import app.Homi.HomiApp.dto.userRequestDto;
import app.Homi.HomiApp.dto.userResponseDto;
import app.Homi.HomiApp.service.userService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class userModelTest {

    @Autowired
    private userService service;

    @Test
    void salvarUsuario(){
        userRequestDto user = new userRequestDto("claudiopreinaldo@gmail.com","Claudio Reinaldo","Teste123","fotinhatop","8736746092");
        userResponseDto userSalvo = service.cadastrarUsuario(user);
        System.out.println(userSalvo.id());
    }

    @Test
    void atualizarUsuario(){
        userRequestDto user = new userRequestDto("jreinaldo@abbc.com","João Mour Reinaldo","774237895972348","testeycudb","119479483847");
        userResponseDto userAtualizado = service.atualizarUsuario(UUID.fromString("55533575-cf8d-486d-b727-52c979aefcce"), user);
        System.out.println(userAtualizado.id());
    }

    @Test
    void buscarUsuario(){
        userResponseDto user = service.buscarUsuario(UUID.fromString("540072eb-a4ac-43a1-9ea8-607e0640b8a0"));
        System.out.println("Nome: "+user.name()+"; Email:"+user.email()+", Celular: "+user.celular());
    }

    @Test
    void deletarUsuario(){
        service.deletarUsuario(UUID.fromString("55533575-cf8d-486d-b727-52c979aefcce"));
        System.out.println("Deletado com sucesso");
    }
}
