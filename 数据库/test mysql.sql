-- 创建shoplist表
create table shoplist(
thing_id char(4) not null,
thing_name varchar(100) not null,
thing_species varchar(32) not null,
thing_sale integer,
thing_purchase integer,
purchase_shijian DATE,
primary key(thing_id)
);

--在shoplist中加入thing_english_name列
alter table shoplist add column thing_english_name varchar(100);


--在shoplist中删除thing_english_name列
alter table shoplist drop column thing_english_name;

--在shoplist中插入值
insert into shoplist values 
	('0001','T恤衫','衣服',1000,500,'2009-09-20'),
	('0002','打孔器','办公用品',500,320,'2009-09-11'),
	('0003','运动T恤','衣服',4000,2800,NULL);

insert into shoplist values('0004','菜刀','厨房用具',3000,2800,'2009-09-20');
insert into shoplist values('0005','高压锅','厨房用具',6800,5000,'2009-01-15');
insert into shoplist values('0006','叉子','厨房用具',500,null,'2009-09-20');
insert into shoplist values('0007','擦菜板','厨房用具',880,790,'2008-04-28');
insert into shoplist values('0008','圆珠笔','办公用品',100,Null,'2009-11-11');


-- 给shoplist重命名

alter table shoplist rename to shoplists;

-- 把shoplist的名字改回来

alter table shoplists rename to shoplist;

--删除所有thing_name='T恤衫'的行

delete from shoplists where thing_name='T恤衫';

--输出thing_id,thing_purchase两列

select thing_id,thing_purchase from shoplist;

--输出shoplist的所有列，等价于show * from shoplist;

slect * from shoplist;

--给列起中文别名输出

select thing_id as "商品编号",
	   thing_name as "商品名称",
	   thing_purchase as '进货单价'
from 	shoplist;

--查询常数
select '商品' as string,38,'2009.02.28',thing_name,thing_id
from shoplist;


--输出shoplist的thing_species、thing_purchase列，重复的数据会合并。

select distinct thing_species from shoplist;
select distinct thing_purchase from shoplist;

--输出shoplist的thing_species、thing_purchase列，两列都一样的数据会合并。

select distinct thing_species,purchase_shijian from shoplist;

-- 选出thing_species = '衣服'的列，输出thing_species和thing_name。

select  thing_species,thing_name
from    shoplist
where   thing_species = '衣服';


--输出有算术运算符的列

select thing_name,thing_sale,thing_sale / 0 as "半价便宜卖" from shoplist;

--没有from也可以输出

select (100+200) as calculator;

--测试不等于

select thing_name 
from  shoplist
where thing_sale <>500;

--where亦可以输出计算表达式

select thing_name,thing_sale,thing_purchase
from shoplist
where thing_sale - thing_purchase >= 500;

--重新创张表，测试chr中大于小于的用法。

create table chars( 
	chr Char(3) not null,
  primary key (chr));

insert into chars values('1');
insert into chars values('2');
insert into chars values('3');
insert into chars values('10');
insert into chars values('11');
insert into chars values('222');
insert into chars values('d');
insert into chars values('a');
insert into chars values('106');	

--测试，大于小于为首字母字典序，字典序即asc2序。
select chr 
from   chars
where chr > '11';	

--输出列中值为null的行

select thing_name,thing_purchase
from shoplist
where thing_purchase is Null;


--测试not
select thing_name,thing_species,thing_sale
from shoplist
where not thing_sale >= 1000;


--测试and or用法

select thing_name,thing_purchase
from shoplist
where thing_sale >= 3000 
or   thing_species = '厨房用具';

select thing_name,thing_purchase
from shoplist
where thing_sale >= 3000 
and thing_species = '厨房用具';

--测试括号

select thing_name,thing_species,purchase_shijian
from shoplist
where thing_species = '办公用品'
and	 (purchase_shijian = '2009-09-11'
or purchase_shijian = '2009-09-20');


--测试null
select thing_name,thing_species,purchase_shijian
from shoplist
where  thing_purchase is null;

--计算表的行数
select count(*)
from shoplist;


