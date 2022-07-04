package htw.berlin.webtech.matefinder.web.api;

import javax.validation.constraints.Positive;

public class RatingManipulationRequest {

    @Positive
    private int value;

    private Long mateid;

    public RatingManipulationRequest(int value, Long mateid) {
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

    public Long getMateid() {
        return mateid;
    }

    public void setMateid(Long mateid) {
        this.mateid = mateid;
    }
}
