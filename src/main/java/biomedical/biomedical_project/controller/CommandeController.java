package biomedical.biomedical_project.controller;

import biomedical.biomedical_project.dto.CommandeDto;
import biomedical.biomedical_project.services.EmailService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/commandes")
public class CommandeController {

    @Autowired
    private EmailService emailService;





    @PostMapping("/envoyer")
    public ResponseEntity<Map<String, String>> envoyerCommande(@RequestBody CommandeDto commande) {
        try {
            // Génération du PDF
            ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, pdfStream);
            document.open();

            // Ajouter les détails de la commande au PDF
            document.add(new Paragraph("Détails de la commande"));
            document.add(new Paragraph("Nom de la personne : " + commande.getNom()));
            document.add(new Paragraph("Nom de l'établissement : " + commande.getEtablissement()));
            document.add(new Paragraph("Date de la commande : " + commande.getDateCommande()));
            document.add(new Paragraph("Matériel commandé : " + commande.getMateriel()));
            document.add(new Paragraph("Caractéristiques : " + commande.getCaracteristiques()));
            document.add(new Paragraph("Quantité : " + commande.getQuantite()));
            document.add(new Paragraph("Local ou lieu : " + commande.getLocal()));
            document.add(new Paragraph("Numéro de téléphone : " + commande.getTelephone()));
            document.add(new Paragraph("Email : " + commande.getEmail()));
            document.close();

            // Envoi de l'email
            emailService.envoyerEmailAvecPDF(
                    "abdo.2000nira@gmail.com",
                    "Nouvelle commande",
                    "Veuillez trouver ci-joint la commande.",
                    pdfStream
            );

            // Retourner une réponse en JSON avec un message de succès
            Map<String, String> response = new HashMap<>();
            response.put("message", "Commande envoyée avec succès!");
            return ResponseEntity.ok(response);
        } catch (DocumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Collections.singletonMap("message", "Erreur lors de la génération du PDF"));
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Collections.singletonMap("message", "Erreur lors de l'envoi de l'email"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Collections.singletonMap("message", "Erreur lors de l'envoi de la commande"));
        }
    }



}

