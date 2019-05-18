package com.zuehlke.apollo.services.impl;

import com.zuehlke.apollo.domain.RocketShip;
import com.zuehlke.apollo.domain.enums.RocketShipStatus;
import com.zuehlke.apollo.repositories.RocketShipRepository;
import com.zuehlke.apollo.services.RocketShipService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RocketShipServiceImpl implements RocketShipService {

    private final RocketShipRepository repository;

    public RocketShipServiceImpl(RocketShipRepository repository) {
        this.repository = repository;
    }

    @Override
    public RocketShip findById(Long id) {
        return repository.findById(id).orElse(null );
    }

    @Override
    public RocketShip save(RocketShip rocketShip) {
        return repository.save(rocketShip);
    }

    @Override
    public List<RocketShip> findByStatus(RocketShipStatus rocketShipStatus) {
        return repository.findByStatus(rocketShipStatus);
    }

    @Override
    public List<RocketShip> findAll() {
        List<RocketShip> rocketShips = new ArrayList<>();
        repository.findAll().iterator().forEachRemaining(rocketShips::add);
        return rocketShips;
    }
}
