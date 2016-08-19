package ca.tklab.core.utils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;


public class HashUtil {
//
//    @SuppressWarnings("unchecked")
//    public static void writeMap(Map<K,V> paramMap) {
//        Iterator<Entry> iterator = paramMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Entry entry = iterator.next();
//            System.out.println("Key :" + entry.getKey());
//            System.out.println("Value :" + entry.getValue());
//        }
//    }
//
//    public static String map2String(Map paramMap) {
//        StringBuffer sb = new StringBuffer();
//        Iterator<Entry> iterator = paramMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Entry entry = iterator.next();
//            sb.append("{" + entry.getKey() + "=" + entry.getValue() + "}");
//        }
//        return sb.toString();
//    }
//
//    @SuppressWarnings("unchecked")
//    public static HashMap<String, Object> convertMap(Map paramMap) {
//        HashMap<String, Object> tempMap = new HashMap<String, Object>();
//        Iterator<Entry> iterator = paramMap.keySet().iterator();
//        while (iterator.hasNext()) {
//            Entry entry = iterator.next();
//            tempMap.put((String) entry.getKey(), entry.getValue());
//        }
//        return tempMap;
//    }

    /**
     * example : "key1=val1;key2=val2;key3=val3"
     * 
     * @param param
     *            "key1=val1;key2=val2;key3=val3"
     * @param delim
     *            example [";"]
     * @return
     */

    public static HashMap<String, String> StringToMap(String param, String delim) {
        HashMap<String, String> pairs = new HashMap<String, String>();
        StringTokenizer st = new StringTokenizer(param, delim);
        String pair, key, val;
        while (st.hasMoreElements()) {
            pair = st.nextToken();
            StringTokenizer subPairs = new StringTokenizer(pair, "=");
            if (subPairs.countTokens() != 2) {
                // bad format!!!
                continue;
            } else {
                key = subPairs.nextToken().trim();
                val = subPairs.nextToken().trim();
                pairs.put(key, val);
            }
        }
        return pairs;
    }

    /**
     * List Map Object 형식에서 Map의 하나의 컬럼의 데이터를 List로 만든다.
     * 
     * @param obj
     *            List Map 형식의 Object
     * @param column
     *            Map에서 불러올 컬럼명
     * @return List<Object> 형식의 데이터
     */
    public static List<Object> getOneColFromListMap(
            List<Map<String, Object>> obj, String column) {
        List<Object> res = new ArrayList<Object>();
        for (Map<String, Object> map : obj) {
            res.add(map.get(column));
        }
        return res;
    }

    /**
     * request에서 파라미터만 추출하여 map<String, Object> 로 변환한다.
     * 
     * @param request
     *            HttpServletRequest object
     * @return Map<String, Object>
     */
    public static Map<String, Object> paramMap(HttpServletRequest request) {
        Map<String, Object> pmap = new HashMap<String, Object>();
        Enumeration<String> en = request
                .getParameterNames();
        while (en.hasMoreElements()) {
            String key = en.nextElement();
            pmap.put(key, ObjUtils.getSafeString(request.getParameter(key)));
        }
        return pmap;
    }

    public static Map<String, Object> paramMap(HttpServletRequest request,
            String paramUsing) {
        Map<String, Object> pmap = new HashMap<String, Object>();
        String[] paramUsingSplit = paramUsing.split("( )*,( )*");
        Enumeration<String> en = request
                .getParameterNames();
        while (en.hasMoreElements()) {
            String key = en.nextElement();
            if(ArrayUtils.contains(paramUsingSplit, key)) {
            //if (ArrayUtil.checkInKey(paramUsingSplit, key))
                pmap.put(key, ObjUtils.getSafeString(request.getParameter(key)));
            }
        }
        return pmap;
    }
}
