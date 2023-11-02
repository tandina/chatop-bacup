# README

## Installation du projet Chatop

### Prérequis


- Java 17
- Maven
- PostgreSQL

### Étapes d'installation

1. Clonez le dépôt GitHub sur votre machine locale en utilisant la commande suivante : git clone https://github.com/tandina/chatop_backend


2. Naviguez jusqu'au répertoire du projet :
cd api


### Configuration de la base de données

1. Installez PostgreSQL si ce n'est pas déjà fait.
2. Créez une base de données nommée postgres.
3. Assurez-vous que les informations de connexion à la base de données dans le fichier application.properties correspondent à votre configuration PostgreSQL. Par défaut, les paramètres sont les suivants :


spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=root
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true
logging.level.root=DEBUG

### Documentation API


La documentation de l'API est accessible à l'adresse http://localhost:8080/swagger-ui/


### Postman


Une fois l'Api exéctuer, vous devez soit vous connecter sur la route : http://localhost:8080/api/auth/signing

à l'aide de l'user par défaut dans le body:

{
"email":"user@user.com",
"password":"user"
}


soit créer un compte sur la route : http://localhost:8080/api/auth/signup :

{
"email":"user2@user.com",
"name":"user2",
"password":"user2"
}


une fois connecter et le token récupérer vous allez pouvoir avoir accès à toutes les annonces en cours dans :

http://localhost:8080/api/auth/rental


l'annonce selon son id :
http://localhost:8080/api/auth/rental/1


la publication d'annonce en respectant le contenu du body :http://localhost:8080/api/auth/rental/create

{
"name": "house user 1 palm",
"surface": 7323,
"price": 342200,
"picture": "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg",
"description": "Lorem ipsum dolor fdfefe sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortormollis tortormollis tortormollis tortor",
"ownerId": 1
}


sur la route http://localhost:8080/api/auth/user/4

insérer le token dans l'onglet authorization et lancer la requête afin d'afficher toutes les informations concernant le user, après /user/{id} remplacer {id} par celui de l'user.


sur la route :http://localhost:8080/api/auth/message

l'envoie d'un message, en paramètre il faudra renseigner le userId qui correspond à celui de la personne qui envoie le message, rentalId qui correspond à l'annonce concerner par le message ainsi que "message" qui contiendra le contenue.

{
"userId": 4,
"rentalId": 6,
"message": "message de test"
}


sur la route http://localhost:8080/api/rental/2/update

vous allez pouvoir mettre à jour l'annonce déjà publier, renseigner le token et le body :

{
"name": "house 2",
"surface": 1223,
"price": 24235,
"picture": "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg",
"description": "Lorem ipsum ipsum ipsum ipsum dolor sit amet",
"owner_id": 1
}