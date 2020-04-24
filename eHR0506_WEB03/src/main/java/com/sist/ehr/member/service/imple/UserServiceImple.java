/**
 * 
 */
package com.sist.ehr.member.service.imple;

import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.sist.ehr.cmn.DTO;
import com.sist.ehr.member.service.Level;
import com.sist.ehr.member.service.UserDao;
import com.sist.ehr.member.service.UserService;
import com.sist.ehr.member.service.UserVO;



/**
 * @author sist
 *
 */
@Service
public class UserServiceImple implements UserService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	/*
	 * case BASIC: return user.getLogin()>=50; case SILVER:return
	 * user.getRecommend()>=30;
	 */

	public static final int MIN_LOGINCOUNT_FOR_SILVER = 50;
	public static final int MIN_RECCOMENDCOUNT_FOR_GOLD = 30;

	@Autowired
	private UserDao userDao;

	@Autowired
	@Qualifier("dummyMailSender")
	private MailSender mailSender;




	public UserServiceImple() {

	}

	/**
	 * Spring Java Mail ------------------------- -----------------------
	 * SimpleMailMessage(Spring) MimeMessage MailSender Transport JavaMailSenderImpl
	 * ------------------------- -----------------------
	 * 
	 * 등업된 사용자에게 메일 전송
	 * 
	 * @param user
	 */
	private void sendUpgradeEmail(UserVO user) {
		/*
		 * POP 서버명 : pop.naver.com SMTP 서버명 : smtp.naver.com POP 포트 : 995, 보안연결(SSL) 필요
		 * SMTP 포트 : 465, 보안 연결(SSL) 필요 아이디 : jamesol 비밀번호 : 네이버 로그인 비밀번호
		 */

		// ------------------------------------------
		// 받는사람
		// 제목
		// 내용
		// ------------------------------------------
		// 받는사람
		String recipient = user.getEmail();
		// 제목
		String title = user.getName() + "님 등업(https://cafe.naver.com/kndjang)";
		// 내용
		String contents = user.getU_id() + "님 등급이\n" + user.getLevel().name() + "로 등업 되었습니다.";

		// ------------------------------------------
		// Message에,받는사람,제목,내용,인증
		// 전송:Java
		// ------------------------------------------

		SimpleMailMessage mimeMessage = new SimpleMailMessage();

		// 보내는 사람
		mimeMessage.setFrom("jamesol@naver.com");

		// 받는사람
		mimeMessage.setTo(recipient);
		// 제목
		mimeMessage.setSubject(title);
		// 내용
		mimeMessage.setText(contents);

		// 전송
		mailSender.send(mimeMessage);

		LOG.debug("===================");
		LOG.debug("=mail send to=" + recipient);
		LOG.debug("===================");

	}

