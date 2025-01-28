package ssii.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssii.dao.ParticipationRepository;
import ssii.dao.PersonneRepository;
import ssii.dao.ProjetRepository;
import ssii.entity.Participation;
import ssii.entity.Personne;
import ssii.entity.Projet;

@Service
public class ParticipationService {

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private ProjetRepository projetRepository;

    public void enregistrerParticipation(Integer matricule, Integer codeProjet, String role, int pourcentage) {
        // Vérifier si la personne existe
        Personne personne = personneRepository.findById(matricule)
                .orElseThrow(() -> new IllegalArgumentException("Personne introuvable"));

        // Vérifier si le projet existe
        Projet projet = projetRepository.findById(codeProjet)
                .orElseThrow(() -> new IllegalArgumentException("Projet introuvable"));

        // Vérifier si une participation existe déjà pour la même personne et le même projet
        boolean alreadyParticipating = participationRepository.existsByPersonneAndProjet(personne, projet);
        if (alreadyParticipating) {
            throw new IllegalStateException("La personne participe déjà à ce projet.");
        }

        // Créer une nouvelle participation et l'enregistrer
        Participation participation = new Participation();
        participation.setRole(role);
        participation.setPourcentage(pourcentage);
        participation.setPersonne(personne);
        participation.setProjet(projet);

        participationRepository.save(participation);
    }
}
