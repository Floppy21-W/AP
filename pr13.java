import java.util.Scanner;

public class pr13 {
    private static final int MAX_ENTRIES = 50;
    private static final String[] diaryEntries = new String[MAX_ENTRIES];
    private static int entryCount = 0;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        pr13 diary = new pr13();
        diary.menu();
    }

    private void menu() {
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1 - Додати запис");
            System.out.println("2 - Видалити запис за датою");
            System.out.println("3 - Переглянути всі записи");
            System.out.println("4 - Вихід");
            System.out.print("Оберіть дію (1-4): ");

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
                    System.out.println("Вихід з програми.");
                    return;
                default:
                    System.out.println("Невірний вибір. Будь ласка, виберіть дію між 1-4.");
            }
        }
    }

    private void addEntry() {
        if (entryCount >= MAX_ENTRIES) {
            System.out.println("Щоденник заповнений. Не можна додати більше записів.");
            return;
        }

        System.out.print("Введіть дату (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();

        System.out.println("Введіть ваш запис (наберіть 'END' на новому рядку, щоб закінчити):");
        StringBuilder entry = new StringBuilder();
        String line;

        while (!(line = scanner.nextLine()).equals("END")) {
            entry.append(line).append("\n");
        }

        diaryEntries[entryCount] = date + " 00:00\n" + entry.toString().trim();
        entryCount++;
        System.out.println("Запис успішно додано.");
    }

    private void deleteEntry() {
        System.out.print("Введіть дату запису для видалення (YYYY-MM-DD): ");
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
            System.out.println("Запис не знайдено для вказаної дати.");
        }
    }

    private void viewEntries() {
        if (entryCount == 0) {
            System.out.println("В щоденнику немає записів.");
            return;
        }

        System.out.println("\nЗаписи щоденника:");
        for (int i = 0; i < entryCount; i++) {
            System.out.println(diaryEntries[i]);
        }
    }

    private int getValidIntegerInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Будь ласка, введіть число між " + min + " і " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Невірний ввід. Будь ласка, введіть дійсне число.");
            }
        }
    }
}
