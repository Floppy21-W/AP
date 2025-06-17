import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class pr12 {
    private static final String FILENAME = "pr12.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SimpleTextEditor editor = new SimpleTextEditor();
        editor.menu();
    }

    private void menu() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Записати в файл");
            System.out.println("2 - Прочитати файл");
            System.out.println("3 - Відображення певного діапазону рядків");
            System.out.println("4 - Записати в певний рядок");
            System.out.println("5 - Вихід з програми");
            System.out.print("Виберіть дію (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    writeToFile();
                    break;
                case 2:
                    readFile();
                    break;
                case 3:
                    displayLineRange();
                    break;
                case 4:
                    writeToSpecificLine();
                    break;
                case 5:
                    System.out.println("Вихід з програми.");
                    return;
                default:
                    System.out.println("Недійсний вибір. Будь ласка, виберіть між 1-5.");
            }
        }
    }

    private void writeToFile() {
        System.out.println("Введіть текст (наберіть 'КІНЕЦЬ' на новому рядку, щоб закінчити введення):");
        StringBuilder content = new StringBuilder();
        String line;

        while (!(line = scanner.nextLine()).equals("КІНЕЦЬ")) {
            content.append(line).append("\n");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
            writer.write(content.toString());
            System.out.println("Текст записано у файл успішно.");
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    private void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.printf("%d: %s%n", lineNumber++, line);
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }
    }

    private void displayLineRange() {
        System.out.print("Введіть номер початкового рядка: ");
        int startLine = scanner.nextInt();
        System.out.print("Введіть номер кінцевого рядка: ");
        int endLine = scanner.nextInt();
        scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                if (lineNumber >= startLine && lineNumber <= endLine) {
                    System.out.printf("%d: %s%n", lineNumber, line);
                }
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }
    }

    private void writeToSpecificLine() {
        System.out.print("Введіть номер рядка для запису: ");
        int lineNumberToWrite = scanner.nextInt();
        scanner.nextLine();

        StringBuilder fileContent = new StringBuilder();
        String line;
        int currentLine = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            while ((line = reader.readLine()) != null) {
                if (currentLine == lineNumberToWrite) {
                    System.out.print("Введіть текст для додавання до рядка " + lineNumberToWrite + ": ");
                    String newText = scanner.nextLine();
                    fileContent.append(newText).append("\n");
                }
                fileContent.append(line).append("\n");
                currentLine++;
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            writer.write(fileContent.toString());
            System.out.println("Текст записано у рядок " + lineNumberToWrite + " успішно.");
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }
}