//	/**
//	 * 등업된 사용자에게 메일 전송
//	 * @param user
//	 */
//	private void sendUpgradeEmail(UserVO user) {
//		/*
//			POP 서버명 : pop.naver.com
//			SMTP 서버명 : smtp.naver.com
//			POP 포트 : 995, 보안연결(SSL) 필요
//			SMTP 포트 : 465, 보안 연결(SSL) 필요
//			아이디 : jamesol
//			비밀번호 : 네이버 로그인 비밀번호
//		 */
//		String smtpHost = "smtp.naver.com";
//		final String userId   = "jamesol";     
//		final String userPass = "비밀번호";
//		int   port      = 465;
//		//------------------------------------------
//		//받는사람		
//		//제목		
//		//내용		
//		//SMTP서버 설정		
//		//인증
//		//------------------------------------------
//		
//		//받는사람
//		String recipient = user.getEmail();	
//		//제목
//		String title     = user.getName()+"님 등업(https://cafe.naver.com/kndjang)";		
//		//내용
//		String contents  = user.getU_id()+"님 등급이\n"+user.getLevel().name()+"로 등업 되었습니다.";
//		//SMTP서버 설정	
//		Properties props= System.getProperties();
//		props.put("mail.smtp.host", smtpHost);
//		props.put("mail.smtp.port", port);
//		props.put("mail.smtp.auth", true);
//		props.put("mail.smtp.ssl.enable", true);
//		props.put("mail.smtp.ssl.trust", smtpHost);		
//		//인증
//		Session  session = Session.getInstance(props, new Authenticator() {
//			String uName = userId;
//			String passwd= userPass;
//			
//			protected  PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(userId,passwd);
//			}
//		});
//		session.setDebug(true);
//		
//		//------------------------------------------
//		//Message에,받는사람,제목,내용,인증
//		//전송:Java
//		//------------------------------------------
//		Message  mimeMessage  =new MimeMessage(session);
//		
//		try {
//			//보내는 사람
//			mimeMessage.setFrom(new InternetAddress("jamesol@naver.com"));
//			
//			//받는사람
//			mimeMessage.setRecipient(Message.RecipientType.TO
//					            , new InternetAddress(recipient));
//			
//			//제목
//			mimeMessage.setSubject(title);
//			//내용
//			mimeMessage.setText(contents);
//			
//			//전송
//			Transport.send(mimeMessage);
//			
//		} catch (AddressException e) {
//			LOG.debug("===================");
//			LOG.debug("=AddressException="+e.getMessage());
//			LOG.debug("===================");
//			e.printStackTrace();
//		} catch (MessagingException e) {
//			LOG.debug("===================");
//			LOG.debug("=MessagingException="+e.getMessage());
//			LOG.debug("===================");			
//			e.printStackTrace();
//		}
//		
//		LOG.debug("===================");
//		LOG.debug("=mail send to="+recipient);
//		LOG.debug("===================");		
//		
//	}

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 최초가입시 : Level.BASIC
	 * 
	 * @param user
	 */
	public int add(UserVO user) {
		// Level null -> Level.BASIC
		if (null == user.getLevel()) {
			user.setLevel(Level.BASIC);
		}

		return userDao.doInsert(user);

	}

	// 등업조건
	private boolean canUpgradeLevel(UserVO user) {
		Level currentLevel = user.getLevel();

		switch (currentLevel) {
		case BASIC:
			return user.getLogin() >= MIN_LOGINCOUNT_FOR_SILVER;
		case SILVER:
			return user.getRecommend() >= MIN_RECCOMENDCOUNT_FOR_GOLD;
		case GOLD:
			return false;
		default:
			throw new IllegalArgumentException("Unknown Level:" + currentLevel);
		}

	}

	// 레벨업그레이드
	public void upgradeLevel(UserVO user) {

		Level nextLevel = user.getLevel().getNextLevel();
		// GOLD
		if (null == nextLevel) {
			LOG.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			LOG.debug(user.getLevel() + "은 업그레이드가 불가능 합니다.");
			LOG.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			throw new IllegalArgumentException(user.getLevel() + "은 업그레이드가 불가능 합니다.");
		} else {
			// BASIC -> SILVER
			// SILVER-> GOLD
			user.setLevel(nextLevel);
		}
		  
		//Transaction 예외 발생용.: 
//		String id ="j04_124";
//		if(user.getU_id().equals(id)) {
//			LOG.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//			LOG.debug(user.getLevel() + "은 업그레이드가 불가능 합니다.");
//			LOG.debug("&&&&&&&&&&&&&&&&&&&&&&&&&&&");		
//			throw new IllegalArgumentException(user.getLevel() + "은 업그레이드가 불가능 합니다.");
//		}
		
		
		
		
		
		userDao.doUpdate(user);
		// mail send
		sendUpgradeEmail(user);
	}

	/**
	 * 트랜잭션 코드가 소스에서 사라짐:UserServiceTx (DI)통해 주입 부가기능 부여.
	 * 사용자 등업 1. 전체 사용자를 읽어 들인다. 2. 등업 대상자를 선별한다. 2.1. BASIC사용자 : 로그인CNT 50이상(=포함)이면
	 * : SILVER 2.2. SILVER사용자: 추천CNT 30이상(=포함): GOLD 2.3. GOLD대상 아님. 3. 등업한다.
	 * 
	 * @throws SQLException
	 */
	public void upgradeLevels(UserVO userVO) throws Exception {
		
		List<UserVO> list = (List<UserVO>) userDao.getAll(userVO);
		for (UserVO user : list) {
			if (canUpgradeLevel(user) == true) {// upgrade대상 선별
				upgradeLevel(user);// upgrade수행
			}

		} // --for

	}

	@Override
	public int doInsert(DTO dto) {
		return userDao.doInsert(dto);
	}

	@Override
	public int doUpdate(DTO dto) {
		return userDao.doUpdate(dto);
	}

	@Override
	public DTO doSelectOne(DTO dto) {
		return userDao.doSelectOne(dto);
	}

	@Override
	public int doDelete(DTO dto) {
		return userDao.doDelete(dto);
	}

	@Override
	public List<?> doRetrieve(DTO dto) {
		return userDao.doRetrieve(dto);
	}

}









