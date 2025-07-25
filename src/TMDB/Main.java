package TMDB;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import IMDB.IMDBTools;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String requestUrl = "https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1";
        String authorizationHeader = "Bearer " + ApiKeys.apiReadingToken;
        // Ainda não estamos tratando a exceção!!
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .header("accept", "application/json")
                .header("Authorization", authorizationHeader)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());
        System.out.println("-------------------------");
        ArrayList<String> titles = IMDBTools.parseAttributeByResponse(httpResponse.body(), "original_title");
//        System.out.println(httpResponse.statusCode());
//        System.out.println(httpResponse.version());
        for(String title:titles){
            System.out.println(title);
        }
        System.out.println(IMDBTools.countFilms(httpResponse.body()));
        System.out.println(IMDBTools.countOcurrencies('{',httpResponse.body()));

    }
}