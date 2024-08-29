/* member */
CREATE TABLE member (
	member_no NUMBER NOT NULL, /* 회원번호 */
	member_id VARCHAR(20) NOT NULL UNIQUE, /* 회원아이디 */
	name VARCHAR2(20) NOT NULL, /* 이름 */
	age NUMBER NOT NULL, /* 나이 */
	address VARCHAR2(100) NOT NULL, /* 주소 */
	password VARCHAR2(20) NOT NULL, /* 비밀번호 */
	balance NUMBER DEFAULT 0/* 잔고 */
);

COMMENT ON TABLE member IS 'member';

COMMENT ON COLUMN member.member_no IS '회원번호';

COMMENT ON COLUMN member.member_id IS '회원아이디';

COMMENT ON COLUMN member.name IS '이름';

COMMENT ON COLUMN member.age IS '나이';

COMMENT ON COLUMN member.address IS '주소';

COMMENT ON COLUMN member.password IS '비밀번호';

COMMENT ON COLUMN member.balance IS '잔고';

ALTER TABLE member
	ADD
		CONSTRAINT PK_member
		PRIMARY KEY (
			member_no
		);

/* dealer */
CREATE TABLE dealer (
	dealer_no NUMBER NOT NULL, /* 딜러번호 */
    dealer_name VARCHAR2(20),
	dealer_id VARCHAR2(20), /* 딜러아이디 */
	dealer_password VARCHAR2(20), /* 딜러비밀번호 */
	dealer_option VARCHAR2(50), /* 딜러옵션 */
	self VARCHAR2(300), /* 자기소개 */
	rate NUMERIC default 0/* 평점 */
);

COMMENT ON TABLE dealer IS 'dealer';

COMMENT ON COLUMN dealer.dealer_no IS '딜러번호';

COMMENT ON COLUMN dealer.dealer_id IS '딜러아이디';

COMMENT ON COLUMN dealer.dealer_password IS '딜러비밀번호';

COMMENT ON COLUMN dealer.dealer_option IS '딜러옵션';

COMMENT ON COLUMN dealer.self IS '자기소개';

COMMENT ON COLUMN dealer.rate IS '평점';

ALTER TABLE dealer
	ADD
		CONSTRAINT PK_dealer
		PRIMARY KEY (
			dealer_no
		);

/* car */
CREATE TABLE car (
	car_no NUMBER NOT NULL, /* 차량모델넘버 */
	car_name VARCHAR2(30), /* 차량명 */
	drive VARCHAR2(30), /* 구동방식 */
	horse_power VARCHAR2(30), /* 최고출력 */
	fuel VARCHAR2(30), /* 연비 */
	quantity NUMBER, /* 재고량 */
	price NUMBER, /* 차량가격 */
	category VARCHAR2(20) /* 카테고리 */
);

COMMENT ON TABLE car IS 'car';

COMMENT ON COLUMN car.car_no IS '차량모델넘버';

COMMENT ON COLUMN car.car_name IS '차량명';

COMMENT ON COLUMN car.drive IS '구동방식';

COMMENT ON COLUMN car.horse_power IS '최고출력';

COMMENT ON COLUMN car.fuel IS '연비';

COMMENT ON COLUMN car.quantity IS '재고량';

COMMENT ON COLUMN car.price IS '차량가격';

COMMENT ON COLUMN car.category IS '카테고리';

ALTER TABLE car
	ADD
		CONSTRAINT PK_car
		PRIMARY KEY (
			car_no
		);
        

/* purchase */
CREATE TABLE purchase (
	purchase_no NUMBER NOT NULL, /* 구매번호 */
	member_no NUMBER, /* 회원번호 */
	sunroof NUMBER DEFAULT 0, /* 선루프여부 */
	seat NUMBER DEFAULT 0, /* 통풍시트여부 */
	around_view NUMBER DEFAULT 0, /* 어라운드뷰 */
	color VARCHAR2(20) NOT NULL, /* 색상 */
	purchase_date DATE NOT NULL, /* 구매일 */
	price NUMBER NOT NULL, /* 구매금액 */
	dealer_no NUMBER NOT NULL, /* 딜러번호 */
	car_no NUMBER NOT NULL /* 차량모델넘버 */
);



COMMENT ON TABLE purchase IS 'purchase';

COMMENT ON COLUMN purchase.purchase_no IS '구매번호';

COMMENT ON COLUMN purchase.member_no IS '회원번호';

COMMENT ON COLUMN purchase.sunroof IS '선루프여부';

COMMENT ON COLUMN purchase.seat IS '통풍시트여부';

COMMENT ON COLUMN purchase.around_view IS '어라운드뷰';

COMMENT ON COLUMN purchase.color IS '색상';

COMMENT ON COLUMN purchase.purchase_date IS '구매일';

COMMENT ON COLUMN purchase.price IS '구매금액';

COMMENT ON COLUMN purchase.dealer_no IS '딜러번호';

