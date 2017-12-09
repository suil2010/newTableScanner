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
