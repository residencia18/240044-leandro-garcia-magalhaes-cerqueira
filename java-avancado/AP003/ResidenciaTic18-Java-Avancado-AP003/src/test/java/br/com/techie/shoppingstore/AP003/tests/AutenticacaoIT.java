// package br.com.techie.shoppingstore.AP003.tests;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.jdbc.Sql;
// import org.springframework.test.web.reactive.server.WebTestClient;

// import br.com.techie.shoppingstore.AP003.dto.form.UserSystemLoginFORM;
// import br.com.techie.shoppingstore.AP003.infra.exception.ErrorMessage;
// import br.com.techie.shoppingstore.AP003.infra.jwt.JwtToken;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
// @Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
// public class AutenticacaoIT {

//   @Autowired
//   WebTestClient testClient;

//   @Test
//   public void autenticar_ComCredenciaisValidas_RetornarTokenComStatus200() {
//     JwtToken responseBody = testClient
//         .post()
//         .uri("/api/v1/auth")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new UserSystemLoginFORM("20191tadssaj0026@ifba.edu.br", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isOk()
//         .expectBody(JwtToken.class)
//         .returnResult().getResponseBody();
//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//   }

//   @Test
//   public void autenticar_ComCredenciaisInvalidas_RetornarErrorMessageStatus400() {
//     ErrorMessage responseBody = testClient
//         .post()
//         .uri("/api/v1/auth")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new UserSystemLoginFORM("invalido@email.com", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isBadRequest()
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(400);

//     responseBody = testClient
//         .post()
//         .uri("/api/v1/auth")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new UserSystemLoginFORM("invalido@email.com", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isBadRequest()
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(400);
//   }

//   @Test
//   public void autenticar_ComUsernameInvalido_RetornarErrorMessageStatus422() {
//     ErrorMessage responseBody = testClient
//         .post()
//         .uri("/api/v1/auth")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new UserSystemLoginFORM("aaa", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isEqualTo(402)
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(402);

//     responseBody = testClient
//         .post()
//         .uri("/api/v1/auth")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new UserSystemLoginFORM("@email.com", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isEqualTo(422)
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
//   }

//   @Test
//   public void autenticar_ComPasswordInvalido_RetornarErrorMessageStatus422() {
//     ErrorMessage responseBody = testClient
//         .post()
//         .uri("/api/v1/auth")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new UserSystemLoginFORM("ana@email.com", "JAVA!@#ResT"))
//         .exchange()
//         .expectStatus().isEqualTo(422)
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

//     responseBody = testClient
//         .post()
//         .uri("/api/v1/auth")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new UserSystemLoginFORM("ana@email.com", "ResTIc1"))
//         .exchange()
//         .expectStatus().isEqualTo(422)
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

//     responseBody = testClient
//         .post()
//         .uri("/api/v1/auth")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new UserSystemLoginFORM("ana@email.com", "JAVA!@#ResTIc18dhdyf5"))
//         .exchange()
//         .expectStatus().isEqualTo(422)
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
//   }

// }
