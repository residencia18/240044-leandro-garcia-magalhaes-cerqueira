// package br.com.techie.shoppingstore.AP003.tests;

// import org.springframework.http.HttpHeaders;
// import org.springframework.test.web.reactive.server.EntityExchangeResult;
// import org.springframework.test.web.reactive.server.WebTestClient;

// import br.com.techie.shoppingstore.AP003.dto.form.UserSystemLoginFORM;
// import br.com.techie.shoppingstore.AP003.infra.jwt.JwtToken;

// import java.util.function.Consumer;

// public class JwtAuthentication {

//   public static Consumer<HttpHeaders> getHeaderAuthorization(WebTestClient client, String username, String password) {
//     // String token = client
//     //     .post()
//     //     .uri("/api/v1/auth")
//     //     .bodyValue(new UsuarioLoginDto(username, password))
//     //     .exchange()
//     //     .expectStatus().isOk()
//     //     .expectBody(JwtToken.class)
//     //     .returnResult().getResponseBody().getToken();
//     // return headers -> headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

//     EntityExchangeResult<JwtToken> result = client
//     .post()
//     .uri("/api/v1/auth")
//     .bodyValue(new UserSystemLoginFORM(username, password))
//     .exchange()
//     .expectStatus().isOk()
//     .expectBody(JwtToken.class)
//     .returnResult();

//     JwtToken responseBody = result.getResponseBody();
//     if (responseBody != null) {
//     String token = responseBody.getToken();
//     return headers -> headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

//     } else {
//     throw new IllegalStateException("Response body is null");
//     }
//   }
// }
