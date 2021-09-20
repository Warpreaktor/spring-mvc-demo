create table cart
(
    id bigserial primary key,
    product_id bigint unique,
    quantity int,

    foreign key (product_id) references product(id)

);