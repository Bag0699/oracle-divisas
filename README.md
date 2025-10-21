# 🌍 Conversor de Divisas (Java CLI)

Este proyecto es una aplicación de consola (CLI) desarrollada en Java que utiliza la API pública de **ExchangeRate-API** para obtener tasas de cambio y realizar conversiones un menú predefinido de monedas.

---

## 🚀 Requisitos y Configuración

Para ejecutar esta aplicación, necesitas tener instalado:

1.  **Java Development Kit (JDK)** versión 17 o superior.
2.  **Maven** (Para gestionar dependencias y la construcción del proyecto).
3.  Una **API Key** de [ExchangeRate-API](https://www.exchangerate-api.com/).

### 🔑 Variable de Entorno

La aplicación requiere que se configure la clave de API como una variable de entorno llamada `API_KEY`.

**Linux/macOS:**

```bash
export API_KEY="TU_CLAVE_AQUI"
```

**Windows (CMD):**

```bash
set API_KEY="TU_CLAVE_AQUI"
```
## 🏗️ Estructura del Proyecto

El proyecto utiliza la librería Gson de Google para el manejo de JSON y el cliente nativo de Java (HttpClient) para las peticiones HTTP.

**Dependencias de Maven**

Asegúrate de que tu archivo `pom.xml` incluya la dependencia de Gson:

```xml
<dependency>
  <groupId>com.google.code.gson</groupId>
  <artifactId>gson</artifactId>
  <version>2.10.1</version>
</dependency>
```
## ⚙️ Uso y Funcionalidades

**📋 Opciones del Menú**

La aplicación presenta un menú interactivo que permite al usuario seleccionar el par de divisas a convertir:

**💻 Ejemplo de Ejecución**

1.  Compila el proyecto usando Maven:
```bash
mvn clean install
```
   
2.  Ejecuta la clase principal `(Main.java)`.
3.  Sigue las indicaciones del menú.

```bash
*******************************************************

Sea bienvenido/a  al conversor de divisas!!!
...
7. Salir
Eliga: 3
Ingrese el valor que desea convertir: 100

El valor 100.0 [USD] a BRL es: 494.50
```
## 🛠️ Estructura del Código

La lógica principal se divide en tres métodos:

`eleccionUserMenu()`

Contiene el bucle principal de la aplicación, muestra el menú, solicita la opción del usuario y gestiona el flujo de las conversiones.

`obtenerTasa(String urlFinal)`

Encargado de realizar la petición HTTP a la API, recibir la respuesta JSON y extraer la tasa de conversión `(conversion_rate)` utilizando la librería Gson.

`calculoDivisa(double tasa, String divisaBase, String divisaDestino)`

Solicita el monto a convertir, aplica la fórmula de cálculo `(valor * tasa)`, formatea el resultado a dos decimales y lo imprime en la consola.
