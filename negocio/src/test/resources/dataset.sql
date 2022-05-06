INSERT INTO CIUDAD VALUES (1,'Bogota');
INSERT INTO CIUDAD VALUES (2,'Cali');
INSERT INTO CIUDAD VALUES (3,'Medellin');

INSERT INTO USUARIO VALUES ('1','juanenmanuel@gmail.com','Juan Enmanuel','123456','1');
INSERT INTO USUARIO VALUES ('2','camila@gmail.com','Camila','123456','2');
INSERT INTO USUARIO VALUES ('3','andres@gmail.com','Andres','123456','3');

INSERT INTO ADMINISTRADOR_HOTEL VALUES ('4','dayan@gmail.com','Dayan Mauricio','1');
INSERT INTO ADMINISTRADOR_HOTEL VALUES ('5','camilo@gmail.com','Camilo','2');
INSERT INTO ADMINISTRADOR_HOTEL VALUES ('6','andrea@gmail.com','Andrea','3');

INSERT INTO ADMINISTRADOR VALUES ('7','admin1@gmailcom','Felipe','123456');
INSERT INTO ADMINISTRADOR VALUES ('8','admin2@gmailcom','Juan','123456');
INSERT INTO ADMINISTRADOR VALUES ('9','admin3@gmailcom','Pedro','123456');

INSERT INTO TELEFONO VALUES (1,'Personal','1');
INSERT INTO TELEFONO VALUES (2,'Personal','2');
INSERT INTO TELEFONO VALUES (3,'Personal','3');

INSERT INTO HOTEL VALUES (1,'Carrera 19#10N-43','Hotel el mirador','4','3103591148','4',1);
INSERT INTO HOTEL VALUES (2,'Calle 23#12-33','Hotel el cielo','3','3103591149','5',2);
INSERT INTO HOTEL VALUES (3,'Carrera 10#56-17','Hotel decameron Bogota','5','3103591147','6',3);

INSERT INTO HABITACION VALUES (1,4,400000,1);
INSERT INTO HABITACION VALUES (2,3,300000,1);
INSERT INTO HABITACION VALUES (3,2,200000,1);

INSERT INTO HABITACION VALUES (4,4,400000,2);
INSERT INTO HABITACION VALUES (5,3,300000,2);
INSERT INTO HABITACION VALUES (6,2,200000,2);

INSERT INTO HABITACION VALUES (7,4,400000,3);
INSERT INTO HABITACION VALUES (8,3,300000,3);
INSERT INTO HABITACION VALUES (9,2,200000,3);

INSERT INTO CAMA VALUES (1,'Cama doble');
INSERT INTO CAMA VALUES (2,'Cama individual');
INSERT INTO CAMA VALUES (3,'Cama matrimonial');
INSERT INTO CAMA VALUES (4,'Cama queen');
INSERT INTO CAMA VALUES (5,'Cama king');

INSERT INTO CAMA_HABITACIONES VALUES (1,1);
INSERT INTO CAMA_HABITACIONES VALUES (1,1);
INSERT INTO CAMA_HABITACIONES VALUES (1,2);
INSERT INTO CAMA_HABITACIONES VALUES (2,2);
INSERT INTO CAMA_HABITACIONES VALUES (1,3);

INSERT INTO CAMA_HABITACIONES VALUES (4,4);
INSERT INTO CAMA_HABITACIONES VALUES (1,4);
INSERT INTO CAMA_HABITACIONES VALUES (1,5);
INSERT INTO CAMA_HABITACIONES VALUES (2,5);
INSERT INTO CAMA_HABITACIONES VALUES (1,6);

INSERT INTO CAMA_HABITACIONES VALUES (1,7);
INSERT INTO CAMA_HABITACIONES VALUES (1,7);
INSERT INTO CAMA_HABITACIONES VALUES (1,8);
INSERT INTO CAMA_HABITACIONES VALUES (2,8);
INSERT INTO CAMA_HABITACIONES VALUES (1,9);

INSERT INTO CARACTERISTICA VALUES (2,'Piscina');
INSERT INTO CARACTERISTICA VALUES (7,'Restaurante buffet');
INSERT INTO CARACTERISTICA VALUES (8,'Bar');
INSERT INTO CARACTERISTICA VALUES (9,'Gimnasio');
INSERT INTO CARACTERISTICA VALUES (10,'Spa');
INSERT INTO CARACTERISTICA VALUES (11,'Mascotas permitidas');
INSERT INTO CARACTERISTICA VALUES (12,'Parqueadero');
INSERT INTO CARACTERISTICA VALUES (13,'Restaurante');
INSERT INTO CARACTERISTICA VALUES (14,'Cancelacion gratuita');

