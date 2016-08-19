package ca.tklab.core.utils;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * @Class Name : DesUtil.java
 * @Description : 클래스 설명을 기술합니다.
 * @author John Doe
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

public class DesUtil {

    private static final String CALENDAR_KEY_FORMAT = "MMdd";

    // DESede | DES
    // public final static String DES_MODE = "DES";

    final static char[] chars = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9' };

    private static String getDailyHashKey() {
        final String HASH_KEY = "80sadwe8dksafasl";
        Calendar cal = Calendar.getInstance();

        String rv = null;
        ;
        SimpleDateFormat fomatter = new SimpleDateFormat();
        // fomatter.setTimeZone(TimeZone.getTimeZone("KST"));
        fomatter.applyPattern(CALENDAR_KEY_FORMAT);
        rv = fomatter.format(cal.getTime());
        return (rv + HASH_KEY).substring(0, 16);

    }

    private static String getRandomString() {

        String rv = "";

        for (int i = 0; i < 16; i++) {
            int m = (int) (Math.random() * DesUtil.chars.length);
            rv = rv + DesUtil.chars[m];
        }
        return rv;
    }

    private static Key getKey() throws Exception {

        return getKey(DesUtil.getDailyHashKey());

    }

    /**
     * 지정된 비밀키를 가지고 오는 메서드 (DES) require Key Size : 16 bytes
     * 
     * @return Key 비밀키 클래스
     * @exception Exception
     */
    private static Key getKey(String keyValue) throws Exception {
        SecretKeyFactory keyFactory = null;
        Key key = null;

        keyFactory = SecretKeyFactory.getInstance("DES");
        DESKeySpec desKeySpec = new DESKeySpec(keyValue.getBytes());
        key = keyFactory.generateSecret(desKeySpec);

        return key;
    }

}
