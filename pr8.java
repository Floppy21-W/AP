import java.util.Scanner;

public class pr8 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Виберіть Опцію:");
        System.out.println("1. Інвертувати весь рядок");
        System.out.println("2. Інвертувати кожне слово в рядку");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        String validString = getValidString(scanner);

        if (choice == 1) {
            String result = invertString(validString);
            System.out.println("Інвертований рядок: " + result);
        } else if (choice == 2) {
            String result = invertWords(validString);
            System.out.println("Рядок з інвертованими словами: " + result);
        } else {
            System.out.println("Недійсний вибір. Перезапустіть програму.");
        }

        scanner.close();
    }

    private static String getValidString(Scanner scanner) {
        String userInput;
        while (true) {
            System.out.print("Введіть рядок, що містить щонайменше 2 слова (кожне слово має містити щонайменше 3 символи): ");
            userInput = scanner.nextLine();
            String[] words = userInput.split("\\s+");
            if (words.length >= 2 && allWordsValid(words)) {
                return userInput;
            }
            System.out.println("Недійсний ввід. Спробуйте ще раз.");
        }
    }

    private static boolean allWordsValid(String[] words) {
        for (String word : words) {
            if (word.length() < 3) {
                return false;
            }
        }
        return true;
    }

    private static String invertString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    private static String invertWords(String s) {
        String[] words = s.split("\\s+");
        StringBuilder invertedString = new StringBuilder();

        for (String word : words) {
            invertedString.append(new StringBuilder(word).reverse().toString()).append(" ");
        }

        return invertedString.toString().trim();
    }
}
