    alter table categories
        drop index UK_t8o6pivur7nn124jehx7cygw5
GO


    alter table roles
        drop index UK_ofx66keruapi6vyqpv6f2or37
GO



    alter table products_categories
        drop constraint FKqt6m2o5dly3luqcm00f5t4h2p

GO

    alter table products_categories
        drop constraint FKtj1vdea8qwerbjqie4xldl1el

GO

    alter table users_roles
        drop constraint FKj6m8fwv7oqv74fcehir1a9ffy

GO

    alter table users_roles
        drop constraint FK2o0jvgh89lemvvo17cbqvdxaa
GO


    drop table categories;
GO

    drop table products;
GO

    drop table products_categories;
GO

    drop table roles;
GO

    drop table users;
GO

    drop table users_roles;
GO

