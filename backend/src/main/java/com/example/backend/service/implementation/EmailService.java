package com.example.backend.service.implementation;

import com.example.backend.config.EmailContext;
import com.example.backend.dto.request.AnswerOnComplaintRequest;
import com.example.backend.model.MedicineReservation;
import com.example.backend.model.Patient;
import com.example.backend.repository.IPatientRepository;
import com.example.backend.service.IEmailService;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class EmailService implements IEmailService {
    private final EmailContext _emailContext;
    private final IPatientRepository _iPatientRepository;

    public EmailService(EmailContext emailContext, IPatientRepository iPatientRepository) {
        _emailContext = emailContext;
        _iPatientRepository = iPatientRepository;
    }

    @Override
    public void approveRegistrationRequest(Patient patient) {
        String to = patient.getUser().getEmail();
        String subject = "Your registration request has been approved.";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", patient.getUser().getFirstName(), patient.getUser().getLastName()));
        context.setVariable("link", String.format("http://localhost:4200/opening-page/login-form/%s", patient.getId()));
        _emailContext.send(to, subject, "approvedRegistration", context);
    }

    @Override
    public void denyRegistrationRequest(Patient patient, String reason) {
        String to = patient.getUser().getEmail();
        String subject = "Your registration request has been denied.";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", patient.getUser().getFirstName(), patient.getUser().getLastName()));
        context.setVariable("reason", String.format("%s", reason));
        _emailContext.send(to, subject, "deniedRegistration", context);
    }

    @Override
    public void approveMedicineReservation(MedicineReservation savedReservation) {
        String to = savedReservation.getPatient().getUser().getEmail();
        String subject = "Your medicament reservation has been approved.";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", savedReservation.getPatient().getUser().getFirstName(), savedReservation.getPatient().getUser().getLastName()));
        context.setVariable("reservationId", String.format("%s", savedReservation.getId()));
        _emailContext.send(to, subject, "approvedMedicineReservation", context);
    }

    public void answerOnComplaint(AnswerOnComplaintRequest request) {
        Patient patient = _iPatientRepository.findOneById(request.getPatientId());
        String to = patient.getUser().getEmail();
        String subject = "Answer on complaint";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", patient.getUser().getFirstName(), patient.getUser().getLastName()));
        context.setVariable("text", String.format("%s", request.getText()));
        _emailContext.send(to, subject, "answeringOnComplaint", context);
    }
}
