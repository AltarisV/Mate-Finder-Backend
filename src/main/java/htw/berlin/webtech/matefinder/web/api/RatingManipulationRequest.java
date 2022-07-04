package htw.berlin.webtech.matefinder.web.api;

import javax.validation.constraints.Positive;

public class RatingManipulationRequest {

    private int value;

    private int mateid;

    public RatingManipulationRequest(int value, int mateid) {
        this.value = value;
        this.mateid = mateid;
    }

    public RatingManipulationRequest() {}

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMateid() {
        return mateid;
    }

    public void setMateid(int mateid) {
        this.mateid = mateid;
    }
}
