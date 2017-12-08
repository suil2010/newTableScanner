DROP TABLE AUTHORITY CASCADE CONSTRAINTS; /* 권한 */
DROP TABLE MEMBER CASCADE CONSTRAINTS; /* 회원 */
DROP TABLE RESTAURANT CASCADE CONSTRAINTS; /* 음식점 정보 */
DROP TABLE MENU CASCADE CONSTRAINTS; /*메뉴*/
DROP TABLE CLASSIFICATION CASCADE CONSTRAINTS; /* 분류 */
DROP TABLE TABLE_ CASCADE CONSTRAINTS; /* 테이블 */
DROP TABLE ORDER_TABLE CASCADE CONSTRAINTS; /* 테이블주문 */
DROP TABLE RESERVATION CASCADE CONSTRAINTS; /* 예약 */
DROP TABLE BOOKMARK CASCADE CONSTRAINTS; /* 즐겨찾기 */
DROP TABLE BOARD CASCADE CONSTRAINTS; /* 게시판 */
DROP TABLE COMMEN CASCADE CONSTRAINTS; /* 댓글 */
DROP TABLE REVIEW CASCADE CONSTRAINTS; /* 후기 */
DROP TABLE REPORT CASCADE CONSTRAINTS; /* 신고 */
DROP TABLE RECOMMEND CASCADE CONSTRAINTS; /* 추천 */
DROP TABLE QUESTION CASCADE CONSTRAINTS; /* 문의 */
DROP TABLE ANSWER CASCADE CONSTRAINTS; /* 답변 */

/* 권한 */
CREATE TABLE AUTHORITY (
   MEMBER_ID VARCHAR2(20) NOT NULL, /* 회원아이디 */
   AUTHORITY VARCHAR2(30) , /* 권한 */
   CONSTRAINT PK_AUTHORITY PRIMARY KEY (MEMBER_ID)
);

/* 회원 */
CREATE TABLE MEMBER (
   MEMBER_ID VARCHAR2(20) NOT NULL, /* 회원아이디 */
   MEMBER_PASSWORD VARCHAR2(80) NOT NULL, /* 비밀번호 */
   MEMBER_NAME VARCHAR2(30) NOT NULL, /* 이름 */
   MEMBER_GENDER VARCHAR2(10) NOT NULL, /* 성별 */
   MEMBER_TEL VARCHAR2(20) NOT NULL, /* 전화번호 */
   MEMBER_BIRTHDAY date NOT NULL, /* 생년월일 */
   MEMBER_EMAIL VARCHAR2(100) NOT NULL, /* 이메일 */
   drop_check NUMBER default 0, /* 탈퇴유무 1이면 탈퇴*/
   CONSTRAINT PK_MEMBER PRIMARY KEY (MEMBER_ID)
);

/* 음식점 정보 */
CREATE TABLE RESTAURANT (
   BUSINESS_ID VARCHAR2(20) NOT NULL, /* 점주 회원 아이디 */
   RT_NUM NUMBER Unique, /* 사업자번호 */
   RT_NAME VARCHAR(200), /* 음식점명 */
   RT_TEL VARCHAR2(20), /* 음식점전화번호 */
   RT_FIELD VARCHAR2(30), /* 업종 */
   RT_HOLIDAY VARCHAR2(10), /* 휴무일 */
   RT_OPEN DATE, /* OPEN 시간 */
   RT_CLOSE DATE, /* CLOSE시간 */
   RT_TERM NUMBER, /* 테이블 이용시간 */
   RT_PICTURE VARCHAR2(500), /* 저장이름 */
   RT_ADDRESS VARCHAR2(300), /* 음식점위치 */
   RT_CAPACITY NUMBER, /* 수용가능인원 */
   RT_DEPOSIT NUMBER, /* 1인 금액 */
   CONSTRAINT PK_RESTAURANT PRIMARY KEY (BUSINESS_ID)
);

