package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexionAPI {

    private HttpClient client;
    private HttpRequest request;
    private HttpResponse<String> response;
    private String uri;
    private final String apiKey = "980fc0dd6aea4966bc8b1dc2";

    public ConexionAPI(){
        client = HttpClient.newHttpClient();
        uri = "https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/USD";
    }

    public void consultarDolar() {
        request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public HttpResponse<String> getResponse(){
        return response;
    }
}
