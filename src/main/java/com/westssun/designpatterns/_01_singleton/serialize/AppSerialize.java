package com.westssun.designpatterns._01_singleton.serialize;

import java.io.*;

public class AppSerialize {
    public static void main(String[] args) {
        /** 싱글톤을 깨뜨리는 방법 */
        /* 2) 직렬화, 역직렬화 사용 */
        Settings1Serialize settings1 = Settings1Serialize.getInstance();

        // 객체를 직렬화하여 파일로 작성
        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream("settings1.obj"))) {
            out.writeObject(settings1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 역직렬화
        Settings1Serialize settings2 = null;
        try (ObjectInput in = new ObjectInputStream(new FileInputStream("settings1.obj"))) {
            settings2 = (Settings1Serialize) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // false
        // Settings1Serialize 에 readResolve() 구현시 true 가 나온다.
        System.out.println(settings1 == settings2);
    }
}
