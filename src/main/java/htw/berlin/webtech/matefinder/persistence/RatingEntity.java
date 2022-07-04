package htw.berlin.webtech.matefinder.persistence;

import javax.persistence.*;

@Entity(name = "ratings")
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "mateid", referencedColumnName = "id")
    private MateEntity mate;

    @Column(name = "value")
    private int value;

    public RatingEntity(MateEntity mate, int value) {
        this.mate = mate;
        this.value = value;
    }

    protected RatingEntity() {}

    public Long getId() {
        return id;
    }

    public MateEntity getMate() {
        return mate;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
