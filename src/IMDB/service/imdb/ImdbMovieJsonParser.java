package IMDB.service.imdb;

import IMDB.domain.Movie;
import IMDB.service.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class ImdbMovieJsonParser implements JsonParser {
    private final String response;

    public ImdbMovieJsonParser(String response) {
        this.response = response;
    }

    // Métdo principal, responsável por retornar a lista de filmes já como objetos.
    public List<Movie> parse(){
        List<String> titles = parseAttribute( "title");
        List<String> ids = parseAttribute("id");
        List<String> ranks = parseAttribute("rank");
        List<String> fullTitles = parseAttribute("fullTitle");
        List<String> years = parseAttribute("year");
        List<String> images = parseAttribute("image");
        List<String> crews = parseAttribute("crew");
        List<String> imDbRatings = parseAttribute("imDbRating");
        List<String> imDbRatingCounts = parseAttribute("imDbRatingCount");


        List<Movie> movies = new ArrayList<>();
        int filmCount = countFilms(); // Poderíamos usar o tamanho de qualquer uma das listas acima
        for(int i=0; i<filmCount; i++){
            movies.add(new Movie(
                    ids.get(i),
                    ranks.get(i),
                    titles.get(i),
                    fullTitles.get(i),
                    years.get(i),
                    images.get(i),
                    crews.get(i),
                    imDbRatings.get(i),
                    imDbRatingCounts.get(i)));
        }

        return movies;
    }

    // Métdos auxiliares, utilizados no métdo principal .parse()
    // Dá pra usar fila pra resolver isso daqui!!!! Palinhas do Alexandre
    // Pega, como String, apenas a lista com todos os filmes
    private String getItemsByString(){
        int beginList = firstOcurrency('[', this.response);
        int endList = lastOcurrency(']', this.response);

        return this.response.substring(beginList+1, endList);
    }

    /*
     Pode dar problema em JSON's mais complexos, com objetos dentro de objetos.
     Isso também poderia ser implementado posteriormente, mas preferi manter a simplicidade já que estamos
     lidando apenas com esse endpoint da API.

     Retorna uma lista com os filmes (em String) separados.
    */
    private List<String> parseJsonMovies(String filmsString){
        List<String> filmList = new ArrayList<>();
        int filmCount = countOcurrencies('{', filmsString);

        for(int i = 0; i < filmCount; i++){
            int objectBegin = firstOcurrency('{', filmsString);
            int objectEnd = firstOcurrency('}', filmsString);
            filmList.add(filmsString.substring(objectBegin+1, objectEnd));

            // Remove o objeto que já pegamos da String
            filmsString = filmsString.substring(objectEnd+1);
        }
        return filmList;
    }

    /*
    Só funciona com atributos que são Strings!! Também poderia ser facilmente implementado posteriormente

    Gera uma lista contendo certo atributo de uma lista de objetos (cujos valores estão em String)
    */
    private List<String> parseAttributeByList(String atribute, List<String> movies){
        List<String> atributeList = new ArrayList<>();
        int atributeNameSize = atribute.length();

        for(String item : movies){
            int atributeEnd = item.indexOf('"' + atribute + '"' + ':') + atributeNameSize + 2; // Abrange até o ':'
            int valueBegin = atributeEnd+2; // Ignora as aspas do comeco

            String temp = item.substring(valueBegin);
            temp = temp.substring(0, firstOcurrency('"', temp));
            atributeList.add(temp);
        }
        return atributeList;
    }

    private List<String> parseAttribute(String atribute){
        // Pega só a lista "Items" (como uma string), que contém todos os objetos dos filmes
        String itemsStr = getItemsByString();

        // Separa os objetos (cada um sendo uma String)
        List<String> responseItemsArray = parseJsonMovies(itemsStr);
        return parseAttributeByList(atribute, responseItemsArray);
    }

    // Não conhecia o .indexOf() kkkk. Estes 3 primeiros métodos podem ser movidos para outra classe (questão de
    // organização)
    private static int firstOcurrency(char value, String s){
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == value){
                return i;
            }
        }
        return -1;
    }
    private static int lastOcurrency(char value, String s){
        for(int i=s.length()-1; i>=0; i--){
            if(s.charAt(i) == value){
                return i;
            }
        }
        return -1;
    }
    public static int countOcurrencies(char value, String s){
        int count = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == value){
                count++;
            }
        }
        return count;
    }
    public int countFilms(){
        String items = getItemsByString();
        return countOcurrencies('{', items);
    }
}
