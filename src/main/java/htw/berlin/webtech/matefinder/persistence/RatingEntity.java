package htw.berlin.webtech.matefinder.persistence;

import javax.persistence.*;

@Entity(name = "ratings")
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mateid")
    private Long mateid;

    @Column(name = "value")
    private int value;

    public RatingEntity(Long mateid, int value) {
        this.mateid = mateid;
        this.value = value;
    }

    protected RatingEntity() {}

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
