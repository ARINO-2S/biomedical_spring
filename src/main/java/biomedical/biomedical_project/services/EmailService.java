package biomedical.biomedical_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.io.ByteArrayOutputStream;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void envoyerEmailAvecPDF(String to, String subject, String text, ByteArrayOutputStream pdfStream) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        // Ajout du PDF en pièce jointe
        helper.addAttachment("commande.pdf", new ByteArrayResource(pdfStream.toByteArray()));

        mailSender.send(message);
    }
}

