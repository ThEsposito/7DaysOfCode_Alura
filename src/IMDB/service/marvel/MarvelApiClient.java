package IMDB.service.marvel;

import IMDB.domain.Content;

import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;

public class MarvelApiClient {
    private static final String BASE_URL = "https://gateway.marvel.com/v1/public";
    private static final String PUBLIC_KEY = ApiKeys.PUBLIC_KEY;
    private static final String PRIVATE_KEY = ApiKeys.PRIVATE_KEY;


    public String getBody(MarvelContentType type) throws Exception{
        String timestamp = String.valueOf(System.currentTimeMillis());

        String url = String.format("%s/%s?ts=%s&apikey=%s&hash=%s&limit=6", //tira o limite depois
                BASE_URL, type.ENDPOINT, timestamp, PUBLIC_KEY, generateMd5Hash(timestamp));
        System.out.println(url);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
//                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Poderíamos lançar uma exceção se o statusCode não for 200
        if(response.statusCode()==200){
            return response.body();
        }
        return null;

    }

    private String generateMd5Hash(String timestamp) {
        String combined_parameters = timestamp + PRIVATE_KEY + PUBLIC_KEY;

        // Ainda não entendi a seguinte parte:
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(combined_parameters.getBytes());

            byte[] digest = md.digest();

            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            System.out.println("Algoritmo de criptografia inválido");
            return null;
        }
    }

    public Content type() {
        return null;
    }
}
