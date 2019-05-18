package com.zuehlke.apollo.services;

import com.zuehlke.apollo.domain.RocketShip;
import com.zuehlke.apollo.domain.enums.RocketShipStatus;

import java.util.List;

public interface RocketShipService {

    RocketShip findById(Long id);

    RocketShip save(RocketShip rocketShip);

    List<RocketShip> findByStatus(RocketShipStatus rocketShipStatus);

    List<RocketShip> findAll();
}
