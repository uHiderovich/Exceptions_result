package view;

public interface iPersonNotebookView {
    public String prompt(String message);
    public String enterPersonData();
    public void printValidateError(String message);
}