--计算总和
select sum(thing_purchase+thing_sale)
from shoplist;

select sum(thing_purchase),sum(thing_sale)
from shoplist;

update into shoplist values('0007','擦菜板','厨房用具',800,790,'2008-04-28');

--计算进货单价平均值
select avg(thing_purchase)
from shoplist;

--计算最大值和最小值
select max(purchase_shijian),min(thing_species)
from shoplist;

--计算去除列中重复数据的行数
select count(distinct thing_species)
from shoplist;

select sum(distinct thing_sale),sum(thing_sale)
from shoplist;

--group by用法
select thing_species as me,count(*)
from   shoplist
group by me;

--having
select thing_species, count(*)
from   shoplist
group by thing_species
having count(*) = 2;

select thing_species,avg(thing_sale)
from shoplist
group by thing_species
having avg(thing_sale) >= 2500;

--having和where

select thing_species,count(*)
from   shoplist
where  thing_species = '衣服'
group by thing_species;

select thing_species,count(*)
from   shoplist
group by thing_species
having  thing_species = '衣服';

--排序键
select thing_id,thing_name,thing_sale,thing_purchase 
from shoplist
order by thing_sale;

--升序降序
select thing_id,thing_name,thing_sale,thing_purchase 
from shoplist
order by thing_sale desc;

--多行排序
SELECT thing_id, thing_name,thing_sale, thing_purchase 
FROM shoplist
ORDER BY thing_purchase;

--列编号的使用

select thing_id,thing_name,thing_sale 
from shoplist
order by 3 desc,1;

select thing_id,thing_name,thing_sale 
from shoplist
order by thing_sale desc,thing_id;

--创建productins表

create table productins
(product_id char(4) not null,
product_name varchar(100) not null,
product_type varchar(100) not null,
sale_price integer default 0,
purchase_price INTEGER,
regist_date date,
primary key(product_id));

    

--多行insert

insert into productins values
	('0001','T恤衫','衣服',1000,500,'2009-09-20'),
	('0002','打孔器','办公用品',500,320,'2009-09-11'),
	('0003','运动T恤','衣服',4000,2800,NULL);

等价于

insert into shoplists （thign_id,thing_name,thign_species,thing_species,thing_sale,thing_purchase,purchase_shijian）
	values ('0001','T恤衫','衣服',1000,500,'2009-09-20');
insert into shoplists （thign_id,thing_name,thign_species,thing_species,thing_sale,thing_purchase,purchase_shijian）
	values('0002','打孔器','办公用品',500,320,'2009-09-11');
insert into shoplists （thign_id,thing_name,thign_species,thing_species,thing_sale,thing_purchase,purchase_shijian）
	('0003','运动T恤','衣服',4000,2800,NULL);

--显式插入默认值

INSERT INTO ProductIns (product_id, product_name, product_type, sale_price, purchase_price, regist_date) VALUES ('0007', '擦菜板', '厨房用具', DEFAULT, 790, '2009-04-28');




--隐式插入默认值

INSERT INTO ProductIns (product_id, product_name, product_type, purchase_price, regist_date) 
VALUES ('0007', '擦菜板', '厨房用具',790, '2009-04-28');

--创建productcopy表

create table productcopy
(product_id char(4) not null,
product_name varchar(100) not null,
product_type varchar(100) not null,
sale_price integer default 0,
purchase_price INTEGER,
regist_date date,
primary key(product_id));

--复制表

insert into productcopy
select * from shoplist;

INSERT INTO ProductCopy (product_id, product_name, product_type, sale_price, purchase_price, regist_date)
SELECT thing_id,thing_name,thing_species,thing_sale,thing_purchase,purchase_shijian
FROM shoplist;
 
--创建producttype表

CREATE TABLE ProductType (
	product_type VARCHAR(32) not null,
	sum_sale_price INTEGER ,
	sum_purchase_price INTEGER,
	PRIMARY KEY (product_type));

