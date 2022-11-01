package models;

import java.util.ArrayList;

public class State {

    private int id;
    private String name;
    private ArrayList<FederalDistrict> federalDistricts;
    private ArrayList<LocalDistrict> localDistricts;
    private ArrayList<Municipality> municipalities;
    private ArrayList<Section> sections;

    public State()
    {

    }

    public State(int id, String name, ArrayList<FederalDistrict> federalDistricts, ArrayList<LocalDistrict> localDistricts, ArrayList<Municipality> municipalities, ArrayList<Section> sections)
    {
        this.id = id;
        this.name = name;
        this.federalDistricts = federalDistricts;
        this.localDistricts = localDistricts;
        this.municipalities = municipalities;
        this.sections = sections;
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

    public ArrayList<FederalDistrict> getFederalDistricts() {
        return federalDistricts;
    }

    public void setFederalDistricts(ArrayList<FederalDistrict> federalDistricts) {
        this.federalDistricts = federalDistricts;
    }

    public ArrayList<LocalDistrict> getLocalDistricts() {
        return localDistricts;
    }

    public void setLocalDistricts(ArrayList<LocalDistrict> localDistricts) {
        this.localDistricts = localDistricts;
    }

    public ArrayList<Municipality> getMunicipalities() {
        return municipalities;
    }

    public void setMunicipalities(ArrayList<Municipality> municipalities) {
        this.municipalities = municipalities;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", federalDistricts=" + federalDistricts +
                ", localDistricts=" + localDistricts +
                ", municipalities=" + municipalities +
                ", sections=" + sections +
                '}';
    }

}