/* 메뉴 */
CREATE TABLE MENU (
   MENU_NUM NUMBER NOT NULL, /* 메뉴번호 */
   MENU_NAME VARCHAR(20), /* 메뉴이름 */
   MENU_COMMENT VARCHAR2(200), /* 메뉴설명 */
   MENU_PRICE NUMBER, /* 메뉴가격 */
   MENU_PICTURE VARCHAR2(500), /* 메뉴사진이름 */
   CLASSIFICATION_NUM NUMBER, /* 대분류리스트번호 */
   BUSINESS_ID VARCHAR2(20), /* 점주 회원 아이디 */
   CONSTRAINT PK_MENU PRIMARY KEY (MENU_NUM)
);

/* 대분류 */
CREATE TABLE CLASSIFICATION (
   CLASSIFICATION_NUM NUMBER NOT NULL, /* 대분류리스트번호 */
   BUSINESS_ID VARCHAR2(20), /* 점주 회원 아이디 */
   CLASSIFICATION_NAME VARCHAR2(15), /* 대분류명 */
   CONSTRAINT PK_CLASSIFICATION PRIMARY KEY (CLASSIFICATION_NUM)
);

/* 테이블 */
CREATE TABLE TABLE_ (
   TABLE_seq NUMBER NOT NULL, /* 테이블리스트번호 */
   TABLE_NUM NUMBER, /* 테이블 번호 */
   TABLE_PEOPLE NUMBER, /* 인원 */
   X_LOCATION NUMBER, /* X좌표 */
   Y_LOCATION NUMBER, /* Y좌표 */
   BUSINESS_ID VARCHAR2(20), /* 점주 회원 아이디 */
   CONSTRAINT PK_TABLE_ PRIMARY KEY (TABLE_seq)
);

/* 테이블주문 */
CREATE TABLE ORDER_TABLE (
   TABLE_seq NUMBER NOT NULL, /* 테이블리스트번호 */
   RES_NUM NUMBER NOT NULL, /* 예약리스트번호 */
   CONSTRAINT PK_ORDER_TABLE PRIMARY KEY (TABLE_seq,RES_NUM)
);

/* 예약 */
CREATE TABLE RESERVATION (
   RES_NUM NUMBER NOT NULL, /* 예약리스트번호 */
   RES_DATE DATE, /* 예약날짜 */
   RES_PEOPLE NUMBER, /* 인원 */
   RES_START_TIME DATE, /* 예약 원하는시간 */
   RES_END_TIME DATE,	/* 예약 종료 시간 */
   RES_PAID_TIME DATE, /* 예약결제완료한시간 */
   RES_PAY_STATEMENT VARCHAR2(20), /* 결제유무 */
   RES_PRICE NUMBER, /* 예약금액 */
   MEMBER_ID VARCHAR2(20), /* 회원아이디 */
   BUSINESS_ID VARCHAR2(20), /* 점주 회원 아이디 */
   CONSTRAINT PK_RESERVATION PRIMARY KEY (RES_NUM)
);

/* 즐겨찾기 */
CREATE TABLE BOOKMARK (
   BOOKMARK_NUM VARCHAR2(20) NOT NULL, /* 즐겨찾기리스트번호 */
   MEMBER_ID VARCHAR2(20), /* 회원아이디 */
   BUSINESS_ID VARCHAR2(20), /* 점주 회원 아이디 */
   CONSTRAINT PK_BOOKMARK PRIMARY KEY (BOOKMARK_NUM)
);

/* 게시판 */
CREATE TABLE BOARD (
   BOARD_NUM NUMBER NOT NULL, /* 글번호 */
   MEMBER_ID VARCHAR2(20), /* 회원아이디 */
   BOARD_SUBJECT VARCHAR2(50) NOT NULL, /* 제목 */
   BOARD_TEXT CLOB NOT NULL, /* 내용 */
   BOARD_VIEWS NUMBER DEFAULT 0, /* 조회수 */
   BOARD_TIME DATE DEFAULT sysdate, /* 작성시간 */
   CONSTRAINT PK_BOARD PRIMARY KEY (BOARD_NUM)
);

/* 댓글 */
CREATE TABLE COMMEN (
   COMMEN_NUM NUMBER NOT NULL, /* 댓글일련번호 */
   COMMEN_ID VARCHAR2(16), /* 작성자아이디 */
   COMMEN_TEXT VARCHAR2(100), /* 댓글내용 */
   COMMEN_TIME DATE DEFAULT sysdate, /* 작성시간 */
   BOARD_NUM NUMBER, /* 글번호 */
   CONSTRAINT memo_mseq_pk PRIMARY KEY (COMMEN_NUM)
);

