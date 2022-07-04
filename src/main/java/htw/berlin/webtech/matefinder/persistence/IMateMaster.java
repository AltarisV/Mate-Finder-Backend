package htw.berlin.webtech.matefinder.persistence;

import java.math.BigDecimal;

public interface IMateMaster {
    int getId();

    String getName();

    BigDecimal getPrice();
}
