package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class DinnerConstructor {
    private final HashMap<String, ArrayList<String>> menu = new HashMap<>();

    public void addDishToCategory(String dishType, String dishName) {
        menu.putIfAbsent(dishType.trim(), new ArrayList<>());
        if (!menu.get(dishType.trim()).isEmpty() && menu.get(dishType.trim()).contains(dishName.trim())) {
            System.out.println("Блюдо уже есть в списке, повторно добавлено не будет ");
        } else {
            menu.get(dishType.trim()).add(dishName.trim());
        }
    }

    public boolean isTypeExist(String dishType) {
        return menu.containsKey(dishType.trim());
    }

    public boolean isMenuFilled(){
        return !menu.isEmpty();
    }

    public HashMap<Integer, ArrayList<String>> generateCombo(int comboCount, ArrayList<String> dishTypes) {
        HashMap<Integer, ArrayList<String>> comboMap = new HashMap<>();
        Random random = new Random();
        for (int i = 0; i < comboCount; i++) {
            for (String dishType : dishTypes) {
                int itemIndex = random.nextInt(menu.get(dishType.trim()).size());
                comboMap.putIfAbsent(i, new ArrayList<>());
                comboMap.get(i).add(menu.get(dishType.trim()).get(itemIndex));
            }
        }
        return comboMap;
    }

    public Set<String> getAllDishTypes() {
        return menu.keySet();
    }
}
