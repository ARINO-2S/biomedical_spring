package biomedical.biomedical_project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Autoriser les requêtes CORS pour toutes les API à partir de votre frontend
        registry.addMapping("/api/**") // Autorise les requêtes vers les chemins qui commencent par /api
                .allowedOrigins("http://127.0.0.1:5500") // L'URL de votre frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Méthodes autorisées
                .allowedHeaders("*") // Autoriser tous les en-têtes
                .allowCredentials(true); // Permet l'envoi de cookies si nécessaire
    }
}
