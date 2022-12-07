// React에서 _'create-react-app'_과 같이 손쉽게 graphql 설치를 할 수 있도록 도와주는 graphql 종합 패키지
// PubSub : GraphQL의 웹소켓과 같음
import {GraphQLServer, PubSub} from "graphql-yoga"
// DB 정보 파일 ormConfig import
import connection from "./ormConfig";
// // schema.ts import (아래 typeDefs, resolvers를 주석처리하고 import 추가)
import schema from "./schema";

/**
> Query, mutation(데이터 추가, 삭제), subscribe(구독)
> ! : 필수로 return 이 되어야함

------------------------------------------------------------
const typeDefs = `
    type Query {
        sayHello : String!
    }
`; // 인자값과 리턴되는 값의 타입을 지정해주는 것
const resolvers = {
    Query : {
        sayHello: () => "Hi there :0)"
    }
}; // 함수의 비지니스 로직이 실제로 들어가는 부분
------------------------------------------------------------
*/

const pubSub = new PubSub(); // 추가)0204
// const server = new GraphQLServer({ typeDefs, resolvers });
// 변경 : schemas.ts 파일 생성 후 아래로 변경 (schema import 추가하여 위 이전 코드 주석처리)
// resolver 들끼리 공유할 수 있는 변수를 담는 것을 'context' 라고한다.
// websocket pubSub를 graphql server 안에 내장시키겠다. (pubSub랑 graphql은 엄연히 다른 서버이기 때문에)
const server = new GraphQLServer({ schema, context: {pubSub} });
// 추가 0204: context: {pubSub} 
// -> context 공간을 통해서 데이터를 주고받는다 (CreateChannelSubscription.resolvers.ts에서 사용)

// DB connection (connection이 이뤄진 후에, 서버를 시작한다.)
connection.then(() =>
    server.start(() => 
        console.log('My first GraphQL Server is running on localhost:4000')
    )
);