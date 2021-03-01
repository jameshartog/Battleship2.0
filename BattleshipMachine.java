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
    
    //Ship hit counters
    public static int carrierCount;
    public static int battleCount;
    public static int subCount;
    public static int destroyerCount;
    public static int patrolCount;
    
    //Ship health checkers
    public static String carrierStatus;
    public static String battleStatus;
    public static String subStatus;
    public static String destroyerStatus;
    public static String patrolStatus;
    
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
    
    static void printList() {
        if (carrierCount == 5) {
            carrierStatus = "Dead";
            System.out.println("Carriership:");
            System.out.println("[X][X][X][X][X]");
        }
        else {
            System.out.println("Carriership:");
            System.out.println("[ ][ ][ ][ ][ ]");
        }
        if (battleCount == 4) {
            battleStatus = "Dead";
            System.out.println("Battleship:");
            System.out.println("[X][X][X][X]");
        }
        else {
            System.out.println("Battleship:");
            System.out.println("[ ][ ][ ][ ]");
        }
        if (subCount == 3) {
            subStatus = "Dead";
            System.out.println("Submarine:");
            System.out.println("[X][X][X]");
        }
        else {
            System.out.println("Submarine:");
            System.out.println("[ ][ ][ ]");
        }
        if (destroyerCount == 3) {
            destroyerStatus = "Dead";
            System.out.println("Destroyer:");
            System.out.println("[X][X][X]");
        }
        else {
            System.out.println("Destroyer:");
            System.out.println("[ ][ ][ ]");
        }
        if (patrolCount == 2) {
            patrolStatus = "Dead";
            System.out.println("Patrol Boat:");
            System.out.println("[X][X]");
        }
        else {
            System.out.println("Patrol Boat:");
            System.out.println("[ ][ ]");
        }
    }
    
    static void spawnShips() {
        createHidden();
        
        //Carrier
        
        int min = 1;
        int max = 3;
        int length = f - 5;
        //Row & Column
        row = (int)(Math.random() * (max - min + 1) + min);
        column = (int)(Math.random() * (max - min + 1) + min);
        //Verticle or Horizonal
        direction = (int)(Math.random() * (1 - 0 + 1) + 0);
        //Start position
        start = 1; //(int)(Math.random() * (length - (s + 1) + 1) + s);
        //Inputs position
        if (direction == 0) {
            for (int r = start; r < (start + 5); r++) {
                hiddenBoard[r][column] = "C";
            }
        }
        else if (direction == 1) {
            for (int c = start; c < (start + 5); c++) {
                hiddenBoard[row][c] = "C";
            }
        }
        
        //Battleship
        
        int min2 = 6;
        int max2 = 7;
        //Row & Column
        row = (int)(Math.random() * (max2 - min2 + 1) + min2);
        column = (int)(Math.random() * (max2 - min2 + 1) + min2);
        //Verticle or Horizonal
        direction = (int)(Math.random() * (1 - 0 + 1) + 0);
        //Start position
        start = 1; //(int)(Math.random() * (length - (s + 1) + 1) + s);
        //Inputs position
        if (direction == 0) {
            for (int r = start; r < (start + 4); r++) {
                hiddenBoard[r][column] = "X";
            }
        }
        else if (direction == 1) {
            for (int c = start; c < (start + 4); c++) {
                hiddenBoard[row][c] = "X";
            }
        }
        
        //Sub
        
        int min3 = 8;
        int max3 = 8;
        //Row & Column
        row = (int)(Math.random() * (max3 - min3 + 1) + min3);
        column = (int)(Math.random() * (max3 - min3 + 1) + min3);
        //Verticle or Horizonal
        direction = (int)(Math.random() * (1 - 0 + 1) + 0);
        //Start position
        start = 4; //(int)(Math.random() * (length - (s + 1) + 1) + s);
        //Inputs position
        if (direction == 0) {
            for (int r = start; r < (start + 3); r++) {
                hiddenBoard[r][column] = "S";
            }
        }
        else if (direction == 1) {
            for (int c = start; c < (start + 3); c++) {
                hiddenBoard[row][c] = "S";
            }
        }
        //Destroyer
        
        int min4 = 6;
        int max4 = 7;
        //Row & Column
        row = (int)(Math.random() * (max4 - min4 + 1) + min4);
        column = (int)(Math.random() * (max4 - min4 + 1) + min4);
        //Verticle or Horizonal
        direction = (int)(Math.random() * (1 - 0 + 1) + 0);
        //Start position
        start = 5; //(int)(Math.random() * (length - (s + 1) + 1) + s);
        //Inputs position
        if (direction == 0) {
            for (int r = start; r < (start + 3); r++) {
                hiddenBoard[r][column] = "D";
            }
        }
        else if (direction == 1) {
            for (int c = start; c < (start + 3); c++) {
                hiddenBoard[row][c] = "D";
            }
        }
        //Patrol Boat
        
        int min5 = 8;
        int max5 = 8;
        //Row & Column
        row = (int)(Math.random() * (max5 - min5 + 1) + min5);
        column = (int)(Math.random() * (max5 - min5 + 1) + min5);
        //Verticle or Horizonal
        direction = (int)(Math.random() * (1 - 0 + 1) + 0);
        //Start position
        start = 7; //(int)(Math.random() * (length - (s + 1) + 1) + s);
        //Inputs position
        if (direction == 0) {
            for (int r = start; r < (start + 2); r++) {
                hiddenBoard[r][column] = "P";
            }
        }
        else if (direction == 1) {
            for (int c = start; c < (start + 2); c++) {
                hiddenBoard[row][c] = "P";
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
            System.out.println("Type: Battleship");
            hiddenBoard[x][y] = "F";
            board[x][y] = " Hit ";
            battleCount++;
        }
        else if (hiddenBoard[x][y].equals("C")) {
            System.out.println("Last Shot: Hit!");
            System.out.println("Type: Carrier");
            hiddenBoard[x][y] = "F";
            board[x][y] = " Hit ";
            carrierCount++;
        }
        else if (hiddenBoard[x][y].equals("S")) {
            System.out.println("Last Shot: Hit!");
            System.out.println("Type: Submarine");
            hiddenBoard[x][y] = "F";
            board[x][y] = " Hit ";
            subCount++;
        }
        else if (hiddenBoard[x][y].equals("D")) {
            System.out.println("Last Shot: Hit!");
            System.out.println("Type: Destroyer");
            hiddenBoard[x][y] = "F";
            board[x][y] = " Hit ";
            destroyerCount++;
        }
        else if (hiddenBoard[x][y].equals("P")) {
            System.out.println("Last Shot: Hit!");
            System.out.println("Type: Patrol Boat");
            hiddenBoard[x][y] = "F";
            board[x][y] = " Hit ";
            patrolCount++;
        }
        else if (hiddenBoard[x][y].equals(" ")) {
            System.out.println("Last Shot: Miss!");
            System.out.println("Type:");
            board[x][y] = "Empty";
        }
        else {
            System.out.println("Error");
            System.out.println("Please Restart the Program");
            int temp = userScan.nextInt();
        }
        i++;
        printBoard();
        printList();
        //printKey();
    }
    
    static void fireControl() {
        printBoard();
        printList();
        //printKey();
        do {
            firer();
        }
        //essentially any X exists
        while 
        (carrierStatus.equals("Alive") || battleStatus.equals("Alive") ||
        subStatus.equals("Alive") || destroyerStatus.equals("Alive") ||
        patrolStatus.equals("Alive"));
        clear();
        System.out.println("You have sunken the enemy Ships in " + i + " turns");
        System.out.println("GAME OVER");
    }
    
    public static void main(String[] args) {
        do {  
            i = 0;
            carrierCount = 0;
            carrierStatus = "Alive";
            battleCount = 0;
            battleStatus = "Alive";
            subCount = 0;
            subStatus = "Alive";
            destroyerCount = 0;
            destroyerStatus = "Alive";
            patrolCount = 0;
            patrolStatus = "Alive";
            
            rules();
            createBoard();
            spawnShips();
            clear();
            fireControl();
            //repeater
            System.out.print("Would you like to play again? (true/false): ");
            repeat = userScan.nextBoolean();
        } while (repeat);
    }
}