package model;

import model.exceptions.*;

public interface iPersonNotebookModel {
    public Person parsePersonData(String personData) throws Exception;
    public void validateDataFormat(String personData) throws DataFormatException;
    public void validateName(String name) throws PersonNameException;
    public void validateDate(String date) throws PersonDateException;
    public void validatePhone(String phone) throws PersonPhoneException;
    public void validateGender(char gender) throws PersonGenderException;
}
