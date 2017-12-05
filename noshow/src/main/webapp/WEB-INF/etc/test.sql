select * from restaurant;
delete from restaurant;
UPDATE AUTHORITY set AUTHORITY = 'ROLE_MEMBER' WHERE member_id = 'rlaghrb';
select * from AUTHORITY;
select * from member;
UPDATE member set drop_check = 1 WHERE member_id = 'dlwltn1';


		select res_date , sum(res_people) from RESERVATION where business_id = 'test-1' group by res_date

