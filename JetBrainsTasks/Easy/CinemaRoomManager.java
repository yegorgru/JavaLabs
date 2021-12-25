package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int a = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int b = scanner.nextInt();
        char[][] array = new char[a][b];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = 'S';
            }
        }
        int purchased = 0;
        int income = 0;
        int code = -1;
        int totalIncome = 0;
        if(a*b < 60){
            totalIncome = a*b*10;
        }
        else {
            int half = a/2;
            totalIncome = half * b * 10 + (a-half) * b * 8;
        }
        while(code != 0){
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            code = scanner.nextInt();
            if(code == 1){
                System.out.print("Cinema:\n  ");
                for(int i = 0; i < b; i++){
                    System.out.print(i + 1 + " ");
                }
                System.out.print("\n");
                for(int i = 0 ;i < a; i++){
                    System.out.print(i+1+" ");
                    for(int j = 0 ;j < b; j++){
                        System.out.print(array[i][j] + " ");
                    }
                    System.out.print("\n");
                }
            }
            else if(code == 2){
                boolean success = false;
                while(!success){
                    System.out.println("Enter a row number:");
                    int c = scanner.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int d = scanner.nextInt();
                    if(c<1 || c > a || d < 1 || d > b){
                        System.out.println("Wrong input!");
                        continue;
                    }
                    else if(array[c-1][d-1] == 'B'){
                        System.out.println("That ticket has already been purchased!");
                        continue;
                    }
                    else{
                        if(a*b > 60 && c <= a/2 || a*b <= 60){
                            System.out.println("\nTicket price: $10");
                            income+=10;
                        }
                        else{
                            System.out.println("\nTicket price: $8");
                            income+=8;
                        }
                        success = true;
                        purchased++;
                        array[c-1][d-1] = 'B';
                    }
                }
            }
            else if(code == 3){
                System.out.println("Number of purchased tickets: "+purchased);
                float percentage = 100 * (float)purchased / (a*b);
                System.out.printf("Percentage: %.2f%%%n",percentage);
                System.out.println("Current income: $"+income);
                System.out.println("Total income: $"+ totalIncome);
            }
        }
    }
}