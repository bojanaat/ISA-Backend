package com.example.backend.service.implementation;

import com.example.backend.config.EmailContext;
import com.example.backend.model.Patient;
import com.example.backend.service.IEmailService;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class EmailService implements IEmailService {
    private final EmailContext _emailContext;

    public EmailService(EmailContext emailContext) {
        _emailContext = emailContext;
    }

    @Override
    public void approveRegistrationRequest(Patient patient) {
        String to = patient.getUser().getEmail();
        String subject = "Your registration request has been approved.";
        Context context = new Context();
        context.setVariable("name", String.format("%s %s", patient.getUser().getFirstName(), patient.getUser().getLastName()));
        context.setVariable("link", String.format("http://localhost:4200/auth/login/%s/patient", patient.getId()));
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
}