insert into ProductType（product_type, sum_sale_price, sum_purchase_price)
select thing_species,sum(thing_sale),sum(thing_purchase)
from shoplist
group by thing_species;
INSERT INTO ProductType (
SELECT product_type, SUM(sale_price), SUM(purchase_price)
FROM 
GROUP BY product_type;

--清空表

delete from productcopy;

--搜索型delete用法

delete from shoplist 
	where thing_sale >= 4000;

--舍弃表中数据

truncate productcopy;

--update语句

UPDATE shoplist
SET purchase_shijian= '2009-10-10';

--搜索型update语句

UPDATE shoplist
WHERE thing_species = '厨房用具';

--null清空

UPDATE shoplist
SET purchase_shijian = NULL
WHERE thing_id = '0008';

--多列更新

UPDATE shoplist
SET （thing_sale,thing_purchase) = (thing_sale * 10,thing_purchase / 2)
WHERE thing_species = '厨房用具';


--事务处理

start transaction;

--DML语句
update shoplist
SET thing_sale = thing_sale-1000
where thing_name = '运动T恤';

update shoplist
set thing_sale = thing_sale  + 1000
where thing_name = 'T恤衫';

rollback or commit;

--创建product表

create table product
	(product_id char(4) not null,
	product_name varchar(100) not null,
	product_species varchar(32) not null,
	product_price integer,
	purchase_price integer,
	registered_date DATE,
	primary key(product_id));

--在product中插入值

insert into product values 
	('0001','T恤衫','衣服',1000,500,'2009-09-20'),
	('0002','打孔器','办公用品',500,320,'2009-09-11'),
	('0003','运动T恤','衣服',4000,2800,NULL);

insert into product values('0004','菜刀','厨房用具',3000,2800,'2009-09-20');
insert into product values('0005','高压锅','厨房用具',6800,5000,'2009-01-15');
insert into product values('0006','叉子','厨房用具',500,null,'2009-09-20');
insert into product values('0007','擦菜板','厨房用具',880,790,'2008-04-28');
insert into product values('0008','圆珠笔','办公用品',100,Null,'2009-11-11');

--创建视图

create view productsum(product_species,cnt_product)
as
SELECT product_species, COUNT(*) from Product
GROUP BY product_species;

--使用视图

select product_species,cnt_product
from productsum;

--多重视图
create view productsumjin(product_species,cnt_product)
as
select product_species,cnt_product
from productsum
where product_species = '办公用品'
order by product_species;

--删除视图

DROP VIEW productsumjin;

--显示库中的所有视图

show table status where comment='view';

--子查询的嵌套

SELECT product_species, cnt_product FROM (SELECT *
FROM (SELECT product_species, COUNT(*) AS cnt_product FROM Product
GROUP BY product_species) AS ProductSum 
WHERE cnt_product = 4) AS ProductSum2;

--标量子查询

select product_id,product_name,product_price
from product
where product_price > (select avg(product_price) from product);

--关联子查询

select product_species,product_name,product_price
from product as p1
where product_price > (select avg(product_price)
from product as p2
where p1.product_species = p2.product_species
group by product_species);

select product_species,product_name,product_price
from product 
where product_price > (select avg(product_price)
from product 
where product_species = '办公用品'
group by product_species)
and product_species = '办公用品';

select product_species,product_name,product_price
from product 
where product_price > (select avg(product_price)
from product 
where product_species = '厨房用具'
group by product_species)
and product_species = '厨房用具';

select product_species,product_name,product_price
from product 
where product_price > (select avg(product_price)
from product 
where product_species = '衣服'
group by product_species)
and product_species = '衣服';


--创建SampleMath表

create table samplemath
(m numeric (10,3),
n integer,
p integer);

--插入数据

insert into samplemath(m,n,p) 
values (500,0,null),
(-180,0,null),
(null,null,null),
(null,7,3)
(5.555,2,null),
(null,4,null),
(8,null,3),
(null,1,null),
(8.76,null,null),
(2.27,1,null),
(null,5,2),
(-500,3,null),
(-180.039,2,null);

--abs绝对值

select m,
abs (m) as abs_col
from samplemath;

--mod函数

