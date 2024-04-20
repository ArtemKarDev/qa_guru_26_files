package guru.qa.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarFeatures {

    private Boolean sunroof;
    private Boolean navigation;
    @JsonProperty("leather seats")
    private Boolean leatherSeats;

    public Boolean getSunroof() {
        return sunroof;
    }

    public void setSunroof(Boolean sunroof) {
        this.sunroof = sunroof;
    }

    public Boolean getNavigation() {
        return navigation;
    }

    public void setNavigation(Boolean navigation) {
        this.navigation = navigation;
    }

    public Boolean getLeatherSeats() {
        return leatherSeats;
    }

    public void setLeatherSeats(Boolean leatherSeats) {
        this.leatherSeats = leatherSeats;
    }
}
