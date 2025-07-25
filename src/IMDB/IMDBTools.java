package IMDB;

import IMDB.domain.Film;
import java.util.ArrayList;

public class IMDBTools {
    // Não conhecia o .indexOf kkkk. Esses 2 primeiros métodos podem ser movidos para outra classe (questão de
    // organização)
    private static int firstOcurrency(char value, String s){
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == value){
                return i;
            }
        }
        return -1;
    }
    private static int countOcurrencies(char value, String s){
        int count = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == value){
                count++;
            }
        }
        return count;
    }
    public static int countFilms(String response){
        String items = getItemsByString(response);
        return countOcurrencies('{', items);
    }

    // Pega, como String, apenas a lista com todos os filmes
    // Poderia dar problema, se houvesse uma lista dentro de outra lista
    private static String getItemsByString(String response){
        int beginList = firstOcurrency('[', response);
        int endList = firstOcurrency(']', response);

        return response.substring(beginList+1, endList);
    }

    // Isso pode dar problema em JSON's mais complexos, com objetos dentro de objetos.
    // Isso também poderia ser implementado posteriormente, mas preferi manter a simplicidade já que estamos
    // lidando apenas com esse endpoint da API.
    // Retorna uma lista com os filmes (em String) separados. Os atributos ainda não são separados.
    private static ArrayList<String> parseJsonMovies(String filmsString){
        ArrayList<String> filmList = new ArrayList<>();
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

    // Só funciona com atributos que são Strings!!
    // Gera uma lista contendo certo atributo de uma lista de objetos (cujos valores estão em String)
    private static ArrayList<String> parseAttribute(String atribute, ArrayList<String> films){
        ArrayList<String> atributeList = new ArrayList<>();
        int atributeNameSize = atribute.length();

        for(String item : films){
            int atributeEnd = item.indexOf('"' + atribute + '"' + ':') + atributeNameSize + 2; // Abrange até o ':'
            int valueBegin = atributeEnd+2; // Ignora as aspas do comeco

            String temp = item.substring(valueBegin);

            String temp2 = temp.substring(0, firstOcurrency('"', temp));
            atributeList.add(temp2);
        }
        return atributeList;
    }

    // Faz o processo completo de uma vez, diretamente da response
    public static ArrayList<String> parseAttributeByResponse(String response, String atribute){
        // Pega só a lista "Items" (como uma string), que contém todos os objetos dos filmes
        String itemsStr = getItemsByString(response);

        // Separa os objetos (cada um sendo uma String)
        ArrayList<String> responseItemsArray = new ArrayList<>();
        responseItemsArray = parseJsonMovies(itemsStr);

        return parseAttribute(atribute, responseItemsArray);
    }

    // Retorna uma lista de objetos do tipo Film
    public static ArrayList<Film> parseMovieObjects(String response){
        ArrayList<String> titles = new ArrayList<String>();
        titles = IMDBTools.parseAttributeByResponse(response, "title");
        ArrayList<String> ids = new ArrayList<String>();
        ids = IMDBTools.parseAttributeByResponse(response, "id");
        ArrayList<String> ranks = new ArrayList<String>();
        ranks = IMDBTools.parseAttributeByResponse(response, "rank");
        ArrayList<String> fullTitles = new ArrayList<String>();
        fullTitles = IMDBTools.parseAttributeByResponse(response, "fullTitle");
        ArrayList<String> years = new ArrayList<String>();
        years = IMDBTools.parseAttributeByResponse(response, "year");
        ArrayList<String> images = new ArrayList<String>();
        images = IMDBTools.parseAttributeByResponse(response, "image");
        ArrayList<String> crews = new ArrayList<String>();
        crews = IMDBTools.parseAttributeByResponse(response, "crew");
        ArrayList<String> imDbRatings = new ArrayList<String>();
        imDbRatings = IMDBTools.parseAttributeByResponse(response, "imDbRating");
        ArrayList<String> imDbRatingCounts = new ArrayList<String>();
        imDbRatingCounts = IMDBTools.parseAttributeByResponse(response, "imDbRatingCount");


        ArrayList<Film> films = new ArrayList<>();
        int filmCount = IMDBTools.countFilms(response);
        for(int i=0; i<filmCount; i++){
            films.add(new Film(ids.get(i),
                    ranks.get(i),
                    titles.get(i),
                    fullTitles.get(i),
                    years.get(i),
                    images.get(i),
                    crews.get(i),
                    imDbRatings.get(i),
                    imDbRatingCounts.get(i)));
        }

        return films;
    }

    private static ArrayList<String> parseTitles(String response){
        ArrayList<String> titles = new ArrayList<String>();
        titles = IMDBTools.parseAttributeByResponse(response, "title");
        return titles;
    }
    private static ArrayList<String> parseUrlImages(String response){
        ArrayList<String> images = new ArrayList<String>();
        images = IMDBTools.parseAttributeByResponse(response, "image");

        return images;
    }
    private static ArrayList<String> parseRatings(String response){
        ArrayList<String> ratings = new ArrayList<String>();
        ratings = IMDBTools.parseAttributeByResponse(response, "imDbRating");

        return ratings;
    }
    private static ArrayList<String> parseYears(String response){
        ArrayList<String> years = new ArrayList<String>();
        years = IMDBTools.parseAttributeByResponse(response, "year");
        return years;
    }
}
