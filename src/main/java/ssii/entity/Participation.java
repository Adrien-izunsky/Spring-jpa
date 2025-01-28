package ssii.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String role;

    private int pourcentage;

    @ManyToOne
    @JoinColumn(name = "personne_id", nullable = false)  // Personne ne doit pas être null
    private Personne personne;

    @ManyToOne
    @JoinColumn(name = "projet_id", nullable = false)  // Projet ne doit pas être null
    private Projet projet;
}
