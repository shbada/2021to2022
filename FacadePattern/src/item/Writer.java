package item;

public class Writer {
    private String fileName;

    public Writer(String fileName) {
        this.fileName = fileName;
    }

    public void fileConnect() {
        String msg = String.format("Writer %s 로 연결합니다.", fileName);
        System.out.println(msg);
    }

    public void fileWrite() {
        String msg = String.format("Writer %s 의 파일쓰기를 실행합니다.", fileName);
        System.out.println(msg);
    }

    public void fileDisConnect() {
        String msg = String.format("Writer %s 로 연결을 종료합니다.", fileName);
        System.out.println(msg);
    }
}
