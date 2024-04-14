
create table Category
(
    Id           bigint auto_increment primary key,
    CategoryName varchar(64) not null,
    RegisteredAt datetime(6) not null,
    constraint UK_idlxl0cnaxo9wthlrjqy0h9es
        unique (CategoryName)
);


create table Product
(
    Id           bigint  auto_increment primary key,
    BrandId      bigint       not null,
    CategoryId   bigint       not null,
    Name         varchar(255) not null,
    Price        bigint       not null,
    RegisteredAt datetime(6)  not null
);

create table Brand
(
    Id           bigint  auto_increment  primary key,
    BrandName    varchar(64) not null,
    RegisteredAt datetime(6) not null,
    constraint UK_7gmlovw4rrg56xk95ugenn749
        unique (BrandName)
);