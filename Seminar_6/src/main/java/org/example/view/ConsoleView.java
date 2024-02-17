package org.example.view;

import java.util.Scanner;

public class ConsoleView implements View  {

    Scanner in;
    public ConsoleView() {
        in = new Scanner(System.in);
    }

    @Override
    public String get() {
        return in.next();
    }

    @Override
    public void set(String value) {
        System.out.println(value);

    }
}
