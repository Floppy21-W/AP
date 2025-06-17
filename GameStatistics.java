public class GameStatistics {
    private int gamesPlayed;
    private int playerXWins;
    private int playerOWins;
    private int draws;

    public GameStatistics() {
        this.gamesPlayed = 0;
        this.playerXWins = 0;
        this.playerOWins = 0;
        this.draws = 0;
    }

    public void incrementGamesPlayed() {
        gamesPlayed++;
    }

    public void addWin(char player) {
        if (player == 'X') {
            playerXWins++;
        } else if (player == 'O') {
            playerOWins++;
        }
    }

    public void addDraw() {
        draws++;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getPlayerXWins() {
        return playerXWins;
    }

    public int getPlayerOWins() {
        return playerOWins;
    }

    public int getDraws() {
        return draws;
    }

    public void displayStatistics() {
        System.out.println("Статистика гри:");
        System.out.println("Зіграні ігри: " + gamesPlayed);
        System.out.println("Перемоги гравця X: " + playerXWins);
        System.out.println("Перемоги гравця O: " + playerOWins);
        System.out.println("Нічия!!!: " + draws);
    }
}
