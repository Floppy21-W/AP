public class Board {
    private static final int SIZE = 3;
    private final char[][] board;

    public Board() {
        board = new char[SIZE][SIZE];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void displayBoard() {
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

    public char[][] getBoard() {
        return board;
    }

    public boolean makeMove(int row, int col, char player) {
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE) {
            if (board[row][col] == ' ') {
                board[row][col] = player;
                return true;
            }
        }
        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
