# Projet Infotraffic API

---

## Description du Projet

Ce projet est une API RESTful développée avec **Spring Boot 3** en Java, conçue pour gérer les informations relatives aux trains, aux sièges disponibles, aux itinéraires et aux plannings. Il intègre un système de **sécurité robuste basé sur JWT (JSON Web Tokens)** et **Spring Security**, permettant l'authentification des utilisateurs et l'autorisation basée sur les rôles (RBAC).

L'API est documentée via **Swagger UI (OpenAPI 3)**, offrant une interface interactive pour explorer et tester les endpoints.

---

## Fonctionnalités Clés

* **Gestion des trains :** CRUD (Création, Lecture, Mise à jour, Suppression) pour les entités `TrainSeatsAvailable`, `TrainPlanned`, et `TrainRoute`.
* **API RESTful :** Implémentation conforme aux principes REST pour une interaction facile avec les clients.
* **Pagination et Filtrage :** Capacités avancées pour les requêtes de lecture afin de gérer de grands ensembles de données.
* **Validation des données :** Utilisation de Jakarta Bean Validation pour assurer l'intégrité des données entrantes.
* **Gestion des exceptions globale :** Centralisation de la gestion des erreurs pour des réponses API cohérentes.
* **Sécurité JWT :**
    * **Authentification :** Les utilisateurs se connectent pour obtenir un token JWT.
    * **Autorisation basée sur les rôles (RBAC) :** Contrôle d'accès granulaire aux endpoints basé sur les rôles `ADMIN` et `USER` via `@PreAuthorize`.
    * **Stateless :** La gestion des sessions est déléguée aux tokens JWT pour une meilleure scalabilité.
* **Documentation API interactive :** Grâce à Springdoc OpenAPI (Swagger UI), l'API est entièrement documentée et peut être testée directement depuis un navigateur.

---

## Technologies Utilisées

* **Spring Boot 3.x**
* **Java 17** (ou version ultérieure)
* **Spring Security** (pour l'authentification et l'autorisation)
* **JJWT** (pour la génération et la validation des JSON Web Tokens)
* **Spring Data JPA** (pour l'accès aux données)
* **Lombok** (pour réduire le code boilerplate)
* **Jakarta Validation** (pour la validation des DTOs)
* **Springdoc OpenAPI / Swagger UI** (pour la documentation API)
* **Maven** (pour la gestion des dépendances et du build)

---

## Configuration Requise

* **JDK 17** (Java Development Kit) ou version supérieure.
* **Maven 3.6+**.
* Un IDE comme **VS Code** (avec l'extension Java), IntelliJ IDEA ou Eclipse.
* Pour la base de données, l'application utilise par défaut une base de données **H2 en mémoire**, ce qui est parfait pour le développement et les tests. Aucune configuration externe n'est nécessaire pour démarrer.

---

## Démarrage du Projet

Suivez ces étapes pour démarrer l'application Infotraffic API sur votre machine locale.

### 1. Cloner le Dépôt

```bash
git clone https://github.com/bugtronik/stg_infotraffic.git
cd stg_infotraffic
