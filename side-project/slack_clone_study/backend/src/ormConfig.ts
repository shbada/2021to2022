import {createConnection, Connection, ConnectionOptions} from "typeorm";

// 데이터베이스의 환경설정 정보 같은 것을 다른 파일에 정의 해두고 사용하고 싶을 때 사용하는 패키지
import dotenv from "dotenv"; // const devenv = require("detenv"); (옛날방식)
dotenv.config();

// ConnectionOptions 안의 객체를 선언한것으로, 잘못된게 있으면 오류가 남
// :ConnectionOptions 에러 이유 : 아래 선언된 값이 비어있을 경우가 있으면?
const connectionOptions: ConnectionOptions = {
    type : "postgres",
    database : "slack_clone",
    synchronize : true, // 소스에서 수정하고 나면, db에 변경된 내역이 바로 반영됨
    logging : true, // 서버 기동시 로그
    entities : ["entities/**/*.*"], // typeORM으로 테이블을 자동으로 만들어주는데, 해당 정보를 담을 엔티티 *.* 파일명.확장자
    host : process.env.DB_HOST,
    port : 5432,
    username : process.env.DB_USERNAME,
    password : process.env.DB_PASSWORD
}

const connection:Promise<Connection> = createConnection(connectionOptions);

export default connection;

// promise : 동기 함수 (connection을 하는 로직을 동기로 실행을 하고, 끝나기 전까지는 다음 로직이 실행되지않음)