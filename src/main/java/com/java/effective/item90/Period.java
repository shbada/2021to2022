package com.java.effective.item90;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

class Period implements Serializable {
    private final Date start;
    private final Date end;

    public Period(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    private static class SerializationProxy implements Serializable {
        // 아무 값이나 상관없다.
        private static final long serialVersionUID = 2123123123;
        private final Date start;
        private final Date end;

        public SerializationProxy(Period p) {
            this.start = p.start;
            this.end = p.end;
        }

        /**
         * Period.SerializationProxy용
         */
        private Object readResolve() {
            return new Period(start, end); // public 생성자를 사용한다.
        }
    }


    /**
     * 직렬화 프록시 패턴용
     */
    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    /**
     * 직렬화 프록시 패턴용 readObject
     */
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("프록시가 필요합니다.");
    }
}
