package IMDB.domain.marvel;

import IMDB.domain.Content;

public record Series(String title, String urlImage, String rating, String year)
        implements Content {}
