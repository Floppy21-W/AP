public class GameSettings {
    private char currentPlayer;

    public GameSettings(char startingPlayer) {
        this.currentPlayer = startingPlayer;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }
}
