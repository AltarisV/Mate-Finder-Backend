package htw.berlin.webtech.matefinder.web;

import htw.berlin.webtech.matefinder.service.RatingService;
import htw.berlin.webtech.matefinder.web.api.Mate;
import htw.berlin.webtech.matefinder.web.api.MateManipulationRequest;
import htw.berlin.webtech.matefinder.web.api.Rating;
import htw.berlin.webtech.matefinder.web.api.RatingManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping(path = "/api/ratings")
    public ResponseEntity<List<Rating>> fetchRatingsByMateid(Long mateid) {
        return ResponseEntity.ok(ratingService.findAllByMate(mateid));
    }

    @GetMapping(path = "/api/mates/{id}")
    public ResponseEntity<Rating> fetchRatingById(@PathVariable Long id) {
        var rating = ratingService.findById(id);
        return rating != null? ResponseEntity.ok(rating) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/ratings")
    public ResponseEntity<Void> createRating(@Valid @RequestBody RatingManipulationRequest request) throws URISyntaxException {
        var rating = ratingService.create(request);
        URI uri = new URI("/api/ratings/" + rating.getId());
        return ResponseEntity
                .created(uri)
                .header("Access-Control-Expose-Headers", "Location")
                .build();
    }

    @PutMapping(path = "/api/ratings/{id}")
    public ResponseEntity<Rating> updateRating(@PathVariable Long id, @RequestBody RatingManipulationRequest request) {
        var rating = ratingService.update(id, request);
        return rating != null? ResponseEntity.ok(rating) : ResponseEntity.notFound().build();
    }
}
