package com.zuehlke.apollo.repositories;

import com.zuehlke.apollo.domain.Astronaut;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AstronautRepository extends CrudRepository<Astronaut, Long> {

    List<Astronaut> findByRocketShipNotNull();

    List<Astronaut> findByRocketShipNull();

}
