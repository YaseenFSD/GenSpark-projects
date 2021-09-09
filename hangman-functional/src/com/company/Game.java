package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Game {
    boolean gameIsDone = false;
    String currentWord = "";
    void start() {
//        TODO
//        ask player for name
//        set current word
        try {
           currentWord = generateRandomWord();
        } catch (Exception err) {
           currentWord = randomLocalWord();
        }
    }

    void play(){
//        TODO
//        print current stage and current letters guessed
//          printMan()
//          printCurrentGuess()
//          checkEnd()
    }

    String generateRandomWord() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://random-word-api.herokuapp.com/word?number=1"))
                .build();
        String json = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        ObjectMapper obj = new ObjectMapper();
        List<String> words = obj.readValue(json, List.class);
        return words.get(0);
    }

    String randomLocalWord() {
//        TODO
        return "";
    }
}
