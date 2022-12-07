package com.designpattern.report._22_template.step2_after.callback;

/**
 * 템플릿 콜백 패턴
 * Callback Interface
 * 상속 대신 위임을 사용할 수 있다.
 */
public interface Operator {
    /* template method */
    abstract int getResult(int result, int number);
}
