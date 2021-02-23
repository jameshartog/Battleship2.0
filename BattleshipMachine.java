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
    public static int f = 9;
    
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
        
        //manual edge inclusion
        board[0][0] = " Key ";
        //top
        board[0][1] = "  1  ";
        board[0][2] = "  2  ";
        board[0][3] = "  3  ";
        board[0][4] = "  4  ";
        board[0][5] = "  5  ";
        board[0][6] = "  6  ";
        board[0][7] = "  7  ";
        board[0][8] = "  8  ";
        //side
        board[1][0] = "  1  ";
        board[2][0] = "  2  ";
        board[3][0] = "  3  ";
        board[4][0] = "  4  ";
        board[5][0] = "  5  ";
        board[6][0] = "  6  ";
        board[7][0] = "  7  ";
        board[8][0] = "  8  ";
        
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
        start = (int)(Math.random() * (length - (s + 1) + 1) + s);
        //Inputs position
        if (direction == 0) {
            for (int r = start; r < (start + 5); r++) {
                hiddenBoard[r][column] = "X";
            }
        }
        else if (direction == 1) {
            for (int c = start; c < (start + 5); c++) {
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
        if (hiddenBoard[x][y].equals("X")) {
            System.out.println("Last Shot: Hit!");
            hiddenBoard[x][y] = "F";
            board[x][y] = " Hit ";
        }
        else if (hiddenBoard[x][y].equals(" ")) {
            System.out.println("Last Shot: Miss!");
            board[x][y] = "Empty";
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
        || hiddenBoard[4][column].equals("X") || hiddenBoard[5][column].equals("X") 
        || hiddenBoard[6][column].equals("X") || hiddenBoard[7][column].equals("X") 
        || hiddenBoard[8][column].equals("X") || hiddenBoard[row][0].equals("X") 
        || hiddenBoard[row][1].equals("X") || hiddenBoard[row][2].equals("X") 
        || hiddenBoard[row][3].equals("X") || hiddenBoard[row][4].equals("X")
        || hiddenBoard[row][5].equals("X") || hiddenBoard[row][6].equals("X")
        || hiddenBoard[row][7].equals("X") || hiddenBoard[row][8].equals("X"));
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