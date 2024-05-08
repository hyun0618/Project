create table orders (
    id                  number(6) generated always as identity, 
    beverage            varchar2(100 char),
    beverage_option     varchar2(1000 char), 
    beverage_price      number(10),
    
    constraint orders_pk primary key (id)
);


select * from orders;

delete from orders;

commit;
