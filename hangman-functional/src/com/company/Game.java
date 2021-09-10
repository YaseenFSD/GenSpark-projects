package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    private static final String[] words = {"banana", "apple", "monkey", "orange", "cat", "dog", "one", "monster"};
    boolean gameIsDone = false;
    private int stage = 0;
    private int score = 0;
    private String answerWord = "";
    private String currentGuess = "";
    private String mistakes = "";
    private String name = "";
    private Scanner sc;
    void start() {
//        TODO
        Scanner sc = new Scanner(System.in);
        this.sc = sc;

        setRandomWord();
        setName();
        initializeCurrentGuess();
        System.out.println(answerWord);
//        System.out.println(Stream.of(answerWord.split("")).collect(Collectors.toList()));
    }

    void setName() {
        System.out.println("Hi what is your name?");
        String name = sc.next("[A-Za-z \\.]+");
        this.name = name;
    }

    void setRandomWord() {
        try {
            answerWord = generateRandomWord();
        } catch (Exception err) {
            answerWord = randomLocalWord();
        }
    }

    void initializeCurrentGuess() {
        currentGuess = Stream.of(answerWord.split("")).map(x -> "_").collect(Collectors.joining());
    }


    void play() {
//        TODO
//        print current stage and current letters guessed
        printMan();
//          printCurrentGuess()
//          printMistakes()
//          makeGuess
//          if(checkEnd()){
//          askRestart()
//          }
    }

    void printMan() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/com/company/stage" + stage + ".txt"));
            lines.stream().map(x -> {
                System.out.println(x);
                return x;
            }).collect(Collectors.toList());
        } catch (IOException err){
            System.out.println("Error: Stage file not found");
        }
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
        int randInt = new Random().nextInt(words.length);
        return words[randInt];
    }
}
