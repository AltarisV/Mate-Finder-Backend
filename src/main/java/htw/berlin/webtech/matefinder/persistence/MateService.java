package htw.berlin.webtech.matefinder.persistence;

import htw.berlin.webtech.matefinder.api.Mate;
import htw.berlin.webtech.matefinder.api.MateManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MateService {

    private final MateRepo repo;

    public MateService(MateRepo repo) {
        this.repo = repo;
    }

    public MateEntity save(MateEntity mate) {
        return repo.save(mate);
    }

    public MateEntity get(Long id) {
        return repo.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Mate> findAll() {
        List<MateEntity> mates = repo.findAll();
        return mates.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Mate findById(Long id) {
        var mateEntity = repo.findById(id);
        return mateEntity.map(this::transformEntity).orElse(null);
    }

    public Mate create(MateManipulationRequest request){
        var MateEntity = new MateEntity(request.getName(), request.getPrice());
        MateEntity = repo.save(MateEntity);
        return transformEntity(MateEntity);
    }

    public Mate update(Long id, MateManipulationRequest request) {
        var mateEntityOptional= repo.findById(id);
        if (mateEntityOptional.isEmpty()) return null;
        var mateEntity = mateEntityOptional.get();
        mateEntity.setName(request.getName()); mateEntity.setPrice(request.getPrice());
        mateEntity = repo.save(mateEntity);
        return transformEntity(mateEntity);

    }

    public boolean deleteById(Long id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }

    private Mate transformEntity(MateEntity mateEntity) {
        return new Mate(
                mateEntity.getId(),
                mateEntity.getName(),
                mateEntity.getPrice()
        );
    }
}
