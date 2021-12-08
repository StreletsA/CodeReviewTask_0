package com.andycodereview.core;

import java.util.*;

public class CashMachineUtils {

    // Возможные номиналы купюр
    private final int[] permittedBanknotes2 = {
            1,
            2,
            5,
            10,
            20,
            50,
            100,
            200,
            500,
            1000
    };
    
    // Расчёт для выдачи необходимой суммы
    // Возвращает: money <номинал купюры, количество таких купюр> - Купюры на выдачу (пустой, если выдача невозможна)
    // cashAmount - Сумма денег, запрашиваемая пользователем
    // banknotes - Номиналы купюр, вводимые пользователем
    public Map<Integer, Integer> calculate(int cashAmount, List<Integer> banknotes){

        Map<Integer, Integer> money = new HashMap<>();

        Collections.sort(banknotes);
        Collections.reverse(banknotes);

        for (int banknote: banknotes) {

            int div = cashAmount / banknote;

            if(div > 0)
                money.put(banknote, div);

            cashAmount -= (banknote * div);
        }

        if(cashAmount != 0)
            money = new HashMap<>();

        return money;

    }

    // Получение номиналов купюр, вводимых пользователем через запятую
    public List<Integer> getBanknotesFromString(String userInput){

        List<Integer> banknotes = new ArrayList<>();

        String[] arr_banknotes = userInput.split(",");

        for (String str_banknote : arr_banknotes){
            try {
                int banknote = Integer.parseInt(str_banknote);

                if (isPermittedBanknote(banknote))
                    banknotes.add(banknote);

            } catch (NumberFormatException e){
                banknotes = new ArrayList<>();
                break;
            }

        }

        return banknotes;

    }

    // Проверка - является ли номинал возможным
    private boolean isPermittedBanknote(int banknote){

        for(int permittedBanknote : permittedBanknotes2)
            if((banknote ^ permittedBanknote) == 0)
                return true;

        return false;
    }

    // Определение правильного склонения слова "купюра"
    public String getTrueDeclension(int count){

        String declension = "купюр";
        int remains = count % 10;

        if(remains == 1 && count != 11)
            declension = "купюра";

        else if(remains >= 2 && remains <= 4)
            declension = "купюры";

        return declension;

    }

    public String getStringFromBanknoteList(List<Integer> banknotes){

        StringBuilder answer = new StringBuilder();

        for (Integer banknote : banknotes)
            answer.append(banknote.toString()).append(", ");

        answer.deleteCharAt(answer.length() - 1);
        answer.deleteCharAt(answer.length() - 1);

        return new String(answer);

    }

}
