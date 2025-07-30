package IMDB.service.imdb;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImdbApiClient {
    private String API_KEY;
    private final static String BASE_URL = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
    public ImdbApiClient(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    // O endpoint que estou usando não precisa de chave
    public ImdbApiClient() {
    }

    public String getBody() throws Exception{
        String url = BASE_URL;
        if(API_KEY != null){
            url += "/" + API_KEY;
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Poderíamos lançar uma exceção se o statusCode não for 200
        if(response.statusCode()==200){
            return response.body();
        }
        return null;
    }
}
