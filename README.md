# Portfolio Backend

Backend du portfolio personnel d'Armand, développé en Spring Boot. Expose une API REST sécurisée par JWT pour gérer les projets, compétences, et messages de contact d'un portfolio de développeur orienté cybersécurité.

## Stack technique

- **Langage** : Java 21
- **Framework** : Spring Boot 4.1.0
- **Base de données** : PostgreSQL
- **ORM** : Spring Data JPA / Hibernate (schéma généré automatiquement via `ddl-auto: update`)
- **Sécurité** : Spring Security 7.1 + JWT (bibliothèque [jjwt](https://github.com/jwtk/jjwt) 0.12.6)
- **Validation** : Spring Validation (Jakarta)
- **Stockage des fichiers** : système de fichiers local, exposé via `/images/**`
- **Build** : Maven

## Architecture

Architecture en couches classique :

```
Controller → Service → Repository → Base de données
```

Avec une séparation stricte entre les entités JPA (`model/`) et ce qui est exposé par l'API (`dto/`), convertis par des classes dédiées (`mapper/`). Les entités JPA ne sont jamais renvoyées directement par un endpoint.

```
src/main/java/com/armand/portfolio_backend/
├── config/        → Sécurité, CORS, stockage de fichiers
├── controller/     → Endpoints REST
├── service/        → Logique métier (+ service/impl)
├── repository/     → Accès aux données (Spring Data JPA)
├── model/          → Entités JPA
├── dto/            → Objets d'entrée/sortie de l'API (request/response)
├── mapper/         → Conversion Entité ↔ DTO
├── exception/      → Gestion centralisée des erreurs
├── security/       → JWT (génération, filtre, UserDetailsService)
└── util/           → Utilitaires (stockage de fichiers)
```

## Prérequis

- Java 21 (JDK)
- Maven (ou le wrapper Maven inclus)
- PostgreSQL installé et lancé en local
- IntelliJ IDEA (ou tout IDE Java)

## Installation et lancement

### 1. Cloner le projet

```bash
git clone <url-du-repo>
cd portfolio_backend
```

### 2. Créer la base de données

```sql
CREATE DATABASE portfolio_bd;
```

### 3. Configurer les variables d'environnement

Le mot de passe PostgreSQL n'est jamais en dur dans le code. Définis la variable d'environnement `DB_PASSWORD` avant de lancer l'application.

**Dans IntelliJ** : Edit Configurations → Environment variables → `DB_PASSWORD=ton_mot_de_passe`

**En ligne de commande (Windows PowerShell)** :
```powershell
$env:DB_PASSWORD="ton_mot_de_passe"
```

**En ligne de commande (Linux/Mac)** :
```bash
export DB_PASSWORD=ton_mot_de_passe
```

### 4. Créer le compte administrateur

Aucune route d'inscription publique n'existe (volontaire, portfolio à un seul utilisateur). Génère un hash BCrypt de ton mot de passe directement dans ton environnement Java, puis insère le compte en base :

```sql
INSERT INTO users (username, password, role)
VALUES ('admin', '<hash_bcrypt_genere>', 'ADMIN');
```

### 5. Lancer l'application

Depuis IntelliJ : clique sur le bouton ▶️ à côté de `PortfolioBackendApplication`.

Ou en ligne de commande :
```bash
./mvnw spring-boot:run
```

L'API est accessible sur `http://localhost:8080`.

## Configuration

La configuration se trouve dans `src/main/resources/application.yaml` :

| Propriété | Description |
|---|---|
| `spring.datasource.url` | URL de connexion PostgreSQL |
| `spring.jpa.hibernate.ddl-auto` | `update` — Hibernate génère/synchronise le schéma automatiquement |
| `app.upload.dir` | Dossier de stockage des images uploadées |
| `app.jwt.secret` | Clé secrète de signature des tokens JWT |
| `app.jwt.expiration-ms` | Durée de validité d'un token (24h par défaut) |
| `server.port` | Port d'écoute du serveur (8080 par défaut) |

## Authentification

L'API utilise un système d'authentification par token JWT, sans session côté serveur.

1. `POST /api/auth/login` avec `username` et `password` → retourne un token
2. Ce token doit être envoyé dans l'en-tête `Authorization: Bearer <token>` pour toutes les routes protégées

## Endpoints principaux

| Méthode | Route | Accès | Description |
|---|---|---|---|
| POST | `/api/auth/login` | Public | Connexion |
| GET | `/api/projects` | Public | Liste les projets |
| POST | `/api/projects` | Authentifié | Crée un projet |
| PUT | `/api/projects/{id}` | Authentifié | Modifie un projet |
| DELETE | `/api/projects/{id}` | Authentifié | Supprime un projet |
| GET | `/api/projects/{id}/images` | Public | Images d'un projet |
| POST | `/api/projects/{id}/images` | Authentifié | Upload une image |
| GET | `/api/skills` | Public | Liste les compétences |
| POST | `/api/skills` | Authentifié | Crée une compétence |
| POST | `/api/contacts` | Public | Envoie un message |
| GET | `/api/contacts` | Authentifié | Liste les messages reçus |

La documentation complète des endpoints est disponible dans `Portfolio_Backend_Documentation.docx`.

## Tests

Un guide de tests manuels pas à pas est disponible (`Guide_Tests_Postman.docx`), couvrant 23 scénarios incluant les cas d'erreur (401, 404, 400) pour valider la sécurité et la validation des données.

Aucun test automatisé (JUnit) n'est inclus à ce stade du projet.

## Sécurité

- Mots de passe hachés en BCrypt, jamais stockés en clair
- Routes d'écriture protégées par JWT, routes de lecture publiques (sauf messages de contact)
- Upload de fichiers : validation du type MIME et de la taille, renommage systématique (UUID) pour éviter le path traversal
- Aucune stack trace exposée au client : toutes les erreurs passent par un gestionnaire centralisé (`GlobalExceptionHandler`)

## Pistes d'amélioration

- Validation du contenu réel des fichiers uploadés (magic number) avec Apache Tika
- Externalisation de la clé secrète JWT en variable d'environnement
- Ajout de tests automatisés (JUnit)
- Pagination des listes si leur volume augmente

## Frontend associé

Ce backend est conçu pour être consommé par un frontend Angular (CORS configuré pour `http://localhost:4200` en développement).

## Auteur

Armand — étudiant ingénieur, spécialisation cybersécurité.
