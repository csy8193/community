--------------------------------------------------------
--  DDL for Table ANIMAL
--------------------------------------------------------

  CREATE TABLE "ANIMAL" 
   (	"ANIMAL_NO" NUMBER, 
	"ANIMAL_NAME" VARCHAR2(20), 
	"ANIMAL_VARIETY" VARCHAR2(30), 
	"ANIMAL_SEX" VARCHAR2(10), 
	"ANIMAL_BIRTH" VARCHAR2(20), 
	"MEMBER_NO" NUMBER, 
	"ANIMAL_CATEGORY_CD" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "ANIMAL"."ANIMAL_NO" IS '동물 번호';
   COMMENT ON COLUMN "ANIMAL"."ANIMAL_NAME" IS '동물 이름';
   COMMENT ON COLUMN "ANIMAL"."ANIMAL_VARIETY" IS '동물 품종';
   COMMENT ON COLUMN "ANIMAL"."ANIMAL_SEX" IS '동물 성별';
   COMMENT ON COLUMN "ANIMAL"."ANIMAL_BIRTH" IS '동물 생일';
--------------------------------------------------------
--  DDL for Table ANIMAL_CATEGORY
--------------------------------------------------------

  CREATE TABLE "ANIMAL_CATEGORY" 
   (	"ANIMAL_CATEGORY_CD" NUMBER, 
	"ANIMAL_CATEGORY_NM" VARCHAR2(10)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "ANIMAL_CATEGORY"."ANIMAL_CATEGORY_CD" IS '동물 카테고리 번호';
   COMMENT ON COLUMN "ANIMAL_CATEGORY"."ANIMAL_CATEGORY_NM" IS '동물 카테고리 종류';
--------------------------------------------------------
--  DDL for Table ANIMAL_IMG
--------------------------------------------------------

  CREATE TABLE "ANIMAL_IMG" 
   (	"ANIMAL_IMG_NO" NUMBER, 
	"ANIMAL_IMG_PATH" VARCHAR2(200), 
	"ANIMAL_IMG_NM" VARCHAR2(30), 
	"ANIMAL_IMG_ORIGINAL" VARCHAR2(100), 
	"ANIMAL_IMG_LEVEL" VARCHAR2(20), 
	"ANIMAL_NO" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "ANIMAL_IMG"."ANIMAL_IMG_NO" IS '반려동물 이미지 번호';
   COMMENT ON COLUMN "ANIMAL_IMG"."ANIMAL_IMG_PATH" IS '반려동물이미지 경로';
   COMMENT ON COLUMN "ANIMAL_IMG"."ANIMAL_IMG_NM" IS '반려동물이미지명';
   COMMENT ON COLUMN "ANIMAL_IMG"."ANIMAL_IMG_ORIGINAL" IS '원본 반려동물이미지 명';
--------------------------------------------------------
--  DDL for Table BOARD
--------------------------------------------------------

  CREATE TABLE "BOARD" 
   (	"BOARD_NO" NUMBER, 
	"BOARD_TITLE" VARCHAR2(200), 
	"BOARD_CONTENT" CLOB, 
	"CREATE_DT" DATE DEFAULT SYSDATE, 
	"READ_COUNT" NUMBER DEFAULT 0, 
	"BOARD_CD" NUMBER, 
	"STATUS_CD" NUMBER DEFAULT 1, 
	"MEMBER_NO" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" 
 LOB ("BOARD_CONTENT") STORE AS BASICFILE (
  TABLESPACE "SYSTEM" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION 
  NOCACHE LOGGING 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;

   COMMENT ON COLUMN "BOARD"."BOARD_NO" IS '게시글';
   COMMENT ON COLUMN "BOARD"."BOARD_TITLE" IS '게시글';
   COMMENT ON COLUMN "BOARD"."BOARD_CONTENT" IS '게시글';
   COMMENT ON COLUMN "BOARD"."CREATE_DT" IS '게시글';
   COMMENT ON COLUMN "BOARD"."READ_COUNT" IS '게시글';
   COMMENT ON COLUMN "BOARD"."BOARD_CD" IS '게시판 코드';
   COMMENT ON COLUMN "BOARD"."STATUS_CD" IS '상태 코드';
   COMMENT ON COLUMN "BOARD"."MEMBER_NO" IS '회원번호';
--------------------------------------------------------
--  DDL for Table BOARD_CODE
--------------------------------------------------------

  CREATE TABLE "BOARD_CODE" 
   (	"BOARD_CD" NUMBER, 
	"BOARD_NAME" VARCHAR2(30)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "BOARD_CODE"."BOARD_CD" IS '게시판 코드';
   COMMENT ON COLUMN "BOARD_CODE"."BOARD_NAME" IS '게시판 이름';
--------------------------------------------------------
--  DDL for Table BOARD_PIC
--------------------------------------------------------

  CREATE TABLE "BOARD_PIC" 
   (	"BOARD_PIC_NO" NUMBER, 
	"BOARD_PIC_PATH" VARCHAR2(200), 
	"BOARD_PIC_NM" VARCHAR2(30), 
	"BOARD_PIC_ORIGINAL" VARCHAR2(100), 
	"BOARD_PIC_LEVEL" NUMBER, 
	"BOARD_NO" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "BOARD_PIC"."BOARD_PIC_NO" IS '게시판 사진 번호';
   COMMENT ON COLUMN "BOARD_PIC"."BOARD_PIC_PATH" IS '게시판 사진 경로';
   COMMENT ON COLUMN "BOARD_PIC"."BOARD_PIC_NM" IS '게시판 사진명';
   COMMENT ON COLUMN "BOARD_PIC"."BOARD_PIC_ORIGINAL" IS '원본게시판이미지명';
   COMMENT ON COLUMN "BOARD_PIC"."BOARD_PIC_LEVEL" IS '게시판사진 위지 지정값';
   COMMENT ON COLUMN "BOARD_PIC"."BOARD_NO" IS '게시글';
--------------------------------------------------------
--  DDL for Table LIKE
--------------------------------------------------------

  CREATE TABLE "LIKE" 
   (	"MEMBER_NO" NUMBER, 
	"BOARD_NO" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "LIKE"."MEMBER_NO" IS '회원번호';
   COMMENT ON COLUMN "LIKE"."BOARD_NO" IS '게시글';
--------------------------------------------------------
--  DDL for Table MEMBER
--------------------------------------------------------

  CREATE TABLE "MEMBER" 
   (	"MEMBER_NO" NUMBER, 
	"MEMBER_ID" VARCHAR2(20), 
	"MEMBER_PW" VARCHAR2(200), 
	"MEMBER_NM" VARCHAR2(15), 
	"MEMBER_EMAIL" VARCHAR2(50), 
	"ENROLL_DT" DATE DEFAULT SYSDATE, 
	"STATUS_CD" NUMBER DEFAULT 1, 
	"GRADE_CD" NUMBER DEFAULT 100, 
	"ANIMAL_PROFILE_IMG" VARCHAR2(200) DEFAULT '/resources/images/main/basis-profile-img.png'
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "MEMBER"."MEMBER_NO" IS '회원번호';
   COMMENT ON COLUMN "MEMBER"."MEMBER_ID" IS '회원아이디';
   COMMENT ON COLUMN "MEMBER"."MEMBER_PW" IS '회원 비밀번호';
   COMMENT ON COLUMN "MEMBER"."MEMBER_NM" IS '회원 이름';
   COMMENT ON COLUMN "MEMBER"."MEMBER_EMAIL" IS '회원 이메일';
   COMMENT ON COLUMN "MEMBER"."ENROLL_DT" IS '회원 가입일';
   COMMENT ON COLUMN "MEMBER"."STATUS_CD" IS '회원 상태 코드';
   COMMENT ON COLUMN "MEMBER"."GRADE_CD" IS '회원 등급 코드';
--------------------------------------------------------
--  DDL for Table MEMBER_GRADE
--------------------------------------------------------

  CREATE TABLE "MEMBER_GRADE" 
   (	"GRADE_CD" NUMBER, 
	"GRADE_NM" VARCHAR2(20)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "MEMBER_GRADE"."GRADE_CD" IS '회원 등급 코드';
   COMMENT ON COLUMN "MEMBER_GRADE"."GRADE_NM" IS '회원 등급 이름';
--------------------------------------------------------
--  DDL for Table MEMBER_STATUS
--------------------------------------------------------

  CREATE TABLE "MEMBER_STATUS" 
   (	"STATUS_CD" NUMBER, 
	"STATUS_NM" VARCHAR2(20)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "MEMBER_STATUS"."STATUS_CD" IS '회원 상태 코드';
   COMMENT ON COLUMN "MEMBER_STATUS"."STATUS_NM" IS '회원 상태 이름';
--------------------------------------------------------
--  DDL for Table POINT
--------------------------------------------------------

  CREATE TABLE "POINT" 
   (	"POINT_NO" NUMBER, 
	"POINT_SCORE" NUMBER DEFAULT 0, 
	"POINT_DATE" DATE DEFAULT SYSDATE, 
	"MEMBER_NO" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "POINT"."POINT_NO" IS '포인트 번호';
   COMMENT ON COLUMN "POINT"."POINT_SCORE" IS '포인트 점수';
   COMMENT ON COLUMN "POINT"."POINT_DATE" IS '포인트 획득 날짜';
   COMMENT ON COLUMN "POINT"."MEMBER_NO" IS '회원번호';
--------------------------------------------------------
--  DDL for Table PRODUCT
--------------------------------------------------------

  CREATE TABLE "PRODUCT" 
   (	"PRODUCT_NO" NUMBER, 
	"PRODUCT_NAME" VARCHAR2(50), 
	"PRODUCT_POINT" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "PRODUCT"."PRODUCT_NO" IS '상품 번호';
   COMMENT ON COLUMN "PRODUCT"."PRODUCT_NAME" IS '상품이름';
   COMMENT ON COLUMN "PRODUCT"."PRODUCT_POINT" IS '상품 포인트';
--------------------------------------------------------
--  DDL for Table PRODUCT_EXCHANGE
--------------------------------------------------------

  CREATE TABLE "PRODUCT_EXCHANGE" 
   (	"EXCHANGE_NO" NUMBER, 
	"EXCHANGE_DATE" DATE DEFAULT SYSDATE, 
	"MEMBER_NO" NUMBER, 
	"PRODUCT_NO" NUMBER
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "PRODUCT_EXCHANGE"."EXCHANGE_NO" IS '교환 번호';
   COMMENT ON COLUMN "PRODUCT_EXCHANGE"."EXCHANGE_DATE" IS '교환 날짜';
   COMMENT ON COLUMN "PRODUCT_EXCHANGE"."MEMBER_NO" IS '회원번호';
   COMMENT ON COLUMN "PRODUCT_EXCHANGE"."PRODUCT_NO" IS '상품 번호';
--------------------------------------------------------
--  DDL for Table REPLY
--------------------------------------------------------

  CREATE TABLE "REPLY" 
   (	"REPLY_NO" NUMBER, 
	"REPLY_CONTENT" VARCHAR2(1000), 
	"REPLY_CREATE_DT" DATE DEFAULT SYSDATE, 
	"BOARD_NO" NUMBER, 
	"MEMBER_NO" NUMBER, 
	"STATUS_CD" NUMBER DEFAULT 1, 
	"FEEDBACK" NUMBER DEFAULT NULL
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "REPLY"."REPLY_NO" IS '댓글번호';
   COMMENT ON COLUMN "REPLY"."REPLY_CONTENT" IS '댓글 내용';
   COMMENT ON COLUMN "REPLY"."REPLY_CREATE_DT" IS '댓글 작성일';
   COMMENT ON COLUMN "REPLY"."BOARD_NO" IS '게시글 번호';
   COMMENT ON COLUMN "REPLY"."MEMBER_NO" IS '회원번호';
   COMMENT ON COLUMN "REPLY"."STATUS_CD" IS '상태 코드';
--------------------------------------------------------
--  DDL for Table STATUS
--------------------------------------------------------

  CREATE TABLE "STATUS" 
   (	"STATUS_CD" NUMBER, 
	"STATUS_NM" VARCHAR2(20)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "STATUS"."STATUS_CD" IS '상태 코드';
   COMMENT ON COLUMN "STATUS"."STATUS_NM" IS '상태 이름';
