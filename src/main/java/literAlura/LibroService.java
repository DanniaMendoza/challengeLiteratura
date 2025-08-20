package literAlura;

import com.google.gson.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class LibroService {
    private static final String API_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    public List<Libro> buscarLibros(String consulta) {
        List<Libro> libros = new ArrayList<>();
        try {
            String url = API_URL + consulta.replace(" ", "+");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonArray items = json.has("items") ? json.getAsJsonArray("items") : new JsonArray();
            for (JsonElement item : items) {
                JsonObject volumeInfo = item.getAsJsonObject().getAsJsonObject("volumeInfo");
                String id = item.getAsJsonObject().get("id").getAsString();
                String titulo = volumeInfo.has("title") ? volumeInfo.get("title").getAsString() : "Sin título";
                String autor = volumeInfo.has("authors") ? volumeInfo.getAsJsonArray("authors").get(0).getAsString() : "Desconocido";
                String descripcion = volumeInfo.has("description") ? volumeInfo.get("description").getAsString() : "Sin descripción";
                libros.add(new Libro(titulo, autor, descripcion, id));
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return libros;
    }
}