select m,n,
	mod(m,n) as mod_col
	from samplemath;

--round 四舍五入

select m,n,
round(m,n) as round_col
from samplemath;

--创建字符串表

CREATE TABLE SampleStr(
str1 VARCHAR(40),
str2 VARCHAR(40), 
str3 VARCHAR(40));

--插入数据

INSERT INTO SampleStr (str1, str2, str3) VALUES ('opx','rt',NULL);
INSERT INTO SampleStr (str1, str2, str3) VALUES ('abc','def' ,NULL);
INSERT INTO SampleStr (str1, str2, str3) VALUES ('山田' , '太郎' ,'是我');
INSERT INTO SampleStr (str1, str2, str3) VALUES ('aaa',NULL ,NULL);
INSERT INTO SampleStr (str1, str2, str3) VALUES (NULL,'xyz',NULL);
INSERT INTO SampleStr (str1, str2, str3) VALUES ('@!#$%',NULL ,NULL);
INSERT INTO SampleStr (str1, str2, str3) VALUES ('ABC',NULL ,NULL);
INSERT INTO SampleStr (str1, str2, str3) VALUES ('aBC',NULL ,NULL);
INSERT INTO SampleStr (str1, str2, str3) VALUES ('abc太郎','abc' ,'ABC');
INSERT INTO SampleStr (str1, str2, str3) VALUES ('abcdefabc','abc' ,'ABC');
INSERT INTO SampleStr (str1, str2, str3) VALUES ('micmic','i' ,'I');

--||拼接函数

select str1,str2,str3,
concat(str1,str2,str3) as str_concat
from samplestr;

--length函数

select str1,
length(str1) as len_str
from samplestr;

--char_length函数

select str1,
char_length(str1) as len_str
from samplestr;

--lower函数

select str1,
lower(str1) as low_str
from samplestr;

--upper函数

select str1,
upper(str1) as low_str
from samplestr;

--replace函数

select str1,str2,str3,
replace(str1,str2,str3) as rep_str
from samplestr;

--substring函数

select str1,
substring(str1 from 3 for 2) as sub_str
from samplestr;

--current_date 当前日期

select current_date;

--current_time 当前时间

select current_time;

--current_timestamp 当前日期和时间

select current_timestamp;

--extract函数

select current_timestamp,
EXTRACT(YEAR FROM CURRENT_TIMESTAMP) AS year,
EXTRACT(MONTH  FROM CURRENT_TIMESTAMP) AS month, 
EXTRACT(DAY FROM CURRENT_TIMESTAMP) AS day,
EXTRACT(HOUR FROM CURRENT_TIMESTAMP) AS hour, 
EXTRACT(MINUTE FROM CURRENT_TIMESTAMP) AS minute,
EXTRACT(SECOND FROM CURRENT_TIMESTAMP) AS second;

--cast函数

select cast('0001' as signed integer) as int_col;
select cast('2009.12.14' as date) as date_col;

--coalesce

select coalesce (null,1) as col_1,
coalesce(null,'test',null) as col_2,
coalesce (null,null,'2001-11-01') as col_3;

SELECT COALESCE(str2, 2) FROM SampleStr;

--创建samplelike表

CREATE TABLE SampleLike
( strcol VARCHAR(6) NOT NULL,
PRIMARY KEY (strcol));

--插入数据

INSERT INTO SampleLike VALUES ('abcddd'); 
INSERT INTO SampleLike (strcol) VALUES ('dddabc'); 
INSERT INTO SampleLike (strcol) VALUES ('abdddc'); 
INSERT INTO SampleLike (strcol) VALUES ('abcdd'); 
INSERT INTO SampleLike (strcol) VALUES ('ddabc'); 
INSERT INTO SampleLike (strcol) VALUES ('abddc');

--前方一致查询

select * 
from SampleLike
where strcol like 'ddd%';

--中间一致查询

select * 
from SampleLike
where strcol like '%ddd%';

--后方一致查询

select * 
from SampleLike
where strcol like '%ddd';


--_的用法

select * 
from SampleLike
where strcol like 'abc__';

--between用法

