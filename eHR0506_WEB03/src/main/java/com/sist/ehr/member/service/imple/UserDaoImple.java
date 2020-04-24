/**
 * 
 */
package com.sist.ehr.member.service.imple;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sist.ehr.cmn.DTO;
import com.sist.ehr.cmn.SearchVO;
import com.sist.ehr.member.service.Level;
import com.sist.ehr.member.service.UserDao;
import com.sist.ehr.member.service.UserVO;

/**
 * @author sist
 *
 */
@Repository("userDao")
public class UserDaoImple implements UserDao {
	//Logger
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	  
	
	RowMapper<UserVO> rowMapper = new RowMapper<UserVO>() {

		public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserVO outData=new UserVO();
			outData.setU_id(rs.getString("u_id"));
			outData.setName(rs.getString("name"));
			outData.setPasswd(rs.getString("passwd"));
			
			//-----------------------------------------
			//-2020/04/09 등업 요건 추가
			//-----------------------------------------			
			outData.setLevel(Level.valueOf(rs.getInt("u_level")));
			outData.setLogin(rs.getInt("login"));
			outData.setRecommend(rs.getInt("recommend"));
			outData.setEmail(rs.getString("mail"));
			outData.setRegDt(rs.getString("reg_dt"));
			outData.setNum(rs.getInt("rnum"));
			outData.setTotalCnt(rs.getInt("total_cnt"));
			return outData;
		}

	};
	

	//JDBCTemplate
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public UserDaoImple() {}
	





	public int doInsert(DTO dto) {
		int flag = 0;
		UserVO inVO = (UserVO) dto;
		
		StringBuilder  sb=new StringBuilder();
		sb.append(" INSERT INTO hr_member ( \n");
		sb.append("     u_id,               \n");
		sb.append("     name,               \n");
		sb.append("     passwd,             \n");
		sb.append("     u_level,            \n");
		sb.append("     login,              \n");
		sb.append("     recommend,          \n");
		sb.append("     mail                \n");
		sb.append(" ) VALUES (              \n");
		sb.append("     ?,                  \n");
		sb.append("     ?,                  \n");
		sb.append("     ?,                  \n");
		sb.append("     ?,                  \n");
		sb.append("     ?,                  \n");
		sb.append("     ?,                  \n");
		sb.append("     ?                   \n");
		sb.append(" )                       \n");	
		//Query수행
		LOG.debug("==============================");
		LOG.debug("=Query=\n"+sb.toString());
		LOG.debug("=Param=\n"+inVO.toString());
		Object[] args= {inVO.getU_id()
				       ,inVO.getName()
				       ,inVO.getPasswd()
				       ,inVO.getLevel().intValue()
				       ,inVO.getLogin()
				       ,inVO.getRecommend()
				       ,inVO.getEmail()
		};
		flag = this.jdbcTemplate.update(sb.toString(), args);
		
		
		LOG.debug("==============================");
		
		return flag;
	}

	public int doUpdate(DTO dto) {
		int flag = 0;
		UserVO inVO = (UserVO) dto;
		StringBuilder sb=new StringBuilder();
		sb.append(" UPDATE hr_member         \n");
		sb.append(" SET  name = ?,           \n");
		sb.append("      passwd = ?,         \n");
		sb.append("      u_level = ?,        \n");
		sb.append("      login = ?,          \n");
		sb.append("      recommend = ?,      \n");
		sb.append("      mail = ?,           \n");
		sb.append("      reg_dt = sysdate    \n");
		sb.append(" WHERE                    \n");
		sb.append("     u_id = ?             \n");
		
		LOG.debug("==============================");
		LOG.debug("=Query=\n"+sb.toString());
		LOG.debug("=Param= "+inVO.toString());
		Object[] args= {inVO.getName()
				      ,inVO.getPasswd()
				      ,inVO.getLevel().intValue()
				      ,inVO.getLogin()
				      ,inVO.getRecommend()
				      ,inVO.getEmail()
				      ,inVO.getU_id()};
		flag = this.jdbcTemplate.update(sb.toString(), args);
		LOG.debug("=flag= "+flag);
		LOG.debug("==============================");
		return flag;
	}
	
	public int count(DTO dto) {
		int cnt = 0;
		UserVO inVO = (UserVO) dto;
		StringBuilder  sb=new StringBuilder();
		sb.append(" SELECT COUNT(*) cnt \n");
		sb.append(" FROM  HR_MEMBER     \n");
		sb.append(" WHERE u_id like ?   \n");
		
		
		//Query수행
		LOG.debug("==============================");
		LOG.debug("=Query=\n"+sb.toString());
		LOG.debug("=Param= "+inVO.toString());
		cnt = this.jdbcTemplate.queryForObject(sb.toString()
				, new Object[] {"%"+inVO.getU_id()+"%"}
		        , Integer.class);
		
		LOG.debug("=cnt= "+cnt);
		return cnt;
	}
	

	public DTO doSelectOne(DTO dto) {
		UserVO outVO = null;        //return UserVO
		UserVO inVO  = (UserVO) dto;//Param UserVO
		StringBuilder  sb=new StringBuilder();
		sb.append(" SELECT                                               \n");
		sb.append("     u_id,                                            \n");
		sb.append("     name,                                            \n");
		sb.append("     passwd,                                          \n");
		sb.append("     u_level,                                         \n");
		sb.append("     login,                                           \n");
		sb.append("     recommend,                                       \n");
		sb.append("     mail,                                            \n");
		sb.append("     TO_CHAR(reg_dt,'YYYY/MM/DD HH24MISS') AS reg_dt, \n");
		sb.append("     1 rnum,       \n"); 
		sb.append("     1 total_cnt   \n"); 		
		sb.append(" FROM                                                 \n");
		sb.append("     hr_member                                        \n");
		sb.append(" WHERE u_id = ?                                       \n");
		
		//Query수행
		LOG.debug("==============================");
		LOG.debug("=Query=\n"+sb.toString());
		LOG.debug("=Param=\n"+inVO.getU_id());
		
		Object []args = {inVO.getU_id()};
		outVO = this.jdbcTemplate.queryForObject(sb.toString()
				,args
				,rowMapper); 
		LOG.debug("=outVO=\n"+outVO);
		LOG.debug("==============================");
		
		
		
		return outVO;
	}

	public int doDelete(DTO dto) {
		int flag = 0;
		UserVO inVO = (UserVO) dto;
		StringBuilder  sb=new StringBuilder();
		sb.append(" DELETE FROM hr_member \n");
		sb.append(" WHERE u_id = ?        \n");
		LOG.debug("==============================");
		LOG.debug("=Query=\n"+sb.toString());
		LOG.debug("=Param="+inVO);
		
		Object[] args = {inVO.getU_id()};
		flag = jdbcTemplate.update(sb.toString(), args);
		
		LOG.debug("=flag="+flag);		
		LOG.debug("==============================");	
		return flag;
	}

	/**
	 * 
	 *Method Name:getAll
	 *작성일: 2020. 4. 8.
	 *작성자: sist
	 *설명: 전체 조회
	 *@param dto
	 *@return
	 */
	public List<UserVO> getAll(DTO dto) {
		UserVO inVO = (UserVO) dto;
		StringBuilder  sb=new StringBuilder();
		sb.append(" SELECT                                               \n");
		sb.append("     u_id,                                            \n");
		sb.append("     name,                                            \n");
		sb.append("     passwd,                                          \n");
		sb.append("     u_level,                                         \n");
		sb.append("     login,                                           \n");
		sb.append("     recommend,                                       \n");
		sb.append("     mail,                                            \n");
		sb.append("     TO_CHAR(reg_dt,'YYYY/MM/DD HH24MISS') AS reg_dt, \n");
		sb.append("     1 rnum,       \n"); 
		sb.append("     1 total_cnt   \n"); 		
		sb.append(" FROM                                                 \n");
		sb.append("     hr_member                      \n");
		sb.append(" WHERE  u_id like ?                 \n");
		sb.append(" ORDER BY u_id                      \n");
		LOG.debug("==============================");
		LOG.debug("=Query=\n"+sb.toString());
		LOG.debug("=Param="+inVO);
		//new Object[] {"%"+inVO.getU_id()+"%"}
		List<UserVO> list = this.jdbcTemplate.query(sb.toString()
				               , new Object[] {"%"+inVO.getU_id()+"%"}
				               , rowMapper);
		LOG.debug("=list="+list);
		LOG.debug("==============================");
		return list;
	}
	
	public List<?> doRetrieve(DTO dto) {
		SearchVO  inVO= (SearchVO) dto;
		//검색구분
		  //ID : 10
		  //이름: 20
		//검색어
		StringBuilder whereSb=new StringBuilder();
		
		if(null !=inVO && !"".equals(inVO.getSearchDiv())) {
			if(inVO.getSearchDiv().equals("10")) {
				whereSb.append("WHERE u_id like '%' || ? ||'%'   \n");
			}else if(inVO.getSearchDiv().equals("20")) {
				whereSb.append("WHERE name like '%' || ? ||'%'   \n");
			}
		}
		
		
		StringBuilder sb=new StringBuilder();
		sb.append("SELECT T1.*,T2.*                                              \n");
		sb.append("FROM(                                                         \n");
		sb.append("    SELECT  B.u_id,                                           \n");
		sb.append("            B.name,                                           \n");
		sb.append("            B.passwd,                                         \n");
		sb.append("            B.u_level,                                        \n");
		sb.append("            B.login,                                          \n");
		sb.append("            B.recommend,                                      \n");
		sb.append("            B.mail,                                           \n");
		sb.append("            TO_CHAR(B.reg_dt,'YYYY/MM/DD') reg_dt,            \n");
		sb.append("            rnum                                              \n");		
		sb.append("    FROM(                                                     \n");
		sb.append("        SELECT ROWNUM rnum,                                   \n");
		sb.append("               A.*                                            \n");
		sb.append("        FROM (                                                \n");
		sb.append("            SELECT *                                          \n");
		sb.append("            FROM hr_member                                    \n");
		sb.append("            --검색조건                                                                               \n");
		//--검색----------------------------------------------------------------------
		sb.append(whereSb.toString());
		//--검색----------------------------------------------------------------------			
		sb.append("            Order by reg_dt desc                              \n");
		sb.append("        )A --10                                               \n");
		//sb.append("        WHERE ROWNUM <= (&PAGE_SIZE*(&PAGE_NUM-1)+&PAGE_SIZE) \n");
		sb.append("        WHERE ROWNUM <= (?*(?-1)+?) \n");
		sb.append("    )B --1                                                    \n");
		//sb.append("    WHERE B.RNUM >= (&PAGE_SIZE*(&PAGE_NUM-1)+1)              \n");
		sb.append("    WHERE B.RNUM >= (?*(?-1)+1)              \n");
		sb.append("    )T1 CROSS JOIN                                            \n");
		sb.append("    (                                                         \n");
		sb.append("    SELECT count(*) total_cnt                                 \n");
		sb.append("    FROM hr_member                                            \n");
		sb.append("    --검색조건                                                   \n");
		//--검색----------------------------------------------------------------------
		sb.append(whereSb.toString());
		//--검색----------------------------------------------------------------------
		sb.append("    )T2                                                       \n");

		//param 
		List<Object> listArg = new ArrayList<Object>();
		
		
		//param set
		if(null !=inVO && !"".equals(inVO.getSearchDiv())) {
			listArg.add(inVO.getSearchWord());
			listArg.add(inVO.getPageSize());
			listArg.add(inVO.getPageNum());
			listArg.add(inVO.getPageSize());
			listArg.add(inVO.getPageSize());
			listArg.add(inVO.getPageNum());				
			listArg.add(inVO.getSearchWord());
			
		}else {
			listArg.add(inVO.getPageSize());
			listArg.add(inVO.getPageNum());
			listArg.add(inVO.getPageSize());
			listArg.add(inVO.getPageSize());
			listArg.add(inVO.getPageNum());			
		}
		List<UserVO> retList = this.jdbcTemplate.query(sb.toString(), listArg.toArray(), rowMapper);
		LOG.debug("query \n"+sb.toString());
		LOG.debug("param:"+listArg);
		return retList;
	}

}















