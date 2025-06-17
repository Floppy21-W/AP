public class GameLogic {
    private static final int SIZE = 3;

    public boolean checkVictory(char[][] board, char player) {

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
}
