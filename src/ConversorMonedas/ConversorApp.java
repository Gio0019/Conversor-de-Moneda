
package ConversorMonedas;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConversorApp {
    private static final String API_KEY = "6944d75669b774c96fdace29"; // Reemplaza con tu API key
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/";

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            Conversor.exibirMenu();
            opcion = scanner.nextInt();
            
            if (opcion >= 1 && opcion <= 6) {
                System.out.println("Ingrese la cantidad a convertir:");
                double cantidad = scanner.nextDouble();
                
                String monedaOrigen = "";
                String monedaDestino = "";
                
                switch (opcion) {
                    case 1:
                        monedaOrigen = "USD";
                        monedaDestino = "ARS";
                        break;
                    case 2:
                        monedaOrigen = "ARS";
                        monedaDestino = "USD";
                        break;
                    case 3:
                        monedaOrigen = "USD";
                        monedaDestino = "BRL";
                        break;
                    case 4:
                        monedaOrigen = "BRL";
                        monedaDestino = "USD";
                        break;
                    case 5:
                        monedaOrigen = "USD";
                        monedaDestino = "COP";
                        break;
                    case 6:
                        monedaOrigen = "COP";
                        monedaDestino = "USD";
                        break;
                }
                
                String url = BASE_URL + monedaOrigen + "/" + monedaDestino;
                double tasa = obtenerTasa(url);
                double resultado = cantidad * tasa;
                
                System.out.printf("%.2f %s = %.2f %s%n%n", 
                    cantidad, monedaOrigen, resultado, monedaDestino);
            } else if (opcion != 7) {
                System.out.println("Opción no válida. Intente nuevamente.\n");
            }
        } while (opcion != 7);
        
        System.out.println("Gracias por usar el conversor de monedas. ¡Hasta pronto!");
        scanner.close();
    }

    public static double obtenerTasa(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JsonElement elemento = JsonParser.parseString(response.body());
        JsonObject objectRoot = elemento.getAsJsonObject();
        return objectRoot.get("conversion_rate").getAsDouble();
    }

       
}