COMMENT ON COLUMN purchase.car_no IS '차량모델넘버';

ALTER TABLE purchase
	ADD
		CONSTRAINT PK_purchase
		PRIMARY KEY (
			purchase_no
		);

/* car_option */
CREATE TABLE car_option (
	op_no NUMBER NOT NULL, /* 옵션번호 */
	option_name VARCHAR2(50), /* 옵션명 */
	option_price NUMBER /* 가격 */
);

COMMENT ON TABLE car_option IS 'car_option';

COMMENT ON COLUMN car_option.op_no IS '옵션번호';

COMMENT ON COLUMN car_option.option_name IS '옵션명';

COMMENT ON COLUMN car_option.option_price IS '가격';

ALTER TABLE car_option
	ADD
		CONSTRAINT PK_car_option
		PRIMARY KEY (
			op_no
		);

/* review */
CREATE TABLE review (
	re_no NUMBER NOT NULL, /* 리뷰번호 */
	purchase_no NUMBER NOT NULL, /* 구매번호 */
	title VARCHAR2(20) NOT NULL, /* 제목 */
	content VARCHAR2(100) NOT NULL, /* 내용 */
	car_star NUMBER NOT NULL, /* 차량평점 */
	deal_star NUMBER NOT NULL, /* 딜러평점 */
	review_date DATE /* 등록일 */
);

COMMENT ON TABLE review IS 'review';

COMMENT ON COLUMN review.re_no IS '리뷰번호';

COMMENT ON COLUMN review.purchase_no IS '구매번호';

COMMENT ON COLUMN review.title IS '제목';

COMMENT ON COLUMN review.content IS '내용';

COMMENT ON COLUMN review.car_star IS '차량평점';

COMMENT ON COLUMN review.deal_star IS '딜러평점';

COMMENT ON COLUMN review.review_date IS '등록일';

ALTER TABLE review
	ADD
		CONSTRAINT PK_review
		PRIMARY KEY (
			re_no
		);

/* reply */
CREATE TABLE reply (
	reply_no NUMBER NOT NULL, /* 답변글번호 */
	re_no NUMBER NOT NULL, /* 리뷰번호 */
	reply_content VARCHAR2(100) NOT NULL, /* 내용 */
	reply_date DATE /* 등록일 */
);

COMMENT ON TABLE reply IS 'reply';

COMMENT ON COLUMN reply.reply_no IS '답변글번호';

COMMENT ON COLUMN reply.re_no IS '리뷰번호';

COMMENT ON COLUMN reply.reply_content IS '내용';

COMMENT ON COLUMN reply.reply_date IS '등록일';

ALTER TABLE reply
	ADD
		CONSTRAINT PK_reply
		PRIMARY KEY (
			reply_no
		);

ALTER TABLE purchase
	ADD
		CONSTRAINT FK_member_TO_purchase
		FOREIGN KEY (
			member_no
		)
		REFERENCES member (
			member_no
		);

ALTER TABLE purchase
	ADD
		CONSTRAINT FK_dealer_TO_purchase
		FOREIGN KEY (
			dealer_no
		)
		REFERENCES dealer (
			dealer_no
		);

ALTER TABLE purchase
	ADD
		CONSTRAINT FK_car_TO_purchase
		FOREIGN KEY (
			car_no
		)
		REFERENCES car (
			car_no
		);

ALTER TABLE review
	ADD
		CONSTRAINT FK_purchase_TO_review
		FOREIGN KEY (
			purchase_no
		)
		REFERENCES purchase (
			purchase_no
		);

ALTER TABLE reply
	ADD
		CONSTRAINT FK_review_TO_reply
		FOREIGN KEY (
			re_no
		)
		REFERENCES review (
			re_no
		);
        


--------------시퀀스 생성



/* 회원번호시퀀스 */
drop SEQUENCE member_no_seq ;
CREATE SEQUENCE member_no_seq 
	MINVALUE 0
	MAXVALUE 100
	INCREMENT BY 1
	START WITH 1;
    
 
/* 구매번호시퀀스 */
CREATE SEQUENCE purchase_no_seq 
	INCREMENT BY 1
	START WITH 1;
    
drop SEQUENCE purchase_no_seq ;

/* 리뷰번호시퀀스 */
drop SEQUENCE re_no_seq ;
CREATE SEQUENCE re_no_seq 
	INCREMENT BY 1
	START WITH 1;


/* 딜러번호시퀀스 */
drop SEQUENCE dealer_no_seq ;
CREATE SEQUENCE dealer_no_seq 
	INCREMENT BY 1
	START WITH 900;

/* 옵션번호시퀀스 */
drop SEQUENCE op_no_seq ;
CREATE SEQUENCE op_no_seq 
	INCREMENT BY 1
	START WITH 1;

/* 답변글번호시퀀스 */
drop SEQUENCE reply_no_seq ;
CREATE SEQUENCE reply_no_seq 
	INCREMENT BY 1
	START WITH 1;