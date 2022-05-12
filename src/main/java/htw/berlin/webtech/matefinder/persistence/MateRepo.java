package htw.berlin.webtech.matefinder.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateRepo extends JpaRepository<MateEntity, Long> {

    List<MateEntity> findAllByName(String name);

}
