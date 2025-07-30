package IMDB.app;

import IMDB.service.HTMLGenerator;
import IMDB.domain.Movie;
import IMDB.service.ImdbApiClient;
import IMDB.service.ImdbMovieJsonParser;

import java.io.FileWriter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        ImdbApiClient apiClient = new ImdbApiClient();
        String body = apiClient.getBody();
        if(body==null) return;

        ImdbMovieJsonParser parser = new ImdbMovieJsonParser(body);
        List<Movie> movies = parser.parse();

        for(Movie movie : movies){
            System.out.println(movie);
        }

        // Auto-closable
        try (var fileWriter = new FileWriter("movies.html")) {
            final var htmlGenerator = new HTMLGenerator(fileWriter);
            htmlGenerator.generate(movies);
        }

    }
}