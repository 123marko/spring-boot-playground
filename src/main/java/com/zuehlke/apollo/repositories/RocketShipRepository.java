package com.zuehlke.apollo.repositories;

import com.zuehlke.apollo.domain.RocketShip;
import com.zuehlke.apollo.domain.enums.RocketShipStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RocketShipRepository extends CrudRepository<RocketShip, Long> {

    List<RocketShip> findByStatus(RocketShipStatus rocketShipStatus);

}
