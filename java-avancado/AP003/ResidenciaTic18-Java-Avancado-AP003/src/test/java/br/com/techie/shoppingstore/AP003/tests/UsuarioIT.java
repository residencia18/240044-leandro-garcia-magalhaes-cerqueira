// package br.com.techie.shoppingstore.AP003.tests;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.jdbc.Sql;
// import org.springframework.test.web.reactive.server.WebTestClient;

// import br.com.techie.shoppingstore.AP003.dto.form.PasswordUpdateFORM;
// import br.com.techie.shoppingstore.AP003.dto.form.UserSystemFORM;
// import br.com.techie.shoppingstore.AP003.dto.view.UserSystemVIEW;
// import br.com.techie.shoppingstore.AP003.infra.exception.ErrorMessage;

// import java.util.List;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @Sql(scripts = "/sql/usuarios/usuarios-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
// @Sql(scripts = "/sql/usuarios/usuarios-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
// public class UsuarioIT {

//   @Autowired
//   WebTestClient testClient;

//   @Test
//   public void createUsuario_ComUsernameEPasswordValidos_RetornarUsuarioCriadoComStatus201() {
//     UserSystemVIEW responseBody = testClient
//         .post()
//         .uri("/api/v1/usuarios")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new UserSystemFORM("joao@Gmail.com", "joao", "JAVA!@#ResTIc18", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isCreated()
//         .expectBody(UserSystemVIEW.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.id()).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.email()).isEqualTo("joao@Gmail.com");
//     org.assertj.core.api.Assertions.assertThat(responseBody.role()).isEqualTo("CLIENT");
//   }

//   @Test
//   public void _createUsuario_ComUsernameInvalido_RetornarErrorMessageStatus422() {
//     ErrorMessage responseBody = testClient
//         .post()
//         .uri("/api/v1/usuarios")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new UserSystemFORM("", "joao","JAVA!@#ResTIc18", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isEqualTo(422)
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

//     responseBody = testClient
//         .post()
//         .uri("/api/v1/usuarios")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new UserSystemFORM("tody@","tody", "JAVA!@#ResTIc18", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isEqualTo(422)
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

//     responseBody = testClient
//         .post()
//         .uri("/api/v1/usuarios")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new UserSystemFORM("tody@email", "tody", "JAVA!@#ResTIc18", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isEqualTo(422)
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
//   }

//   @Test
//   public void _createUsuario_ComPasswordInvalido_RetornarErrorMessageStatus422() {
//     ErrorMessage responseBody = testClient
//         .post()
//         .uri("/api/v1/usuarios")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new UserSystemFORM("tody@gmail.com", "tody", "", ""))
//         .exchange()
//         .expectStatus().isEqualTo(422)
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

//     responseBody = testClient
//         .post()
//         .uri("/api/v1/usuarios")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new UserSystemFORM("tody@gmail.com", "tody", "123", "123"))
//         .exchange()
//         .expectStatus().isEqualTo(422)
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

//     responseBody = testClient
//         .post()
//         .uri("/api/v1/usuarios")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new UserSystemFORM("tody@gmail.com", "tody", "JAVA!@#ResTIc18fg444", "JAVA!@#ResTIc18fg444"))
//         .exchange()
//         .expectStatus().isEqualTo(422)
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
//   }

