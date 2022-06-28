package htw.berlin.webtech.matefinder.web.api;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class MateManipulationRequest {

    @Size(min = 3, message = "Please prvide a name longer than 3 characters")
    private String name;

    @Positive(message = "Mates must have a price")
    private BigDecimal price;

    public MateManipulationRequest(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public MateManipulationRequest() {}

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
