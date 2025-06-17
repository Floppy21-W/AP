import java.io.*;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicTacToe {
    private static final int MAX_SIZE = 10;
    private static char[][] board;
    private static int boardSize;
    private static String player1Name;
    private static String player2Name;
    private static int player1Wins = 0;
    private static int player2Wins = 0;
    private static int draws = 0;
    private static final Scanner scanner = new Scanner(System.in);
    private static final String SETTINGS_FILE = "settings.txt";
    private static final String STATISTICS_FILE = "statistics.txt";

    public static void main(String[] args) {
        loadSettings();
        menu();
    }

    private static void menu() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Почати гру");
            System.out.println("2 - Налаштування");
            System.out.println("3 - Переглянути статистику");
            System.out.println("4 - Вихід");
            System.out.print("Виберіть дію (1-4): ");

            int choice = getValidIntegerInput(1, 4);

            switch (choice) {
                case 1:
                    startGame();
                    break;
                case 2:
                    changeSettings();
                    break;
                case 3:
                    viewStatistics();
                    break;
                case 4:
                    System.out.println("Вихід з програми.");
                    return;
                default:
                    System.out.println("Недійсний вибір. Будь ласка, виберіть між 1-4.");
            }
        }
    }

    private static void startGame() {
        initializeBoard();
        char currentPlayer = 'X';
        boolean gameWon = false;
        int moves = 0;

        while (moves < boardSize * boardSize && !gameWon) {
            printBoard();
            System.out.printf("Хід гравця %s (%c). Введіть рядок і стовпець (0-%d): ", 
                currentPlayer == 'X' ? player1Name : player2Name, currentPlayer, boardSize - 1);
            int row = getValidIntegerInput(0, boardSize - 1);
            int col = getValidIntegerInput(0, boardSize - 1);

            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                moves++;
                gameWon = checkWin(currentPlayer);
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; 
            } else {
                System.out.println("Клітинка вже зайнята. Спробуйте ще раз.");
            }
        }

        printBoard();
        if (gameWon) {
            String winner = (currentPlayer == 'X') ? player2Name : player1Name;
            System.out.printf("Вітаємо %s! Ви перемогли!!\n", winner);
            if (currentPlayer == 'X') {
                player1Wins++;
            } else {
                player2Wins++;
            }
            saveStatistics(winner);
        } else {
            System.out.println("Нічия!");
            draws++;
            saveStatistics("Нічия");
        }
    }

    private static void initializeBoard() {
        board = new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() {
        System.out.println();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j]);
                if (j < boardSize - 1) System.out.print("|");
            }
            System.out.println();
            if (i < boardSize - 1) {
                for (int j = 0; j < boardSize; j++) {
                    System.out.print("-");
                    if (j < boardSize - 1) System.out.print("+");
                }
                System.out.println();
            }
        }
    }

    private static boolean checkWin(char player) {
        for (int i = 0; i < boardSize; i++) {
            if (checkRow(i, player) || checkColumn(i, player)) {
                return true;
            }
        }
        return checkDiagonal(player);
    }

    private static boolean checkRow(int row, char player) {
        for (int j = 0; j < boardSize; j++) {
            if (board[row][j] != player) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkColumn(int col, char player) {
        for (int i = 0; i < boardSize; i++) {
            if (board[i][col] != player) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkDiagonal(char player) {
        boolean diagonal1 = true;
        boolean diagonal2 = true;
        for (int i = 0; i < boardSize; i++) {
            if (board[i][i] != player) {
                diagonal1 = false;
            }
            if (board[i][boardSize - 1 - i] != player) {
                diagonal2 = false;
            }
        }
        return diagonal1 || diagonal2;
    }

    private static void changeSettings() {
        System.out.print("Введіть розмір дошки (3-10): ");
        boardSize = getValidIntegerInput(3, MAX_SIZE);
        System.out.print("Введіть ім'я гравця 1: ");
        player1Name = scanner.nextLine().trim();
        System.out.print("Введіть ім'я гравця 2: ");
        player2Name = scanner.nextLine().trim();
        saveSettings();
    }

    private static void loadSettings() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SETTINGS_FILE))) {
            boardSize = Integer.parseInt(reader.readLine());
            player1Name = reader.readLine();
            player2Name = reader.readLine();
        } catch (IOException e) {
            System.out.println("Налаштування не знайдено. Використання налаштувань за замовчуванням.");
            boardSize = 3;
            player1Name = "Гравець 1";
            player2Name = "Гравець 2";
        }
    }

    private static void saveSettings() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SETTINGS_FILE))) {
            writer.write(boardSize + "\n");
            writer.write(player1Name + "\n");
            writer.write(player2Name + "\n");
            System.out.println("Налаштування успішно збережено.");
        } catch (IOException e) {
            System.out.println("Помилка збереження налаштувань: " + e.getMessage());
        }
    }

    private static void saveStatistics(String winner) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STATISTICS_FILE, true))) {
            String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write("Переможець: " + winner + ", Дата: " + dateTime + ", Розмір дошки: " + boardSize + "\n");
            System.out.println("Статистика успішно збережена.");
        } catch (IOException e) {
            System.out.println("Помилка збереження статистики: " + e.getMessage());
        }
    }

    private static void viewStatistics() {
        try (BufferedReader reader = new BufferedReader(new FileReader(STATISTICS_FILE))) {
            String line;
            System.out.println("\nСтатистика гри:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Статистики не знайдено.");
        }
    }

    private static int getValidIntegerInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Будь ласка, введіть число від " + min + " до " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Недійсне введення. Будь ласка, введіть дійсне число.");
            }
        }
    }
}
