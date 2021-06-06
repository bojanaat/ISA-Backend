package com.example.backend.model;

import com.example.backend.utils.ExaminationStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class ExaminationPharmacist {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time_examination")
    private LocalTime startTimeExamination;

    @Column(name = "end_time_examination")
    private LocalTime endTimeExamination;

    @Column(name = "date_examination")
    private LocalDate dateExamination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shift_pharmacist_id")
    private ShiftPharmacist shiftPharmacist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private ExaminationStatus examinationStatus;

    private double price;
}
