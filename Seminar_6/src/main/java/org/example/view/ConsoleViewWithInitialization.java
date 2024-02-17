package org.example.view;

import java.util.List;

public class ConsoleViewWithInitialization extends ConsoleView implements View{
    public ConsoleViewWithInitialization() {
        super();
    }

    public String chooseDictionary(List<String> typesDictionary) {

        set("Выбери словарь, возможные значения: "+typesDictionary);
        String interType = get().toUpperCase();
        if (!typesDictionary.contains(interType)) {
            set("Словарь не найден");
            return null;
        }
        return interType;
    }

    public Integer inputWordSize() {

        set("Введите размер слова: ");
        String interValue = get();
        try {
            return Integer.parseInt(interValue);
        } catch (NumberFormatException e) {
            set("Введено не число.");
            return null;
        }
    }

    public Integer inputTryCount() {

        set("Введите количество попыток: ");
        String interValue = get();
        try {
            return Integer.parseInt(interValue);
        } catch (NumberFormatException e) {
            set("Введено не число.");
            return null;
        }
    }
}
