# Creación básica de usuarios

La creación de usuarios contiene, entre otras cosas, lo siguiente:

- Guardar los usuarios en una BD en memoria (H2)
- Validación del contenido de la request
- Validación de contraseña con una expresión regular configurable desde el archivo application.yml
- Validación de que el email no se encuentre previamente registrado
- Generación de un token JWT a modo de ejemplo con el secret y el tiempo de expiración en el archivo application.yml
- Test unitarios (JUnit y Mockito) para la mayoría de las clases que lo requieran


# Instalación

Para ejecutar una aplicación de Spring Boot con comandos de Maven, primero asegúrate de tener Maven y Java 17 instalado en tu sistema. Luego, ve a la carpeta donde se encuentra el archivo pom.xml del proyecto y ejecuta los siguientes comandos:

```shell
mvn clean install -DskipTests=true
```

```shell
mvn spring-boot:run
```

O bien puede ser compilando y ejecutado con tu IDE favorito.

El archivo application.yml contiene las siguientes configuraciones que se pueden modificar:

```yaml
general:
  token:
    secret: my-ultra-mega-secret
    expiration: 3600000
  regex:
#    Minimo 8 caracteres, incluyendo alguna mayuscula y un numero
    password: ^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$
```

# Ejemplo

## Request

Endpoint: localhost:8080/api/v1/user

La request es de tipo POST y el body es el siguiente:

```json
{
  "name" : "Juan",
  "email" : "juan.perez@gmail.com",
  "password" : "qw123asdaaA",
  "phones" : [
    {
      "number" : "1234567",
      "cityCode" : "1",
      "countryCode" : "57"
    }
  ]
}
```

## Response

```json
{
    "id": "c29db030-3742-432e-af9f-4eea8d58d007",
    "name": "Adsadasdas",
    "email": "asd1@asd.com",
    "phones": [
        {
            "id": 2,
            "number": "1234567",
            "cityCode": "1",
            "countryCode": "57"
        }
    ],
    "created": "2023-01-07T18:02:24.2900549",
    "modified": "2023-01-07T18:02:24.2984272",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc2QxQGFzZC5jb20iLCJleHAiOjE2NzMxMjg5NDR9.ABq9XAc1IFHy9uiC-UUDvuGTy-rTnHFfdBe8azZU0oE",
    "lastLogin": "2023-01-07T18:02:24.2900549",
    "active": true
}
```