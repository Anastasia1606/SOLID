package org.example.service;

import org.example.enums.GameStatus;
import org.example.model.Answer;

public interface Game {
    void startGame(Integer wordSize, Integer tryCount);
    Answer inputAnswer(String value);
    GameStatus getGameStatus();
}
