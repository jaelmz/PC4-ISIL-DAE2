CREATE TABLE tbl_address(
    add_id identity not null primary key,
    country varchar(30) not null,
    city varchar(30),
    street varchar(30)
);

CREATE TABLE tbl_employee(
    emp_id identity not null primary key,
    name varchar(100) not null,
    addr_id int not null,
    foreign key (addr_id) references tbl_address(add_id)
);
