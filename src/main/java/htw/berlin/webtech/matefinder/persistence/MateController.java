package htw.berlin.webtech.matefinder.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MateController {

    @Autowired
    MateService service;

    @PostMapping("/mate")
    public MateEntity createMate(@RequestBody MateEntity mate) {
        return service.save(mate);
    }

    @GetMapping("/mate/{id}")
    public MateEntity getThing(@PathVariable String id) {
        Long mateId = Long.parseLong(id);
        return service.get(mateId);
    }
}
