package htw.berlin.webtech.matefinder.web.api;

import htw.berlin.webtech.matefinder.persistence.MateEntity;

public class Rating {

    private final Long id;
    private final Long mateid;
    private int value;

    public Rating(Long id, Long mateid, int value) {
        this.id = id;
        this.mateid = mateid;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public Long getMateid() {
        return mateid;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
