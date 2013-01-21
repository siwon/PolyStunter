CREATE DATABASE IF NOT EXISTS polystunter DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE polystunter;

CREATE TABLE USER (
	idUser INT AUTO_INCREMENT NOT NULL,
	loginUser VARCHAR(32),
	passwordUser VARCHAR(64),
	mailUser VARCHAR(150),
	statusUser ENUM("CUSTOMER", "SELLER", "DELIVERYMAN"),
	PRIMARY KEY (idUser)) ENGINE=InnoDB;	

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
	locationProduct VARCHAR(100),
	photoProduct VARCHAR(32),
	PRIMARY KEY (idProduct, idSeller),
	FOREIGN KEY (idSeller) REFERENCES USER(idUser) ON DELETE CASCADE) ENGINE=InnoDB;
	
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
	

