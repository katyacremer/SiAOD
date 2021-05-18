import java.io. *;
import java.util.*;
import java.lang. *;

public class Puzzle{
    public static void main(String[] args){
        //решаемая позиция
        int[][] blocks = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        //нерешаемая позиция
        //int[][] blocks = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 15, 14, 0}};
        if(canSolve(blocks)){
            System.out.println("Can be solved");
            Pieces initial = new Pieces(blocks);
            More solver = new More(initial);

            System.out.println("Minimum number of moves = " + solver.moves());
            for (Pieces board : solver.solution())
                System.out.println(board);
        }
        else{
            System.out.println("Can't be solved");
        }
    }
    static boolean canSolve(int[][] array){
        int status, sum = 0;

        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                status = 0;
                if(array[i][j] == 0){
                    sum += i * array.length + j + 1;
                }
                else{

                    sum += help(array, i, j);
                }
            }
        }
        if(sum % 2 != 0)
            return false;
        return true;
    }
    static int help(int[][] array, int a, int b){
        int status = 0;
        int b1 = b;
        for(int i = a; i < array.length; i++){
            for(int j = b; j < array[i].length; j++){
                if(array[a][b1] > array[i][j] && array[i][j] != 0){
                    ++status;
                }
            }
            b = 0;
        }
        return status;
    }
}