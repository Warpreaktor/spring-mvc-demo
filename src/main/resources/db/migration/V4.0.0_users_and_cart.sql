create table users_cart
(
    users_id  bigint not null,
    cart_id bigint not null,

    primary key (users_id, cart_id),
    foreign key (users_id) references users (id),
    foreign key (cart_id) references cart (id)
)