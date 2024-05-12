# FAKEBOOK AUTHENTICATION AND LOGIN MODULE

## Technologies Used:

- Java (SDK 22)
- SpringBoot
- Spring Security
- PostgreSQL (Database)
- Swagger UI (API Documentation)
- Flyway (Database migration tool)
- JWT
- OAuth2.0
- AuditLog (Logs Audit tool)

## Endpoints:

### To access the endpoints of this module (Authentication and Password recovery), you may need to:

1. Start the application.
2. Access the URL: [http://localhost:8080/swagger-ui/index.html#](http://localhost:8080/swagger-ui/index.html#)

### You can also use Postman to verify the endpoints. Follow these steps:

1. Go to [https://www.postman.com/](https://www.postman.com/), create an account if you haven't already, then go to "Workspaces/Create Workspace".
2. Create a collection to archive all methods for possible future queries.
3. For this module, you'll need to create 3 requests:

    - POST [http://localhost:8080/api/auth/register](http://localhost:8080/api/auth/register)

    ### Usage Example:

    #### Body:

    ```json
    {
        "username":"username",
        "password":"@Test123",
        "email":"email@gmail.com"
    }
    ```

    - POST [http://localhost:8080/api/auth/login](http://localhost:8080/api/auth/login)

    ### Usage Example:

    #### Body:

    ```json
    {
        "username":"username",
        "password":"@Test123"
    }
    ```

    #### Response example:

    ```json
    {
        "token": "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiTGVhbmRybyIsImV4cCI6MTcxNTQxNjA2MSwiaWF0IjoxNzE1NDEyNDYxLCJzY29wZSI6IlJPTEVfVVNFUiJ9.3ERyQijCMkvmqjAj2l4RUemnYGkRGRSw96d9tmHMSk81csMf1nCVUR8CKb17oXk4yKxRwsbAqo-rYrbFJLuGJSId00mfZY4jOA4TROJKE6ZhlFkmb2r7ntR5EXnG5udqybfzZEhW2DltSJlaOKplqllDkr3Y8YBaPBJUY1nlvjP0SYlhOHcYdWbBWmiATaP8lZ1Uqi-sQOFpGYYsbQ-_vEdngUcxmI--D-4ansMXpGNmbcHUmPieLl8Fw7z7wExVgRZXtO81JmK6r_-rUkW0lO-vnPdIEerQy31WoQW4484I9eCaWf1i6j7cKy9ShKt73xx2BJtMW2xUGwf1Iom4sw"
    }
    ```

    - GET [http://localhost:8080/api/dashboard](http://localhost:8080/api/dashboard)

    1. To test this endpoint, you first need to pass the token returned in the "/login" endpoint in the "Authorization" field, using the Auth Type "Bearer Token".

    #### Authorization:

    ```json
    {
        "token": "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiTGVhbmRybyIsImV4cCI6MTcxNTQxNjA2MSwiaWF0IjoxNzE1NDEyNDYxLCJzY29wZSI6IlJPTEVfVVNFUiJ9.3ERyQijCMkvmqjAj2l4RUemnYGkRGRSw96d9tmHMSk81csMf1nCVUR8CKb17oXk4yKxRwsbAqo-rYrbFJLuGJSId00mfZY4jOA4TROJKE6ZhlFkmb2r7ntR5EXnG5udqybfzZEhW2DltSJlaOKplqllDkr3Y8YBaPBJUY1nlvjP0SYlhOHcYdWbBWmiATaP8lZ1Uqi-sQOFpGYYsbQ-_vEdngUcxmI--D-4ansMXpGNmbcHUmPieLl8Fw7z7wExVgRZXtO81JmK6r_-rUkW0lO-vnPdIEerQy31WoQW4484I9eCaWf1i6j7cKy9ShKt73xx2BJtMW2xUGwf1Iom4sw"
    }
    ```

    - POST [http://localhost:8080/api/auth/password-recovery/request](http://localhost:8080/api/auth/password-recovery/request)

    ### Usage Example:

    #### Body:

    ```json
    {
        "email": "email@gmail.com"
    }
    ```

    - POST [http://localhost:8080/api/auth/password-recovery/reset?token=EMAIL_TOKEN](http://localhost:8080/api/auth/password-recovery/reset?token=EMAIL_TOKEN)

    ### Usage Example:

    #### Body:

    ```json
    {
        "password": "@YourNewPassword123"
    }
    ```







    





