
CREATE DATABASE BANCO_DB;
USE BANCO_DB; 


CREATE TABLE `persona` (
  `Dni` varchar(8) NOT NULL,
  `Cuil` varchar(11) NOT NULL,
  `Nombre` varchar(45) NOT NULL,
  `Apellido` varchar(45) NOT NULL,
  `Sexo` varchar(45) NOT NULL,
  `Nacionalidad` varchar(45) NOT NULL,
  `FechaDeNacimiento` date NOT NULL,
  `Direccion` varchar(45) NOT NULL,
  `Localidad` varchar(45) NOT NULL,
  `Provincia` varchar(45) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Telefono` varchar(45) NOT NULL,
  PRIMARY KEY (`Dni`)
);

CREATE TABLE `cliente` (
  `NdeCliente` int NOT NULL AUTO_INCREMENT,
  `Dni` varchar(8) NOT NULL,
  `Estado` tinyint(4) NOT NULL,
  PRIMARY KEY (`NdeCliente`),
  FOREIGN KEY(`Dni`) REFERENCES persona (`Dni`) 
) ;

CREATE TABLE `cuenta` (
  `NdeCuenta` int NOT NULL AUTO_INCREMENT,
  `NdeCliente` int NOT NULL,
  `Cbu` varchar(45) NOT NULL,
  `TipoDeCuenta` varchar(45) NOT NULL,
  `FechaDeCreacion` date NOT NULL,
  `Saldo` decimal(10,0) NOT NULL,
  `Estado` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`NdeCuenta`),
  FOREIGN KEY (`NdeCliente`) REFERENCES cliente (`NdeCliente`)
) ;

CREATE TABLE `movimiento` (
  `IdMovimiento` int NOT NULL AUTO_INCREMENT,
  `NdeCuenta` int NOT NULL,
  `TipoDeMovimiento` varchar(45) NOT NULL,
  `Fecha` date NOT NULL,
  `Detalle` varchar(45) NOT NULL,
  `Importe` decimal(10,0) NOT NULL,
  PRIMARY KEY (`IdMovimiento`)
) ;
CREATE TABLE `cuotas` (
  `NdePrestamo` int NOT NULL AUTO_INCREMENT,
  `NdeCuota` int NOT NULL,
  `IdMovimiento` int NOT NULL,
  PRIMARY KEY (`NdePrestamo`),
  FOREIGN KEY (`IdMovimiento`) REFERENCES movimiento (`IdMovimiento`)
) ;


CREATE TABLE `prestamo` (
  `NdePrestamo` int NOT NULL AUTO_INCREMENT,
  `NdeCliente` int NOT NULL,
  `NdeCuenta` int NOT NULL,
  `Fecha` date NOT NULL,
  `ImporteSolicitado` decimal(10,0) NOT NULL,
  `ImporteAPagar` decimal(10,0) NOT NULL,
  `Plazo` int NOT NULL,
  `Monto` decimal(10,0) NOT NULL,
  `Estado` varchar(45) NOT NULL,
  `CuotasPagas` int NOT NULL DEFAULT '0',
  
  PRIMARY KEY (`NdePrestamo`),
  FOREIGN KEY (`NdeCliente`) REFERENCES cliente (`NdeCliente`),
  FOREIGN KEY (`NdeCuenta`) REFERENCES cuenta (`NdeCuenta`)
);

CREATE TABLE `solicitud` (
  `NdeSolicitud` int NOT NULL AUTO_INCREMENT,
  `NdeCliente` int NOT NULL,
  `NdeCuenta` int NOT NULL,
  `Fecha` date NOT NULL,
  `ImporteSolicitado` decimal(10,0) NOT NULL,
  `ImporteAPagar` decimal(10,0) NOT NULL,
  `Plazo` int NOT NULL,
  `Monto` decimal(10,0) NOT NULL,
  `Estado` varchar(45) NOT NULL,
  PRIMARY KEY (`NdeSolicitud`),
  FOREIGN KEY (`NdeCliente`) REFERENCES cliente (`NdeCliente`),
  FOREIGN KEY (`NdeCuenta`) REFERENCES cuenta (`NdeCuenta`)
);

CREATE TABLE `transferencia` (
  `IdMovimientoSalida` int NOT NULL,
  `IdMovimientoEntrada` int NOT NULL,
  PRIMARY KEY (`IdMovimientoSalida`)
) ;

CREATE TABLE `usuario` (
  `Usuario` varchar(16) NOT NULL,
  `Dni` varchar(8) NOT NULL,
  `Contrasenia` varchar(16) NOT NULL,
  `EsAdmin` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Usuario`),
  FOREIGN KEY(`Dni`) REFERENCES persona (`Dni`) 
) ;

