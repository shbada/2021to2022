package com.java.effective.item86;

import java.io.InvalidObjectException;

public class Test {
    private void readObjectNoData() throws InvalidObjectException {
        throw new InvalidObjectException("스트림 데이터가 필요합니다.");
    }
}
