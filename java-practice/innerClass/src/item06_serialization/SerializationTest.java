package item06_serialization;

import java.io.*;

/**
 * Externalizable : 프로그래머가 직접 읽고 쓰는 메서드를 구현할 수 있음
 * Serializable : 자동으로 구현해줌
 */
class Person implements Serializable {
    String name;
    transient String job; // transient : 이 멤버는 직렬화 대상에서 제외한다. (default 값 null 로 출력될거임)

    public Person() {

    }

    public Person(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String toString() {
        return name + "," + job;
    }
}

public class SerializationTest {
    public static void main(String[] args) {
        Person personLee = new Person("테스트", "개발자");
        Person personKim = new Person("테스투", "기획자");

        try (FileOutputStream fos = new FileOutputStream("serial.txt")) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            /**
             * NotSerializableException 에러 발생 : 클래스에 implements Serializable
             */
            oos.writeObject(personLee);
            oos.writeObject(personKim);

        } catch (IOException e) {

            System.out.println(e);
        }

        try (FileInputStream fis = new FileInputStream("serial.txt")) {
            ObjectInputStream ois = new ObjectInputStream(fis);

            // readObject 할때 클래스의 정보가 없으면 에러 발생 */
            Person pLee = (Person) ois.readObject();
            Person pKim = (Person) ois.readObject();

            System.out.println(pLee);
            System.out.println(pKim);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
