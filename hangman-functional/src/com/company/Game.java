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
//        System.out.println(answerWord);
    }

    void setName() {
        System.out.println("Hi what is your name?");
        String name = sc.next("[A-Za-z \\.]+");
        this.name = name;
    }

    void setRandomWord() {
        answerWord = generateRandomWord();
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
        if (isEnd()) {
            askRestart();
        }
    }

    void replay() {
        setRandomWord();
        initializeCurrentGuess();
        mistakes.clear();
        stage = 0;
    }

    void askRestart() {
        System.out.println("Would you like to play again? (y or n)");
        Character input = Character.toLowerCase(sc.next().charAt(0));
        if (input == 'y') {
            replay();
        } else if (input == 'n') {
            gameIsDone = true;
            if (isWin()) {
//               TODO saveScore();
            }
//            TODO printLeaderboardRank()
        } else {
            System.out.println("Invalid option");
            askRestart();
        }
    }

    void addScore() {
        score += 5 - stage;
    }

    void resetScore() {
        score = 0;
    }

    boolean isWin() {
        return !currentGuess.contains('_');
    }

    boolean isLoss() {
        return stage == 5;
    }

    boolean isEnd() {
        if (isWin()) {
            System.out.println("Congrats! you got it!");
            addScore();
            return true;
        } else if (isLoss()) {
            System.out.println("Oh no hangman is gone :(");
//            TODO saveScore();
            resetScore();
            return true;
        } else {
//            still going
            return false;
        }
    }

    void makeGuess() {
        Character input = Character.toLowerCase(sc.next().charAt(0));
        if (answerWord.contains(String.valueOf(input))) {
//            correct guess
            IntStream.range(0, answerWord.length()).mapToObj(index -> {
                if (answerWord.charAt(index) == input) {
                    currentGuess.set(index, input);
                }
                ;
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
        } catch (IOException err) {
            System.out.println("Error: Stage file not found");
        }
    }

    String generateRandomWord() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://random-word-api.herokuapp.com/word?number=1"))
                    .build();
            String json = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            ObjectMapper obj = new ObjectMapper();
            List<String> words = obj.readValue(json, List.class);
            return words.get(0);
        } catch (Exception err) {
            return randomLocalWord();
        }
    }

    String randomLocalWord() {
        int randInt = new Random().nextInt(words.length);
        return words[randInt];
    }
}
