package models;

public class LocalDistrict {

    private int id;
    private int number;
    private String name;

    public LocalDistrict()
    {

    }

    public LocalDistrict(int id, int number, String name)
    {
        this.id = id;
        this.number = number;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LocalDistrict{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                '}';
    }

}
