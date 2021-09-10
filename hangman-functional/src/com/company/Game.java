package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Game {
    private static final String[] words = {"banana", "apple", "monkey", "orange", "cat", "dog", "one", "monster"};
    boolean gameIsDone = false;
    private int stage = 0;
    private int score = 0;
    private String answerWord = "";
    private ArrayList<Character> currentGuess = new ArrayList<>();
    private ArrayList<Character> mistakes = new ArrayList<>();
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
        currentGuess = Stream.of(answerWord.split("")).map(x -> '_').collect(Collectors.toCollection(ArrayList::new));
    }


    void play() {
//        TODO
//        print current stage and current letters guessed
        printMan();
        printCurrentGuess();
        printMistakes();
        makeGuess();
//          if(isEnd()){
//          askRestart()
//          }
    }

    void makeGuess() {
        Character input = sc.next().charAt(0);
        if (answerWord.contains(String.valueOf(input))) {
//            correct guess
            IntStream.range(0, answerWord.length()).mapToObj(index -> {
                if (answerWord.charAt(index) == input) {
                    currentGuess.set(index, input);
                };
                return index;
            }).collect(Collectors.toList());

        } else {
//          incorrect guess
            if (mistakes.contains(input)) {
                System.out.println("You have already tried " + input);
            } else {
                mistakes.add(input);
                stage++;
            }
        }
    }

    void printMistakes() {
        if (mistakes.isEmpty()) return;
        System.out.println("Mistakes:");
        mistakes.stream().map(mistake -> {
            System.out.print(mistake + " ");
            return mistake;
        }).collect(Collectors.toList());
        System.out.println("");
    }

    void printCurrentGuess() {
        System.out.println("Guess:");
        currentGuess.stream().map(letter -> {
            System.out.print(letter);
            return letter;
        }).collect(Collectors.toList());

        System.out.println("");
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
