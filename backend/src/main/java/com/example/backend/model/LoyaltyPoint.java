package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LoyaltyPoint {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  //  private int points;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loyalty_program_id")
    private LoyaltyProgram loyaltyProgram;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medicine_reservation_id", referencedColumnName = "id")
    private MedicineReservation medicineReservation;



}
