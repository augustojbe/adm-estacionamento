package com.augustojbe.demo_park_api;

import com.augustojbe.demo_park_api.web.dto.UsuarioCreateDto;
import com.augustojbe.demo_park_api.web.dto.UsuarioResponseDto;
import com.augustojbe.demo_park_api.web.dto.UsuarioSenhaDto;
import com.augustojbe.demo_park_api.web.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UsuarioIt {

    @Autowired
    WebTestClient testClient;

    @Test
    public void createUsuario_ComUsernameEPasswordValidos_RotrnarUsuarioCriadoComStatus201(){
        UsuarioResponseDto responseBody = testClient
                .post()
                .uri("api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("tody@email.com", "123456"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UsuarioResponseDto.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat((responseBody)).isNotNull();
        org.assertj.core.api.Assertions.assertThat((responseBody.getId())).isNotNull();
        org.assertj.core.api.Assertions.assertThat((responseBody.getUsername())).isEqualTo("tody@email.com");
        org.assertj.core.api.Assertions.assertThat((responseBody.getRole())).isEqualTo("CLIENTE");


    }

    @Test
    public void createUsuario_ComUsernameIvalidos_RotrnarErromenssageComStatus422(){
        ErrorMessage responseBody = testClient
                .post()
                .uri("api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("", "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat((responseBody)).isNotNull();
        org.assertj.core.api.Assertions.assertThat((responseBody.getStatus())).isEqualTo(422);

        responseBody = testClient
                .post()
                .uri("api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("tody@", "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat((responseBody)).isNotNull();
        org.assertj.core.api.Assertions.assertThat((responseBody.getStatus())).isEqualTo(422);

        responseBody = testClient
                .post()
                .uri("api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("tody@email.", "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat((responseBody)).isNotNull();
        org.assertj.core.api.Assertions.assertThat((responseBody.getStatus())).isEqualTo(422);


    }

    @Test
    public void createUsuario_ComPasswordIvalidos_RotrnarErromenssageComStatus422(){
        ErrorMessage responseBody = testClient
                .post()
                .uri("api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("ana@gmail.com", ""))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat((responseBody)).isNotNull();
        org.assertj.core.api.Assertions.assertThat((responseBody.getStatus())).isEqualTo(422);

        responseBody = testClient
                .post()
                .uri("api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("ana@gmail.com", "12345"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat((responseBody)).isNotNull();
        org.assertj.core.api.Assertions.assertThat((responseBody.getStatus())).isEqualTo(422);

        responseBody = testClient
                .post()
                .uri("api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("ana@gmail.com", "1234567"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat((responseBody)).isNotNull();
        org.assertj.core.api.Assertions.assertThat((responseBody.getStatus())).isEqualTo(422);


    }

    @Test
    public void createUsuario_ComUsernameRepetidoValidos_RotrnarErrorMessagedoComStatus201(){
        ErrorMessage responseBody = testClient
                .post()
                .uri("api/v1/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioCreateDto("ana@gmail.com", "123456"))
                .exchange()
                .expectStatus().isEqualTo(409)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat((responseBody)).isNotNull();
        org.assertj.core.api.Assertions.assertThat((responseBody.getStatus())).isEqualTo(409);



    }


    @Test
    public void buscarUsuario_ComIdExistente_RotrnarUsuarioCriadoComStatus200(){
        UsuarioResponseDto responseBody = testClient
                .get()
                .uri("api/v1/usuarios/100")
                .exchange()
                .expectStatus().isOk()
                .expectBody(UsuarioResponseDto.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat((responseBody)).isNotNull();
        org.assertj.core.api.Assertions.assertThat((responseBody.getId())).isEqualTo(100);
        org.assertj.core.api.Assertions.assertThat((responseBody.getUsername())).isEqualTo("ana@gmail.com");
        org.assertj.core.api.Assertions.assertThat((responseBody.getRole())).isEqualTo("ADMIN");


    }

    @Test
    public void buscarUsuario_ComIdInexistente_RotrnarUsuarioCriadoComStatus404(){
        ErrorMessage responseBody = testClient
                .get()
                .uri("api/v1/usuarios/0")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat((responseBody)).isNotNull();
        org.assertj.core.api.Assertions.assertThat((responseBody.getStatus())).isEqualTo(404);



    }

    @Test
    public void editarSenha_ComDadosValidos_RotrnarStatus204(){
         testClient
                .patch()
                .uri("api/v1/usuarios/100")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioSenhaDto("123456", "654321", "654321"))
                .exchange()
                .expectStatus().isNoContent();

    }

    @Test
    public void editarSenha_ComIdInexistente_RotrnarErrorMenssageComStatus404(){
        ErrorMessage responseBody = testClient
                .patch()
                .uri("api/v1/usuarios/0")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioSenhaDto("123456", "654321", "654321"))
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat((responseBody)).isNotNull();
        org.assertj.core.api.Assertions.assertThat((responseBody.getStatus())).isEqualTo(404);



    }

    @Test
    public void editarSenha_ComCamposInvalidos_RotrnarErrorMenssageComStatus422(){
        ErrorMessage responseBody = testClient
                .patch()
                .uri("api/v1/usuarios/100")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioSenhaDto("", "", ""))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat((responseBody)).isNotNull();
        org.assertj.core.api.Assertions.assertThat((responseBody.getStatus())).isEqualTo(422);

        responseBody = testClient
                .patch()
                .uri("api/v1/usuarios/100")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioSenhaDto("12345", "12345", "123456"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat((responseBody)).isNotNull();
        org.assertj.core.api.Assertions.assertThat((responseBody.getStatus())).isEqualTo(422);

    responseBody = testClient
                .patch()
                .uri("api/v1/usuarios/100")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioSenhaDto("1234566", "1234566", "1234566"))
                .exchange()
                .expectStatus().isEqualTo(422)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat((responseBody)).isNotNull();
        org.assertj.core.api.Assertions.assertThat((responseBody.getStatus())).isEqualTo(422);



    }

    @Test
    public void editarSenha_ComSenhasInvalidas_RotrnarErrorMenssageComStatus400(){
        ErrorMessage responseBody = testClient
                .patch()
                .uri("api/v1/usuarios/100")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioSenhaDto("123456", "123455", "123456"))
                .exchange()
                .expectStatus().isEqualTo(500)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat((responseBody)).isNotNull();
        org.assertj.core.api.Assertions.assertThat((responseBody.getStatus())).isEqualTo(500);

        responseBody = testClient
                .patch()
                .uri("api/v1/usuarios/100")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UsuarioSenhaDto("000000", "123456", "123456"))
                .exchange()
                .expectStatus().isEqualTo(500)
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat((responseBody)).isNotNull();
        org.assertj.core.api.Assertions.assertThat((responseBody.getStatus())).isEqualTo(500);


    }

    @Test
    public void listarUsuarioRotrnarUsuarioCriadoComStatus200() {
        List<UsuarioResponseDto> responseBody = testClient
                .get()
                .uri("api/v1/usuarios")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(UsuarioResponseDto.class)
                .returnResult().getResponseBody();

        org.assertj.core.api.Assertions.assertThat((responseBody)).isNotNull();
    }








}
