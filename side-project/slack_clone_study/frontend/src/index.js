import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import GlobalStyle from "./GlobalStyle";

// apollo import
import {ApolloProvider} from "react-apollo"
import {ApolloProvider as ApolloHookProvider} from "react-apollo-hooks"

import client from "./apolloClient"

// App 컴포넌트를 'root'라는 ID를 가진 DOM에 씌운다는 의미이다.
// index.html 에 'root' ID를 가진 DOM 이 있다.
// provider 들에게 client 에 설정파일이 있음을 명시한다.
ReactDOM.render(
    <>
        <ApolloProvider client={client}>
            <ApolloHookProvider client={client}>
                <App />
                <GlobalStyle></GlobalStyle>
            </ApolloHookProvider>
        </ApolloProvider>
    </>, 
    document.getElementById('root')
);
