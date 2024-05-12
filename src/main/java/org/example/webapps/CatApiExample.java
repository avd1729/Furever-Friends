package org.example.webapps;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CatApiExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.thecatapi.com/v1/images/search"))
                .header("x-api-key", "live_gtwvpIu7SwdrWfK7aicqEy9nCcMMCZM3pxDHhDzEPZaEt0uiYcCGaITbfZCQiAI5")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Response: " + response.body());
    }
}

