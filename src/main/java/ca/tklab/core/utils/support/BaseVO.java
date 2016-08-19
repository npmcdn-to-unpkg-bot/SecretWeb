package ca.tklab.core.utils.support;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * @Class Name : BaseVO.java
 * @Description : 클래스 설명을 기술합니다.
 * @author SangJoon Kim
 * @since 2011. 8. 4.
 * @version 1.0
 * @see
 * 
 * @Modification Information
 * 
 *               <pre>
 *    수정일         수정자              수정내용
 *  ===========    =========    ===========================
 *  2011. 8. 4.      SangJoon Kim      최초 생성
 * </pre>
 */

public class BaseVO implements Serializable {


    /**
     * 
     */
    private static final long serialVersionUID = 5964387415789635560L;
    
// 
	

    @Override
	public String toString() {
        try {
//            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * equals
     */
    @Override
	public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    /**
     * hashCode
     */
    @Override
	public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
