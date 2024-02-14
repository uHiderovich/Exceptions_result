package controller;

import model.Person;

public interface IPersonNotebookController {
    public void run();
    public String getPersonSecondName(Person person);
    public String getPersonDataString(Person person);
}
