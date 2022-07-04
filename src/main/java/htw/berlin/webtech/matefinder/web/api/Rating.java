package htw.berlin.webtech.matefinder.web.api;

public class Rating {

    private final int id;
    private final Mate mate;
    private int value;

    public Rating(int id, Mate mate, int value) {
        this.id = id;
        this.mate = mate;
        this.value = value;
    }

    public int getId() {
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
