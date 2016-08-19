package ca.tklab.core.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

/**
 * @Class Name : GStringUtils.java
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

public class TStringUtils {

    public static final int LEFT = 1;

    public static final int CENTER = 2;

    public static final int RIGHT = 3;

    private TStringUtils() {
    }

    public static boolean equals(String str1, String str2) {
        return (null == str1 ? null == str2 : str1.compareTo(str2) == 0);
    }

    public static boolean isEmpty(String str) {
        return (str == null || str.trim().length() == 0);
    }

    public static boolean isWord(String str) {
        if (null == str)
            return false;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 주어진 문자열이 AlphaNumeric인지 확인
     * 
     * @param str
     * @return
     */
    public static boolean isAlphanumeric(String str) {
        if (null == str)
            return false;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (!Character.isLetterOrDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 주어진 문자열이 숫자인지 확인
     * 
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (null == str)
            return false;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 문자열 앞뒤 공백을 없앰
     * 
     * @param str
     * @return
     */
    public static String trim(String str) {
        return trim(str, null);
    }

    /**
     * 문자열 앞뒤공백을 없앰, 만약 null이면 def를 돌려줌.
     * 
     * @param str
     * @param def
     * @return
     */
    public static String trim(String str, String def) {
        return (null == str ? def : str.trim());
    }

    /**
     * 문자열 앞 공백을 없앰
     * 
     * @param str
     * @return
     */
    public static String ltrim(String str) {
        return stripStart(str, null);
    }

    /**
     * 문자열뒤 공백을 없앰
     * 
     * @param str
     * @return
     */
    public static String rtrim(String str) {
        return stripEnd(str, null);
    }

    private static String stripStart(String str, String prefix) {
        if (str == null)
            return str;
        int start = 0;
        if (prefix == null) {
            while (Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else if (str.startsWith(prefix)) {
            if (str.equals(prefix))
                return "";
            return str.substring(prefix.length());
        }
        return str.substring(start);
    }

    private static String stripEnd(String str, String postfix) {
        if (str == null)
            return str;
        int end = str.length();
        if (null == postfix) {
            while (Character.isWhitespace(str.charAt(end - 1))) {
                end--;
            }
        } else if (str.endsWith(postfix)) {
            if (str.equals(postfix))
                return "";
            return str.substring(0, str.lastIndexOf(postfix));
        }
        return str.substring(0, end);
    }

    /**
     * leftPad, rightPad에 의해 실행되는 메소드
     * 
     * @param str
     *            문자열
     * @param size
     *            사이즈
     * @param padStr
     *            채울 문자열
     * @param where
     *            true : left, false : right
     * @return 변환된 문자열
     */
    private static String strPad(String str, int size, String padStr,
            boolean where) {
        if (str == null)
            return "";
        if (str.length() >= size)
            return str;

        String res = null;
        StringBuffer sb = new StringBuffer();
        String tmpStr = null;
        int tmpSize = size - str.length();

        for (int i = 0; i < size; i = i + padStr.length()) {
            sb.append(padStr);
        }
        tmpStr = sb.toString().substring(0, tmpSize);

        if (where)
            res = tmpStr.concat(str);
        else
            res = str.concat(tmpStr);
        return res;
    }

    public static String left(String str, int len) {
        if (str == null || len < 0)
            return "";
        if (str.length() < len)
            return str;
        else
            return str.substring(0, len);
    }

    public static String right(String str, int len) {
        if (str == null || len < 0)
            return "";
        if (str.length() < len)
            return str;
        else
            return str.substring(str.length() - len);
    }

    public static String mid(String str, int pos, int len) {
        if (str == null || len < 0 || pos > str.length())
            return "";
        if (pos < 0)
            pos = 0;
        if (str.length() < pos + len)
            return str.substring(pos);
        else
            return str.substring(pos, pos + len);
    }
    public static String mid(String str, int pos) {
        if (str == null || pos > str.length())
            return "";
        if (pos < 0)
            pos = 0;
        return str.substring(pos);
    }

    /**
     * 문자열의 왼쪽에 해당 사이즈 만큼 문자로 채운다.
     * 
     * @param str
     *            문자열
     * @param size
     *            사이즈
     * @param padChar
     *            채울 문자
     * @return 변환된 문자열
     */
    public static String lPad(String str, int size, char padChar) {
        return lPad(str, size, String.valueOf(padChar));
    }

    /**
     * 문자열의 왼쪽에 해당 사이즈 만큼 문자열로 채운다.
     * 
     * @param str
     *            문자열
     * @param size
     *            사이즈
     * @param padStr
     *            채울 문자열
     * @return 변환된 문자열
     */
    public static String lPad(String str, int size, String padStr) {
        return strPad(str, size, padStr, true);
    }

    /**
     * 문자열의 오른쪽에 해당 사이즈 만큼 문자로 채운다.
     * 
     * @param str
     *            문자열
     * @param size
     *            사이즈
     * @param padChar
     *            채울 문자
     * @return 변환된 문자열
     */
    public static String rPad(String str, int size, char padChar) {
        return rPad(str, size, String.valueOf(padChar));
    }

    /**
     * 문자열의 오른쪽에 해당 사이즈 만큼 문자열로 채운다.
     * 
     * @param str
     *            문자열
     * @param size
     *            사이즈
     * @param padStr
     *            채울 문자열
     * @return 변환된 문자열
     */
    public static String rPad(String str, int size, String padStr) {
        return strPad(str, size, padStr, false);
    }

    public static String substring(String input, int beginIndex, int endIndex) {
        if (input == null)
            input = "";
        if (beginIndex >= input.length())
            return "";
        if (beginIndex < 0)
            beginIndex = 0;
        if (endIndex < 0 || endIndex > input.length())
            endIndex = input.length();
        if (endIndex < beginIndex)
            return "";
        return input.substring(beginIndex, endIndex);
    }

    /**
     * 
     * 
     * 입력된 스트링을 지정된 길이만큼 Byte단위로 남기고 나머지를 잘라낸다.! <br>
     * 
     * @author Choi Eui Youb
     * @version 1.0
     * @createdate 2004. 6. 9.
     * @modifydate 2004. 6. 9.
     * 
     * @param str
     *            잘라내고자 하는 원본 문자열
     * @param sz
     *            자르고 남을 문자열의 byte단위 길이.
     * @return 원본 문자열에서 자르고 남은 sz만큼의 문자열
     * @throws UnsupportedEncodingException
     */
    public static String getByteCut(String str, int sz)
            throws UnsupportedEncodingException {
        str = ObjUtils.nvl(str, "");

        if (str.equals("") || str.getBytes().length <= sz) {
            return str;
        }

        String a = str;
        int i = 0;
        String imsi = "";
        String rlt = "";
        imsi = a.substring(0, 1);
        while (i < sz) {
            byte[] ar = imsi.getBytes();

            i += ar.length;

            rlt += imsi;
            a = a.substring(1);
            if (a.length() == 1) {
                imsi = a;
            } else if (a.length() > 1) {
                imsi = a.substring(0, 1);
            }
        }

        return rlt + "...";
    }

    /**
     * String의 replace는 정규식을 사용하므로 값에 정규식이 포함되었을때 문제가 되어 이 함수를 사용함.
     * @param source
     * @param original
     * @param replace
     * @return
     */
    public static String replaceFirst(String source, String original, String replace) {
        return replace(source, original, replace, 1);
    }
    /**
     * String의 replace는 정규식을 사용하므로 값에 정규식이 포함되었을때 문제가 되어 이 함수를 사용함.
     * @param source
     * @param ch
     * @param replace
     * @return
     */
    public static String replace(String source, char ch, String replace) {
        return replace(source, ch, replace, -1);
    }
    /**
     * String의 replace는 정규식을 사용하므로 값에 정규식이 포함되었을때 문제가 되어 이 함수를 사용함.
     * @param source
     * @param ch
     * @param replace
     * @param max
     * @return
     */
    public static String replace(String source, char ch, String replace, int max) {
        return replace(source, ch + "", replace, max);
    }

    /**
     * String의 replace는 정규식을 사용하므로 값에 정규식이 포함되었을때 문제가 되어 이 함수를 사용함.
     * @param source
     * @param original
     * @param replace
     * @return
     */
    public static String replace(String source, String original, String replace) {
        return replace(source, original, replace, -1);
    }

    /**
     * String의 replace는 정규식을 사용하므로 값에 정규식이 포함되었을때 문제가 되어 이 함수를 사용함.
     * @param source
     * @param original
     * @param replace
     * @param max
     * @return
     */
    public static String replace(String source, String original,
            String replace, int max) {
        if (null == source)
            return null;
        int nextPos = 0; //
        int currentPos = 0; //
        int len = original.length();
        StringBuffer result = new StringBuffer(source.length());
        
        while ((nextPos = source.indexOf(original, currentPos)) != -1) {
            result.append(source.substring(currentPos, nextPos));
            result.append(replace);
            currentPos = nextPos + len;
            if (-- max == 0) {
                break;
            }
        }
        if (currentPos < source.length()) {
            result.append(source.substring(currentPos));
        }
        return result.toString();
    }

    /**
     * 주어진 문자열(pattern)을 n번 반복하여 돌려줌
     * 
     * @param pattern
     * @param n
     * @return
     */
    public static String repeat(String pattern, int n) {
        if (null == pattern)
            return null;
        if(n<0) n = 0;
        StringBuffer sb = new StringBuffer(n * pattern.length());
        repeat(sb, pattern, n);
        return sb.toString();
    }

    private static void repeat(StringBuffer sb, String pattern, int n) {
        if (null == pattern)
            return;
        for (int i = 0; i < n; i++) {
            sb.append(pattern);
        }
    }

    public static int indexOf(String str, String[] strs) {
        if (null == str)
            return -1;
        int len = strs.length;
        int tmp = 0;
        int ret = Integer.MAX_VALUE;

        for (int i = 0; i < len; i++) {
            tmp = str.indexOf(strs[i]);
            if (tmp == -1) {
                continue;
            }
            if (tmp < ret) {
                ret = tmp;
                break;
            }
        }
        return (ret == Integer.MAX_VALUE ? -1 : ret);
    }

    public static int lastIndexOf(String str, String[] strs) {
        if (null == str)
            return -1;
        int len = strs.length;
        int ret = -1;
        int tmp = 0;
        for (int i = 0; i < len; i++) {
            tmp = str.lastIndexOf(strs[i]);
            if (tmp > ret) {
                ret = tmp;
            }
        }
        return ret;
    }

    public static String getLastValue(String packageName) {
        return packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    /**
     * deli로 구분된 문자열을 List<String>로 돌려줌
     * 
     * @param source
     * @param deli
     * @return
     */
    public static List<String> tokenizer(String source, String deli) {
        if (source == null)
            return null;
        if (deli == null)
            deli = " ";
        int idx = source.indexOf(deli);
        List<String> list = new ArrayList<String>();
        while (idx > -1) {
            String sub = source.substring(0, idx);
            source = source.substring(idx + 1);
            idx = source.indexOf(deli);
            list.add(sub);
        }
        list.add(source);
        return list;
        // String[] result = (String[]) list.toArray(new String[list.size()]);
        // return result;
    }

    /**
     * HTML 문자열을 escape한다.
     * 
     * @param s
     * @return
     */
    public static String htmlEscape(String s) {
        if (s == null) {
            s = "";
        } else {
            // s = s.replaceAll("&", "&amp;");
            // s = s.replaceAll("<", "&lt;");
            // s = s.replaceAll(">", "&gt;");
            // s = s.replaceAll("\"", "&quot;");
            // s = s.replaceAll("'", "&#39;");
            s = HtmlUtils.htmlEscape(s);
        }
        return s;
    }

    // /**
    // * Javascript에 문자열 사용시 적용함
    // * @param s
    // * @return
    // */
    // public static String jsString(String s) {
    // if (s == null) {
    // s = "";
    // } else {
    // s = s.replaceAll("\\\\", "\\\\\\\\");
    // s = s.replaceAll("\"", "\\\\\"");
    // s = s.replaceAll("\n", "\\\\n");
    // s = s.replaceAll("\r", "\\\\r");
    // }
    // return (new StringBuilder()).append("\"").append(s).append("\"")
    // .toString();
    // }

    /**
     * Turn special characters into escaped characters conforming to JavaScript.
     * Handles complete character set defined in HTML 4.01 recommendation.
     * 
     * @param input
     *            the input string
     * @return the escaped string
     */
    public static String javaScriptEscape(String input) {
        if (input == null) {
            return input;
        }

        StringBuffer filtered = new StringBuffer(input.length());
        char prevChar = '\u0000';
        char c;
        for (int i = 0; i < input.length(); i++) {
            c = input.charAt(i);
            if (c == '"') {
                filtered.append("\\\"");
            } else if (c == '\'') {
                filtered.append("\\'");
            } else if (c == '\\') {
                filtered.append("\\\\");
            } else if (c == '/') {
                filtered.append("\\/");
            } else if (c == '\t') {
                filtered.append("\\t");
            } else if (c == '\n') {
                if (prevChar != '\r') {
                    filtered.append("\\n");
                }
            } else if (c == '\r') {
                filtered.append("\\n");
            } else if (c == '\f') {
                filtered.append("\\f");
            } else {
                filtered.append(c);
            }
            prevChar = c;

        }
        return filtered.toString();
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
        if (obj == null) {
            return null;
        } else if (obj instanceof String) {
            return String.valueOf(obj).trim();
            // return ((String) obj).v;
        } else if (obj instanceof Collections) {
            return StringUtils.collectionToDelimitedString((Collection<?>) obj, ";");
            // return ((String) obj).v;
        } else {
            return obj.toString();
        }

    }

    /**
     * TRUE나 T, Y, y의 경우 true로
     * 
     * @param v
     * @return
     */
    public static boolean toBoolean(Object v) {
        if (v instanceof String) {
            if ("t".equalsIgnoreCase((String) v)
                    || "true".equalsIgnoreCase((String) v)
                    || "y".equalsIgnoreCase((String) v)
                    || "yes".equalsIgnoreCase((String) v)) {
                return true;
            } else {
                return false;
            }
        } else if (v instanceof Boolean) {
            return ((Boolean) v).booleanValue();
        } else {
            return false;
        }

    }

    /**
     * Capitalize a <code>String</code>, changing the first letter to upper case
     * as per {@link Character#toUpperCase(char)}. No other letters are lower.
     * 
     * @param str
     *            the String to capitalize, may be <code>null</code>
     * @return the capitalized String, <code>null</code> if null
     */
    public static String capitalize(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        return changeFirstCharacterCase(str.toLowerCase(), true);
    }

    /**
     * Uncapitalize a <code>String</code>, changing the first letter to lower
     * case as per {@link Character#toLowerCase(char)}. No other letters are
     * changed.
     * 
     * @param str
     *            the String to uncapitalize, may be <code>null</code>
     * @return the uncapitalized String, <code>null</code> if null
     */
    public static String uncapitalize(String str) {
        return changeFirstCharacterCase(str, false);
    }

    private static String changeFirstCharacterCase(String str,
            boolean capitalize) {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str.length());
        if (capitalize) {
            buf.append(Character.toUpperCase(str.charAt(0)));
        } else {
            buf.append(Character.toLowerCase(str.charAt(0)));
        }
        buf.append(str.substring(1));
        return buf.toString();
    }

    /**
     * 문자열을 대문자로 , null일 경우 def값을 리턴
     * 
     * @param str
     * @param def
     * @return
     */
    public static String toUpperCase(String str, String def) {
        if (str == null) {
            return def;
        } else {
            return str.toUpperCase();
        }
    }

    /**
     * 문자열을 대문자로 , null일 경우 ""값을 리턴
     * 
     * @param str
     * @param def
     * @return
     */
    public static String toUpperCase(String str) {
        return toUpperCase(str, "");
    }

    /**
     * 문자열을 소문자로 , null일 경우 def값을 리턴
     * 
     * @param str
     * @param def
     * @return
     */
    public static String toLowerCase(String str, String def) {
        if (str == null) {
            return def;
        } else {
            return str.toLowerCase();
        }
    }

    /**
     * 문자열을 소문자로 , null일 경우 ""값을 리턴
     * 
     * @param str
     * @param def
     * @return
     */
    public static String toLowerCase(String str) {
        return toLowerCase(str, "");
    }

    /**
     * 비어 있지 않은경우 앞뒤에 문자 삽입
     * 
     * @param pre
     * @param dst
     * @param after
     * @return
     */
    public static String capsule(String pre, Object dst, String after) {
        if (ObjUtils.isEmpty(dst))
            return "";
        else
            return pre + dst + after;
    }

    /**
     * multiplier번 반복한 input_str을 반환합니다. multiplier는 0 이상이여야 합니다. multiplier를
     * 0으로 설정하면, 빈 문자열을 반환합니다.
     */
    public static String strRepeat(String input, int multiplier) {
        StringBuffer sBuf = new StringBuffer();
        try {
            for (int i = 0; i < multiplier; i++) {
                sBuf.append(input);
            }
        } catch (Exception e) {
            return null;
        }
        return sBuf.toString();
    }


    
    
    public static String toCamelCase(String columnName) {
        return convert2CamelCase(columnName);
    }
    
    /**
     * underscore ('_') 가 포함되어 있는 문자열을 Camel Case ( 낙타등
     * 표기법 - 단어의 변경시에 대문자로 시작하는 형태. 시작은 소문자) 로 변환해주는
     * utility 메서드 ('_' 가 나타나지 않고 첫문자가 대문자인 경우도 변환 처리
     * 함.)
     * @param underScore
     *        - '_' 가 포함된 변수명
     * @return Camel 표기법 변수명
     */
    public static String convert2CamelCase(String underScore) {

        // '_' 가 나타나지 않으면 이미 camel case 로 가정함.
        // 단 첫째문자가 대문자이면 camel case 변환 (전체를 소문자로) 처리가
        // 필요하다고 가정함. --> 아래 로직을 수행하면 바뀜
        if (underScore.indexOf('_') < 0
            /*&& Character.isLowerCase(underScore.charAt(0) ) */) {
            return underScore;
        }
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;
        int len = underScore.length();

        for (int i = 0; i < len; i++) {
            char currentChar = underScore.charAt(i);
            if (currentChar =='_'  ) {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    result.append(Character.toUpperCase(currentChar));
                    nextUpper = false;
                } else {
                    result.append(Character.toLowerCase(currentChar));
                }
            }
        }
        return result.toString();
    }
    
    /**
     * get count of line seperator in string
     * @param str
     * @return
     */
    public static int countLines(String str){
        if (str != null) {
            String[] lines = str.split("\r\n|\r|\n");
            return  lines.length;
        } else {
            return 0;
        }
     }

    
    public static String bytesToHex(byte[] bytes) {

        return Hex.encodeHexString( bytes );
    }
    public static byte[] HexTobytes(String strs) throws DecoderException {

        return Hex.decodeHex(strs.toCharArray() );
    }
}
