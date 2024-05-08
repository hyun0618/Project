create table order_check (
    id                number(6) generated always as identity, 
    order_time        date default sysdate,
    order_beverages   varchar2(100 char),
    order_payment     varchar2(100 char), 
    order_price       varchar2(100 char),
    
    constraint order_check_pk primary key (id)
);

insert into order_check (order_time, order_beverages, order_payment, order_price)
values ('2024/05/01 12:10', '미숫가루', '신용카드', '\2,000');

insert into order_check (order_time, order_beverages, order_payment, order_price)
values ('2024/05/01 12:10', '식혜', '신용카드', '\2,500');

insert into order_check (order_time, order_beverages, order_payment, order_price)
values ('2024/05/01 18:20', '바닐라라떼', '카카오페이', '\5,000');

insert into order_check (order_time, order_beverages, order_payment, order_price)
values ('2024/05/02 20:10', '말차프라푸치노', '신용카드', '\6,000');

insert into order_check (order_time, order_beverages, order_payment, order_price)
values ('2024/05/02 17:35', '헤이즐넛라떼, 아메리카노', '카카오페이', '\10,000');

insert into order_check (order_time, order_beverages, order_payment, order_price)
values ('2024/05/03 11:15', '딸기스무디, 카페라떼', '신용카드', '\9,500');

insert into order_check (order_time, order_beverages, order_payment, order_price)
values ('2024/05/03 07:20', '아메리카노, 카페라떼', '신용카드', '\8,000');

insert into order_check (order_time, order_beverages, order_payment, order_price)
values ('2024/05/04 20:23', '카푸치노, 카페라떼, 에스프레소', '신용카드', '\11,500');

insert into order_check (order_time, order_beverages, order_payment, order_price)
values ('2024/05/04 21:30', '아메리카노', '카카오페이', '\4,000');

insert into order_check (order_time, order_beverages, order_payment, order_price)
values ('2024/05/05 20:23', '자몽에이드, 카페라떼, 에스프레소', '신용카드', '\12,000');

insert into order_check (order_time, order_beverages, order_payment, order_price)
values ('2024/05/05 21:30', '카페라떼', '신용카드', '\5,500');

insert into order_check (order_time, order_beverages, order_payment, order_price)
values ('2024/05/06 20:23', '딸기스무디', '신용카드', '\5,000');

insert into order_check (order_time, order_beverages, order_payment, order_price)
values ('2024/05/06 21:30', '자바칩프라푸치노', '카카오페이', '\7,000');

insert into order_check (order_time, order_beverages, order_payment, order_price)
values ('2024/05/07 20:23', '레몬 캐모마일 티', '신용카드', '\4,000');

insert into order_check (order_time, order_beverages, order_payment, order_price)
values ('2024/05/07 21:30', '밀크티, 아메리카노', '카카오페이', '\8,500');



select * from order_check;

delete from order_check;

commit;
