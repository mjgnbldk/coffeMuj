create table coffees(
    id bigint not null primary key ,
    coffee_name varchar(255)
);

create table ingredient(
    id bigint not null primary key ,
    ingredient_name varchar(255)
);

create table ingredient_list (
    coffee_id bigint references coffees(id),
    ingredient_id bigint references ingredient(id),
    primary key (coffee_id, ingredient_id)
    );