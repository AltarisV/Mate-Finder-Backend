package htw.berlin.webtech.matefinder.service;

import htw.berlin.webtech.matefinder.web.api.Mate;
import htw.berlin.webtech.matefinder.web.api.MateManipulationRequest;
import htw.berlin.webtech.matefinder.persistence.MateEntity;
import htw.berlin.webtech.matefinder.persistence.MateRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MateService {

    private final MateRepo mateRepo;

    public MateService(MateRepo mateRepo) {
        this.mateRepo = mateRepo;
    }

    public List<Mate> findAll() {
        List<MateEntity> mates = mateRepo.findAll();
        return mates.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Mate findById(Long id) {
        var mateEntity = mateRepo.findById(id);
        return mateEntity.map(this::transformEntity).orElse(null);
    }

    public Mate create(MateManipulationRequest request){
        var MateEntity = new MateEntity(request.getName(), request.getPrice());
        MateEntity = mateRepo.save(MateEntity);
        return transformEntity(MateEntity);
    }

    public Mate update(Long id, MateManipulationRequest request) {
        var mateEntityOptional= mateRepo.findById(id);
        if (mateEntityOptional.isEmpty()) return null;
        var mateEntity = mateEntityOptional.get();
        mateEntity.setName(request.getName()); mateEntity.setPrice(request.getPrice());
        mateEntity = mateRepo.save(mateEntity);
        return transformEntity(mateEntity);

    }

    public boolean deleteById(Long id) {
        if (!mateRepo.existsById(id)) return false;
        mateRepo.deleteById(id);
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