-- ------------------------INSERTS--------------------
#PERSONA
INSERT INTO PERSONA (Dni, Cuil, Nombre, Apellido, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, Email, Telefono) 
VALUES ("34093682", "20340936825", "Santiago", "Valente", "Masculino", "Argentino","1981-01-12", "Peirano 669", "Garin", "Buenos Aires", "sanvalente@gmail.com", "1129834683" );

INSERT INTO PERSONA (Dni, Cuil, Nombre, Apellido, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, Email, Telefono) 
VALUES ("35205488", "20352054885", "Sergio", "Martinez", "Masculino", "Español","1995-02-11", "Pueyrredon 2980", "San Martin", "Buenos Aires", "sergiomartinez@gmail.com", "1187903492" );

INSERT INTO PERSONA (Dni, Cuil, Nombre, Apellido, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, Email, Telefono) 
VALUES ("32152654", "27321526544", "Luciana", "Gutierrez", "Femenino", "Uruguaya","1992-04-15", "Libertad 55", "San Carlos de Bariloche", "Rio Negro", "lugutierrez@gmail.com", "1158930123" );

INSERT INTO PERSONA (Dni, Cuil, Nombre, Apellido, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, Email, Telefono) 
VALUES ("27845123", "27278451234", "Agustina", "Diaz", "Femenino", "Argentina","1979-07-12", "Chaco 1276", "San Isidro", "Buenos Aires", "agusdiaz@gmail.com", "1189460212" );

INSERT INTO PERSONA (Dni, Cuil, Nombre, Apellido, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, Email, Telefono) 
VALUES ("45854114", "20458541145", "Ricardo", "Lavolpe", "Masculino", "Argentino","1998-10-05", "Roma 417", "Tigre", "Mendoza", "richardlavolpe@gmail.com", "1190458721" );

INSERT INTO PERSONA (Dni, Cuil, Nombre, Apellido, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, Email, Telefono) 
VALUES ("42098253", "20420982535", "Mariano", "Gimenez", "Masculino", "Argentino","1992-11-15", "Mitre 1352", "Munro", "Buenos Aires", "marianogi@gmail.com", "1132567345" );

INSERT INTO PERSONA (Dni, Cuil, Nombre, Apellido, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, Email, Telefono) 
VALUES ("23092178", "20230921785", "Maria", "Riedel", "Femenino", "Argentina","1977-08-23", "Arias 980", "Olivos", "Buenos Aires", "mriedel@gmail.com", "1143567902" );

INSERT INTO PERSONA (Dni, Cuil, Nombre, Apellido, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, Email, Telefono) 
VALUES ("41234568", "20412345685", "Juan", "Pérez", "Masculino", "Argentino", "1990-08-22", "Formosa 123", "Liniers", "Buenos Aires", "juanperez@gmail.com", "1122334455");

INSERT INTO PERSONA (Dni, Cuil, Nombre, Apellido, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, Email, Telefono) 
VALUES ("45092874", "20450928745", "Jimena", "Velazquez", "Femenino", "Argentina", "2002-03-13", "America 456", "Garin", "Buenos Aires", "jimevelazquez@gmail.com", "1143098132");

