import java.util.Scanner;

class User {
    String username;
    String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}

class InvalidUsernameException extends Exception {
    public InvalidUsernameException(String message) {
        super(message);
    }
}

class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}

public class AuthenticationSystem {
    private static final int MAX_USERS = 15;
    private static User[] users = new User[MAX_USERS];
    private static int userCount = 0;
    private static String[] forbiddenPasswords = {"admin", "pass", "password", "qwerty", "ytrewq"};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Меню:");
            System.out.println("1 - Додати користувача");
            System.out.println("2 - Видалити користувача");
            System.out.println("3 - Аутентифікація користувача");
            System.out.println("4 - Вихід");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        addUser (scanner);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        removeUser (scanner);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        authenticateUser (scanner);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Вихід з програми.");
                    return;
                default:
                    System.out.println("Невірний вибір.");
            }
        }
    }

    private static void addUser (Scanner scanner) throws Exception {
        if (userCount >= MAX_USERS) {
            throw new UserAlreadyExistsException("Не можна додати більше користувачів.");
        }

        System.out.print("Введіть ім'я користувача (мінімум 5 символів, без пробілів): ");
        String username = scanner.nextLine();
        validateUsername(username);

        System.out.print("Введіть пароль (мінімум 10 символів, з принаймні 3 цифрами, без пробілів): ");
        String password = scanner.nextLine();
        validatePassword(password);

        users[userCount++] = new User(username, password);
        System.out.println("Користувача успішно додано.");
    }

    private static void removeUser (Scanner scanner) throws Exception {
        System.out.print("Введіть ім'я користувача для видалення: ");
        String username = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < userCount; i++) {
            if (users[i].username.equals(username)) {
                users[i] = users[userCount - 1];
                users[userCount - 1] = null;
                userCount--;
                found = true;
                System.out.println("Користувача успішно видалено.");
                break;
            }
        }

        if (!found) {
            throw new UserNotFoundException("Користувача не знайдено.");
        }
    }

    private static void authenticateUser (Scanner scanner) throws Exception {
        System.out.print("Введіть ім'я користувача: ");
        String username = scanner.nextLine();
        System.out.print("Введіть пароль: ");
        String password = scanner.nextLine();

        for (int i = 0; i < userCount; i++) {
            if (users[i].username.equals(username) && users[i].password.equals(password)) {
                System.out.println("Користувача було аутентифіковано.");
                return;
            }
        }

        throw new UserNotFoundException("Невірне ім'я користувача або пароль.");
    }

    private static void validateUsername(String username) throws InvalidUsernameException {
        if (username.length() < 5 || username.contains(" ")) {
            throw new InvalidUsernameException("Ім'я користувача має містити не менше 5 символів і не може містити пробілів.");
        }

        for (int i = 0; i < userCount; i++) {
            if (users[i] != null && users[i].username.equals(username)) {
                throw new UserAlreadyExistsException("Користувач з таким ім'ям вже існує.");
            }
        }
    }

    private static void validatePassword(String password) throws InvalidPasswordException {
        if (password.length() < 10 || password.contains(" ")) {
            throw new InvalidPasswordException("Пароль має містити не менше 10 символів і не може містити пробілів.");
        }

        int digitCount = 0;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                digitCount++;
            }
        }

        if (digitCount < 3) {
            throw new InvalidPasswordException("Пароль має містити принаймні 3 цифри.");
        }

        for (String forbidden : forbiddenPasswords) {
            if (password.toLowerCase().contains(forbidden)) {
                throw new InvalidPasswordException("Пароль не може містити заборонені слова.");
            }
        }
    }
}
