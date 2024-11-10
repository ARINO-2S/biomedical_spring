package biomedical.biomedical_project;

import biomedical.biomedical_project.entities.Client;
import biomedical.biomedical_project.entities.Equipement;
import biomedical.biomedical_project.entities.Fournisseur;
import biomedical.biomedical_project.repositories.ClientRepository;
import biomedical.biomedical_project.repositories.EquipementRepository;
import biomedical.biomedical_project.repositories.FournisseurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class BiomedicalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiomedicalProjectApplication.class, args);
	}

	//@Bean
	@Order(1)
	CommandLineRunner commandLineRunner(ClientRepository clientRepository){
		return args -> {
			Client client = Client.builder().nom("arino").email("arino@gmail.com").build();

			clientRepository.save(client);
		};
	}
	//@Bean
	@Order(2)
	CommandLineRunner commandLineRunner2(EquipementRepository equipementRepository, ClientRepository clientRepository){
		return args -> {
			Client client = clientRepository.findById(1).get();
			Equipement equipement1 = Equipement.builder().designation("respirateur").sn("361A").marque("DRAGER").build();
			equipement1.setNomClient(client.getNom());
			equipementRepository.save(equipement1);
		};
	}

	//@Bean
	@Order(3)
	CommandLineRunner commandLineRunner3(FournisseurRepository fournisseurRepository,EquipementRepository equipementRepository, ClientRepository clientRepository){
		return args -> {
			Fournisseur fournisseur = Fournisseur.builder().nom("DRAGR").email("f@gmail.com").build();
			fournisseurRepository.save(fournisseur);
			Fournisseur fournisseur2 = Fournisseur.builder().nom("GE").email("j@gmail.com").build();
			List<Equipement> equipements = equipementRepository.findAll();
			Equipement equipement1 = equipements.get(0);
			equipement1.setFournisseur(fournisseur);
			equipementRepository.save(equipement1);
			fournisseur.setEquipements(List.of(equipement1));
			fournisseurRepository.save(fournisseur);
			fournisseurRepository.findAll().forEach(e-> System.out.println(e.getEquipements()));
		};
	}
}
