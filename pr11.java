class UserAuthenticationError(Exception):
pass

class UserAlreadyExistsError(UserAuthenticationError):
pass

class UserNotFoundError(UserAuthenticationError):
pass

class InvalidUsernameError(UserAuthenticationError):
pass

class InvalidPasswordError(UserAuthenticationError):
pass

class MaxUsersExceededError(UserAuthenticationError):
pass

class ProhibitedPasswordError(UserAuthenticationError):
pass

class UserAuthenticationSystem:
MAX_USERS = 15

def init(self):
self.users = [] [username, password]
self.prohibited_passwords = ["admin", "pass", "password", "qwerty", "ytrewq"]

def add_prohibited_password(self, password):
        if password and password.lower() not in (p.lower() for p in self.prohibited_passwords):
        self.prohibited_passwords.append(password)
print(f"Prohibited password '{password}' додано.")
        else:
print("Заборонений пароль вже існує або введено недійсний пароль.")

def is_username_valid(self, username):
        if len(username) < 5:
        return False
        if ' ' in username:
        return False
        return True

def is_password_valid(self, password):
        if len(password) < 10:
        return False
        if ' ' in password:
        return False

        digit_count = 0
special_char_count = 0

        for c in password:
        if ('a' <= c <= 'z') or ('A' <= c <= 'Z'):
        continue
elif c.isdigit():
digit_count += 1
elif c == ' ':
        return False
            else:
special_char_count += 1

        if digit_count < 3:
        return False
        if special_char_count < 1:
        return False

        low_password = password.lower()
        for p in self.prohibited_passwords:
        if p.lower() in low_password:
        return False

        return True

def register_user(self, username, password):
        if len(self.users) >= self.MAX_USERS:
raise MaxUsersExceededError("Неможливо додати більше користувачів. Досягнуто максимальної кількості – 15..")
        if not self.is_username_valid(username):
raise InvalidUsernameError("Ім'я користувача повинно містити не менше 5 символів і не містити пробілів.")

        if not self.is_password_valid(password):
raise InvalidPasswordError("Пароль повинен містити не менше 10 символів, містити не менше 3 цифр, не менше 1 спеціального символу, не містити пробілів і не містити заборонених слів.")

        for user in self.users:
        if user[0] == username:
raise UserAlreadyExistsError("Користувач з таким ім'ям вже існує.")

        self.users.append([username, password])
print(f"Користувач '{username}' успішно зареєстрований.")

def delete_user(self, username):
        for i, user in enumerate(self.users):
        if user[0] == username:
del self.users[i]
print(f"Користувач '{username}' успішно видалений.")
                return
raise UserNotFoundError("Користувача не знайдено.")

def perform_action(self, username, password):
        for user in self.users:
        if user[0] == username:
        if user[1] == password:
print(f"Користувач '{username}' успішно аутентифікований.")
                    return
                            else:
raise InvalidPasswordError("Пароль не співпадає.")
raise UserNotFoundError("Користувача не знайдено.")

def input_username(self):
        return input("Введіть ім'я користувача: ").strip()

def input_password(self):
        return input("Введіть пароль: ").strip()

def input_prohibited_password(self):
        return input("Введіть новий заборонений пароль для додавання: ").strip()
def menu(self):
        while True:
print("\nMenu:")
print("1 - Зареєструвати нового користувача")
print("2 - Видалити користувача за ім'ям")
print("3 - Виконати дію користувача (аутентифікація)")
print("4 - Додати заборонений пароль")
print("5 - Вийти")

choice = input("Оберіть дію (1-5): ").strip()

            try:
                    if choice == '1':
username = self.input_username()
password = self.input_password()
                    self.register_user(username, password)
elif choice == '2':
username = self.input_username()
                    self.delete_user(username)
elif choice == '3':
username = self.input_username()
password = self.input_password()
                    self.perform_action(username, password)
elif choice == '4':
new_prohibited = self.input_prohibited_password()
                    if new_prohibited == '':
print("Empty input, nothing added.")
                    else:
                            self.add_prohibited_password(new_prohibited)
elif choice == '5':
print("Exiting program.")
                    break
                            else:
print("Invalid choice. Please choose between 1-5.")
except UserAuthenticationError as e:
print("Error:", e)


if name == "pr10":
auth_system = UserAuthenticationSystem()
    auth_system.menu()