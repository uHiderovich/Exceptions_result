package model;

public class Person {
    private String name;
    private String date;
    private Long phone;
    private Character gender;

    public Person(String name, String date, Long phone, Character gender) {
        this.name = name;
        this.date = date;
        this.phone = phone;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Long getPhone() {
        return phone;
    }

    public Character getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Имя: " + name + "\n" +
                "Дата рождения: " + date + "\n" +
                "Телефон: " + phone + "\n" +
                "Пол: " + gender + "\n";
    }
}
