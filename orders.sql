create table orders (
    id                  number(6) generated always as identity, 
    beverage            varchar2(100 char),
    beverage_option     varchar2(1000 char), 
    beverage_price      number(10),
    
    constraint orders_pk primary key (id)
);

/*
insert into orders (order_time, beverage, beverage_option, beverage_price)
values ('2024/05/02 12:10', '자몽에이드', 'Large', 5500);

insert into orders (order_time, beverage, beverage_option, beverage_price)
values ('2024/05/02 15:40', '딸기스무디', 'Regular', 5000);

insert into orders (order_time, beverage, beverage_option, beverage_price)
values ('2024/05/03 13:20', '아메리카노', 'Ice/ Regular', 3000);

insert into orders (order_time, beverage, beverage_option, beverage_price)
values ('2024/05/03 12:10', '카페라떼', 'Ice/ Regular', 4000);

insert into orders (order_time, beverage, beverage_option, beverage_price)
values ('2024/05/04 17:50', '초코프라푸치노', 'Regular', 5500);

insert into orders (order_time, beverage, beverage_option, beverage_price)
values ('2024/05/04 20:20', '아메리카노', 'Hot/ Large', 3500);

insert into orders (order_time, beverage, beverage_option, beverage_price)
values ('2024/05/05 16:45', '아메리카노', 'Hot/ Regular', 3000);

insert into orders (order_time, beverage, beverage_option, beverage_price)
values ('2024/05/05 20:20', '아메리카노', 'Ice/ Large', 3500);

insert into orders (order_time, beverage, beverage_option, beverage_price)
values ('2024/05/06 17:50', '카페라떼', 'Ice/ Large', 4500);

insert into orders (order_time, beverage, beverage_option, beverage_price)
values ('2024/05/06 20:20', '카푸치노', 'Single', 4000);
*/

select * from orders;

delete from orders;

commit;