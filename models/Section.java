package models;

public class Section {

    private int id;
    private State state;
    private Municipality municipality;
    private LocalDistrict localDistrict;
    private FederalDistrict federalDistrict;

    public Section()
    {

    }

    public Section(int id, State state, Municipality municipality, LocalDistrict localDistrict, FederalDistrict federalDistrict)
    {
        this.id = id;
        this.state = state;
        this.municipality = municipality;
        this.localDistrict = localDistrict;
        this.federalDistrict = federalDistrict;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }

    public LocalDistrict getLocalDistrict() {
        return localDistrict;
    }

    public void setLocalDistrict(LocalDistrict localDistrict) {
        this.localDistrict = localDistrict;
    }

    public FederalDistrict getFederalDistrict() {
        return federalDistrict;
    }

    public void setFederalDistrict(FederalDistrict federalDistrict) {
        this.federalDistrict = federalDistrict;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", state=" + state +
                ", municipality=" + municipality +
                ", localDistrict=" + localDistrict +
                ", federalDistrict=" + federalDistrict +
                '}';
    }

}
