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


select * from RESTAURANT;


select RESTAURANT.BUSINESS_ID, RESTAURANT.RT_HOLIDAY, RESTAURANT.RT_TERM, RESTAURANT.RT_FIELD, RESTAURANT.RT_NUM, RESTAURANT.RT_NAME, 
	   RESTAURANT.RT_TEL, RESTAURANT.RT_OPEN, RESTAURANT.RT_CLOSE, RESTAURANT.RT_PICTURE, RESTAURANT.RT_ADDRESS, RESTAURANT.RT_CAPACITY, RT_DEPOSIT,
	   holiday.Holiday_Name, holiday.Holiday_VAL, term.Term_name, term.Term_VAL, field.field_name, field.field_VAL
FROM RESTAURANT, holiday, field, term 
WHERE RESTAURANT.rt_holiday = holiday.holiday_name and 
	  RESTAURANT.rt_field = field.field_name and
	  RESTAURANT.rt_term = term.term_name and 
	  RESTAURANT.business_id = 'djaqnsghd';
	  
  
	  
	  
	  
-- 관리자 권한을 가지는 member  // selectMemberAuthorityAdmin
select MEMBER.member_id, MEMBER.member_password, MEMBER.member_name, MEMBER.member_gender, MEMBER.member_tel, MEMBER.member_birthday, MEMBER.member_email, MEMBER.drop_check
from MEMBER, AUTHORITY
where member.member_id = AUTHORITY.member_id and AUTHORITY.AUTHORITY = 'ROLE_ADMIN';
-- 일반 사용자 권한을 가지는 member // selectMemberAuthorityMember
select MEMBER.member_id, MEMBER.member_password, MEMBER.member_name, MEMBER.member_gender, MEMBER.member_tel, MEMBER.member_birthday, MEMBER.member_email, MEMBER.drop_check
from MEMBER, AUTHORITY
where member.member_id = AUTHORITY.member_id and AUTHORITY.AUTHORITY = 'ROLE_MEMBER';
-- 탈퇴한 회원 조회 // selectWithdrawMember
select MEMBER.member_id, MEMBER.member_password, MEMBER.member_name, MEMBER.member_gender, MEMBER.member_tel, MEMBER.member_birthday, MEMBER.member_email, MEMBER.drop_check
from MEMBER
where member.drop_Check = '1';
-- 식당 사업자 권한을 가지는 member // selectMemberAuthorityOwner
select MEMBER.member_id, MEMBER.member_password, MEMBER.member_name, MEMBER.member_gender, MEMBER.member_tel, MEMBER.member_birthday, MEMBER.member_email, MEMBER.drop_check
from MEMBER, AUTHORITY
where member.member_id = AUTHORITY.member_id and AUTHORITY.AUTHORITY = 'ROLE_OWNER';
-- 전체 식당 정보 조회 // selectAllRestaurant

select RESTAURANT.BUSINESS_ID, RESTAURANT.RT_HOLIDAY, RESTAURANT.RT_TERM, RESTAURANT.RT_FIELD, RESTAURANT.RT_NUM, RESTAURANT.RT_NAME, 
	   RESTAURANT.RT_TEL, RESTAURANT.RT_OPEN, RESTAURANT.RT_CLOSE, RESTAURANT.RT_PICTURE, RESTAURANT.RT_ADDRESS, RESTAURANT.RT_CAPACITY, RT_DEPOSIT,
	   holiday.Holiday_Name, holiday.Holiday_VAL, term.Term_name, term.Term_VAL, field.field_name, field.field_VAL
FROM RESTAURANT, holiday, field, term 
WHERE RESTAURANT.rt_holiday = holiday.holiday_name and 
	  RESTAURANT.rt_field = field.field_name and
	  RESTAURANT.rt_term = term.term_name
AND	  holiday.holiday_val != '월'
AND	  RESTAURANT.rt_address like '%서울%'

	  
select to_char(RT_OPEN,'hh24:mi')
from restaurant;

select * from BOARD;

select * from TABLE_


