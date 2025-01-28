-- Insertion des données pour la table Personne
INSERT INTO personne (nom, prenom) VALUES ('John', 'Doe');
INSERT INTO personne (nom, prenom) VALUES ('Jane', 'Doe');
INSERT INTO personne (nom, prenom) VALUES ('Alice', 'Smith');
INSERT INTO personne (nom, prenom) VALUES ('Bob', 'Brown');

-- Insertion des données pour la table Projet
INSERT INTO projet (nom) VALUES ('Projet 1');
INSERT INTO projet (nom) VALUES ('Projet 2');
INSERT INTO projet (nom) VALUES ('Projet 3');

-- Insertion des données pour la table Participation
-- On fait attention à l'ordre, car les IDs sont générés automatiquement
-- Supposons que les IDs des personnes et projets sont 1, 2, 3, etc.
-- Ici, on lie chaque personne à un projet avec un rôle et un pourcentage

INSERT INTO participation (role, pourcentage, personne_id, projet_id) VALUES
                                                                          ('Développeur', 50, 1, 1),
                                                                          ('Manager', 30, 2, 2),
                                                                          ('Designer', 40, 3, 3),
                                                                          ('Analyste', 60, 4, 1);
