package com.zuehlke.apollo.bootstrap;

import com.zuehlke.apollo.domain.Astronaut;
import com.zuehlke.apollo.domain.RocketShip;
import com.zuehlke.apollo.domain.enums.Gender;
import com.zuehlke.apollo.domain.enums.RocketShipStatus;
import com.zuehlke.apollo.services.AstronautService;
import com.zuehlke.apollo.services.RocketShipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataBootup implements ApplicationListener<ContextRefreshedEvent> {

    private RocketShipService rocketShipService;
    private AstronautService astronautService;

    public DataBootup(RocketShipService rocketShipService, AstronautService astronautService) {
        this.rocketShipService = rocketShipService;
        this.astronautService = astronautService;
    }

    private void initData(){
        log.info("#######################Initializing data#######################");
        RocketShip rocketShip1 = new RocketShip();
        rocketShip1.setName("Apollo18");
        rocketShip1.setCapacity(6);
        rocketShip1.setStatus(RocketShipStatus.LAUNCHED);
        rocketShipService.save(rocketShip1);

        RocketShip rocketShip2 = new RocketShip();
        rocketShip2.setName("Apollo19");
        rocketShip2.setCapacity(6);
        rocketShip2.setStatus(RocketShipStatus.READY);
        rocketShipService.save(rocketShip2);

        log.info("#######################Data initializing done#######################");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
