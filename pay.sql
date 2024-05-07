create table orders (
    id                  number(6) generated always as identity, 
    order_time          date default sysdate,
    beverage            varchar2(100 char),
    beverage_option     varchar2(1000 char), 
    beverage_price      number(10),
    
    constraint orders_pk primary key (id)
);