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
public class Complaint {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    private boolean answered;

    //odredjivacemo sta se ocenjuje
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dermatologist_id")
    private Dermatologist dermatologist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacist_id")
    private Pharmacist pharmacist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicament_id")
    private Medicine medicine;


    //onaj koji ocenjuje
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
