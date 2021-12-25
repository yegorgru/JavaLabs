package tictactoe;

import java.util.Scanner;

public class Main {
    public static void Print(char[][] array){
        System.out.println("---------");
        for(int i=0;i<3;i++){
            System.out.print("| ");
            for(int j=0;j<3;j++){
                System.out.print(array[i][j]+" ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] array = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        boolean x = false;
        boolean o = false;
        char cur = 'X';
        while(!x && !o){
            Print(array);
            for(int i = 0; i < 3; i++){
                if(array[i][0] == array[i][1] && array[i][0] == array[i][2]){
                    if(array[i][0] == 'X'){
                        x = true;
                    }
                    else if(array[i][0] == 'O'){
                        o = true;
                    }
                }
            }
            for(int i = 0; i < 3; i++){
                if(array[0][i] == array[1][i] && array[0][i] == array[2][i]){
                    if(array[0][i] == 'X'){
                        x = true;
                    }
                    else if(array[0][i] == 'O'){
                        o = true;
                    }
                }
            }
            if(array[0][0] == 'X' && array[1][1] == 'X' && array[2][2] == 'X'){
                x = true;
            }
            if(array[0][0] == 'O' && array[1][1] == 'O' && array[2][2] == 'O'){
                o = true;
            }
            if(array[2][0] == 'X' && array[1][1] == 'X' && array[0][2] == 'X'){
                x = true;
            }
            if(array[2][0] == 'O' && array[1][1] == 'O' && array[0][2] == 'O'){
                o = true;
            }
            if(x || o){
                continue;
            }
            boolean isSpace = false;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(array[i][j] == ' '){
                        isSpace = true;
                    }
                }
            }
            if(!isSpace){
                System.out.println("Draw");
                return;
            }
            boolean success = false;
            boolean notInt = false;
            while(!success){
                notInt = false;
                int xx = 0;
                int y = 0;
                System.out.println("Enter the coordinates: ");
                if(!scanner.hasNextInt()){
                    notInt = true;
                }
                else{
                    xx = scanner.nextInt();
                }
                if(!scanner.hasNextInt()){
                    notInt = true;
                }
                else{
                    y = scanner.nextInt();
                }
                if(notInt){
                    System.out.println("You should enter numbers!");
                }
                if(xx < 1 || xx > 3 || y < 1 || y > 3){
                    System.out.println("Coordinates should be from 1 to 3!");
                }
                else if(array[xx-1][y-1] != ' '){
                    System.out.println("This cell is occupied! Choose another one!");
                }
                else{
                    array[xx-1][y-1] = cur;
                    cur = cur == 'X' ? 'O' : 'X';
                    success = true;
                }
            }
        }
        if(x){
            System.out.println("X wins");
        }
        else{
            System.out.println("O wins");
        }
    }
}