//   @Test
//   public void _createUsuario_ComUsernameRepetido_RetornarErrorMessageComStaus409() {
//     ErrorMessage responseBody = testClient
//         .post()
//         .uri("/api/v1/usuarios")
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new UserSystemFORM("ana@email.com", "ana", "JAVA!@#ResTIc18", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isEqualTo(409)
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(409);
//   }

//   @Test
//   public void buscarUsuario_ComIdExistente_RetornarUsuarioComStatus200() {
//     UserSystemVIEW responseBody = testClient
//         .get()
//         .uri("/api/v1/usuarios/100")
//         .headers(
//             JwtAuthentication.getHeaderAuthorization(testClient, "20191tadssaj0026@ifba.edu.br", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isOk()
//         .expectBody(UserSystemVIEW.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.id()).isEqualTo(100);
//     org.assertj.core.api.Assertions.assertThat(responseBody.username()).isEqualTo("20191tadssaj0026@ifba.edu.br");
//     org.assertj.core.api.Assertions.assertThat(responseBody.role()).isEqualTo("ADMIN");

//     responseBody = testClient
//         .get()
//         .uri("/api/v1/usuarios/101")
//         .headers(
//             JwtAuthentication.getHeaderAuthorization(testClient, "20191tadssaj0026@ifba.edu.br", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isOk()
//         .expectBody(UserSystemVIEW.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.id()).isEqualTo(101);
//     org.assertj.core.api.Assertions.assertThat(responseBody.username()).isEqualTo("bia@email.com");
//     org.assertj.core.api.Assertions.assertThat(responseBody.role()).isEqualTo("CLIENT");

//     responseBody = testClient
//         .get()
//         .uri("/api/v1/usuarios/101")
//         .headers(JwtAuthentication.getHeaderAuthorization(testClient, "bia@email.com", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isOk()
//         .expectBody(UserSystemVIEW.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.id()).isEqualTo(101);
//     org.assertj.core.api.Assertions.assertThat(responseBody.username()).isEqualTo("bia@email.com");
//     org.assertj.core.api.Assertions.assertThat(responseBody.role()).isEqualTo("CLIENT");
//   }

//   @Test
//   public void buscarUsuario_ComIdInexistente_RetornarErrorMessageComStatus404() {
//     ErrorMessage responseBody = testClient
//         .get()
//         .uri("/api/v1/usuarios/0")
//         .headers(
//             JwtAuthentication.getHeaderAuthorization(testClient, "20191tadssaj0026@ifba.edu.br", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isNotFound()
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
//   }

//   @Test
//   public void buscarUsuario_ComUsuarioClienteBuscandoOutroCliente_RetornarErrorMessageComStatus403() {
//     ErrorMessage responseBody = testClient
//         .get()
//         .uri("/api/v1/usuarios/102")
//         .headers(JwtAuthentication.getHeaderAuthorization(testClient, "bia@email.com", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isForbidden()
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
//   }

//   @Test
//   public void editarSenha_ComDadosValidos_RetornarStatus204() {
//     testClient
//         .patch()
//         .uri("/api/v1/usuarios/100")
//         .headers(
//             JwtAuthentication.getHeaderAuthorization(testClient, "20191tadssaj0026@ifba.edu.br", "JAVA!@#ResTIc18"))
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new PasswordUpdateFORM("JAVA!@#ResTIc18", "JAVA!@#ResTIc18", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isNoContent();

//     testClient
//         .patch()
//         .uri("/api/v1/usuarios/101")
//         .headers(JwtAuthentication.getHeaderAuthorization(testClient, "bia@email.com", "JAVA!@#ResTIc18"))
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new PasswordUpdateFORM("JAVA!@#ResTIc18", "JAVA!@#ResTIc18", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isNoContent();
//   }

//   @Test
//   public void editarSenha_ComUsuariosDiferentes_RetornarErrorMessageComStatus403() {
//     ErrorMessage responseBody = testClient
//         .patch()
//         .uri("/api/v1/usuarios/0")
//         .headers(
//             JwtAuthentication.getHeaderAuthorization(testClient, "20191tadssaj0026@ifba.edu.br", "JAVA!@#ResTIc18"))
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new PasswordUpdateFORM("JAVA!@#ResTIc18", "JAVA!@#ResTIc18", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isForbidden()
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);

//     responseBody = testClient
//         .patch()
//         .uri("/api/v1/usuarios/0")
//         .headers(JwtAuthentication.getHeaderAuthorization(testClient, "bia@email.com", "JAVA!@#ResTIc18"))
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new PasswordUpdateFORM("JAVA!@#ResTIc18", "JAVA!@#ResTIc18", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isForbidden()
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
//   }

//   @Test
//   public void editarSenha_ComCamposInvalidos_RetornarErrorMessageComStatus422() {
//     ErrorMessage responseBody = testClient
//         .patch()
//         .uri("/api/v1/usuarios/100")
//         .headers(
//             JwtAuthentication.getHeaderAuthorization(testClient, "20191tadssaj0026@ifba.edu.br", "JAVA!@#ResTIc18"))
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new PasswordUpdateFORM("", "", ""))
//         .exchange()
//         .expectStatus().isEqualTo(422)
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

//     responseBody = testClient
//         .patch()
//         .uri("/api/v1/usuarios/100")
//         .headers(
//             JwtAuthentication.getHeaderAuthorization(testClient, "20191tadssaj0026@ifba.edu.br", "JAVA!@#ResTIc18"))
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new PasswordUpdateFORM("JAVA!@#Res", "JAVA!@#Res", "JAVA!@#Res"))
//         .exchange()
//         .expectStatus().isEqualTo(422)
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);

//     responseBody = testClient
//         .patch()
//         .uri("/api/v1/usuarios/100")
//         .headers(
//             JwtAuthentication.getHeaderAuthorization(testClient, "20191tadssaj0026@ifba.edu.br", "JAVA!@#ResTIc18"))
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new PasswordUpdateFORM("JAVA!@#ResTIc18456", "JAVA!@#ResTIc18456", "JAVA!@#ResTIc18456"))
//         .exchange()
//         .expectStatus().isEqualTo(422)
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
//   }