SELECT product_name,product_price 
FROM Product
WHERE product_price>100 AND product_price<1000;

--in用法

select product_name, purchase_price
from product 
where purchase_price = 320 or
purchase_price =500 or
purchase_price =5000;

--创建shooproduct表

CREATE TABLE ShopProduct (shop_id CHAR(4)NOT NULL, 
shop_name VARCHAR(200) NOT NULL, 
product_id CHAR(4) NOT NULL,
quantity INTEGER NOT NULL,
PRIMARY KEY (shop_id, product_id));

--插入数据
             
INSERT INTO ShopProduct  VALUES ('000A','东京', '0001', 30);
INSERT INTO ShopProduct VALUES ('000A', '东京', '0002', 50);
INSERT INTO ShopProduct (shop_id, shop_name, product_id, quantity) VALUES ('000A', '东京', '0003', 15);
INSERT INTO ShopProduct (shop_id, shop_name, product_id, quantity) VALUES ('000B', '名古屋', '0003', 120);
INSERT INTO ShopProduct (shop_id, shop_name, product_id, quantity) VALUES ('000B', '名古屋', '0004', 20);
INSERT INTO ShopProduct (shop_id, shop_name, product_id, quantity) VALUES ('000B', '名古屋', '0002', 30);
INSERT INTO ShopProduct (shop_id, shop_name, product_id, quantity) VALUES ('000B', '名古屋', '0006', 10);
INSERT INTO ShopProduct (shop_id, shop_name, product_id, quantity) VALUES ('000B', '名古屋', '0007', 40);
INSERT INTO ShopProduct (shop_id, shop_name, product_id, quantity) VALUES ('000C', '大阪', '0003', 20);
INSERT INTO ShopProduct (shop_id, shop_name, product_id, quantity) VALUES ('000C', '大阪', '0004', 50);
INSERT INTO ShopProduct (shop_id, shop_name, product_id, quantity) VALUES ('000C', '大阪', '0006', 90);
INSERT INTO ShopProduct (shop_id, shop_name, product_id, quantity) VALUES ('000C', '大阪', '0007', 70);
INSERT INTO ShopProduct (shop_id, shop_name, product_id, quantity) VALUES ('000D','福冈', '0001', 100);

--in与子查询

select product_name,product_price
from product
where product_id in(select product_id
from ShopProduct
where shop_id = '000C');

select product_name,product_price
from product
where product_id not in(select product_id
from ShopProduct
where shop_id = '000a');

--exist

select product_name,product_price
from product as p
where not exists (select 1
from shopproduct as sp
where sp.shop_id = '000A'
and sp.product_id = p.product_id);

--case用法(搜索表达式)

select product_name,case when product_species = '衣服'
then concat('A:',product_species)
when product_species = '办公用品'
then concat('B:',product_species)
when product_species = '厨房用具'
then concat('C:',product_species)
else null
end as abc_product_trpe
from product;

--case用法举例

select product_species,sum(product_price)
from product
group by product_species;

select sum(case when product_species = '衣服'
then product_price else 0 end) as sum1,
sum(case when product_species = '办公用品'
then product_price else 0 end) as sum2,
sum(case when product_species = '厨房用具'
then product_price else 0 end) as sum3
from product;

--简单表达式

select product_name,case product_species
when  '衣服'
then concat('A:',product_species)
when '办公用品'
then concat('B:',product_species)
when '厨房用具'
then concat('C:',product_species)
else null
end as abc_product_trpe
from product;

--创建product2表

CREATE TABLE Product2
(product_id CHAR(4) not null,
product_name VARCHAR(100) not null,
product_type VARCHAR(32) not null,
sale_price INTEGER,
purchase_price INTEGER, 
regist_date DATE,
PRIMARY KEY (product_id));

--插入数据

