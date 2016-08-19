package ca.tklab.core.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;







/**
 * @Class Name : GUtils
 * @Description : 클래스 설명을 기술합니다.
 * @author SangJoon Kim
 * @since 2011. 8. 3.
 * @version 1.0
 * @see
 * 
 * @Modification Information
 * 
 *               <pre>
 *    수정일         수정자              수정내용
 *  ===========    =========    ===========================
 *  2011. 8. 3.      John Doe      최초 생성
 * </pre>
 */
@Component
public class ObjUtils  implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(ObjUtils.class);
	
	private  static ApplicationContext ac;
	private ObjUtils() {
		
	}

	@Override
	public void setApplicationContext(ApplicationContext newac) {
		ac = newac;
	}
	


    /**
     * <pre>
     * 개체가 비어있음(null, &quot;&quot;) : True 
     * Collection의경우 Size 가 0 이어도 : True 
     * Array 의 경우 size 가 0 이어도 :  True
     * 
     * ex) if(&quot;&quot;.equal(sample) &amp;&amp; sample == null) { }
     *  = if(Util.empty(sample)) {}
     * 
     * </pre>
     * 
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        } else if (obj instanceof Object[]) {
            return (((Object[]) obj).length == 0) ? true : false;
        } else {
            return (obj == null || "".equals(getSafeString(obj))) ? true
                    : false;
        }
    }
    
    public static boolean isNotEmpty(Object obj) {
        return !ObjUtils.isEmpty(obj);
    }

    /**
     * 모든 Object의 String 값을 가져온다.
     * 
     * <pre>
     *  null =&gt; null;
     *  String =&gt; string.trim();
     *  Collection =&gt; delimited by &quot;;&quot; string
     *  Object -&gt; obj.toString();
     * </pre>
     * 
     * @param obj
     * @return
     */
    public static String getSafeString(Object obj) {
        return getSafeString(obj, null);
    }

    public static String getSafeString(Object obj, Object def) {
        if (obj == null) {
            return null;
        } else if (obj instanceof String) {
            return String.valueOf(obj).trim();
            // return ((String) obj).v;
        } else if (obj instanceof Collections) {
            return StringUtils.collectionToDelimitedString((Collection<?>) obj, ";");
        } else if (obj instanceof Object[]) {
            return StringUtils.arrayToDelimitedString((Object[]) obj, ";");
            // return ((String) obj).v;
        } else {
            return obj.toString();
        }
    }
    
    public static String nvl(Object obj, Object def) {
        return ObjUtils.getSafeString(isEmpty(obj) ? def : obj);
    }
    public static Object nvlObj(Object obj, Object def) {
        return isEmpty(obj) ? def : obj;
    }
    public static Throwable getRootCause(Throwable e) {
        Throwable t = e.getCause();
        if (t != null) {
            return getRootCause(t);
        }
        return e;

    }

    
    public static Date long2Date(long x) {
        return new Date(x);
    }

    /**
     * 
     * 문자를 int 형으로 변환 <br>
     * Exception 발생시 0 을 반환
     * 
     * @author
     * @version 1.0
     * @modifydate 2004. 5. 19.
     * 
     * @param str
     * @return
     */
    public static int parseInt(Object obj) {
        return parseInt(obj, 0);
    }

    /**
     * 
     * 문자를 int 형으로 변환 <br>
     * Exception 발생시 default_num 을 반환
     * 
     * @author
     * @version 1.0
     * @modifydate 2004. 5. 19.
     * 
     * @param str
     * @param default_num
     *            에러 발생시 반환할 기본 값
     * @return
     */
    public static int parseInt(Object obj, int default_num) {
        int parseInt = 0;
        try {
            if(obj instanceof Integer) {
                parseInt =  (Integer) obj;
            } else {   
                String str = getSafeString( obj);
                if (str != null) {
                	double d = Double.parseDouble(str.trim());
                    parseInt = (int)d;
                } else {
                    parseInt = default_num;
                }
            }
        } catch (Exception nf) {
            parseInt = default_num;
        }
        return parseInt;
    }
    
    
    public static Boolean parseBoolean(Object str, Boolean def) {
    	if(str instanceof Boolean) {
    		return (Boolean ) str;
    	} else if( str instanceof String) {
    		return Boolean.parseBoolean((String) str);
    	} else {
    		return def;
    	}
    }

    /**
     * 스트링을 float 변환. NumberFormatException, NullPointerException 을 검사하기 위해,
     * Exception 발생시 0 리턴
     * 
     * @author :
     * @e-mail :
     */
    public static float parseFloat(String str) {
        return parseFloat(str, 0);
    }

    /**
     * 스트링을 float 변환. NumberFormatException, NullPointerException 을 검사하기 위해,
     * Exception 발생시 default_num 리턴
     * 
     * @author :
     * @e-mail :
     */
    public static float parseFloat(String str, float default_num) {
        float parseFloat = 0.0f;
        try {
            if (str != null) {
                parseFloat = Float.parseFloat(str.trim());
            } else {
                parseFloat = default_num;
            }
        } catch (Exception nf) {
        }
        return parseFloat;
    }
    public static Double parseDouble(Object str) {
        return parseDouble(str, 0.0);
    }
    public static Double parseDouble(Object str, Double def) {
        Double n = def;
        try {
            n = Double.parseDouble(str.toString());
        } catch (Throwable e) {
            // logger.error("str = [" + str + "] : " + e.getMessage());
        }
        return n;
    }


    /**
     * URL에서 데이타를 읽어와 String으로 돌려줌.
     * @param addr
     * @return
     * @throws IOException
     */
    public static String stringOfUrl(String addr) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        URL url = new URL(addr);
        IOUtils.copy(url.openStream(), output);
        return output.toString();
    }
    /**
     * Exception Stack Trace to String
     * 
     * <pre>

     * 
     * </pre>
     * 
     * @version 2009. 2. 10.
     * @author goindole
     * @param e
     * @param sDelimiter
     * @return
     */
    public static String getStackTrace(Exception e) {
        StringBuffer sBuf = new StringBuffer();
        sBuf.append(e);
        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement el : trace) {
            sBuf.append("\n").append("\tat " + el);
        }
        return sBuf.toString();
    }
    
    /**
     * 날자값에 들어간 쓸때 없는 "-" 문자 제거 
     * @param dateStr
     * @return
     */
    public static String cleanDate(String dateStr) {
        return dateStr.replace("-", "");
    }
    
}
