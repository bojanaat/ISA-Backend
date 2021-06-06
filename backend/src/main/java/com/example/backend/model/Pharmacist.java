package com.example.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pharmacist {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    @OneToMany(mappedBy = "pharmacist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings;

    @OneToMany(mappedBy = "pharmacist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShiftPharmacist> shiftPharmacists;

    @OneToMany(mappedBy = "pharmacist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complaint> complaints;

}
