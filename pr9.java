import java.util.Scanner;

public class pr9 {
    private static final int SIZE = 3;

    public static void displayBoard(char[][] board) {
        System.out.println("---------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("| ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static String getUserCommand(Scanner scanner) {
        System.out.println("Введіть команду («запустити користувача», «вихід»):");
        return scanner.nextLine();
    }

    public static void initializeBoard(char[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static boolean checkVictory(char[][] board, char player) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == player &&
                    board[i][1] == player &&
                    board[i][2] == player) {
                return true;
            }
        }

        for (int j = 0; j < SIZE; j++) {
            if (board[0][j] == player &&
                    board[1][j] == player &&
                    board[2][j] == player) {
                return true;
            }
        }

        if (board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player) {
            return true;
        }

        if (board[0][2] == player &&
                board[1][1] == player &&
                board[2][0] == player) {
            return true;
        }

        return false;
    }

    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++){
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean makeMove(char[][] board, int row, int col, char player) {
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE) {
            if (board[row][col] == ' ') {
                board[row][col] = player;
                return true;
            }
        }
        return false;
    }
}
