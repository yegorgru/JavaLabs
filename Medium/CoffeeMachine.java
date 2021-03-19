package machine;

import java.util.Scanner;

class Main {
   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean active = true;
        CoffeeMachine machine = new CoffeeMachine();
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        while(active){
            String str = scanner.nextLine();
            active = machine.Run(str);
        }
    }
}

class CoffeeMachine{
      
    enum State {
        MENU, BUY, FILL, WATER, MILK, BEANS, CUPS
    }
    
    public CoffeeMachine() {
        state = State.MENU;
        water = 400;
        milk = 540;
        beans = 120;
        cups = 9;
        money = 550;
    }
    
    public static void print(int water, int milk, int beans, int cups, int money){
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");
    }
    
    public boolean Run(String str){
        switch (state){
            case MENU:{
                if(str.equals("buy")){
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                    state = State.BUY;
                }
                else if(str.equals("fill")){
                    System.out.println("Write how many ml of water do you want to add:");
                    state = State.WATER;
                }
                else if(str.equals("take")){
                    System.out.println("I gave you $" + money);
                    money = 0;
                    state = State.MENU;
                }
                else if(str.equals("remaining")){
                    print(water, milk, beans,cups, money);
                    state = State.MENU;
                }
                else if(str.equals("exit")){
                    return false;
                }
                break;
            }
            case BUY:{
                try {
                    int what = Integer.parseInt(str);
                    if(what == 1){
                        if(water < 250){
                            System.out.println("Sorry, not enough water!");
                        }
                        else if(beans < 16){
                            System.out.println("Sorry, not enough coffee beans!");
                        }
                        else if(cups == 0){
                            System.out.println("Sorry, not enough disposable cups!");
                        }
                        else{
                            water -= 250;
                            beans -= 16;
                            money += 4;
                            cups--;   
                        }
                    }
                    else if(what == 2){
                        if(water < 350){
                            System.out.println("Sorry, not enough water!");
                        }
                        else if(beans < 20){
                            System.out.println("Sorry, not enough coffee beans!");
                        }
                        else if(milk < 75){
                            System.out.println("Sorry, not enough milk!");
                        }
                        else if(cups == 0){
                            System.out.println("Sorry, not enough disposable cups!");
                        }
                        else{
                            water -= 350;
                            milk -= 75;
                            beans -= 20;
                            money += 7;
                            cups--;
                        }
                    }
                    else if(what == 3){
                        if(water < 200){
                            System.out.println("Sorry, not enough water!");
                        }
                        else if(beans < 12){
                            System.out.println("Sorry, not enough coffee beans!");
                        }
                        else if(milk < 100){
                            System.out.println("Sorry, not enough milk!");
                        }
                        else if(cups == 0){
                            System.out.println("Sorry, not enough disposable cups!");
                        }
                        else{
                            water -= 200;
                            milk -= 100;
                            beans -= 12;
                            money += 6;
                            cups--;
                        }
                    }
                } catch (NumberFormatException e) {
                    if(str.equals("back")){
                        state = State.MENU;
                    }
                }
                state = State.MENU;
                break;
            }
            case WATER : {
                int a = Integer.parseInt(str);
                water += a;
                System.out.println("Write how many ml of milk do you want to add:");
                state = State.MILK;
                break;
            }
            case MILK : {
                int a = Integer.parseInt(str);
                milk += a;
                System.out.println("Write how many grams of coffee beans do you want to add:");
                state = State.BEANS;
                break;
            }
            case BEANS : {
                int a = Integer.parseInt(str);
                beans += a;
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                state = State.CUPS;
                break;
            }
            case CUPS : {
                int a = Integer.parseInt(str);
                cups += a;
                state = State.MENU;
                break;
            }
        }
        if(state == State.MENU){
            System.out.println("Write action (buy, fill, take, remaining, exit):");
        }
        return true;
    }
    
    
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;
    
    private State state;
}
