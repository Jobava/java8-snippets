//Two players (numbered and ) are playing a game with stones. Player always plays first, and the two players move in alternating turns. The game's rules are as follows:
//
//    In a single move, a player can remove either , , or stones from the game board.
//    If a player is unable to make a move, that player loses the game.
//
//Given the number of stones, find and print the name of the winner (i.e., or ) on a new line. Each player plays optimally, meaning they will not make a move that causes them to lose the game if some better, winning move exists.
//
//Input Format
//
//The first line contains an integer, , denoting the number of test cases.
//Each of the subsequent lines contains a single integer, , denoting the number of stones in a test case.
//
//Constraints
//
//Output Format
//
//On a new line for each test case, print if the first player is the winner; otherwise, print .
//
//Sample Input
//
//8
//1
//2
//3
//4
//5
//6
//7
//10
//
//Sample Output
//
//Second
//First
//First
//First
//First
//First
//Second
//First



import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static int max3 (int a, int b, int c) {
        if (a>=b) {
            if (a >= c) {
                return a;
            } else {
                return c;
            }
        } else {
            if (b >= c) {
                return b;
            } else {
                return c;
            }
        }
    }
    
    public static int min3 (int a, int b, int c) {
        if (a<=b) {
            if (a <= c) {
                return a;
            } else {
                return c;
            }
        } else {
            if (b <= c) {
                return b;
            } else {
                return c;
            }
        }
    }
    
    public static Map<Integer, Integer> memo = new HashMap<>();
    //static initialization block
    static {
        memo.put(0, -1);
        memo.put(1, -1);
        memo.put(2, 1);
        memo.put(3, 1);
        memo.put(4, 1);
        memo.put(5, 1);
    }
    
    public static int calculate (int n, int player) {
        if (memo.containsKey(n)) {
            return memo.get(n) * player;
        }
        
        int take2 = calculate(n-2, player);
        int take3 = calculate(n-3, player);
        int take5 = calculate(n-5, player);
        
        if (-1 == player) {
            int max = max3(take2, take3, take5);
            if (max == take2) {
            } else if (max == take3) {
                return calculate(n - 3, player * (-1));
            } else {
                return calculate(n - 5, player * (-1));
            }
        } else {
            int min = min3(take2, take3, take5);
            if (min == take2) {
                return calculate(n - 2, player * (-1));
            } else if (min == take3) {
                return calculate(n - 3, player * (-1));
            } else {
                return calculate(n - 5, player * (-1));
            }
        }
        return 1;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine(); //ignore the first line
        
        //first build the memo for quick lookup
        for (int i=6; i<100; ++i) {
            int winner = calculate(i, 1);
            memo.put(i, winner);
        }
        
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int result = calculate(n, 1);
            if (result == 1) {
                System.out.println("First");
            } else {
                System.out.println("Second");
            }
        }
        sc.close();
    }
}
