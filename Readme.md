**DOCUMENTATION API GROUPE 5**
-
***
Bienvenu sur le projet du **groupe 5**.
Vous trouverez dans le **dossier postman** une collection d'API que vous pouvez 
importter dans votre postman ou insomnia afin de tester les API
***
Pour pouvoir utiliser ses APIS, vous devez d'abord vous connecter appartir 
de l'API **signin(api/auth/signin)** au cas contraire creer un compte et 
confirmer votre email.

**NB: n'oubliez pas de creer votre base de donnée *consultingdb***
**Si vous n'avez pas postgres sur votre pc, une configuration docker a été ajouté au projet.
allez dans le dossier application properties et decommenter la config docker et commenter l'autre.**
***

**API**
-

**user** dans l'api represente id qui est retourné lors du login

**profil** dans l'api represente id qui est retourné lors de la creation du profil

***
**ENONCE**
-
***
ArchiTradeMe est une jeune startup voulant lancer sa plateforme de mise en relation entre des consultants
architectes et des clients
* Le consultants offrent une expertise sur différents domaines 
* Les clients proposent des missions

Use cases de la plateforme
* Inscription sur la plateforme des consultants & renseignement du profil : compétences, TJM, disponibilité &
modalités 
* Mise à jour du profil Consultant
* Ajout des offres disponibles Client
* Recherche des consultants en fonction de plusieurs critères
* Facturation des commissions
* Facturation des prestations
* Paiement des prestations

Recommandations
* Ne pas oublier de faire des schémas d’architecture
* Explorer le détail de l’Hexagonal Architecture en fonction du use case
* Ne pas hésitez à utiliser des stub de données ou des mocks pour les parties infrastructure (Base de données, Système de paiement, etc)
* Introduire des événements pour favoriser la modularité et la flexibilité

* Utiliser des Web APIs de type REST
