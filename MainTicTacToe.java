import java.util.Scanner;

public class MainTicTacToe {
    public static final char PLAYER_X = 'X';
    public static final char PLAYER_O = 'O';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        GameLogic gameLogic = new GameLogic();
        String command;

        System.out.println("Ласкаво просимо до гри Хрестики-нулики!");

        while (true) {
            System.out.println("Введіть команду (старт / вихід):");
            command = scanner.nextLine().trim().toLowerCase();
            if (command.equals("вихід")) {
                System.out.println("До побачення!");
                break;
            } else if (command.equals("початок")) {
                board.initializeBoard();
                char currentPlayer = PLAYER_X;
                board.displayBoard();
                while (true) {
                    System.out.printf("Player %c, введіть свій хід (ряд[1-3] і колона[1-3]): ", currentPlayer);
                    String[] move = scanner.nextLine().trim().split("\\s+");
                    if (move.length != 2) {
                        System.out.println("Будь ласка, введіть два числа для рядка та стовпця.");
                        continue;
                    }
                    int row, col;
                    try {
                        row = Integer.parseInt(move[0]) - 1;
                        col = Integer.parseInt(move[1]) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Ви повинні ввести числа!");
                        continue;
                    }
                    if (!board.makeMove(row, col, currentPlayer)) {
                        System.out.println("Ця клітинка зайнята або поза межами. Спробуйте ще раз.");
                        continue;
                    }
                    board.displayBoard();

                    if (gameLogic.checkVictory(board.getBoard(), currentPlayer)) {
                        System.out.printf("Player %c wins!%n", currentPlayer);
                        break;
                    } else if (board.isBoardFull()) {
                        System.out.println("Нічия!");
                        break;
                    }
                    currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
                }
            } else {
                System.out.println("Недійсна команда. Використайте «старт» або «вихід».");
            }
        }
        scanner.close();
    }
}