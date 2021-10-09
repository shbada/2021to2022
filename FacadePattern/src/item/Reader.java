package item;

public class Reader {
    private String fileName;

    public Reader(String fileName) {
        this.fileName = fileName;
    }

    public void fileConnect() {
        String msg = String.format("Reader %s 로 연결합니다.", fileName);
        System.out.println(msg);
    }

    public void fileRead() {
        String msg = String.format("Reader %s 의 내용을 읽어옵니다.", fileName);
        System.out.println(msg);
    }

    public void fileDisConnect() {
        String msg = String.format("Reader %s 로 연결을 종료합니다.", fileName);
        System.out.println(msg);
    }
}