/* 후기 */
CREATE TABLE REVIEW (
   REVEIW_NUM NUMBER NOT NULL, /* 후기 글번호 */
   REVIEW_TEXT VARCHAR2(1500), /* 내용 */
   REVIEW_TIME DATE, /* 작성시간 */
   REVEIW_IMG VARCHAR2(36), /* 리뷰사진 */
   REVIEW_GRADE	NUMBER,	/* 리뷰 평점 */
   MEMBER_ID VARCHAR2(20), /* 회원아이디 */
   BUSINESS_ID VARCHAR2(20), /* 점주 회원 아이디 */
   RES_NUM NUMBER, /* 예약리스트번호 */
   CONSTRAINT PK_REVIEW PRIMARY KEY (REVEIW_NUM)
);

/* 신고 */
CREATE TABLE REPORT (
   REPORT_NUM NUMBER NOT NULL, /* 신고리스트번호 */
   REPORTER_ID VARCHAR2(20), /* 신고인아이디 */
   REVIEW_NUM NUMBER, /* 후기 글번호 */
   CONSTRAINT PK_REPORT PRIMARY KEY (REPORT_NUM)
);

/* 추천 */
CREATE TABLE RECOMMEND (
   RECOMMEND_NUM NUMBER NOT NULL, /* 추천리스트번호 */
   RECOMMEND_ID VARCHAR2(12), /* 추천인아이디 */
   REVEIW_NUM NUMBER, /* 후기 글번호 */
   CONSTRAINT PK_RECOMMEND PRIMARY KEY (RECOMMEND_NUM)
);

/* 문의 */
CREATE TABLE QUESTION (
   QUESTION_NUM NUMBER NOT NULL, /* 문의일련번호 */
   MEMBER_ID VARCHAR2(20), /* 회원아이디 */
   QUESTION_TEXT CLOB, /* 내용 */
   QUESTION_TIME DATE, /* 작성시간 */
   CONSTRAINT PK_QUESTION PRIMARY KEY (QUESTION_NUM)
);

/* 답변 */
CREATE TABLE ANSWER (
   ANSWER_NUM NUMBER NOT NULL, /* 답변일련번호 */
   ANSWER_DATE DATE, /* 작성시간 */
   ANSWER_TEXT CLOB, /* 답변내용 */
   QUESTION_NUM NUMBER, /* 문의일련번호 */
   BUSINESS_ID VARCHAR2(20), /* 작성자아이디 */
   CONSTRAINT PK_ANSWER PRIMARY KEY (ANSWER_NUM)
);

-- 외래키 설정
/* 권한 */
ALTER TABLE AUTHORITY
   ADD
      CONSTRAINT FK_MEMBER_TO_AUTHORITY
      FOREIGN KEY (
         MEMBER_ID
      )
      REFERENCES MEMBER (
         MEMBER_ID
      );
      
/* 음식점 정보 */      
ALTER TABLE RESTAURANT
   ADD
      CONSTRAINT FK_MEMBER_TO_RESTAURANT
      FOREIGN KEY (
         BUSINESS_ID
      )
      REFERENCES MEMBER (
         MEMBER_ID
      );
      
/* 메뉴 */      
ALTER TABLE MENU
   ADD
      CONSTRAINT FK_RESTAURANT_TO_MENU
      FOREIGN KEY (
         BUSINESS_ID
      )
      REFERENCES RESTAURANT (
         BUSINESS_ID
      );

ALTER TABLE MENU
   ADD
      CONSTRAINT FK_CLASSIFICATION_TO_MENU
      FOREIGN KEY (
         CLASSIFICATION_NUM
      )
      REFERENCES CLASSIFICATION (
         CLASSIFICATION_NUM
      );
      
/* 테이블 */
ALTER TABLE TABLE_
   ADD
      CONSTRAINT FK_RESTAURANT_TO_TABLE_
      FOREIGN KEY (
         BUSINESS_ID
      )
      REFERENCES RESTAURANT (
         BUSINESS_ID
      );
      
