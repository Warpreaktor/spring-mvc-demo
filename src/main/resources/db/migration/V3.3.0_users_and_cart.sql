alter table users
    add column cart_id bigint,
    add foreign key (cart_id) references cart(id)
;

alter table cart
    add column owner_id bigint,
    add foreign key (owner_id) references users(id)
;