INSERT INTO Product2 VALUES('0001', 'T恤衫' ,'衣服', 1000, 500, '2008-09-20');
INSERT INTO Product2 VALUES('0002', '打孔器', '办公用品', 500, 320, '2009-09-11');
INSERT INTO Product2 VALUES('0003', '运动T恤', '衣服', 4000, 2800, NULL);
INSERT INTO Product2 VALUES('0009', '手套', '衣服', 800, 500, NULL); 
INSERT INTO Product2 VALUES('0010', '水壶', '厨房用具', 2000, 1700,'2009-09-20');

--加法运算（并集）

select haha,product_name
from productins
union all
select product_id, product_name
from product
union all
select product_id,product_name
from product2;
order by haha desc;

--交运算（交集）

select haha,product_name
from productins
intersect
select product_id, product_name
from product
intersect
select product_id,product_name
from product2
order by haha desc;

--差运算（差集）

select product_id, product_name
from product
except
select haha,product_name
from productins;

--内联结 inner join

select sp.shop_id,shop_name,p.product_id,p.product_name,p.product_price
from shopproduct as sp inner join product as p
on sp.product_id = p.product_id;

--内联结和where

select sp.shop_id,sp.shop_name,p.product_id,p.product_name,p.product_price
from shopproduct as sp inner join product as p
on sp.product_id = p.product_id
where sp.shop_id = '000A';

--外联结 outer join

select sp.shop_id,shop_name,p.product_id,p.product_name,p.product_price
from product as p right outer join shopproduct as sp 
on sp.product_id = p.product_id;

--建立inventoryproduct表

CREATE TABLE InventoryProduct
( inventory_id CHAR(4) NOT NULL,
product_id CHAR(4) NOT NULL, 
inventory_quantity INTEGER NOT NULL, 
PRIMARY KEY (inventory_id, product_id));

--插入数据

INSERT INTO InventoryProduct (inventory_id, product_id, inventory_quantity)  VALUES ('P001', '0001', 0);
INSERT INTO InventoryProduct (inventory_id, product_id, inventory_quantity)  VALUES ('P001', '0002', 120);
INSERT INTO InventoryProduct (inventory_id, product_id, inventory_quantity)  VALUES ('P001', '0003', 200);
INSERT INTO InventoryProduct (inventory_id, product_id, inventory_quantity)  VALUES ('P001', '0004', 3);
INSERT INTO InventoryProduct (inventory_id, product_id, inventory_quantity)  VALUES ('P001', '0005', 0);
INSERT INTO InventoryProduct (inventory_id, product_id, inventory_quantity)  VALUES ('P001', '0006', 99);
INSERT INTO InventoryProduct (inventory_id, product_id, inventory_quantity)  VALUES ('P001', '0007', 999);
INSERT INTO InventoryProduct (inventory_id, product_id, inventory_quantity)  VALUES ('P001', '0008', 200);
INSERT INTO InventoryProduct (inventory_id, product_id, inventory_quantity)  VALUES ('P002', '0001', 10);
INSERT INTO InventoryProduct (inventory_id, product_id, inventory_quantity)  VALUES ('P002', '0002', 25);
INSERT INTO InventoryProduct (inventory_id, product_id, inventory_quantity)  VALUES ('P002', '0003', 34);
INSERT INTO InventoryProduct (inventory_id, product_id, inventory_quantity)  VALUES ('P002', '0004', 19);
INSERT INTO InventoryProduct (inventory_id, product_id, inventory_quantity)  VALUES ('P002', '0005', 99);
INSERT INTO InventoryProduct (inventory_id, product_id, inventory_quantity)  VALUES ('P002', '0006', 0);
INSERT INTO InventoryProduct (inventory_id, product_id, inventory_quantity)  VALUES ('P002', '0007', 0);
INSERT INTO InventoryProduct (inventory_id, product_id, inventory_quantity)  VALUES ('P002', '0008', 18);

--三张表以上的联结

SELECT SP.shop_id, SP.shop_name, SP.product_id, P.product_name, P.product_price, IP.inventory_quantity
FROM ShopProduct AS SP INNER JOIN Product AS P 
ON SP.product_id = P.product_id
left outer JOIN InventoryProduct AS IP  
ON SP.product_id = IP.product_id
WHERE IP.inventory_id = 'P001';

--交叉联结

