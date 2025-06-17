import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MyDiary {
    private static final int MAX_ENTRIES = 50;
    private static final String[] diaryEntries = new String[MAX_ENTRIES];
    private static int entryCount = 0;
    private static final Scanner scanner = new Scanner(System.in);
    private static String dateFormat = "yyyy-MM-dd HH:mm";

    public static void main(String[] args) {
        MyDiary diary = new MyDiary();
        diary.start();
    }

    private void start() {
        System.out.print("Ви хочете завантажити існуючий щоденник? (так/ні): ");
        String loadChoice = scanner.nextLine().trim().toLowerCase();

        if (loadChoice.equals("Так")) {
            System.out.print("Введіть шлях до файлу для завантаження щоденника: ");
            String filePath = scanner.nextLine().trim();
            loadDiary(filePath);
        }

        setDateFormat();

        menu();
    }

    private void setDateFormat() {
        System.out.print("Введіть формат дати (або натисніть Enter для формату за замовчуванням'yyyy-MM-dd HH:mm'): ");
        String inputFormat = scanner.nextLine().trim();
        if (!inputFormat.isEmpty()) {
            dateFormat = inputFormat;
        }
    }

    private void menu() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Додати запис");
            System.out.println("2 - Видалити запис за датою");
            System.out.println("3 - Переглянути всі записи");
            System.out.println("4 - Вихід");
            System.out.print("Виберіть дію (1-4): ");

            int choice = getValidIntegerInput(1, 4);

            switch (choice) {
                case 1:
                    addEntry();
                    break;
                case 2:
                    deleteEntry();
                    break;
                case 3:
                    viewEntries();
                    break;
                case 4:
                    exitDiary();
                    return;
                default:
                    System.out.println("Недійсний вибір. Будь ласка, виберіть між 1-4.");
            }
        }
    }

    private void addEntry() {
        if (entryCount >= MAX_ENTRIES) {
            System.out.println("Diary is full. Cannot add more entries.");
            return;
        }

        System.out.print("Введіть дату (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();
        String time = "00:00";

        System.out.println("Введіть ваш запис (введіть 'END' на новому рядку, щоб закінчити):");
        String entry = "";
        String line;

        while (!(line = scanner.nextLine()).equals("END")) {
            entry += line + "\n";
        }

        diaryEntries[entryCount] = date + " " + time + "\n" + entry.trim();
        entryCount++;
        System.out.println("Запис успішно додано.");
    }

    private void deleteEntry() {
        System.out.print("Введіть дату запису, який потрібно видалити (YYYY-MM-DD): ");
        String dateToDelete = scanner.nextLine().trim();
        boolean found = false;

        for (int i = 0; i < entryCount; i++) {
            if (diaryEntries[i].startsWith(dateToDelete)) {
                found = true;
                for (int j = i; j < entryCount - 1; j++) {
                    diaryEntries[j] = diaryEntries[j + 1];
                }
                diaryEntries[--entryCount] = null;
                System.out.println("Запис успішно видалено.");
                break;
            }
        }

        if (!found) {
            System.out.println("Запис для вказаної дати не знайдено.");
        }
    }

    private void viewEntries() {
        if (entryCount == 0) {
            System.out.println("Немає записів у щоденнику.");
            return;
        }

        System.out.println("\nЗаписи у щоденнику:");
        for (int i = 0; i < entryCount; i++) {
            System.out.println(diaryEntries[i]);
        }
    }

    private void exitDiary() {
        System.out.print("Ви хочете зберегти щоденник? (так/ні): ");
        String saveChoice = scanner.nextLine().trim().toLowerCase();

        if (saveChoice.equals("так")) {
            System.out.print("Введіть шлях до файлу для збереження щоденника: ");
            String filePath = scanner.nextLine().trim();
            saveDiary(filePath);
        }
        System.out.println("Вихід з програми.");
    }

    private void saveDiary(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < entryCount; i++) {
                writer.write(diaryEntries[i]);
                writer.write("\n");
            }
            System.out.println("Щоденник успішно збережено.");
        } catch (IOException e) {
            System.out.println("Помилка збереження щоденника: " + e.getMessage());
        }
    }

    private void loadDiary(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder entry = new StringBuilder();
            String date = "";
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    if (entry.length() > 0) {
                        diaryEntries[entryCount++] = date + "\n" + entry.toString().trim();
                        entry.setLength(0);
                    }
                } else {
                    if (date.isEmpty()) {
                        date = line;
                    } else {
                        entry.append(line).append("\n");
                    }
                }
            }
            if (entry.length() > 0) {
                diaryEntries[entryCount++] = date + "\n" + entry.toString().trim();
            }
            System.out.println("Щоденник успішно завантажено.");
        } catch (IOException e) {
            System.out.println("Помилка завантаження щоденника: " + e.getMessage());
        }
    }

    private int getValidIntegerInput(int min, int max) {
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
