package controller;

import controller.exeptions.FileWriterException;

import java.io.FileWriter;

public interface iFileBehaviour {
    public void saveDataToFile(String fileName, String data);
    public void createFile(String fileName) throws FileWriterException;
    public void closeFile(FileWriter fileWriter);
}
