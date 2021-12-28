--------------------------------------------------------
--  Ref Constraints for Table ANIMAL
--------------------------------------------------------

  ALTER TABLE "ANIMAL" ADD FOREIGN KEY ("MEMBER_NO")
	  REFERENCES "MEMBER" ("MEMBER_NO") ON DELETE CASCADE ENABLE;
  ALTER TABLE "ANIMAL" ADD FOREIGN KEY ("ANIMAL_CATEGORY_CD")
	  REFERENCES "ANIMAL_CATEGORY" ("ANIMAL_CATEGORY_CD") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table ANIMAL_IMG
--------------------------------------------------------

  ALTER TABLE "ANIMAL_IMG" ADD FOREIGN KEY ("ANIMAL_NO")
	  REFERENCES "ANIMAL" ("ANIMAL_NO") ON DELETE CASCADE ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BOARD
--------------------------------------------------------

  ALTER TABLE "BOARD" ADD FOREIGN KEY ("BOARD_CD")
	  REFERENCES "BOARD_CODE" ("BOARD_CD") ENABLE;
  ALTER TABLE "BOARD" ADD FOREIGN KEY ("STATUS_CD")
	  REFERENCES "STATUS" ("STATUS_CD") ENABLE;
  ALTER TABLE "BOARD" ADD FOREIGN KEY ("MEMBER_NO")
	  REFERENCES "MEMBER" ("MEMBER_NO") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BOARD_PIC
--------------------------------------------------------

  ALTER TABLE "BOARD_PIC" ADD FOREIGN KEY ("BOARD_NO")
	  REFERENCES "BOARD" ("BOARD_NO") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table LIKE
--------------------------------------------------------

  ALTER TABLE "LIKE" ADD FOREIGN KEY ("MEMBER_NO")
	  REFERENCES "MEMBER" ("MEMBER_NO") ENABLE;
  ALTER TABLE "LIKE" ADD FOREIGN KEY ("BOARD_NO")
	  REFERENCES "BOARD" ("BOARD_NO") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table MEMBER
--------------------------------------------------------

  ALTER TABLE "MEMBER" ADD FOREIGN KEY ("STATUS_CD")
	  REFERENCES "MEMBER_STATUS" ("STATUS_CD") ENABLE;
  ALTER TABLE "MEMBER" ADD FOREIGN KEY ("GRADE_CD")
	  REFERENCES "MEMBER_GRADE" ("GRADE_CD") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table POINT
--------------------------------------------------------

  ALTER TABLE "POINT" ADD FOREIGN KEY ("MEMBER_NO")
	  REFERENCES "MEMBER" ("MEMBER_NO") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PRODUCT_EXCHANGE
--------------------------------------------------------

  ALTER TABLE "PRODUCT_EXCHANGE" ADD FOREIGN KEY ("MEMBER_NO")
	  REFERENCES "MEMBER" ("MEMBER_NO") ENABLE;
  ALTER TABLE "PRODUCT_EXCHANGE" ADD FOREIGN KEY ("PRODUCT_NO")
	  REFERENCES "PRODUCT" ("PRODUCT_NO") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table REPLY
--------------------------------------------------------

  ALTER TABLE "REPLY" ADD FOREIGN KEY ("BOARD_NO")
	  REFERENCES "BOARD" ("BOARD_NO") ENABLE;
  ALTER TABLE "REPLY" ADD FOREIGN KEY ("MEMBER_NO")
	  REFERENCES "MEMBER" ("MEMBER_NO") ENABLE;
  ALTER TABLE "REPLY" ADD FOREIGN KEY ("STATUS_CD")
	  REFERENCES "STATUS" ("STATUS_CD") ENABLE;
  ALTER TABLE "REPLY" ADD FOREIGN KEY ("FEEDBACK")
	  REFERENCES "REPLY" ("REPLY_NO") ENABLE;
