package org.bag;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws Exception {
    eleccionUserMenu();
  }

  public static double obtenerTasa(String urlFinal) throws Exception {

    HttpClient cliente = HttpClient.newHttpClient();
    HttpRequest solicitud = HttpRequest.newBuilder().uri(URI.create(urlFinal)).GET().build();

    try {
      HttpResponse<String> response = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

      JsonElement elemento = new JsonParser().parseString(response.body());
      JsonObject objectRoot = elemento.getAsJsonObject();
      return objectRoot.get("conversion_rate").getAsDouble();

    } catch (Exception e) {
      throw new Exception("Error al obtener tasa de cambio");
    }
  }

  public static void eleccionUserMenu() throws Exception {

    String apikey = System.getenv("API_KEY");
    Scanner scanner = new Scanner(System.in);

    String urlBase = "https://v6.exchangerate-api.com/v6/" + apikey + "/pair/";
    int opcion = 0;
    double tasa = 0;

    do {
      String divisaBase = "";
      String divisaDestino = "USD";

      System.out.println("\n*******************************************************");
      System.out.println("\nSea bienvenido/a  al conversor de divisas!!!");
      System.out.println("1. Dólar -> Soles");
      System.out.println("2. Soles -> Dólar");
      System.out.println("3. Dólar -> Real brasileño");
      System.out.println("4. Real brasileño -> Dólar");
      System.out.println("5. Dólar -> Peso chileno");
      System.out.println("6. Peso chileno -> Dólar");
      System.out.println("7. Salir");
      System.out.print("Eliga: ");
      opcion = scanner.nextInt();

      switch (opcion) {
        case 1, 3, 5:
          divisaBase = "USD";
          if (opcion == 1) {
            divisaDestino = "PEN";
          } else if (opcion == 3) {
            divisaDestino = "BRL";
          } else {
            divisaDestino = "CLP";
          }
          break;
        case 2:
          divisaBase = "PEN";
          break;
        case 4:
          divisaBase = "BRL";
          break;
        case 6:
          divisaBase = "CLP";
          break;
        case 7:
          System.out.println("Hasta pronto!!!");
          break;
        default:
          System.out.println("Opción no valida, intente nuevamente.");
          break;
      }
      if (opcion > 0 && opcion < 7) {
        tasa = obtenerTasa(urlBase + divisaBase + "/" + divisaDestino);
        calculoDivisa(tasa, divisaBase, divisaDestino);
      }
    } while (opcion != 7);
  }

  public static void calculoDivisa(double tasa, String divisaBase, String divisaDestino) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Ingrese el valor que desea convertir: ");
    double valor = scanner.nextDouble();

    DecimalFormat df = new DecimalFormat("0.00");
    String rst = df.format(valor * tasa);

    System.out.println(
        "\nEl valor " + valor + " [" + divisaBase + "] a " + divisaDestino + " es: " + rst);
  }
}
