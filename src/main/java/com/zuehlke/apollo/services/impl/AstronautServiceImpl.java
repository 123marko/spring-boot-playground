package com.zuehlke.apollo.services.impl;

import com.zuehlke.apollo.domain.Astronaut;
import com.zuehlke.apollo.repositories.AstronautRepository;
import com.zuehlke.apollo.services.AstronautService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AstronautServiceImpl implements AstronautService {

    private final AstronautRepository repository;

    public AstronautServiceImpl(AstronautRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Astronaut> findByRocketShipNotNull() {
        return repository.findByRocketShipNotNull();
    }

    @Override
    public List<Astronaut> findByRocketShipNull() {
        return repository.findByRocketShipNull();
    }

    @Override
    public Astronaut save(Astronaut astronaut) {
        return repository.save(astronaut);
    }

    @Override
    public Astronaut findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
