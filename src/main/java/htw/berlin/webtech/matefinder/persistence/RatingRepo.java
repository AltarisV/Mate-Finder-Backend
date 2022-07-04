package htw.berlin.webtech.matefinder.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepo extends JpaRepository<RatingEntity, Long> {

}