/* 테이블주문 */
ALTER TABLE ORDER_TABLE
   ADD
      CONSTRAINT FK_TABLE__TO_ORDER_TABLE
      FOREIGN KEY (
         TABLE_seq
      )
      REFERENCES TABLE_ (
         TABLE_seq
      );

ALTER TABLE ORDER_TABLE
   ADD
      CONSTRAINT FK_RESERVATION_TO_ORDER_TABLE
      FOREIGN KEY (
         RES_NUM
      )
      REFERENCES RESERVATION (
         RES_NUM
      );

/* 예약 */
ALTER TABLE RESERVATION
   ADD
      CONSTRAINT FK_MEMBER_TO_RESERVATION
      FOREIGN KEY (
         MEMBER_ID
      )
      REFERENCES MEMBER (
         MEMBER_ID
      );

ALTER TABLE RESERVATION
   ADD
      CONSTRAINT FK_RESTAURANT_TO_RESERVATION
      FOREIGN KEY (
         BUSINESS_ID
      )
      REFERENCES RESTAURANT (
         BUSINESS_ID
      );
      
/* 즐겨찾기 */
ALTER TABLE BOOKMARK
   ADD
      CONSTRAINT FK_MEMBER_TO_BOOKMARK
      FOREIGN KEY (
         MEMBER_ID
      )
      REFERENCES MEMBER (
         MEMBER_ID
      );
      
/* 게시판 */
ALTER TABLE BOARD
   ADD
      CONSTRAINT FK_MEMBER_TO_BOARD
      FOREIGN KEY (
         MEMBER_ID
      )
      REFERENCES MEMBER (
         MEMBER_ID
      );
      
/* 댓글 */
ALTER TABLE COMMEN
   ADD
      CONSTRAINT FK_BOARD_TO_COMMEN
      FOREIGN KEY (
         BOARD_NUM
      )
      REFERENCES BOARD (
         BOARD_NUM
      );

/* 후기 */
ALTER TABLE REVIEW
   ADD
      CONSTRAINT FK_MEMBER_TO_REVIEW
      FOREIGN KEY (
         MEMBER_ID
      )
      REFERENCES MEMBER (
         MEMBER_ID
      );

ALTER TABLE REVIEW
   ADD
      CONSTRAINT FK_RESTAURANT_TO_REVIEW
      FOREIGN KEY (
         BUSINESS_ID
      )
      REFERENCES RESTAURANT (
         BUSINESS_ID
      );

ALTER TABLE REVIEW
   ADD
      CONSTRAINT FK_RESERVATION_TO_REVIEW
      FOREIGN KEY (
         RES_NUM
      )
      REFERENCES RESERVATION (
         RES_NUM
      );
      
/* 신고 */
ALTER TABLE REPORT
   ADD
      CONSTRAINT FK_REVIEW_TO_REPORT
      FOREIGN KEY (
         REVIEW_NUM
      )
      REFERENCES REVIEW (
         REVEIW_NUM
      );

/* 추천 */
ALTER TABLE RECOMMEND
   ADD
      CONSTRAINT FK_REVIEW_TO_RECOMMEND
      FOREIGN KEY (
         REVEIW_NUM
      )
      REFERENCES REVIEW (
         REVEIW_NUM
      );
      
/* 문의 */
ALTER TABLE QUESTION
   ADD
      CONSTRAINT FK_MEMBER_TO_QUESTION
      FOREIGN KEY (
         MEMBER_ID
      )
      REFERENCES MEMBER (
         MEMBER_ID
      );
      
/* 답변 */
ALTER TABLE ANSWER
   ADD
      CONSTRAINT FK_QUESTION_TO_ANSWER
      FOREIGN KEY (
         QUESTION_NUM
      )
      REFERENCES QUESTION (
         QUESTION_NUM
      );
      
/*시퀀스*/
      drop sequence table_list_seq;
      drop sequence res_num_seq;
      drop sequence tabel_list_seq;
      drop sequence bookmark_num_seq;
      drop sequence review_num_seq;
      
      create sequence table_list_seq;
      create sequence res_num_seq;
      create sequence tabel_list_seq;
      create sequence bookmark_num_seq;
      create sequence review_num_seq;

      select * from seq;
      SELECT * FROM dual;