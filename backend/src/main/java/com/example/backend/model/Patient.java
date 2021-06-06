package com.example.backend.model;

import com.example.backend.utils.RequestType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private RequestType requestType;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Allergies> allergies;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedicineReservation> medicineReservations;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complaint> complaints;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private LoyaltyProgram loyaltyProgram;

    @ManyToMany(mappedBy = "patients")
    private Collection<Pharmacy> pharmacies;


}