INSERT INTO PERSONA (Dni, Cuil, Nombre, Apellido, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, Email, Telefono) 
VALUES ("41905672", "20419056725", "Carlos", "García", "Masculino", "Argentino", "1988-12-18", "Belgrano 723", "San Fernando", "Buenos Aires", "carlosgarcia@gmail.com", "116781234");

INSERT INTO PERSONA (Dni, Cuil, Nombre, Apellido, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, Email, Telefono) 
VALUES ("87654321", "30387654321", "Ana", "Martínez", "Femenino", "Chilena", "1995-03-25", "Parana 3092", "Victoria", "Buenos Aires", "anamartinez@gmail.com", "119654321");

INSERT INTO PERSONA (Dni, Cuil, Nombre, Apellido, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, Email, Telefono) 
VALUES ("20908177", "20209081772", "Pedro", "Rodríguez", "Masculino", "Uruguayo", "1980-07-14", "Av. Rivadavia 789", "Tigre", "Buenos Aires", "pedrorodriguez@gmail.com", "1122334455");

INSERT INTO PERSONA (Dni, Cuil, Nombre, Apellido, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, Email, Telefono) 
VALUES ("37589034", "20375890345", "Laura", "Sánchez", "Femenino", "Argentina", "1993-09-03", "Carrera 567", "Martinez", "Buenos Aires", "laurasanchez@gmail.com", "117654321");

INSERT INTO PERSONA (Dni, Cuil, Nombre, Apellido, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, Email, Telefono) 
VALUES ("45890327", "20458903275", "Francisco", "Hernández", "Masculino", "Argentino", "1987-11-28", "Formosa 987", "Escobar", "Buenos Aires", "franciscohernandez@gmail.com", "1154332211");

INSERT INTO PERSONA (Dni, Cuil, Nombre, Apellido, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, Email, Telefono) 
VALUES ("34987109", "20349871095", "Elena", "Gómez", "Femenino", "Argentina", "1998-02-12", "Av. Sarmiento 654", "Pilar", "Buenos Aires", "elenagomez@gmail.com", "1122334455");

INSERT INTO PERSONA (Dni, Cuil, Nombre, Apellido, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, Email, Telefono) 
VALUES ("43758923", "20437589235", "Martín", "Fernández", "Masculino", "Argentino", "1994-06-30", "Pringles 789", "Lujan", "Buenos Aires", "martinfernandez@gmail.com", "119654321");

INSERT INTO PERSONA (Dni, Cuil, Nombre, Apellido, Sexo, Nacionalidad, FechaDeNacimiento, Direccion, Localidad, Provincia, Email, Telefono) 
VALUES ("32874403", "20328744034", "Cecilia", "Ramírez", "Femenino", "Argentina", "1991-01-05", "Italia 805", "Del Viso", "Buenos Aires", "ceciliaramirez@gmail.com", "1154432211");

#CLIENTE
/*INSERT INTO CLIENTE (Dni)
VALUES ("34093682");*/ -- es admin

INSERT INTO CLIENTE (Dni, Estado)
VALUES ("35205488", true);

INSERT INTO CLIENTE (Dni, Estado)
VALUES ("32152654", true);

INSERT INTO CLIENTE (Dni, Estado)
VALUES ("27845123", true);

INSERT INTO CLIENTE (Dni, Estado)
VALUES ("45854114", true);

INSERT INTO CLIENTE (Dni, Estado)
VALUES ("42098253", true);

INSERT INTO CLIENTE (Dni, Estado)
VALUES ("23092178", true);

INSERT INTO CLIENTE (Dni, Estado)
VALUES ("41234568", true);

INSERT INTO CLIENTE (Dni, Estado)
VALUES ("45092874", true);

INSERT INTO CLIENTE (Dni, Estado)
VALUES ("41905672", true);

INSERT INTO CLIENTE (Dni, Estado)
VALUES ("87654321", true);

INSERT INTO CLIENTE (Dni, Estado)
VALUES ("20908177", true);

INSERT INTO CLIENTE (Dni, Estado)
VALUES ("37589034", true);

