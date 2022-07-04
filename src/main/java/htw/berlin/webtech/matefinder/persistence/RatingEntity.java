package htw.berlin.webtech.matefinder.persistence;

import javax.persistence.*;

@Entity(name = "ratings")
public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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

    public int getId() {
        return this.id;
    }

    public MateEntity getMate() {
        return this.mate;
    }

    public void setMate(MateEntity mate){
        this.mate = mate;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
