package com.example.backend.model;

import com.example.backend.utils.MedShape;
import com.example.backend.utils.MedicineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Medicine  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    private MedShape medShape;

    private MedicineType medicineType;

    private String ingredients;

    private String manufacturer;

    private boolean recipe;

    private String replacementCode;

    private String notes;

    @ManyToMany(mappedBy = "meds")
    private Collection<Patient> patients;
}
