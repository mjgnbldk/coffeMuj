insert into coffees(id, coffee_name)
values (1, 'milk-coffee'),
       (2, 'espresso');

insert into ingredient(id, ingredient_name)
values (1, 'milk'),
       (2, 'coffee');

insert into ingredient_list (coffee_id, ingredient_id)
values (1,1), (1,2), (2,2)