//   @Test
//   public void editarSenha_ComSenhaInvalidas_RetornarErrorMessageComStatus400() {
//     ErrorMessage responseBody = testClient
//         .patch()
//         .uri("/api/v1/usuarios/100")
//         .headers(
//             JwtAuthentication.getHeaderAuthorization(testClient, "20191tadssaj0026@ifba.edu.br", "JAVA!@#ResTIc18"))
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new PasswordUpdateFORM("JAVA!@#ResTIc18", "JAVA!@#ResTIc18", "JAVA!@#ResTIc1"))
//         .exchange()
//         .expectStatus().isEqualTo(400)
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(400);

//     responseBody = testClient
//         .patch()
//         .uri("/api/v1/usuarios/100")
//         .headers(
//             JwtAuthentication.getHeaderAuthorization(testClient, "20191tadssaj0026@ifba.edu.br", "JAVA!@#ResTIc18"))
//         .contentType(MediaType.APPLICATION_JSON)
//         .bodyValue(new PasswordUpdateFORM("@#ResTIc18", "JAVA!@#ResTIc18", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isEqualTo(400)
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(400);
//   }

//   @Test
//   public void listarUsuarios_ComUsuarioComPermissao_RetornarListaDeUsuariosComStatus200() {
//     List<UserSystemVIEW> responseBody = testClient
//         .get()
//         .uri("/api/v1/usuarios")
//         .headers(
//             JwtAuthentication.getHeaderAuthorization(testClient, "20191tadssaj0026@ifba.edu.br", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isOk()
//         .expectBodyList(UserSystemVIEW.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.size()).isEqualTo(3);
//   }

//   @Test
//   public void listarUsuarios_ComUsuarioSemPermissao_RetornarErrorMessageComStatus403() {
//     ErrorMessage responseBody = testClient
//         .get()
//         .uri("/api/v1/usuarios")
//         .headers(JwtAuthentication.getHeaderAuthorization(testClient, "bia@email.com", "JAVA!@#ResTIc18"))
//         .exchange()
//         .expectStatus().isForbidden()
//         .expectBody(ErrorMessage.class)
//         .returnResult().getResponseBody();

//     org.assertj.core.api.Assertions.assertThat(responseBody).isNotNull();
//     org.assertj.core.api.Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
//   }
// }
