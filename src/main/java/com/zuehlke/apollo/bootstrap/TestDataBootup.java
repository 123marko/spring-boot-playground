package com.zuehlke.apollo.bootstrap;

import com.zuehlke.apollo.domain.Astronaut;
import com.zuehlke.apollo.domain.RocketShip;
import com.zuehlke.apollo.domain.enums.Gender;
import com.zuehlke.apollo.domain.enums.RocketShipStatus;
import com.zuehlke.apollo.services.AstronautService;
import com.zuehlke.apollo.services.RocketShipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("test")
public class TestDataBootup implements ApplicationListener<ContextRefreshedEvent> {
    private RocketShipService rocketShipService;
    private AstronautService astronautService;

    public TestDataBootup(RocketShipService rocketShipService, AstronautService astronautService) {
        this.rocketShipService = rocketShipService;
        this.astronautService = astronautService;
    }

    private void initData(){
        log.info("#######################Initializing TEST data#######################");
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

        Astronaut janeDoe = new Astronaut();
        janeDoe.setName("Jane Doe");
        janeDoe.setGender(Gender.FEMALE);
        janeDoe.setEmail("a@a.com");
        janeDoe.setAge(23);
        rocketShip1.assignAstronaut(janeDoe);
        astronautService.save(janeDoe);

        Astronaut janeDoe2 = new Astronaut();
        janeDoe2.setName("Jane Doe II");
        janeDoe2.setGender(Gender.FEMALE);
        janeDoe2.setEmail("q@q.com");
        janeDoe2.setAge(27);
        astronautService.save(janeDoe2);

        Astronaut johnDoe = new Astronaut();
        johnDoe.setName("John Doe");
        johnDoe.setGender(Gender.MALE);
        johnDoe.setEmail("c@c.com");
        johnDoe.setAge(30);
        astronautService.save(johnDoe);

        log.info("#######################TEST data initializing done#######################");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
