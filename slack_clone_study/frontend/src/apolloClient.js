import { WebSocketLink } from "apollo-link-ws";
import { HttpLink } from "apollo-link-http";
import { getMainDefinition } from "apollo-utilities";
import ApolloClient from "apollo-client";
import { InMemoryCache } from "apollo-cache-inmemory";
import { split } from "apollo-link";

/** 아폴로 클라이언트 설정 */ 
// 백엔드에 대한 endpoint 설정
const GRAPHQL_URI = "localhost:4000";

// subscription
const wsLink = new WebSocketLink({
  uri: `ws://${GRAPHQL_URI}`,
  option: {
    reconnect: true // 한번 요청하면 계속해서 연결해야하기 때문에 연결상태가 안좋을때 재시도
  }
});
// query,mute
const httpLink = new HttpLink({
  uri: `http://${GRAPHQL_URI}`
});
const link = split(
  ({ query }) => {
    const { kind, operation } = getMainDefinition(query);
    // subscription은 웹소켓 / query,mute->http
    // subscription > 클라이언트가 아폴로에 '값을 갖다줘' 요청을 하고 아폴로가 백엔드에 요청
    // 아폴로가 백엔드에 요청할때 wsLink, 또는 httpLink, 로 요청
    return kind === "OperationDefinition" && operation === "subscription";
  },
  wsLink,
  httpLink
);

export default new ApolloClient({
  link,
  cache: new InMemoryCache(),
  onError: ({ networkError, graphQLErrors }) => {
    console.log("graphQLErrors", graphQLErrors);
    console.log("networkError", networkError);
  }
});