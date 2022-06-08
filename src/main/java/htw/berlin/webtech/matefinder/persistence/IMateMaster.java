package htw.berlin.webtech.matefinder.persistence;

import java.math.BigDecimal;

public interface IMateMaster {
    Long getId();

    String getName();

    BigDecimal getPrice();
}
