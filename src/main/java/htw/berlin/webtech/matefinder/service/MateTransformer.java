package htw.berlin.webtech.matefinder.service;

import htw.berlin.webtech.matefinder.persistence.MateEntity;
import htw.berlin.webtech.matefinder.persistence.RatingEntity;
import htw.berlin.webtech.matefinder.web.api.Mate;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MateTransformer {

    public Mate transformEntity(MateEntity mateEntity) {
        var ratingIds = mateEntity.getRatings().stream().map(RatingEntity::getId).collect(Collectors.toList());
        return new Mate(
                mateEntity.getId(),
                mateEntity.getName(),
                mateEntity.getPrice(),
                ratingIds);
    }
}
