insert into roles (name)
values
    ('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_MANAGER');

insert into users (username, password, email)
values
    ('user', '$2a$12$Gat.mibJi5VmJm71DXDpReCVb3aPQeleL9h4GedPYt8v3n19ERIzO', 'user@gmail.com'),
    ('admin', '$2a$12$QmVhaLhIyEj3xl1SfEs9LOMupIgEWzSb.DubVCCSfTRFLmsApQDrm', 'admin@gmail.com'),
    ('manager', '$2a$12$BHsUlgVaPwMrZzLNGcaxlu0b.8JxML6nED4RbV7P3kACmDU/0VqvC', 'manager@gmail.com');

insert into users_roles (user_id, role_id)
values
    (1, 1),
    (2, 2),
    (3, 3);

insert into category(name, alias)
values
    ('Ноутбуки', 'Ноутбуки'), ('Смартфоны', 'Смартфоны'), ('Мониторы', 'Мониторы'), ('Персональные компьютеры', 'Персональные компьютеры')
;

insert into product(title, price)
values
('Ноутбук Acer', 75000), ('Смартфон Huawei', 42000), ('Монитор Dell', 9700),
('Монитор Asus', 5000), ('Монитор Benq', 2000), ('Монитор Panasonic', 6000)
;

insert into product_category(product_id, category_id)
values
(1, 1), (1, 4), (2, 2), (3, 3), (4, 3), (5, 3), (6, 3)
;