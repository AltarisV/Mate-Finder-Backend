package htw.berlin.webtech.matefinder.web.api;

public class Rating {

    private final Long id;
    private final Mate mate;
    private int value;

    public Rating(Long id, Mate mate, int value) {
        this.id = id;
        this.mate = mate;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public Mate getMate() {
        return mate;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
