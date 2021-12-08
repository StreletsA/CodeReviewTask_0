package com.andycodereview.core;

import java.util.*;

public class CashMachine {

    /*

        cashAmount - Сумма денег, запрашиваемая пользователем
        banknotes - Номиналы купюр, вводимые пользователем
        money <номинал купюры, количество таких купюр> - Купюры на выдачу (пустой, если выдача невозможна)

     */


    private final Scanner scanner;
    private final CashMachineUtils utils;

    private boolean exitFromCashMachine;

    private int cashAmount;
    private List<Integer> banknotes;
    private Map<Integer, Integer> money;

    public CashMachine(){

        scanner = new Scanner(System.in);
        utils = new CashMachineUtils();

        exitFromCashMachine = false;

        clear();

    }

    public void start(){

        exitFromCashMachine = false;

        inputInfo();
        processData();
        giveMoney();

    }

    // Получение входных данных
    private void inputInfo(){

        inputCashAmount();
        inputBanknotes();

    }

    private void inputCashAmount(){

        clearCashAmount();

        System.out.print("Введите сумму: ");

        try{

            cashAmount = Integer.parseInt(scanner.next());

            if (cashAmount < 0){
                System.out.println("Сумма не должна быть отрицательной. Попробуйте еще раз");
                inputCashAmount();
            }

        }catch (NumberFormatException e){
            System.out.println("Ошибочный ввод. Попробуйте еще раз");
            inputCashAmount();
        }

        if(isCompleteInfo())
            showUserActions();
    }

    private void inputBanknotes(){

        clearBanknotes();

        String str_banknotes;

        System.out.print("Введите доступые номиналы через запятую: ");
        str_banknotes = scanner.next();

        banknotes = utils.getBanknotesFromString(str_banknotes);

        if(banknotes.isEmpty()) {
            System.out.println("Ошибочный ввод. Попробуйте еще раз");
            inputBanknotes();
        }

        if(isCompleteInfo())
            showUserActions();

    }

    private void showUserActions(){

        System.out.println("*********************************");

        System.out.print("Запрашиваемая сумма: ");
        System.out.println(cashAmount);

        System.out.print("Доступные номиналы: ");
        System.out.println(utils.getStringFromBanknoteList(banknotes));

        System.out.println("Возможные действия (введите цифру для выбора):");
        System.out.println("1) Всё верно. Продолжить");
        System.out.println("2) Изменить запрашиваемую сумму");
        System.out.println("3) Изменить доступные номиналы");
        System.out.println("4) Ввести данные заново");
        System.out.println("5) Закончить работу. Выйти");

        chooseAction();

        System.out.println("*********************************");

    }

    private void chooseAction(){

        int choice;

        System.out.print("-> ");

        try{

            choice = Integer.parseInt(scanner.next());

            switch (choice) {
                case 1 -> processData();
                case 2 -> inputCashAmount();
                case 3 -> inputBanknotes();
                case 4 -> inputInfo();
                case 5 -> exit();
                default -> {
                    System.out.println("Ошибочный ввод. Попробуйте еще раз");
                    chooseAction();
                }
            }

        }catch (NumberFormatException e){

            System.out.println("Ошибочный ввод. Попробуйте еще раз");
            chooseAction();

        }

    }

    private void exit(){
        clear();
        exitFromCashMachine = true;
    }

    private boolean isCompleteInfo(){

        if(cashAmount == 0 || banknotes.isEmpty())
            return false;

        return true;

    }

    // Обработка данных. Получение купюр на выдачу
    private void processData(){

        money = utils.calculate(cashAmount, banknotes);

    }

    // Выдача купюр
    private void giveMoney(){

        if(exitFromCashMachine)
            return;

        if(money.isEmpty()){
            System.out.println("К сожалению, невозможно выдать данную сумму");
            return;
        }

        System.out.println("На выдачу:");

        for(Integer banknote : money.keySet()){

            int banknoteCount = money.get(banknote);

            System.out.print(banknoteCount);
            System.out.print(" " + utils.getTrueDeclension(banknoteCount) + " номиналом ");
            System.out.println(banknote);
        }

        clear();
    }

    // Очистка пользовательских данных
    private void clear(){

        clearCashAmount();
        clearBanknotes();

        money = new HashMap<>();

    }

    private void clearCashAmount(){
        cashAmount = 0;
    }

    private void clearBanknotes(){
        banknotes = new ArrayList<>();
    }
}
