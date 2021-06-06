package com.example.backend.model;
import com.example.backend.utils.LoyaltyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LoyaltyProgram {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LoyaltyType loyaltyType;



    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @OneToMany(mappedBy = "loyaltyProgram", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoyaltyPoint> loyaltyPoints;



}
