package org.example.service;

import org.example.model.Answer;
import org.example.model.History;
import org.example.service.Game;
import org.example.service.GameHistory;
import org.example.enums.GameStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public abstract class AbstractGame implements Game {
    protected abstract List<String> generateCharList();
    private String word;
    private History history;
    private Integer tryCount;
    private GameStatus gameStatus;

    public AbstractGame() {
        gameStatus = GameStatus.INIT;
        history = new History();
    }
    @Override
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public ArrayList<String> getHistory() {
        return history.getHistory();
    }

    public String getWord() {
        if (!getGameStatus().equals(GameStatus.START)) {
            return word;
        }
        return null;
    }

    @Override
    public void startGame(Integer wordSize, Integer tryCount) {
        word = generateWord(wordSize);
        this.tryCount = tryCount;
        gameStatus = GameStatus.START;
        history.addHistory("Старт игры, загадано слово: "+word+", количество попыток: "+tryCount);
    }

    private String generateWord(Integer wordSize) {
        List<String> alphabet = generateCharList();
        Random rnd = new Random();
        String result = "";
        for (int i = 0; i < wordSize; i++) {
            int randomIndex = rnd.nextInt(alphabet.size());
            result += alphabet.get(randomIndex);
            alphabet.remove(randomIndex);
        }
        return result;
    }

    @Override
    public Answer inputAnswer(String value) {
        if (!getGameStatus().equals(GameStatus.START)) {
            throw new RuntimeException("Игра не запущена");
        }

        int cowCounter = 0;
        int bullCounter = 0;
        for (int i = 0; i < word.length(); i++) {
            if (value.charAt(i) == word.charAt(i)) {
                cowCounter++;
                bullCounter++;
            } else if (word.contains(String.valueOf(value.charAt(i)))) {
                cowCounter++;
            }
        }
        tryCount--;
        if (tryCount == 0){
            gameStatus = GameStatus.LOOSE;
        }
        if (bullCounter == word.length()){
            gameStatus = GameStatus.WIN;
        }
        history.addHistory(String.format("Введено: %s, совпадений по позиции: %d (быков), " +
                "совпадений по наличию: %d (коров)," +
                " осталось попыток: %d, статус игры: %s",
                value,
                bullCounter,
                cowCounter,
                tryCount,
                gameStatus));
        return new Answer(cowCounter, bullCounter, tryCount);
    }


}