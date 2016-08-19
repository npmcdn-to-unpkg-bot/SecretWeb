package ca.tklab.core.utils;

import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.regexp.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlUtils {

	private static final Logger logger = LoggerFactory.getLogger(HtmlUtils.class);

    // static final RE reg_NL = new RE("(/(\015\012)|(\015)|(\012)/)");
    static final RE reg_NL = new RE("(\015\012)|(\015)|(\012)");

    static RE reg_HTML_1 = new RE("&");

    static RE reg_HTML_2 = new RE("\"");

    static RE reg_HTML_3 = new RE("'");

    static RE reg_HTML_4 = new RE("<");

    static RE reg_HTML_5 = new RE(">");

    public static String getChecked(String origin, String comp) {
        return origin.equals(comp) ? "checked=\"checked\"" : "";
    }

    public static String getSelected(String origin, String comp) {
        return origin.equals(comp) ? "selected=\"selected\"" : "";
    }

    public static String getChecked(int origin, int comp) {
        return (origin == comp) ? "checked=\"checked\"" : "";
    }

    public static String getSelected(int origin, int comp) {
        return (origin == comp) ? "selected=\"selected\"" : "";
    }

    public static String crop(String origin, int i) {
        if (origin == null)
            return "";
        return (origin.length() > i) ? origin.substring(0, i) + "..." : origin;
    }

    public static int getTotalPage(long totalCnt, int listBlock) {
        return (int) Math.ceil((double) totalCnt / listBlock);
    }

    /**
     * <pre>
     * Paging시 전체 갯수를 가져옴, nm 은 filed Name
     */
    public static int getTocalCount(List<Map<String, Object>> itemList,
            String filedName) {
        int totalCnt = 0;
        Object obj = null;
        if (itemList != null) {
            if (!itemList.isEmpty()) {
                obj = itemList.get(0).get(filedName);
                if (obj != null) {
                    totalCnt = ((Number) obj).intValue();
                }
            }
        }

        return totalCnt;
    }

    /**
     * <pre>
     * Paging시 전체 갯수를 가져옴  !!주의!!  전체 목록을 자져오는 부문에서 <b><font color="RED">COUNT(*) OVER(), TOTAL_CNT</font></b>를 추가해 주어야 함!
     *  SELECT RNUM, <b><font color="RED">TOTAL_CNT</font></b>, ......   *             
     *  FROM  ( SELECT  ROW_NUMBER() OVER(ORDER BY V_IMP DESC, NOT_NO DESC)     AS RNUM,
     *                  <b><font color="RED">COUNT(*) OVER()    AS TOTAL_CNT</font></b>
     *                  NOT_NO, TITLE, *                   ......
     *          FROM    NOTICE
     *          WHERE 1 = 1    
     *             [ WHERE 조건 ]
     *        ) A
     *  WHERE CEIL(RNUM/10) = #pageNo#
     * </pre>
     * 
     * @param itemList
     * @return
     */
    public static int getTocalCount(List<Map<String, Object>> itemList) {
        return getTocalCount(itemList, "total_cnt");
    }

    /**
     * 
     * nl2br -- 문자열의 모든 줄바꿈 앞에 HTML 줄바꿈 태그" <br />
     * "를 삽입합니다.
     * 
     * Windows style newlines are like this: <br>
     * <CR><LF>-> \r\n -> \015\012 <br>
     * 
     * Mac style like this: <br>
     * <CR>-> \r -> \015 <br>
     * 
     * Unix style like this: <br>
     * <LF>-> \n -> \012 <br>
     * 
     * \012 -> 10 -> \n<br>
     * \015 -> 13 -> \r<br>
     * 
     * @param txt
     * @return
     */
    public static String nl2br(String txt) {
        return nl2br(txt, null);
    }

    public static String nl2br(String txt, String def) {
        try {
            if (txt == null) {
                return "&nbsp;";
            } else {
                if (def == null) {
                    return reg_NL.subst(ObjUtils.getSafeString(ObjUtils.nvl(txt, "&nbsp;")), "<br />");
                } else {
                    return reg_NL.subst(ObjUtils.getSafeString(ObjUtils.nvl(txt, "&nbsp;")),
                            ObjUtils.getSafeString(ObjUtils.nvl(def, "")));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "&nbsp;";
        }
    }

    /**
     * 개행 문자 없애기
     * 
     * @param txt
     * @return
     */
    public static String removenl(String txt) {
        try {
            if (txt == null) {
                return null;
            } else {
                return reg_NL.subst(ObjUtils.getSafeString(ObjUtils.nvl(txt, "")), "");

            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "&nbsp;";
        }
    }

    /**
     * val과 cur 이 같으면 checked 리턴
     * 
     * @param val
     * @param cur
     * @return
     */
    public static String checked(String val, String cur) {
        return equal(val, cur, " checked ");
    }

    /**
     * val과 cur 이 같으면 selected 리턴
     * 
     * @param val
     * @param cur
     * @return
     */
    public static String selected(String val, String cur) {
        return equal(val, cur, " selected ");
    }

    /**
     * var1과 var가 같은면 str 리턴 아니면 null
     * 
     * @param var1
     * @param var2
     * @param str
     * @return
     */
    public static String equal(String var1, String var2, String str) {
        String rv = null;
        if (var1 != null) {
            if (var1.equals(var2)) {
                rv = str;
            }
        }
        return rv;
    }
    
    public static String getBaseUrl(HttpServletRequest hreq) {
    	StringBuffer reqUrl= hreq.getRequestURL(); 
		String basePath = null;
		try {
			URL url = new URL(reqUrl.toString());
			basePath=	url.getProtocol()+"://"+ url.getHost() ;
			if(url.getPort() != 80) {
				basePath +=  ":" +url.getPort() ;
			}
			basePath +=  hreq.getContextPath();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return basePath;
    }
}
