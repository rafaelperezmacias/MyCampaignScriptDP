package models;

import java.util.ArrayList;

public class State {

    private int id;
    private String name;
    private ArrayList<Municipality> municipalities;
    private ArrayList<FederalDistrict> federalDistricts;
    private ArrayList<LocalDistrict> localDistricts;
    private ArrayList<Section> sections;

    public State()
    {
        municipalities = new ArrayList<>();
        federalDistricts = new ArrayList<>();
        localDistricts = new ArrayList<>();
        sections = new ArrayList<>();
    }

    public State(int id, String name)
    {
        this();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Municipality> getMunicipalities() {
        return municipalities;
    }

    public ArrayList<FederalDistrict> getFederalDistricts() {
        return federalDistricts;
    }

    public ArrayList<LocalDistrict> getLocalDistricts() {
        return localDistricts;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
