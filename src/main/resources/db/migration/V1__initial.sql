CREATE TABLE beer (
    id SERIAL PRIMARY KEY,
    name VARCHAR (50) NOT NULL,
    description VARCHAR(150)    ,
    style VARCHAR (50) NOT NULL,
    brewery VARCHAR (50) not null,
    ibu INT NOT null,
    alcohol NUMERIC not null,
    price NUMERIC not null
);

CREATE TABLE purchase (
    id SERIAL PRIMARY KEY,
    delivery BOOL default false,
    address VARCHAR (80),
   	status VARCHAR(10)
);


CREATE TABLE order_item (
    id SERIAL PRIMARY KEY,
    purchase INT NOT NULL,
    beer Int NOT NULL,
    cant Int not null,
    price NUMERIC not null,
    CONSTRAINT "item_to_beer_id_FK" FOREIGN KEY (beer) 
    REFERENCES beer(id) MATCH simple,
    CONSTRAINT "item_to_order_id_FK" FOREIGN KEY (purchase) 
    REFERENCES purchase(id) MATCH SIMPLE


);
