package htw.berlin.webtech.matefinder.web.api;

import java.math.BigDecimal;

/**
 * Good drink
 */
public class Mate {

    private final long id;
    private String name;
    private BigDecimal price;

    public Mate(long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
