package IMDB.domain;

/*
Para o contexto deste exercício, pensei em criar uma classe (contendo os atributos disponibilizados
pela api) com as seguintes características:
    - Não teria setters, já que vamos apenas receber os dados da API e a classe serviria apenas como uma
      representação deles.
    - Portanto, teria um construtor padrão com os atributos obrigatórios.
    - Todos os atributos serão finals, graças à ausência dos setters.
    - Ela não será interfaceada, já que não estamos lidando com outros tipos de mídia (séries, documentários, etc)
    - Faz sentido sobrescrever/implementar alguns métodos comuns, como getters, toString(), hashCode(), equals(), etc
 */
public class Film {
    private final String id;
    private final String rank;
    private final String title;
    private final String fullTitle;
    private final String year;
    private final String image;
    private final String crew;
    private final String imDbRating;
    private final String imDbRatingCount;

    public Film(String id, String rank, String title, String fullTitle, String year, String image, String crew, String imDbRating, String imDbRatingCount) {
        this.id = id;
        this.rank = rank;
        this.title = title;
        this.fullTitle = fullTitle;
        this.year = year;
        this.image = image;
        this.crew = crew;
        this.imDbRating = imDbRating;
        this.imDbRatingCount = imDbRatingCount;
    }

    public String getId() {
        return id;
    }

    public String getRank() {
        return rank;
    }

    public String getTitle() {
        return title;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public String getYear() {
        return year;
    }

    public String getImage() {
        return image;
    }

    public String getCrew() {
        return crew;
    }

    public String getImDbRating() {
        return imDbRating;
    }

    public String getImDbRatingCount() {
        return imDbRatingCount;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id='" + id + '\'' +
                ", rank='" + rank + '\'' +
                ", title='" + title + '\'' +
                ", fullTitle='" + fullTitle + '\'' +
                ", year='" + year + '\'' +
                ", image='" + image + '\'' +
                ", crew='" + crew + '\'' +
                ", imDbRating='" + imDbRating + '\'' +
                ", imDbRatingCount='" + imDbRatingCount + '\'' +
                '}';
    }
}
