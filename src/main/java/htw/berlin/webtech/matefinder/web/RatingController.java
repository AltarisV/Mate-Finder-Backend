package htw.berlin.webtech.matefinder.web;

import htw.berlin.webtech.matefinder.service.RatingService;
import htw.berlin.webtech.matefinder.web.api.Rating;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

/*    @GetMapping(path = "/api/ratings")
    public ResponseEntity<List<Rating>> fetchRatings() {
        return ResponseEntity.ok(ratingService.)
    }*/
}
