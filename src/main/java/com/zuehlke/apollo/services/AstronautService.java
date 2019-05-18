package com.zuehlke.apollo.services;

import com.zuehlke.apollo.domain.Astronaut;

import java.util.List;

public interface AstronautService {

    List<Astronaut> findByRocketShipNotNull();

    List<Astronaut> findByRocketShipNull();

    Astronaut save(Astronaut astronaut);

    Astronaut findById(Long id);
}