INSERT INTO CLIENTE (Dni, Estado)
VALUES ("45890327", true);

INSERT INTO CLIENTE (Dni, Estado)
VALUES ("34987109", true);

INSERT INTO CLIENTE (Dni, Estado)
VALUES ("43758923", true);

INSERT INTO CLIENTE (Dni, Estado)
VALUES ("32874403", true);
/*
ALTER TABLE CLIENTE 
ADD Estado boolean not null;
*/
UPDATE CLIENTE SET Estado=true  WHERE NdeCliente=1 ;
UPDATE CLIENTE SET Estado=true  WHERE NdeCliente=2 ;
UPDATE CLIENTE SET Estado=true  WHERE NdeCliente=3 ;
UPDATE CLIENTE SET Estado=true  WHERE NdeCliente=4 ;

UPDATE CUENTA SET Estado=true  WHERE NdeCuenta=1 ;
UPDATE CUENTA SET Estado=true  WHERE NdeCuenta=2 ;
UPDATE CUENTA SET Estado=true  WHERE NdeCuenta=3 ;
UPDATE CUENTA SET Estado=true  WHERE NdeCuenta=4 ;
UPDATE CUENTA SET Estado=true  WHERE NdeCuenta=5 ;
UPDATE CUENTA SET Estado=true  WHERE NdeCuenta=6 ;

#USUARIO
INSERT INTO USUARIO (Usuario, Dni, Contrasenia, EsAdmin)
VALUES ("santiago","34093682","123",1); -- es admin

INSERT INTO USUARIO (Usuario, Dni, Contrasenia, EsAdmin)
VALUES ("sergio","35205488","123",0);

INSERT INTO USUARIO (Usuario, Dni, Contrasenia, EsAdmin)
VALUES ("luciana","32152654","123",0);

INSERT INTO USUARIO (Usuario, Dni, Contrasenia, EsAdmin)
VALUES ("agustina","27845123","123",0);

INSERT INTO USUARIO (Usuario, Dni, Contrasenia, EsAdmin)
VALUES ("ricardo","45854114","123",0);

INSERT INTO USUARIO (Usuario, Dni, Contrasenia, EsAdmin)
VALUES ("mariano", "42098253", "123", 0);

INSERT INTO USUARIO (Usuario, Dni, Contrasenia, EsAdmin)
VALUES ("maria", "23092178", "123", 0);

INSERT INTO USUARIO (Usuario, Dni, Contrasenia, EsAdmin)
VALUES ("juan", "41234568", "123", 0);

INSERT INTO USUARIO (Usuario, Dni, Contrasenia, EsAdmin)
VALUES ("jimena", "45092874", "123", 0);

INSERT INTO USUARIO (Usuario, Dni, Contrasenia, EsAdmin)
VALUES ("carlos", "41905672", "123", 0);

INSERT INTO USUARIO (Usuario, Dni, Contrasenia, EsAdmin)
VALUES ("ana", "87654321", "123", 0);

INSERT INTO USUARIO (Usuario, Dni, Contrasenia, EsAdmin)
VALUES ("pedro", "20908177", "123", 0);

INSERT INTO USUARIO (Usuario, Dni, Contrasenia, EsAdmin)
VALUES ("laura", "37589034", "123", 0);

INSERT INTO USUARIO (Usuario, Dni, Contrasenia, EsAdmin)
VALUES ("francisco", "45890327", "123", 0);

INSERT INTO USUARIO (Usuario, Dni, Contrasenia, EsAdmin)
VALUES ("elena", "34987109", "123", 0);

INSERT INTO USUARIO (Usuario, Dni, Contrasenia, EsAdmin)
VALUES ("martin", "43758923", "123", 0);

INSERT INTO USUARIO (Usuario, Dni, Contrasenia, EsAdmin)
VALUES ("cecilia", "32874403", "123", 0);

