package com.zuehlke.apollo.schedulers;

import com.zuehlke.apollo.domain.Astronaut;
import com.zuehlke.apollo.domain.RocketShip;
import com.zuehlke.apollo.domain.enums.RocketShipStatus;
import com.zuehlke.apollo.services.AstronautService;
import com.zuehlke.apollo.services.RocketShipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class RocketShipStatusTask {

    private RocketShipService rocketShipService;
    private AstronautService astronautService;

    public RocketShipStatusTask(RocketShipService rocketShipService, AstronautService astronautService) {
        this.rocketShipService = rocketShipService;
        this.astronautService = astronautService;
    }

    @Scheduled(cron="0 * * * * *")
    public void updateStatus(){
        log.info("############### Starting status update");
        List<RocketShip> launched = rocketShipService.findByStatus(RocketShipStatus.LAUNCHED);
        launched.forEach(rocketShip -> {
            rocketShip.setStatus(RocketShipStatus.READY);
            Set<Astronaut> assigned = rocketShip.getAssignedAstronauts();
            assigned.forEach(astronaut -> {
                astronaut.setRocketShip(null);
                astronautService.save(astronaut);
            });
            rocketShip.getAssignedAstronauts().clear();
            rocketShipService.save(rocketShip);
        });
        log.info("############### Finished status update");
    }
}