INSERT INTO CARACTERISTICA VALUES (1,'WIFI gratis');
INSERT INTO CARACTERISTICA VALUES (3,'Aire acondicionado');
INSERT INTO CARACTERISTICA VALUES (4,'Cocina');
INSERT INTO CARACTERISTICA VALUES (6,'Caja fuerte');
INSERT INTO CARACTERISTICA VALUES (5,'TV');

INSERT INTO CARACTERISTICA_HOTELES VALUES (2,1);
INSERT INTO CARACTERISTICA_HOTELES VALUES (7,1);
INSERT INTO CARACTERISTICA_HOTELES VALUES (8,1);
INSERT INTO CARACTERISTICA_HOTELES VALUES (12,1);

INSERT INTO CARACTERISTICA_HOTELES VALUES (7,2);
INSERT INTO CARACTERISTICA_HOTELES VALUES (13,2);
INSERT INTO CARACTERISTICA_HOTELES VALUES (10,2);
INSERT INTO CARACTERISTICA_HOTELES VALUES (14,2);

INSERT INTO CARACTERISTICA_HOTELES VALUES (2,3);
INSERT INTO CARACTERISTICA_HOTELES VALUES (13,3);
INSERT INTO CARACTERISTICA_HOTELES VALUES (9,3);
INSERT INTO CARACTERISTICA_HOTELES VALUES (10,3);
INSERT INTO CARACTERISTICA_HOTELES VALUES (11,3);

INSERT INTO CARACTERISTICA_HABITACIONES VALUES (1,1);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (3,1);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (4,1);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (6,1);

INSERT INTO CARACTERISTICA_HABITACIONES VALUES (3,2);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (6,2);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (5,2);

INSERT INTO CARACTERISTICA_HABITACIONES VALUES (1,3);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (3,3);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (5,3);

INSERT INTO CARACTERISTICA_HABITACIONES VALUES (1,4);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (3,4);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (4,4);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (6,4);

INSERT INTO CARACTERISTICA_HABITACIONES VALUES (3,5);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (6,5);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (5,5);

INSERT INTO CARACTERISTICA_HABITACIONES VALUES (1,6);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (3,6);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (5,6);

INSERT INTO CARACTERISTICA_HABITACIONES VALUES (1,7);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (3,7);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (4,7);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (6,7);

INSERT INTO CARACTERISTICA_HABITACIONES VALUES (3,8);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (6,8);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (5,8);

INSERT INTO CARACTERISTICA_HABITACIONES VALUES (1,9);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (3,9);
INSERT INTO CARACTERISTICA_HABITACIONES VALUES (5,9);

INSERT INTO COMENTARIO VALUES (1,4,'Muy buen hotel',CAST('2018-01-01' AS DATE),1,1,'');
INSERT INTO COMENTARIO VALUES (2,5,'Muy buen hotel',CAST('2020-02-01' AS DATE),2,2,'');
INSERT INTO COMENTARIO VALUES (3,4,'Muy buen hotel',CAST('2020/02/02' AS DATE),3,3,'');

INSERT INTO RESERVA VALUES (1,2,'Aprobada',CAST('2018/01/02'AS DATE),CAST('2018/01/01'AS DATE),CAST('2018/01/01'AS DATE),400000,1);
INSERT INTO RESERVA VALUES (2,2,'Aprobada',CAST('2018/01/02'AS DATE),CAST('2018/01/01'AS DATE),CAST('2018/01/01'AS DATE),400000,2);
INSERT INTO RESERVA VALUES (3,2,'Aprobada',CAST('2018/01/02'AS DATE),CAST('2018/01/01'AS DATE),CAST('2018/01/01'AS DATE),400000,3);

INSERT INTO RESERVA_HABITACION VALUES (1,400000,1,1);
INSERT INTO RESERVA_HABITACION VALUES (2,400000,4,2);
INSERT INTO RESERVA_HABITACION VALUES (3,400000,7,3);

INSERT INTO VUELO VALUES ('1','Viva colombia','Aprobada',2,1);
INSERT INTO VUELO VALUES ('2','Viva air','Aprobada',3,2);
INSERT INTO VUELO VALUES ('3','Copa air lines','Aprobada',2,3);

INSERT INTO SILLA VALUES ('1','1',1,'1');
INSERT INTO SILLA VALUES ('2','2',2,'1');
INSERT INTO SILLA VALUES ('3','3',3,'1');

INSERT INTO SILLA VALUES ('4','1',1,'2');
INSERT INTO SILLA VALUES ('5','2',2,'2');
INSERT INTO SILLA VALUES ('6','3',3,'2');

INSERT INTO SILLA VALUES ('7','1',1,'3');
INSERT INTO SILLA VALUES ('8','2',2,'3');
INSERT INTO SILLA VALUES ('9','3',3,'3');

INSERT INTO RESERVA_SILLA VALUES (1,1,1,1);
INSERT INTO RESERVA_SILLA VALUES (2,2,1,2);
INSERT INTO RESERVA_SILLA VALUES (3,3,1,3);

SELECT * FROM telefono;