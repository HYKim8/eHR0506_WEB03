package com.sist.ehr.cmn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 모든 메소드는 static method
 * @author sist
 *
 */
public class StringUtil {
	private static final Logger LOG = LoggerFactory.getLogger(StringUtil.class);
	
	
	
	public static Date getToDate(String dateStr,String format) {
		String formatStr = format;

		SimpleDateFormat transFormat = new SimpleDateFormat(formatStr);
		Date dateS = new Date();
		try {
			dateS = transFormat.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.debug("format:"+format);
		LOG.debug("dateS:"+dateS);
		return dateS;
	}
	/**
	 * UUID
	 *Method Name:getUUID
	 *작성일: 2020. 3. 11.
	 *작성자: sist
	 *설명:
	 *@return
	 */
	public static String getUUID() {
		UUID  uuid = UUID.randomUUID();
		String retUUID = uuid.toString().replace("-", "");
		LOG.debug("retUUID:"+retUUID);
		return retUUID;
	}
	
	
	/**
	 * 
	 *Method Name:getDate
	 *작성일: 2020. 3. 11.
	 *작성자: sist
	 *설명:
	 *@param format
	 *@return format string date
	 */
	public static String getDate(String format) {
		SimpleDateFormat formatter=new SimpleDateFormat(format);
		
		return formatter.format(new Date());
	}
	
	
	
	
	
	
	
	/**
	   * Paging처리 
	   * @param maxNum_i
	   * @param currPageNoIn_i
	   * @param rowsPerPage_i
	   * @param bottomCount_i
	   * @param url_i
	   * @param scriptName_i
	   * @return
	   */
	  public static String renderPaging(int maxNum, int currPageNo, int rowPerPage, int bottomCount,
	   String url, String scriptName) {

	   /**
	    * 총글수: 21
	    * 현재페이지: 1
	    * 한페이지에 보여질 행수: 10
	    * 바닥에 보여질 페이지 수: 10
	    * << < 1 2 3 4 5 6 7 8 9 10 > >>
	    */


	   int maxPageNo = ((maxNum - 1) / rowPerPage) + 1;//3
	   int startPageNo = ((currPageNo - 1) / bottomCount) * bottomCount + 1;//1
	   int endPageNo = ((currPageNo - 1) / bottomCount + 1) * bottomCount;//10
	   
	   int nowBlockNo = ((currPageNo - 1) / bottomCount) + 1;//1
	   int maxBlockNo = ((maxNum - 1) / bottomCount) + 1;//3

	   int inx = 0;
	   StringBuilder html = new StringBuilder();
	   if (currPageNo > maxPageNo) {
	    return "";
	   }

	   html.append("<table border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">   \n");
	   html.append("<tr>                       \n");
	   html.append("<td align=\"center\">                                                                    \n");
	   html.append("<ul class=\"pagination pagination-sm\">                                                  \n");
	   
	   // <<
	   if (nowBlockNo > 1 && nowBlockNo <= maxBlockNo) {
	    html.append("<li class=\"active\"> <a href=\"javascript:" + scriptName + "( '" + url+ "', 1 );\">  \n");
	    html.append("&laquo;   \n");
	    html.append("</a></li> \n");
	   }

	   // <
	   if (startPageNo > bottomCount) {
	    html.append("<li class=\"active\"> <a href=\"javascript:" + scriptName + "( '" + url + "'," + (startPageNo - 1)+ ");\"> \n");
	    html.append("<        \n");
	    html.append("</a></li>     \n");
	   }


	   // 1 2 3 ... 10 (숫자보여주기)
	   for (inx = startPageNo; inx <= maxPageNo && inx <= endPageNo; inx++) {
			if (inx == currPageNo) {// 현재 page
				html.append("<li  class=\"disabled\" 	>");
				html.append("<a  href=\"javascript:#\"  > ");
				html.append(inx);
				html.append("</a> \n");
				html.append("</li>");
			} else {
				html.append("<li  class=\"active\">");
				html.append("<a  href=\"javascript:" + scriptName + "('" + url + "'," + inx + ");\"  > ");
				html.append(inx);
				html.append("</a> \n");
				html.append("</li>");
			}
	   }
	   
	   // >
	   if (maxPageNo >= inx) {
	    html.append("<li class=\"active\"><a href=\"javascript:" + scriptName + "('" + url + "',"+ ((nowBlockNo * bottomCount) + 1) + ");\"> \n");
	    html.append(">                       \n");
	    html.append("</a></li>              \n");
	   }

	   // >>
	   if (maxPageNo >= inx) {
	    html.append("<li class=\"active\"><a href=\"javascript:" + scriptName + "('" + url + "'," + maxPageNo+ ");\">      \n");
	    html.append("&raquo;     \n");
	    html.append("</a></li>    \n");
	   }

	   html.append("</td>   \n");
	   html.append("</tr>   \n");
	   html.append("</table>   \n");

	   return html.toString();
	  }

	
	
	/**
	 * list,selectBoxName,selectNm,allYN
	 *Method Name:makeSelectBox
	 *작성일: 2020. 2. 27.
	 *작성자: sist
	 *설명:
	 *@return
	 */
//	public static String makeSelectBox(List<CodeVO> list
//			                           ,String selectBoxNm
//			                           ,String selectNm
//			                           ,boolean allYn
//			                           ) {
//		StringBuilder  sb=new StringBuilder();
//		sb.append("<select  class=\"form-control input-sm\" name='"+selectBoxNm+"' id='"+selectBoxNm+"' >\n");
//		
//		//전체
//		if(allYn==true) {
//			sb.append("<option value=''>전체</option>\n");
//		}
//		
//		//for
//		if(null !=list) {
//			for(CodeVO vo :list) {
//				sb.append("\t\t<option value='"+vo.getDtlId()+"'  ");
//				if(selectNm.equals(vo.getDtlId())) {
//					sb.append("selected");
//				}
//				
//				sb.append(">");
//				sb.append(vo.getDtlNm());
//				sb.append("</option>\n");
//			}
//		}
//		sb.append("</select> \n");
///*		<select name="search_div" id="search_div">
//			<option value="">전체</option>
//			<option value="10" <%if(searchDiv.equals("10"))out.print("selected"); %> >제목</option>
//			<option value="20" <%if(searchDiv.equals("20"))out.print("selected"); %> >내용</option>
//			<option value="30" <%if(searchDiv.equals("30"))out.print("selected"); %> >등록자</option>
//	    </select>	*/
//		
//		LOG.debug("===========================");
//		LOG.debug(sb.toString());
//		LOG.debug("===========================");
//		return sb.toString();
//	}
	
	/**
	 * 
	 *Method Name:nvl
	 *작성일: 2020. 2. 24.
	 *작성자: sist
	 *설명: null -> ""
	 *@param val String
	 *@return String
	 */  
	public static String nvl(String val) {
     
		return nvl(val,"");
	}
	
	/**
	 * 
	 *Method Name:nvl
	 *작성일: 2020. 2. 24.
	 *작성자: sist
	 *설명: request param null to ""
	 *@param val 원본 String
	 *@param rep 치환 String
	 *@return String
	 */
	public static String nvl(String val,String rep) {
		if(null == val || "".equals(val)) {
			val = rep;
		}
		
		return val;
	}
	
	/**
	 * 
	 *Method Name:getCutUTFString
	 *작성일: 2020. 2. 27.
	 *작성자: sist
	 *설명: 문자를 특정길이
	 *@param str
	 *@param len
	 *@param tail 
	 *@return
	 */
	public static String getCutUTFString(String str, int len, String tail) {
		if (str.length() <= len) {
			return str;
		}
		StringCharacterIterator sci = new StringCharacterIterator(str);
		StringBuffer buffer = new StringBuffer();
		buffer.append(sci.first());
		for (int i = 1; i < len; i++) {
			if (i < len - 1) {
				buffer.append(sci.next());
			} else {
				char c = sci.next();
				if (c != 32) { // 마지막 charater가 공백이 아닐경우
					buffer.append(c);
				}
			}
		}
		buffer.append(tail);
		return buffer.toString();
	}	  
}
