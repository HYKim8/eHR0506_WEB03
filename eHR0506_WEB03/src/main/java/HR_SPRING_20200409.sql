DROP TABLE HR_MEMBER;

CREATE TABLE HR_MEMBER 
(	
	U_ID    VARCHAR2(20 BYTE) NOT NULL , 
	NAME    VARCHAR2(50 CHAR), 
	PASSWD  VARCHAR2(50 CHAR), 
	U_LEVEL NUMBER(1) NOT NULL,
	LOGIN	NUMBER(7) NOT NULL,
	RECOMMEND	NUMBER(7) NOT NULL,
	MAIL 	VARCHAR2(350 BYTE) NOT NULL,
	REG_DT	DATE DEFAULT SYSDATE,	
	CONSTRAINT PK_MEMBER PRIMARY KEY (U_ID)
); 	 
--user01=new UserVO("j01_124","이상무","1234",Level.BASIC,1,0,"jamesol@paran.com","");	 
INSERT INTO hr_member (
    u_id,
    name,
    passwd,
    u_level,
    login,
    recommend,
    mail
) VALUES (
    :v0,
    :v1,
    :v2,
    :v3,
    :v4,
    :v5,
    :v6
);     
select * from hr_member;

rollback;
     
--단건조회
SELECT
    u_id,
    name,
    passwd,
    u_level,
    login,
    recommend,
    mail,
    TO_CHAR(reg_dt,'YYYY/MM/DD HH24MISS') AS reg_dt
FROM
    hr_member
WHERE u_id = :U_ID;    
     
     
--수정     
UPDATE hr_member
SET  name = :v1,
     passwd = :v2,
     u_level = :v3,
     login = :v4,
     recommend = :v5,
     mail = :v6,
     reg_dt = sysdate
WHERE
    u_id = :v0;     
     
     
select * from hr_member;     
     
     
     
     
--목록조회     
SELECT T1.*,T2.*
FROM(
    SELECT B.U_ID,
           B.NAME,
           B.PASSWD,
           B.U_LEVEL,
           B.LOGIN,
           B.RECOMMEND,
           B.MAIL,
           TO_CHAR(B.REG_DT,'YYYY/MM/DD') REG_DT,
           RNUM
    FROM(
        SELECT ROWNUM RNUM,
               A.*
        FROM (
            SELECT * 
            FROM hr_member
            --검색조건
            WHERE U_ID LIKE  :U_ID || '%'
        )A --10
        WHERE ROWNUM <= (&PAGE_SIZE*(&PAGE_NUM-1)+&PAGE_SIZE)
    )B --1
    WHERE B.RNUM >= (&PAGE_SIZE*(&PAGE_NUM-1)+1)
    )T1 CROSS JOIN
    (
    SELECT count(*) total_cnt 
    FROM hr_member
    --검색조건
    WHERE U_ID LIKE  :U_ID || '%'
    )T2
    
     
--100만건 DATA입력
INSERT INTO hr_member
SELECT 'j_hr'||lpad(rownum,7,'0'),
    '이상무'||lpad(rownum,7,'0'),
    '1234',
    DECODE(MOD(rownum,1000),0,'2','1'),
    MOD(rownum,50),
    MOD(rownum,30),
    'james'||lpad(rownum,7,'0')||'@naver.com',
    TO_CHAR(sysdate - rownum/24,'YYYYMMDD')
FROM dual
CONNECT BY level <=1000000
;

commit;     
     