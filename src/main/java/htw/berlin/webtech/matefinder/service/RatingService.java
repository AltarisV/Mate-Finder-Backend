package htw.berlin.webtech.matefinder.service;

import htw.berlin.webtech.matefinder.persistence.MateRepo;
import htw.berlin.webtech.matefinder.persistence.RatingEntity;
import htw.berlin.webtech.matefinder.persistence.RatingRepo;
import htw.berlin.webtech.matefinder.web.api.Rating;
import htw.berlin.webtech.matefinder.web.api.RatingManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {


    private final RatingRepo ratingRepo;
    private final MateRepo mateRepo;
    private final MateTransformer mateTransformer;

    public RatingService(RatingRepo ratingRepo, MateRepo mateRepo, MateTransformer mateTransformer) {
        this.ratingRepo = ratingRepo;
        this.mateRepo = mateRepo;
        this.mateTransformer = mateTransformer;
    }

    public List<Rating> findAll() {
        List<RatingEntity> ratings = ratingRepo.findAll();
        return ratings.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Rating findById(Long id) {
        var ratingEntity = ratingRepo.findById(id);
        return ratingEntity.map(this::transformEntity).orElse(null);
    }

    public Rating create(RatingManipulationRequest request){
        var mate = mateRepo.findById(request.getMateid()).orElseThrow();
        var RatingEntity = new RatingEntity(mate, request.getValue());
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
                mateTransformer.transformEntity(ratingEntity.getMate()),
                ratingEntity.getValue()
        );
    }
}
