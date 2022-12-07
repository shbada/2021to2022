package item;

import item.facade.SftpClient;

public class Main {
    public static void main(String[] args) {
        Ftp ftpClient = new Ftp("www.naver.com", 22, "/home/etc");
        ftpClient.connect();
        ftpClient.moveDirectory();

        Writer writer = new Writer("text.tmp"); /* 파일 생성 */
        writer.fileConnect();
        writer.fileWrite();

        Reader reader = new Reader("text.tmp");
        reader.fileConnect();
        reader.fileRead();

        reader.fileDisConnect();
        writer.fileDisConnect();
        ftpClient.disConnect();

        /* SftpClient 생성 */
        SftpClient sftpClient = new SftpClient("www.naver.com", 22, "/home/etc", "text.tml");
        sftpClient.connect();

        sftpClient.write();
        sftpClient.read();

        sftpClient.disConnect();
    }
}
