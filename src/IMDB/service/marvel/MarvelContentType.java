package IMDB.service.marvel;

public enum MarvelContentType {
    COMICS("comics"), COMIC_SERIES("series"),
    COMIC_STORIES("comic_stories"),
    COMIC_EVENTS_AND_CROSSOVERS("comic_events_and_crossovers");

    public final String ENDPOINT;

    MarvelContentType(String ENDPOINT){
        this.ENDPOINT = ENDPOINT;
    }
}
