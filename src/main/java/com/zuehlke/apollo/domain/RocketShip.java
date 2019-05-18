package com.zuehlke.apollo.domain;

import com.zuehlke.apollo.domain.enums.RocketShipStatus;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = "assignedAstronauts")
@EqualsAndHashCode(exclude = "assignedAstronauts")
@NoArgsConstructor
@Entity
public class RocketShip {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    private Integer capacity = 10;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rocketShip", fetch = FetchType.EAGER)
    private Set<Astronaut> assignedAstronauts = new HashSet<>();

    @Enumerated(value = EnumType.STRING)
    private RocketShipStatus status;

    public RocketShip assignAstronaut(Astronaut astronaut){
        astronaut.setRocketShip(this);
        this.assignedAstronauts.add(astronaut);
        return this;
    }
}
