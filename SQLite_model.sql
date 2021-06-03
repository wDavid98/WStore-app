CREATE TABLE "Clientes" (
	"cc"	INTEGER NOT NULL UNIQUE,
	"nombre"	TEXT,
	"telefono"	INTEGER,
	"direccion"	TEXT,
	PRIMARY KEY("cc")
);

CREATE TABLE "Producto" (
	"ID"	INTEGER NOT NULL UNIQUE,
	"nombre"	TEXT,
	"precio_compra"	INTEGER,
	"precio_venta"	INTEGER,
	"imagen"	BLOB,
	"cantidad"	INTEGER,
	PRIMARY KEY("ID" AUTOINCREMENT)
);

CREATE TABLE "Proveedor" (
	"ID"	INTEGER NOT NULL UNIQUE,
	"nombre"	INTEGER,
	"telefono"	INTEGER,
	"direccion"	INTEGER,
	"NID"	INTEGER UNIQUE,
	PRIMARY KEY("ID" AUTOINCREMENT)
);

CREATE TABLE "Compra" (
	"id"	INTEGER NOT NULL UNIQUE,
	"id_producto"	INTEGER,
	"id_proveedor"	INTEGER,
	"precio_compra"	INTEGER,
	"fecha"	INTEGER,
	FOREIGN KEY("id_producto") REFERENCES "Producto"("ID"),
	FOREIGN KEY("id_proveedor") REFERENCES "Proveedor"("ID"),
	PRIMARY KEY("id" AUTOINCREMENT)
);

CREATE TABLE "Venta" (
	"ID"	INTEGER NOT NULL,
	"id_producto"	INTEGER,
	"id_cliente"	INTEGER,
	"fecha"	TEXT,
	"precio_venta"	INTEGER,
	"precio_compra"	INTEGER,
	"ganancia"	INTEGER,
	PRIMARY KEY("ID"),
	FOREIGN KEY("id_cliente") REFERENCES "Clientes"("cc"),
	FOREIGN KEY("id_producto") REFERENCES "Producto"("ID")
);

INSERT INTO "main"."Producto"("nombre","precio_compra","precio_venta","imagen") VALUES ("Escoba",800,1200,NULL);
INSERT INTO "main"."Producto"("nombre","precio_compra","precio_venta","imagen") VALUES ("Trapero",800,1500,NULL);
INSERT INTO "main"."Producto"("nombre","precio_compra","precio_venta","imagen") VALUES ("Jabon de loza",500,2000,NULL);
INSERT INTO "main"."Producto"("nombre","precio_compra","precio_venta","imagen") VALUES ("Cuaderno",900,1100,NULL);


UPDATE "main"."Producto" SET "imagen"=? WHERE "_rowid_"='6'