package com.biOneMany.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Mobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "price", nullable = false)
    private String price;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonManagedReference("sims")
    private List<Sim> sims;

}