select sp.shop_id,sp.shop_name,sp.product_id,p.product_name
from ShopProduct as sp cross join product as p;


--过时的联结语法

select sp.shop_id,shop_name,p.product_id,p.product_name,p.product_price
from shopproduct sp,product p
where sp.product_id = p.product_id
and sp.shop_id = '000A';

--创建skills表

CREATE TABLE Skills (skill VARCHAR(32),
PRIMARY KEY(skill));

--创建empskills表

CREATE TABLE EmpSkills (emp VARCHAR(32), skill VARCHAR(32),
PRIMARY KEY(emp, skill));

--插入数据

INSERT INTO Skills VALUES('Oracle'); 
INSERT INTO Skills VALUES('UNIX'); 
INSERT INTO Skills VALUES('Java');
INSERT INTO EmpSkills VALUES('相田', 'Oracle'); 
INSERT INTO EmpSkills VALUES('相田', 'UNIX'); 
INSERT INTO EmpSkills VALUES('相田', 'Java');
INSERT INTO EmpSkills VALUES('相田', 'C#');
INSERT INTO EmpSkills VALUES('神崎', 'Oracle'); 
INSERT INTO EmpSkills VALUES('神崎', 'UNIX'); 
INSERT INTO EmpSkills VALUES('神崎', 'Java'); 
INSERT INTO EmpSkills VALUES('平井', 'UNIX'); 
INSERT INTO EmpSkills VALUES('平井', 'Oracle'); 
INSERT INTO EmpSkills VALUES('平井', 'PHP'); 
INSERT INTO EmpSkills VALUES('平井', 'Perl'); 
INSERT INTO EmpSkills VALUES('平井', 'C++'); 
INSERT INTO EmpSkills VALUES('若田部', 'Perl'); 
INSERT INTO EmpSkills VALUES('渡来', 'Oracle');

--关系除法

select distinct emp
from EmpSkills as es1
where not exists
(	select skill
	from Skills
	except
	select skill
	from EmpSkills as es2
	where es1.emp = es2.emp);


--窗口函数

select product_name,product_species,product_price,
rank() over (order by product_price) as ranking,
dense_rank() over (order by product_price) as dense_ranking,
row_number()over(order by product_price)as rankhaha
from product;

--聚合函数作为窗口函数

select product_id,product_name,product_price,
avg(product_price)over(order by product_id) as current_avg
from product;

--框架(汇总范围)

select product_id,product_name,product_price,
avg(product_price)over(order by product_id rows  3 following) as moving_avg
from product;

--结果排序

SELECT product_name, product_species, product_price,
RANK () OVER (ORDER BY product_price) AS ranking
FROM Product
order by ranking;

--rollup

select '合计' as product_species,sum(product_price)
from product
union all
select product_species,sum(product_price)
from product
group by product_species;

select product_species,sum(product_price) as sum_purchase_price
from product
group by product_species with rollup;

--rollup两个聚合键

SELECT product_species,registered_date, SUM(product_price) AS sum_price 
FROM Product
GROUP BY product_species,registered_date with rollup;

--grouping函数，分辨超级分组记录的null
sELECT  case when GROUPING(product_species) = 1
then '商品种类 合计'
else product_species end as product_species,
case when GROUPING(registered_date) = 1
then '登记日期合计' 
else  registered_date end as registered_date, SUM(product_price) AS sum_price
FROM Product
GROUP BY product_species, registered_date with rollup;

--cube

sELECT  case when GROUPING(product_species) = 1
then '商品种类 合计'
else product_species end as product_species,
case when GROUPING(registered_date) = 1
then '登记日期合计' 
else  registered_date end as registered_date, SUM(product_price) AS sum_price
FROM Product
GROUP BY cube(product_species, registered_date);

--grouping sets

sELECT  case when GROUPING(product_species) = 1
then '商品种类 合计'
else product_species end as product_species,
case when GROUPING(registered_date) = 1
then '登记日期合计' 
else  registered_date end as registered_date, SUM(product_price) AS sum_price
FROM Product
GROUP BY grouping set(product_species, registered_date);






