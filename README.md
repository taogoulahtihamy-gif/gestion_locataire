# Gestion des Locations JEE - Projet complet

Projet Maven WAR en Jakarta EE / Tomcat / JSP / Servlets / JPA-Hibernate / PostgreSQL.

## Fonctionnalités couvertes
- Authentification
- Tableau de bord
- Gestion des immeubles
- Gestion des unités
- Gestion des locataires
- Gestion des contrats
- Gestion des paiements
- Génération de reçu imprimable
- Gestion des utilisateurs

## Compte par défaut
- **Email** : admin@admin.com
- **Mot de passe** : admin123

## Pré-requis
- JDK 17+
- Maven
- PostgreSQL
- Tomcat 10
- IntelliJ IDEA / Eclipse

## Configuration BD
Créer la base :
```sql
CREATE DATABASE gestion_locations;
```

Adapter si besoin `src/main/resources/META-INF/persistence.xml`.

## Lancement
1. Ouvrir le projet dans IntelliJ
2. Laisser Maven télécharger les dépendances
3. Déployer en WAR sur Tomcat 10
4. URL : `http://localhost:8080/gestion-locations/login`

## Note
Le projet est volontairement simple et académique pour rester facile à déployer dans un contexte d'examen.
