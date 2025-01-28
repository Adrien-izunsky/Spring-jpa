package ssii.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssii.entity.Participation;
import ssii.entity.Personne;
import ssii.entity.Projet;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Integer> {
    boolean existsByPersonneAndProjet(Personne personne, Projet projet);  // Vérification avec les entités
}
