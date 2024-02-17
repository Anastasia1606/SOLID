package org.example.controller;

import org.example.enums.GameStatus;
import org.example.model.Answer;
import org.example.model.variationGame.GameCyrillic;
import org.example.model.variationGame.GameLatin;
import org.example.model.variationGame.GameNumber;
import org.example.enums.TypeDictionary;
import org.example.service.AbstractGame;
import org.example.view.ConsoleViewWithInitialization;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    ConsoleViewWithInitialization view;
    String typeByString;
    Integer wordSize;
    Integer tryCount;
    public Controller() {
        view = new ConsoleViewWithInitialization();
    }

    private void initializationParam(List<String> types) {
        typeByString = view.chooseDictionary(types);
        if (typeByString == null) {
            throw new RuntimeException("Не указан словарь");
        }

        wordSize = view.inputWordSize();
        if (wordSize == null) {
            throw new RuntimeException("Ошибка при вводе длины слова");
        }

        tryCount = view.inputTryCount();
        if (tryCount == null) {
            throw new RuntimeException("Ошибка при вводе количества попыток");
        }
    }

    public void start(){

        List<String> types = new ArrayList<>();
        for(TypeDictionary typeDictionary: TypeDictionary.values()) {
            types.add(typeDictionary.toString());
        }

        initializationParam(types);
        AbstractGame ag = switch (TypeDictionary.valueOf(typeByString)) {
            case NUM -> new GameNumber();
            case RUS -> new GameCyrillic();
            case ENG -> new GameLatin();
        };

        ag.startGame(wordSize,tryCount);
        view.set("Ваш ответ: ");

        String interValue = null;
        while (ag.getGameStatus().equals(GameStatus.START)){
            interValue = view.get();
            if (interValue.length() == wordSize) {
                Answer answer = ag.inputAnswer(interValue);
                view.set(answer.toString());
            } else {
                view.set(String.format("Введите ответ (%d символов): ", wordSize));
            }
        }

        if (ag.getGameStatus().equals(GameStatus.WIN)){
            view.set("Вы победили");
        } else if (ag.getGameStatus().equals(GameStatus.LOOSE)){
            view.set("Вы проиграли. Загаданное слова: " + ag.getWord());
        }
        else {
            throw new RuntimeException("Неопознанный статус");
        }
        view.set("Вывести историю игры (y/n): ");
        if (view.get().toLowerCase().trim().charAt(0) == 'y') {
            List<String> history = ag.getHistory();
            for (String line : history) {
                view.set(line);
            }
        }
    }
}
