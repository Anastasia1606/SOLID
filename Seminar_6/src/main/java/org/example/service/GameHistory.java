package org.example.service;

import java.util.ArrayList;

public interface GameHistory {

    ArrayList<String> getHistory();

    void addHistory(String mes);
}
