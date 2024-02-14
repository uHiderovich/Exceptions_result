package controller;

import controller.exeptions.FileWriterException;
import model.Person;
import model.PersonNotebookModel;
import view.PersonNotebookView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PersonNotebookController implements IPersonNotebookController, iFileBehaviour {
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
            saveDataToFile(getPersonSecondName(person), getPersonDataString(person));
        } catch (Exception e) {
            view.printValidateError(e.getMessage());
        }

        run();
    }

    public String getPersonSecondName(Person person) {
        return person.getName().split(" ")[0];
    }

    public String getPersonDataString(Person person) {
        return person.getName() + " " + person.getDate() + " " + person.getPhone() + " " + person.getGender() + "\n";
    }

    public void saveDataToFile(String fileName, String data) {
        String fullFileName = fileName.concat(".txt");
        try(FileWriter writer = new FileWriter(fullFileName, true)) {
            writer.write(data);
            closeFile(writer);
        } catch(IOException ex){
            try {
                createFile(fullFileName);
                saveDataToFile(fileName, data);
            } catch (FileWriterException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void createFile(String fileName) throws FileWriterException {
        try {
            String pathProject = System.getProperty("user.dir.src.files");
            String pathFile = pathProject.concat(fileName);
            File file = new File(pathFile);
            file.createNewFile();
        } catch (IOException e) {
            throw new FileWriterException("Ошибка создания файла: " + e.getMessage());
        }
    }

    public void closeFile(FileWriter fileWriter) {
        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
