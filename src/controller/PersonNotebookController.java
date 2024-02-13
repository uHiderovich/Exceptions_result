package controller;

import model.Person;
import model.PersonNotebookModel;
import view.PersonNotebookView;

public class PersonNotebookController {
    PersonNotebookModel model;
    PersonNotebookView view;

    public PersonNotebookController() {
        model = new PersonNotebookModel();
        view = new PersonNotebookView();
    }

    public void run() {
        String personData = view.enterPersonData();

        if (personData.equals("exit")) {
            return;
        }

        try {
            Person person = model.parsePersonData(personData);
            System.out.println(person);
        } catch (Exception e) {
            view.printValidateError(e.getMessage());
        }

        run();
    }
}
