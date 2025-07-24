import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        String requestUrl = "https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1";
        String authorization = "Bearer " + ApiKeys.apiReadingToken;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(requestUrl))
                .header("accept", "application/json")
                .header("Authorization", authorization)
                .GET()
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());
        System.out.println(httpResponse.statusCode());
        System.out.println(httpResponse.version());
    }
}