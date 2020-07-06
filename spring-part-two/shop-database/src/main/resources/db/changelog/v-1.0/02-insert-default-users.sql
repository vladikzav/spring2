insert into users (name, password) value ('admin', '12s3'),
      ('guest', '12s3');
GO

INSERT INTO roles (name)
VALUE ('ROLE_ADMIN'), ('ROLE_GUEST');
GO

INSERT INTO users_roles(user_id, role_id)
SELECT (SELECT id FROM users WHERE name = 'admin'), (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT id FROM users WHERE name = 'guest'), (SELECT id FROM roles WHERE name = 'ROLE_GUEST')
GO

insert into products (name, description, price) value ('firstProduct', 'description', 10),
    ('secondProduct', 'description', 15);
GO

INSERT INTO categories (name)
    VALUE ('TEST_CATEGORY_1'), ('TEST_CATEGORY_2');
GO

INSERT INTO products_categories(product_id, category_id)
SELECT (SELECT id FROM products WHERE name = 'firstProduct'), (SELECT id FROM categories WHERE name = 'TEST_CATEGORY_1')
UNION ALL
SELECT (SELECT id FROM products WHERE name = 'secondProduct'), (SELECT id FROM categories WHERE name = 'TEST_CATEGORY_2')
GO