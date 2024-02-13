package view;

import java.util.Scanner;

public class PersonNotebookView implements iPersonNotebookView {
    Scanner scanner;

    public PersonNotebookView() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String prompt(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    @Override
    public String enterPersonData() {
        return prompt(
            "Введите данные в формате 'Фамилия Имя Отчество дата_рождения номер_телефона пол' :" +
            "\n(или введите exit для завершения программы)");
    }

    @Override
    public void printValidateError(String message) {
        System.out.println(message);
    }
}
