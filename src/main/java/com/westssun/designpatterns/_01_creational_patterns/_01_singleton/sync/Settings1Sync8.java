package com.westssun.designpatterns._01_creational_patterns._01_singleton.sync;

/**
 * Java8 이후 버전
 */
public class Settings1Sync8 {
    private Settings1Sync8() {}

    /**
     * static inner 클래스 사용
     */
    private static class SettingsHolder {
        private static final Settings1Sync8 INSTANCE = new Settings1Sync8();
    }

    /**
     * 멀티스레드 환경에서 도 안정
     * getInstance()가 호출될때 로딩
     * @return
     */
    public static Settings1Sync8 getInstance() {
        return SettingsHolder.INSTANCE;
    }
}
