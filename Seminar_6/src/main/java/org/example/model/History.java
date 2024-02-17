package org.example.model;

import org.example.service.Game;
import org.example.service.GameHistory;

import java.util.ArrayList;
import java.util.Date;

public class History implements GameHistory {

    private ArrayList<String> history;

    public History(){
        history = new ArrayList<>();
    }

    @Override
    public void addHistory(String mes) {

        history.add((new Date()).toString() + ": " + mes);
    }

    @Override
    public ArrayList<String> getHistory() {
        return history;
    }

}
