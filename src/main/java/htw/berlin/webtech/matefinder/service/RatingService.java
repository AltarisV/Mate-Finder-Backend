package htw.berlin.webtech.matefinder.service;

import htw.berlin.webtech.matefinder.persistence.MateEntity;
import htw.berlin.webtech.matefinder.persistence.RatingEntity;
import htw.berlin.webtech.matefinder.persistence.RatingRepo;
import htw.berlin.webtech.matefinder.web.api.Mate;
import htw.berlin.webtech.matefinder.web.api.MateManipulationRequest;
import htw.berlin.webtech.matefinder.web.api.Rating;
import htw.berlin.webtech.matefinder.web.api.RatingManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {


    public final RatingRepo ratingRepo;

    public RatingService(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    public List<Rating> findAllByMate(Long mateid){
        List<RatingEntity> ratings = ratingRepo.findAllByMateid(mateid);
        return ratings.stream().map(this::transformEntity).collect(Collectors.toList());
    }

    public Rating findById(Long id) {
        var ratingEntity = ratingRepo.findById(id);
        return ratingEntity.map(this::transformEntity).orElse(null);
    }

    public Rating create(RatingManipulationRequest request){
        var RatingEntity = new RatingEntity(request.getMateid(), request.getValue());
        RatingEntity = ratingRepo.save(RatingEntity);
        return transformEntity(RatingEntity);
    }

    public Rating update(Long id, RatingManipulationRequest request) {
        var ratingEntityOptional= ratingRepo.findById(id);
        if (ratingEntityOptional.isEmpty()) return null;
        var ratingEntity = ratingEntityOptional.get();
        ratingEntity.setValue(request.getValue());
        ratingEntity = ratingRepo.save(ratingEntity);
        return transformEntity(ratingEntity);
    }

    private Rating transformEntity(RatingEntity ratingEntity) {
        return new Rating(
                ratingEntity.getId(),
                ratingEntity.getMateid(),
                ratingEntity.getValue()
        );
    }
}
