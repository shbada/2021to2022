/**
 * 에러 발생할 경우
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

package com.co.kr.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionAdvice {

  private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);

  //@ExceptionHandler(Exception.class)
  public String common(Exception e) {

    logger.info(e.toString());

    return "error_common";
  }

  /** 
   * @ExceptionHandler
   * 이 클래스의 객체가 컨트롤러에서 발생하는 Exception을 전문적으로 처리하는 클래스라는 것을 명시해줌*/
  @ExceptionHandler(Exception.class)
  private ModelAndView errorModelAndView(Exception ex) {

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("/error_common");
    modelAndView.addObject("exception", ex);

    return modelAndView;
  }

}


