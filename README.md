# 🧩 Proyecto: Autenticación con Google OAuth2 en Spring Boot

Este proyecto implementa un **inicio de sesión con Google** utilizando **Spring Boot Security y OAuth2 Client**, aprovechando la configuración automática del framework.

---

## 🚀 Tecnologías utilizadas

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Security (OAuth2 Client)**
- **Maven**
- **Thymeleaf** (opcional, si tienes vistas)
- **Google Cloud Console**

---

## 📁 Estructura del proyecto

```
src/
 ├── main/
 │   ├── java/
 │   │    └── com.example.oauth2/
 │   │          ├── SpringbootOauth2GoogleApplication.java
 │   │          └── controllers/
 │   │                 └── HomeController.java
 │   └── resources/
 │        ├── static/
 │        ├── templates/
 │        └── application.properties
 └── test/
```

---

## ⚙️ Configuración de `application.properties`

Tu archivo `application.properties` debe lucir así:

```properties
spring.application.name=springboot-oauth2-google

spring.security.oauth2.client.registration.google.client-id=
spring.security.oauth2.client.registration.google.client-secret=
spring.security.oauth2.client.registration.google.scope=email,profile
```

> 📌 **Nota:**  
> Los campos `client-id` y `client-secret` se dejan vacíos para llenarlos con tus credenciales reales obtenidas desde Google Cloud Console.

---

## 🧭 Pasos para obtener tus credenciales de Google

1. **Ir a la consola de Google Cloud**  
   👉 https://console.cloud.google.com/

2. **Crear un nuevo proyecto**  
   - En la parte superior, haz clic en el **selector de proyectos** y elige **“Nuevo proyecto”**.  
   - Asigna un nombre, por ejemplo: `springboot-oauth2-google`.

3. **Habilitar la API de OAuth2**
   - En el menú lateral, ve a:  
     **APIs y servicios → Pantalla de consentimiento OAuth**  
   - Selecciona **Tipo de usuario: Externo**.  
   - Rellena los campos básicos (nombre de la app, correo de soporte, dominio si aplica).

4. **Crear las credenciales de OAuth2**
   - Ve a: **APIs y servicios → Credenciales**  
   - Clic en **“Crear credenciales” → ID de cliente de OAuth**  
   - Tipo de aplicación: **Aplicación web**

5. **Configurar los orígenes autorizados**
   - En **Orígenes JavaScript autorizados**, coloca:
     ```
     http://localhost:8080
     ```
   - En **URIs de redireccionamiento autorizados**, coloca:
     ```
     http://localhost:8080/login/oauth2/code/google
     ```

6. **Obtener tu Client ID y Client Secret**
   - Una vez creadas las credenciales, Google te mostrará:
     - `Client ID`
     - `Client Secret`

7. **Copiar al archivo de propiedades**
   - Abre tu archivo `application.properties` y pega los valores:
     ```properties
     spring.security.oauth2.client.registration.google.client-id=TU_CLIENT_ID_AQUI
     spring.security.oauth2.client.registration.google.client-secret=TU_CLIENT_SECRET_AQUI
     ```

---

## 🧠 Cómo funciona internamente

Spring Boot detecta automáticamente los archivos de configuración (`application.properties` o `application.yml`) durante el arranque gracias a su clase interna:

```
org.springframework.boot.context.config.ConfigDataEnvironmentPostProcessor
```

Este componente busca y carga las propiedades desde:
```
classpath:/application.properties
classpath:/application.yml
file:./config/application.properties
file:./config/application.yml
```

Y usa el loader interno:
- `PropertiesPropertySourceLoader` → para `.properties`
- `YamlPropertySourceLoader` → para `.yml`

Así, **no necesitas registrar manualmente tu archivo**.

---

## ▶️ Ejecución

1. Compila el proyecto:
   ```bash
   mvn clean install
   ```

2. Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

3. Abre en tu navegador:
   ```
   http://localhost:8080
   ```

4. Haz clic en el botón **“Iniciar sesión con Google”**  
   Serás redirigido a la página de autorización de Google.  
   Una vez aceptes, Google devolverá el control a tu aplicación.

---

## ✅ Resultado esperado

- El usuario puede autenticarse usando su cuenta de Google.
- Spring Security gestiona automáticamente el flujo OAuth2.
- No es necesario crear controladores personalizados para el login/logout básico (el framework ya lo hace).

---

## 🧾 Notas adicionales

- Si usas `application.yml`, la configuración equivalente sería:

  ```yaml
  spring:
    application:
      name: springboot-oauth2-google
    security:
      oauth2:
        client:
          registration:
            google:
              client-id: TU_CLIENT_ID
              client-secret: TU_CLIENT_SECRET
              scope:
                - email
                - profile
  ```

- El `scope` (`email`, `profile`) indica los datos del usuario que Google enviará a tu aplicación.
