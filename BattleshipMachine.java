/**
 * Battleship with only 1 ship in a 5 x 5 grid.
 *
 * @author (JJH)
 * @version (1.0)
 */
import java.util.Scanner;

public class BattleshipMachine
{
    public static Scanner userScan = new Scanner(System.in);
    public static boolean repeat = true;
    public static int i;
    public static int row;
    public static int column;
    public static int direction;
    public static int start;
    
    //Size bounds:
    public static int s = 0;
    public static int f = 5;
    
    public static String[][] board = new String [f][f];
    public static String[][] hiddenBoard = new String [f][f];
    public static String[][] key = new String [f][f];
    static void clear() {
        System.out.print('\u000C');
    }

    static void rules() {
        String rules = "yes";
        while (rules.equals("yes")) {
            System.out.println("Battleship 1.0");
            System.out.println("By JJ Hartog");
            System.out.println("-----------------------------------------------");            System.out.println("Rules:");
            System.out.println("The 5-length enemy Battleship is within a 5 x 5 grid");
            System.out.println("The Battleship is either horizontal or verticle");
            System.out.println("-----------------------------------------------");
            System.out.println("Key:");
            System.out.println("[     ] = Unknown");
            System.out.println("[ Hit ] = Hit the Battleship");
            System.out.println("[Empty] = Missed the Battleship");
            System.out.println("-----------------------------------------------");
            System.out.println("WARNING: Fire Selection is very sensitive to incorrect inputs");
            System.out.println("To continue input (start)");
            rules = userScan.next();
        }
    }
    
    static String[][] createBoard() {
        for (int r = s; r < f; r++) {
            for (int c = s; c < f; c++) {
                board[r][c] = "     ";
            }
        }
        
        return board;
    }
    
    static String[][] createHidden() {
        for (int r = s; r < f; r++) {
            for (int c = s; c < f; c++) {
                hiddenBoard[r][c] = " ";
            }
        }
        
        return hiddenBoard;
    }
    
    //Work in progress
    static String[][] createKey() {
        int n = 1;
        int g = 1;
        for (int r = s; r < f; r++) {
            for (int c = s; c < f; c++) {
                key[r][c] = "(" + n + "," + g + ")";
                n++;
            }
            g++;
        }
        
        return key;
    }
    
    static void printBoard() {
        for (int r = s; r < f; r++) {
            for (int c = s; c < f; c++) {
                System.out.print("[" + board[r][c] + "]");
            }
            System.out.println();
        }
    }
    
    static void printHidden() {
        for (int r = s; r < f; r++) {
            for (int c = s; c < f; c++) {
                System.out.print("[" + hiddenBoard[r][c] + "]");
            }
            System.out.println();
        }
    }
    
    //Work in progress
    static void printKey() {
        for (int r = s; r < f; r++) {
            for (int c = s; c < f; c++) {
                System.out.print("[" + key[r][c] + "]");
            }
            System.out.println();
        }
    }
    
    static void spawnShip() {
        createHidden();
        int min = s;
        int max = f - 1;
        int length = f - 5;
        //Row & Column
        row = (int)(Math.random() * (max - min + 1) + min);
        column = (int)(Math.random() * (max - min + 1) + min);
        //Verticle or Horizonal
        direction = (int)(Math.random() * (1 - 0 + 1) + 0);
        //Start position
        start = (int)(Math.random() * (length - s + 1) + s);
        //Inputs position
        if (direction == 0) {
            for (int r = start; r < 5; r++) {
                hiddenBoard[r][column] = "X";
            }
        }
        else if (direction == 1) {
            for (int c = start; c < 5; c++) {
                hiddenBoard[row][c] = "X";
            }
        }
    }

    static void firer() {
        System.out.println("-----------Fire Control------------");
        System.out.print("x-coordinate fire position: ");
        int y = userScan.nextInt();
        System.out.print("y-coordinate fire position: ");
        int x = userScan.nextInt();
        clear();
        if (hiddenBoard[x-1][y-1].equals("X")) {
            System.out.println("Last Shot: Hit!");
            hiddenBoard[x-1][y-1] = "F";
            board[x-1][y-1] = " Hit ";
        }
        else if (hiddenBoard[x-1][y-1].equals(" ")) {
            System.out.println("Last Shot: Miss!");
            board[x-1][y-1] = "Empty";
        }
        else {
            System.out.println("Error");
            System.out.println("Please Restart the Program");
            int temp = userScan.nextInt();
        }
        i++;
        printBoard();
        //printKey();
    }
    
    static void fireControl() {
        printBoard();
        //printKey();
        do {
            firer();
        }
        //essentially any X exists
        while 
        (hiddenBoard[0][column].equals("X") || hiddenBoard[1][column].equals("X")
        || hiddenBoard[2][column].equals("X") || hiddenBoard[3][column].equals("X") 
        || hiddenBoard[4][column].equals("X") || hiddenBoard[row][0].equals("X") 
        || hiddenBoard[row][1].equals("X") || hiddenBoard[row][2].equals("X") 
        || hiddenBoard[row][3].equals("X") || hiddenBoard[row][4].equals("X"));
        clear();
        System.out.println("You have sunken the enemy Battleship in " + i + " turns");
        System.out.println("GAME OVER");
    }
    
    public static void main(String[] args) {
        do {  
            i = 0;
            rules();
            createBoard();
            spawnShip();
            clear();
            fireControl();
            //repeater
            System.out.print("Would you like to play again? (true/false): ");
            repeat = userScan.nextBoolean();
        } while (repeat);
    }
}