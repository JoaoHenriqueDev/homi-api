package app.Homi.HomiApp.model;

import app.Homi.HomiApp.dto.familyRequestDto;
import app.Homi.HomiApp.dto.familyResponseDto;
import app.Homi.HomiApp.service.familyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class familyModelTest {
//
//    @Autowired
//    private familyService service;
//
//    @Test
//    void criarGrupo(){
//        familyRequestDto family = new familyRequestDto("Famila teste", "Organização Financeira da Familia teste", UUID.fromString("077714b5-41c8-4f22-af6b-2a0e87e42368"));
//        familyResponseDto familySalva = service.criarGrupo(family);
//        System.out.println("Convite:"+familySalva.invite());
//    }
//
//    @Test
//    void entrarNoGrupo() throws Exception {
//        service.entrarNoGrupo(UUID.fromString("888c2e6d-12f2-4827-a364-90af13bff31c"),UUID.fromString("077714b5-41c8-4f22-af6b-2a0e87e42368"));
//        System.out.println("Usuario adicionado no grupo com sucesso");
//    }
//
//    @Test
//    void atualizarDados(){
//        familyRequestDto family = new familyRequestDto("teste12","te4ste666",UUID.fromString("540072eb-a4ac-43a1-9ea8-607e0640b8a0"));
//        familyResponseDto response = service.atualizarDadosGrupo(UUID.fromString("c1a1d045-78e4-4b0f-85ea-6cfc7560ba4d"),family);
//        System.out.println(response.id());
//    }
//
//    @Test
//    void deletarFamilia(){
//        service.deletarFamilia(UUID.fromString("fedba00a-fce3-4865-83b0-ea9563745499"));
//        System.out.println("Deletado com sucesso");
//    }
//
//    @Test
//    void buscarGrupoPorNome(){
//        familyResponseDto familia = service.buscarPorNome("Famila Reinaldo");
//        System.out.println("Nome: "+familia.name()+"; Descrição: "+familia.description()+"; Convite: "+familia.invite());
//    }
//
//    @Test
//    void deletarUsuarioDaFamilia(){
//        service.deletarUsuario(UUID.fromString("4c3e20b6-6bfb-4e20-93b2-a0fe13cc6f9b"),UUID.fromString("4c3e20b6-6bfb-4e20-93b2-a0fe13cc6f9b"),UUID.fromString("581f8bc0-4662-4fb5-ad22-495642cd3fc5"));
//        System.out.println("Menbro do grubo deletado com sucesso");
//    }
}
