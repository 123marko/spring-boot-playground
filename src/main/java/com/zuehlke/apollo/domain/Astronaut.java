package com.zuehlke.apollo.domain;


import com.zuehlke.apollo.domain.enums.Gender;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "rocketShip")
@NoArgsConstructor
@Entity
public class Astronaut {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank
    @Size(min = 2, max = 255)
    private String name;

    @Min(value = 1)
    @Max(value = 99)
    private Integer age;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @NotBlank
    @Email
    private String email;

    @ManyToOne
    private RocketShip rocketShip;

}
