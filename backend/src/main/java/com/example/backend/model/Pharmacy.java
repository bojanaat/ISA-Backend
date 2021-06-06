package com.example.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pharmacy {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private String description;

    private boolean deleted;

    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PharmacyAdmin> pharmacyAdmins = new ArrayList<>();

    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Supplier> pharmacySuppliers = new ArrayList<>();

    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dermatologist> pharmacyDermatologists = new ArrayList<>();

    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pharmacist> pharmacists;

    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Action> actions;

    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PharmacyMeds> pharmacyMeds;

    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complaint> complaints;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "subscribed_patients",
            joinColumns = @JoinColumn(name = "pharmacy_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"))
    private List<Patient> patients;

}

