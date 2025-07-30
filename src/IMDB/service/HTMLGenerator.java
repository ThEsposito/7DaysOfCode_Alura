package IMDB.service;

import IMDB.domain.Movie;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class HTMLGenerator {
    private final Writer WRITER;
    private static final String HEAD =
                  """
                  \t<head>
                  \t\t<meta charset="UTF-8">
                  \t\t<meta name="viewport" content="width=device-width, initial-scale=1.0">
                  \t\t<meta http-equiv="X-UA-Compatible" content="ie=edge">
                  \t\t<title>Top 250 Movies by IMDb</title>
                  \t</head>
                  """;

    public HTMLGenerator(Writer WRITER) {
        this.WRITER = WRITER;
    }

    private static String movieBody(Movie movie){
        return String.format("""
                \t\t<div>
                \t\t\t<h2>%s</h2>
                \t\t\t<img src="%s" alt="%s - poster">
                \t\t\t<p>Rate: %s - Year: %s</p>
                \t\t</div>
                
                """, movie.title(), movie.image(),movie.title(), movie.imDbRating(), movie.year());
    }

    public void generate(List<Movie> movies) throws java.io.IOException{
        WRITER.write(
    """
    <!DOCTYPE html>
    <html lang="en">
    """);
        WRITER.write(HEAD+"\n");
        WRITER.write("""
        \t<body>
        \t\t<h1>Top 250 movies by IMDb</h1>
        """);
        for(Movie movie:movies){
            WRITER.write(movieBody(movie));
        }
        WRITER.write("""
                \t</body>
                </html>
                """);
    }
}
