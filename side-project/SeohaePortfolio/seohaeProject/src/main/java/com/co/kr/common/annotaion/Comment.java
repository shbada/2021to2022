/**
 * 어노테이션 VO Comment 완성
 * 
 * @author 김서해
 * @since 2017. 11. 02.
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>

 * 1. (2017. 11. 02 / seohae / 최초생성)
 *
 * </pre>
 */

package com.co.kr.common.annotaion;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ METHOD, FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Comment {
	/**
	    * @Method value
	    * @Date 2017. 04. 19.
	    * @Writter rlatjgo0406(�輭��)
	    * @Param
	    * @Param 
	    * @EditHistory
	    * @Discript �ּ� ����
	    * @Return String
	  */
	String value() default "";  
}
