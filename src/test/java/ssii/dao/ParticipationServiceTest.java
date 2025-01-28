package ssii.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ssii.entity.Personne;
import ssii.entity.Projet;
import ssii.service.ParticipationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ParticipationServiceTest {

    @Autowired
    private ParticipationService participationService;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private ProjetRepository projetRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @BeforeEach
    void setup() {
        participationRepository.deleteAll();
        personneRepository.deleteAll();
        projetRepository.deleteAll();
    }

    @Test
    void testEnregistrerParticipation() {
        // Ajouter une personne et un projet
        var personne = new Personne("John", "Doe");
        personne = personneRepository.save(personne);

        var projet = new Projet("Projet 1");
        projet = projetRepository.save(projet);

        // Enregistrer une participation valide
        participationService.enregistrerParticipation(personne.getMatricule(), projet.getCode(), "Développeur", 50);

        var participations = participationRepository.findAll();
        assertEquals(1, participations.size());
    }

    @Test
    void testDoubleParticipation() {
        // Ajouter une personne et un projet
        var personne = new Personne("Jane", "Doe");
        personne = personneRepository.save(personne);

        var projet = new Projet("Projet 2");
        projet = projetRepository.save(projet);

        // Enregistrer une première participation
        participationService.enregistrerParticipation(personne.getMatricule(), projet.getCode(), "Manager", 30);

        // Tenter d'enregistrer une deuxième participation au même projet
        Personne finalPersonne = personne;
        Projet finalProjet = projet;
        assertThrows(IllegalStateException.class, () ->
                participationService.enregistrerParticipation(finalPersonne.getMatricule(), finalProjet.getCode(), "Manager", 20));
    }

    @Test
    void testEnregistrerParticipationSansPersonne() {
        // Ajouter un projet
        var projet = new Projet("Projet 3");
        projet = projetRepository.save(projet);

        // Essayer d'enregistrer une participation sans personne existante
        Projet finalProjet = projet;
        assertThrows(IllegalArgumentException.class, () ->
                participationService.enregistrerParticipation(9999, finalProjet.getCode(), "Chef", 40));
    }

    @Test
    void testEnregistrerParticipationSansProjet() {
        // Ajouter une personne
        var personne = new Personne("Alice", "Smith");
        personne = personneRepository.save(personne);

        // Essayer d'enregistrer une participation sans projet existant
        Personne finalPersonne = personne;
        assertThrows(IllegalArgumentException.class, () ->
                participationService.enregistrerParticipation(finalPersonne.getMatricule(), 9999, "Chef", 40));
    }
}
