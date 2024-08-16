package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dinnerConstructor = new DinnerConstructor();
    static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();
        dinnerConstructor.addDishToCategory(dishType, dishName);
        // добавьте новое блюдо
    }

    private static void generateDishCombo() {
        ArrayList<String> dishesTypesList = new ArrayList<>();
        System.out.println("Начинаем конструировать обед...");
        if (!dinnerConstructor.isMenuFilled()) {
            System.out.println("Ни один тип в меню ещё не добавлен, перед составлением комбинаций заполните меню");
            return;
        }

        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem = scanner.nextLine();

        //реализуйте ввод типов блюд
        while (!nextItem.isEmpty()) {
            if (dinnerConstructor.isTypeExist(nextItem)) {
                dishesTypesList.add(nextItem);
            } else {
                System.out.println("Тип " + nextItem + " не существует, не будет учтён при составлении комбинации. Введите один из существующих типов: {" + String.join(",", dinnerConstructor.getAllDishTypes()) + "}");

            }
            nextItem = scanner.nextLine();
        }

        HashMap<Integer, ArrayList<String>> combos = dinnerConstructor.generateCombo(numberOfCombos, dishesTypesList);// сгенерируйте комбинации блюд и выведите на экран
        for (int i = 0; i < numberOfCombos; i++) {
            System.out.print("Комбо " + (i + 1) + ":");
            System.out.println("[" + String.join(", ", combos.get(i)) + "]");
        }
    }
}
