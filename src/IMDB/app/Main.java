package IMDB.app;

import IMDB.service.HTMLGenerator;
import IMDB.service.IMDBTools;
import IMDB.domain.Movie;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String requestUrl = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        if(response.statusCode()!=200){return;}
        ArrayList<Movie> movies = IMDBTools.parseMovieObjects(response.body());

        for(Movie movie : movies){
            System.out.println(movie);
        }

        var printWriter = new FileWriter("movies.html");
        try {
            final var htmlGenerator = new HTMLGenerator(printWriter);
            htmlGenerator.generate(movies);
        } finally {
            printWriter.close();
        }

    }
}