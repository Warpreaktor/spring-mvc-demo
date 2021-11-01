create table product (
    id bigserial primary key,
    title text not null,
    price float not null,
    image_link text
);

---

create table category (
     id bigserial primary key,
     name text not null,
     alias text not null,
     parent_id int,

     foreign key (parent_id) references category(id)
);

create index category_parent_id_idx on category(parent_id);

---

create table product_category (
    product_id bigint not null,
    category_id bigint not null,

    primary key (product_id, category_id),
    foreign key (product_id) references product(id),
    foreign key (category_id) references category(id)
);

create table users (
    id bigserial primary key,
    username varchar(30) not null unique,
    password varchar(80) not null,
    email varchar(50) unique,
    account_non_locked bool default true,
    enabled bool default true
);

create table roles (
    id serial primary key,
    name varchar(50) not null
);

CREATE TABLE users_roles (
    user_id bigint not null,
    role_id int not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

create table cart
(
    id bigserial primary key,
    product_id bigint unique,
    quantity int,
    owner_id bigint,
    foreign key (owner_id) references users(id)
);

create table users_cart
(
    users_id  bigint not null,
    cart_id bigint not null,

    primary key (users_id, cart_id),
    foreign key (users_id) references users (id),
    foreign key (cart_id) references cart (id)
)