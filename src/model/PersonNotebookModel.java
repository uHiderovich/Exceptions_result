package model;

import model.exceptions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class PersonNotebookModel implements iPersonNotebookModel {
    @Override
    public Person parsePersonData(String personData) throws Exception {
        List<String> personDataList = Arrays.asList(personData.split(" "));
        // Иванов Иван Иванович 01.01.2001 +79999999999 М
        try {
            String name = personDataList.get(0) + " " + personDataList.get(1) + " " + personDataList.get(2);

            validateDataFormat(personData);
            validateName(name);
            validateDate(personDataList.get(3));
            validatePhone(personDataList.get(4));
            validateGender(personDataList.get(5).charAt(0));

            return new Person(name, personDataList.get(3), Long.parseLong(personDataList.get(4)), personDataList.get(5).charAt(0));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void validateDataFormat(String personData) throws DataFormatException {
        int COUNT_DATA_PARTS = 6;
        int currentPartsCount = personData.split(" ").length;
        if (currentPartsCount != COUNT_DATA_PARTS) {
            StringBuilder message = new StringBuilder("Неверный формат данных. Введено ");
            message.append(currentPartsCount);
            message.append(" частей, ожидалось ");
            message.append(COUNT_DATA_PARTS);
            message.append(".");
            message.append("Сверьтесь с примером ввода данных: 'Фамилия Имя Отчество дата_рождения номер_телефона пол'.");

            throw new DataFormatException(message.toString());
        }
    }

    @Override
    public void validateName(String name) throws PersonNameException {
        List<String> nameParts = Arrays.asList(name.split(" "));
        if (nameParts.size() != 3) {
            throw new PersonNameException("Имя должно содержать три части: Фамилия, Имя, Отчество.");
        }
    }

    @Override
    public void validateDate(String date) throws PersonDateException {
        String DATE_FORMAT = "dd.mm.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);

        try {
            sdf.parse(date);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            throw new PersonDateException("Неверный формат даты. Ожидается формат: " + DATE_FORMAT);
        }
    }

    @Override
    public void validatePhone(String phone) throws PersonPhoneException {
        try {
            Long.parseLong(phone);
        } catch (NumberFormatException e) {
            throw new PersonPhoneException("Номер телефона должен содержать только цифры.");
        }

        String PHONE_FORMAT = "^([78])\\d{10}$";
        if (!phone.matches(PHONE_FORMAT)) {
            throw new PersonPhoneException("Неверный формат номера телефона. Ожидается число формата: 7XXXXXXXXXX или 8XXXXXXXXXX");
        }
    }

    @Override
    public void validateGender(char gender) throws PersonGenderException {
        if (gender != 'М' && gender != 'Ж') {
            throw new PersonGenderException("Неверный пол: " + gender + ". Ожидается 'М' или 'Ж'.");
        }
    }

}