#CUENTA
INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (2, "50590940090411254247", "Caja de Ahorro", "2021-05-18", 120000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (3, "23094940090411124557", "Caja de Ahorro", "2022-02-21", 540000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (4, "42470940090414778459", "Cuenta Corriente", "2021-05-18", 65900, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (1, "90547840090411254247", "Caja de Ahorro", "2020-09-12", 4120000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (2, "43879140090411254247", "Cuenta Corriente", "2022-04-15", 9650000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (2, "47983940090411254247", "Cuenta Corriente", "2020-09-25", 50000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (1, "12345678901234567890", "Caja de Ahorro", "2021-01-15", 50000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (4, "50590940090411254247", "Cuenta Corriente", "2021-05-18", 120000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (3, "78901234567890123456", "Caja de Ahorro", "2022-02-20", 75000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (4, "11223344556677889900", "Cuenta Corriente", "2021-11-10", 180000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (5, "99887766554433221100", "Caja de Ahorro", "2022-08-05", 90000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (6, "12312312312312312312", "Cuenta Corriente", "2022-03-15", 150000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (7, "8765432107777777775", "Caja de Ahorro", "2022-01-01", 30000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (8, "87654321098765432109", "Caja de Ahorro", "2021-09-30", 60000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (9, "11223344556677889900", "Cuenta Corriente", "2021-12-01", 120000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (10, "10101010101010101010", "Cuenta Corriente", "2022-02-15", 50000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (11, "99887766554433221100", "Caja de Ahorro", "2022-06-10", 75000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (12, "33445566778899001122", "Caja de Ahorro", "2021-04-22", 90000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (13, "55443322110099887766", "Cuenta Corriente", "2022-01-08", 180000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (14, "14141414141414141414", "Cuenta Corriente", "2022-03-10", 80000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (15, "99887766554433221100", "Cuenta Corriente", "2021-07-15", 120000, true);

INSERT INTO CUENTA (NdeCliente, Cbu, TipoDeCuenta, FechaDeCreacion, Saldo, Estado)
VALUES (16, "11223344556677889900", "Caja de Ahorro", "2022-04-30", 60000, true);

#MOVIMIENTO
INSERT INTO MOVIMIENTO (NdeCuenta, TipoDeMovimiento, Fecha, Detalle, Importe)
VALUES(1, "Alta de Cuenta", "2023-10-27", "Dinero inicial por alta de cuenta", 100000);

INSERT INTO MOVIMIENTO (NdeCuenta, TipoDeMovimiento, Fecha, Detalle, Importe)
VALUES(2, "Alta de Cuenta", "2023-10-22", "Dinero inicial por alta de cuenta", 10000);

INSERT INTO MOVIMIENTO (NdeCuenta, TipoDeMovimiento, Fecha, Detalle, Importe)
VALUES(3, "Alta de Cuenta", "2023-10-2", "Dinero inicial por alta de cuenta", 120000);

INSERT INTO MOVIMIENTO (NdeCuenta, TipoDeMovimiento, Fecha, Detalle, Importe)
VALUES(4, "Alta de Cuenta", "2023-05-22", "Dinero inicial por alta de cuenta", 500000);

INSERT INTO MOVIMIENTO (NdeCuenta, TipoDeMovimiento, Fecha, Detalle, Importe)
VALUES(5, "Alta de Cuenta", "2023-02-07", "Dinero inicial por alta de cuenta", 60000);

INSERT INTO MOVIMIENTO (NdeCuenta, TipoDeMovimiento, Fecha, Detalle, Importe)
VALUES(6, "Transferencia", "2023-10-27", "Por regalo de cumpleaños", 50000);

INSERT INTO MOVIMIENTO (NdeCuenta, TipoDeMovimiento, Fecha, Detalle, Importe)
VALUES(7, "Transferencia", "2023-10-22", "Pago de helado", -6000);

INSERT INTO MOVIMIENTO (NdeCuenta, TipoDeMovimiento, Fecha, Detalle, Importe)
VALUES(8, "Transferencia", "2023-10-2", "Asado con amigos", 12000);

INSERT INTO MOVIMIENTO (NdeCuenta, TipoDeMovimiento, Fecha, Detalle, Importe)
VALUES(9, "Transferencia", "2023-05-22", "Delivery", -5000);

INSERT INTO MOVIMIENTO (NdeCuenta, TipoDeMovimiento, Fecha, Detalle, Importe)
VALUES(10, "Transferencia", "2023-02-07", "Compra de ropa", -7000);

#TRANSFERENCIA

#PROC TRANSF

DELIMITER //

CREATE PROCEDURE Transferencia(
    IN pNroCuenta INT,
    IN pCBU VARCHAR(45),
    IN pMonto DECIMAL(10, 0),
    IN pDetalle VARCHAR(45)
)
BEGIN
    DECLARE vIdMovimiento INT;
    DECLARE vTipoMovimiento VARCHAR(45);
    DECLARE vIdMovimientoS INT;
    DECLARE vIdMovimientoE INT; 
    DECLARE NdeCuenta2 INT;
    DECLARE vFecha DATE;

    -- Obtener el último IdMovimiento e incrementarlo para el nuevo movimiento
    SELECT COALESCE(MAX(IdMovimiento), 0) + 1 INTO vIdMovimientoS FROM movimiento;
    SELECT COALESCE(MAX(IdMovimiento), 0) + 2 INTO vIdMovimientoE FROM movimiento;
    
    -- Establecer el tipo de movimiento (puedes ajustar esto según tus necesidades)
    SET vTipoMovimiento = 'Transferencia';

    -- Obtener la fecha actual
    SET vFecha = CURDATE();

    -- Insertar el nuevo movimiento en la tabla Movimientos
    INSERT INTO movimiento (NdeCuenta, TipoDeMovimiento, Fecha, Detalle, Importe)
    VALUES (pNroCuenta, vTipoMovimiento, vFecha, pDetalle, -pMonto);
    
    -- Conseguir numero de cuenta con CBU para el segundo mov
    
    CALL BuscarCuentaXCBU(pCBU, @numeroCuenta);
	SELECT @numeroCuenta INTO NdeCuenta2;
    
    -- Insertar el segundo movimiento en la table Movimientos
    INSERT INTO movimiento (NdeCuenta, TipoDeMovimiento, Fecha, Detalle, Importe)
    VALUES (NdeCuenta2, vTipoMovimiento, vFecha, pDetalle, pMonto);
    
    -- Insertar la transferencia con sus respectivos id mov
    INSERT INTO transferencia (IdMovimientoSalida, IdMovimientoEntrada)
    VALUES (vIdMovimientoS, vIdMovimientoE);
    
    COMMIT;
END //

DELIMITER ;


# PROC Pagar Cuota
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `PagarCuota`(
	IN pNroCuenta INT,
    IN pNroPrestamo INT,
    IN pMonto DECIMAL(10, 0),
    IN PDetalle VARCHAR(45),
    IN pCuotasPagas INT)
BEGIN
    DECLARE vTipoMovimiento VARCHAR(45);
    DECLARE vFecha DATE;
     DECLARE pPlazo int;
     
    select pPlazo=Plazo from prestamo where NdePrestamo =pNroPrestamo;
    
	
-- Establecer el tipo de movimiento (puedes ajustar esto según tus necesidades)
    SET vTipoMovimiento = 'Pago de Prestamo';

    -- Obtener la fecha actual
    SET vFecha = CURDATE();
	
    UPDATE cuenta
	SET Saldo = Saldo - pMonto
	WHERE NdeCuenta=pNroCuenta;
    
    UPDATE prestamo
	SET CuotasPagas = pCuotasPagas
	WHERE NdePrestamo=pNroPrestamo;
    
    if  pPlazo=pCuotasPagas then
     UPDATE prestamo
	SET Estado = 'Pago'
	WHERE NdePrestamo=pNroPrestamo;
    end if;
    -- Insertar el nuevo movimiento en la tabla Movimientos
    INSERT INTO movimiento (NdeCuenta, TipoDeMovimiento, Fecha, Detalle, Importe)
    VALUES (pNroCuenta, vTipoMovimiento, vFecha, pDetalle, -pMonto);
END$$
DELIMITER ;



# PROC Solicitud
DELIMITER //

CREATE PROCEDURE Solicitud(
    IN pNroCliente INT,
    IN pNroCuenta INT,
    IN pImporteSolicitado DECIMAL(10, 0),
    IN pImporteAPagar DECIMAL(10, 0),
    IN pPlazo INT,
	IN pMonto DECIMAL(10, 0),
	IN pEstado VARCHAR(45)
)
BEGIN
    DECLARE vFecha DATE;

    -- Obtener la fecha actual
    SET vFecha = CURDATE();

    -- Insertar la nueva solicitud en la tabla Solicitud
    INSERT INTO solicitud (NdeCliente, NdeCuenta, Fecha, ImporteSolicitado , ImporteAPagar, Plazo, Monto, Estado)
    VALUES (pNroCliente, pNroCuenta, vFecha, pImporteSolicitado, pImporteAPagar, pPlazo, pMonto, pEstado);

    COMMIT;
END //

DELIMITER ;

# PROC BUSCAR CUENTA X CBU

DELIMITER //

CREATE PROCEDURE BuscarCuentaXCBU(
    IN pCBU VARCHAR(45),
    OUT pNroCuenta INT
)
BEGIN
    -- Buscar el número de cuenta correspondiente al CBU proporcionado
    SELECT NdeCuenta INTO pNroCuenta
    FROM cuenta
    WHERE Cbu = pCBU
    LIMIT 1;

END //

DELIMITER ;

#TRIGGER ACTUALIZAR SALDO

DELIMITER //

CREATE TRIGGER actualizar_saldo_despues_de_movimiento
AFTER INSERT ON movimiento
FOR EACH ROW
BEGIN
    DECLARE saldo_actual DECIMAL(10,0);
    
    -- Obtener el saldo actual de la cuenta
    SELECT Saldo INTO saldo_actual
    FROM cuenta
    WHERE NdeCuenta = NEW.NdeCuenta;
    
    -- Actualizar el saldo de la cuenta basado en el importe del movimiento
    UPDATE cuenta
    SET Saldo = saldo_actual + NEW.Importe
    WHERE NdeCuenta = NEW.NdeCuenta;
END;
//

DELIMITER ;

#TRIGGER SOLICITUD A PRESTAMO

DELIMITER //

CREATE TRIGGER crear_movimiento_update_solicitud
AFTER UPDATE ON solicitud
FOR EACH ROW
BEGIN
	DECLARE nestado VARCHAR(45);
	SELECT NEW.Estado INTO nestado
    FROM solicitud	
    WHERE NdeSolicitud = NEW.NdeSolicitud;
    
    -- Actualizar el saldo de la cuenta basado en el importe del movimiento si nestado es Aprobado
	IF nestado = 'Aprobado' THEN
		
		INSERT INTO prestamo (NdeCliente, NdeCuenta, Fecha, ImporteSolicitado , ImporteAPagar, Plazo, Monto, Estado)
		VALUES(NEW.NdeCliente, NEW.NdeCuenta, CURDATE(), NEW.ImporteSolicitado, NEW.ImporteAPagar, NEW.Plazo, NEW.Monto, 'En curso');
      
        INSERT INTO movimiento (NdeCuenta, TipoDeMovimiento, Fecha, Detalle, Importe)
		VALUES(NEW.NdeCuenta, 'Alta de Prestamo', CURDATE(), (concat('TOTAL A PAGAR: ', NEW.ImporteAPagar, ' en ', NEW.Plazo, ' cuotas' )), NEW.ImporteSolicitado);
        
    END IF;
END;
//
DELIMITER ;









/* CALL Transferencia(1, '42470940090414778459', -1000, 'Compra de productos');
CALL Transferencia(7, '42470940090414778459', 2500, 'Varios'); */





#SOLICITUD

#PRESTAMO

#CUOTAS


