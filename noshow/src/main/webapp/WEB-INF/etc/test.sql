select * from restaurant;
delete from restaurant;
delete from menu;
UPDATE AUTHORITY set AUTHORITY = 'ROLE_MEMBER' WHERE member_id = 'rlaghrb';
UPDATE AUTHORITY set AUTHORITY = 'ROLE_OWNER' WHERE business_id = '00000000';
select * from AUTHORITY;
select * from member;
select * from menu;
update MEMBER set drop_check = '0' where member_id = 'dldbfl';
insert into AUTHORITY values('dldbfl', 'ROLE_MEMBER');
UPDATE member set member_name = "dkssudgktpdy" WHERE member_id = '201234026';


		select res_date , sum(res_people) from RESERVATION where business_id = 'test-1' group by res_date


select * from reservation;
select * from restaurant

insert into MEMBER(member_id, member_password, member_name, member_gender, member_tel, member_birthday, member_email) values('phj','phj','박현준','남','02132',sysdate,'djd' );
insert into RESTAURANT values('phj',1,8,18,2012121,'현준이네고기집','010212132',sysdate,sysdate,'ahffk','ahffk',30,5000);

select a.business_id, b.code_val, c.code_val, d.code_val
from RESTAURANT a, rt_code b, rt_code c, rt_code d
where a.rt_holiday = b.code_num and 
	  a.rt_field = c.code_num and
	  a.rt_term = d.code_num;