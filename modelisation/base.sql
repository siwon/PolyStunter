CREATE DATABASE IF NOT EXISTS polystunter DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE polystunter;

CREATE TABLE USER (
	idUser INT AUTO_INCREMENT NOT NULL,
	loginUser VARCHAR(32),
	passwordUser VARCHAR(64),
	mailUser VARCHAR(150),
	statusUser ENUM("CUSTOMER", "SELLER", "DELIVERYMAN", "ADMIN"),
	PRIMARY KEY (idUser)) ENGINE=InnoDB;
	
INSERT INTO `user` (`idUser`, `loginUser`, `passwordUser`, `mailUser`, `statusUser`) VALUES
(1, 'client', '948fe603f61dc036b5c596dc09fe3ce3f3d30dc90f024c85f3c82db2ccab679d', 'client', 'CUSTOMER'),
(2, 'livreur', '21bef81c50399dd680bcc9961659fa67ff8c2a6cd551baa22f5fe5d431c2f5fd', 'livreur', 'DELIVERYMAN'),
(3, 'vendeur', '3fe6dd5dd172cef095720595fe45bac03bdfd9844d68a5b7dfc20320437aba92', 'vendeur', 'SELLER'),
(4, 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'admin', 'ADMIN');

CREATE TABLE WAREHOUSE (
	idWarehouse INT AUTO_INCREMENT NOT NULL,
	idSeller INT,
	nameWarehouse VARCHAR(32),
	streetWarehouse VARCHAR(64),
	zipCodeWarehouse INT,
	cityWarehouse VARCHAR(32),
	PRIMARY KEY (idWarehouse),
	FOREIGN KEY (idSeller) REFERENCES USER(idUser) ON DELETE CASCADE) ENGINE=InnoDB;
	
CREATE TABLE ORDERS (
	idOrder INT AUTO_INCREMENT NOT NULL,
	idCustomer INT,
	forwardingAddressOrder VARCHAR(32),
	deliveryDateOrder TIMESTAMP,
	costOrder DOUBLE,
	statusOrder ENUM("IN_PREPARATION", "READY", "DELIVERED"),
	PRIMARY KEY (idOrder, idCustomer),
	FOREIGN KEY (idCustomer) REFERENCES USER(idUser)) ENGINE=InnoDB;
	
CREATE TABLE PRODUCT (
	idProduct INT AUTO_INCREMENT NOT NULL,
	idSeller INT,
	priceProduct DOUBLE,
	nameProduct VARCHAR(32),
	referenceProduct VARCHAR(32),
	quantityProduct INT,
	informationProduct TEXT,
	idWarehouse INT,
	photoProduct VARCHAR(32),
	PRIMARY KEY (idProduct, idSeller),
	FOREIGN KEY (idSeller) REFERENCES USER(idUser) ON DELETE CASCADE,
	FOREIGN KEY (idWarehouse) REFERENCES WAREHOUSE(idWarehouse) ON DELETE CASCADE) ENGINE=InnoDB;
	
CREATE TABLE NOTIFICATION (
	idNotification INT AUTO_INCREMENT NOT NULL,
	idSender INT,
	typeNotification ENUM("BUILDING_SITE", "ACCIDENT", "DEMONSTRATION", "OTHER"),
	informationNotification TEXT,
	locationNotification VARCHAR(100),
	PRIMARY KEY (idNotification, idSender),
	FOREIGN KEY (idSender) REFERENCES USER(idUser)  ON DELETE CASCADE) ENGINE=InnoDB;
	
CREATE TABLE PRODUCTSINBASKET (
	idBasket INT,
	idProduct INT,
	quantityToOrder INT,
	PRIMARY KEY (idBasket, idProduct),
	FOREIGN KEY (idProduct) REFERENCES PRODUCT(idProduct)  ON DELETE CASCADE,
	FOREIGN KEY (idBasket) REFERENCES USER(idUser)  ON DELETE CASCADE) ENGINE=InnoDB;
	
CREATE TABLE ORDEREDPRODUCTS (
	idOrder INT,
	idProduct INT,
	quantityOrdered INT,
	PRIMARY KEY (idOrder, idProduct),
	FOREIGN KEY (idOrder) REFERENCES ORDERS(idOrder)  ON DELETE CASCADE,
	FOREIGN KEY (idProduct) REFERENCES PRODUCT(idProduct)) ENGINE=InnoDB;
	

