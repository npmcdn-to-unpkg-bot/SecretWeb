package ca.tklab.core.utils;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;


/**
 * @Class Name : GArrayUtil.java
 * @Description : 클래스 설명을 기술합니다.
 * @author SangJoon Kim
 * @since 2011. 8. 3.
 * @version 1.0
 * @see 
 *
 * @Modification Information
 * <pre>
 *    수정일         수정자              수정내용
 *  ===========    =========    ===========================
 *  2011. 8. 3.      SangJoon Kim      최초 생성
 * </pre>
 */

public class ArrayUtil {

//	/**
//	 * Map에 있는 value object가 collection이나 array가 아니면 array로 만들어줌.
//	 * 
//	 * @param param
//	 * @param key
//	 * @return
//	 */
//	public static Map object2arryaInMap(Map param, String key) {
//		if(param!= null && param.containsKey(key)) {
//			Object o = param.get(key);			
//			param.put(key, ArrayUtil.toArray(o));
//		}
//		return param;
//	}
	
    /**
     * 문자열 배열을 delimiter를 추가한 문자열로 돌려줌.
     * @param arr
     * @param delimeter
     * @return
     */
    public static String arrayToDelimString(String arr[], String delimiter){
        if(arr == null) return "";
        
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < arr.length; i++){
            if(i > 0)
                sb.append(delimiter);
            sb.append(arr[i]);
        }
        return sb.toString();
    }
    
//    /**
//     * Object를 Object Array 형태로 돌려준다. 
//     * object가 Array면 그냥 돌려줌.
//     * @param obj
//     * @return
//     */
//    public static Object[] toArray(Object obj) {
//         if(obj == null ){
//             return ArrayUtils.EMPTY_OBJECT_ARRAY;
//         } else if (obj instanceof Object[]) {
//             return (Object[]) obj;
//         } else if (obj instanceof Collection) {
//             return (Object[]) ((Collection)obj).toArray(new Object[0])  ;         
//         } else {
//             return ArrayUtils.add(ArrayUtils.EMPTY_OBJECT_ARRAY, obj);
//         }
//    }
    
    /**
     * 배열을 List로 돌려줌
     * @param obj
     * @return
     */
    @SafeVarargs
	public static <T> List<T> convertArrayToList(T... obj) {
        return Arrays.asList(obj);
    }
    
 
    
    /**
     * List<String>을 문자열 배열로 변경 하여 돌려줌. 
     * null일 경우는 길이0인 문자열 배열 return
     * @param list
     * @return
     */
    public static String[] list2StrArray(List<String> list) {
        if (list != null) {
            return list.toArray(new String[0]);
        } else {
            return new String[0];
        }
    }


    
    public static String[] safeArray(String arr[], int length){
        String newArr[] = new String[length];
        Arrays.fill(newArr, "");
        
        for(int j =0; j < arr.length; j++){
            newArr[j] = arr[j];
        }
        return newArr;
    }
    

//    /**
//     * 키가 존재하는지 체크한다. 
//     * @param obj
//     * @return
//     */
//    public static boolean checkInKey(Object[] arrayObj, Object checkObj) {
//        boolean res     = false;
//        if(arrayObj != null) {
//                
//            for(Object objTmp : arrayObj) {
//                if(objTmp.equals(checkObj))     res = true;
//            }
//        }
//        return res;
//    }
//    
//    public static boolean checkInKey(List<Object> arrayObj, Object checkObj) {
//        return arrayObj.contains(checkObj);
//    }
    
    /**
     * 두 Object Array 함침 ( Appache Common ArrayUtils 활용) 
     * concatenate  two arrays.
     * @param arg0
     * @param arg1
     * @return
     */
    public static Object[] addAll(Object[] arg0, Object[] arg1) {
    	return ArrayUtils.addAll(arg0, arg1);
    }
}
