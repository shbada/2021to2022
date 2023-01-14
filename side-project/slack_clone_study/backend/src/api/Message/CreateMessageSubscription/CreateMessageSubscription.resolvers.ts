const resolvers = {
    Subscription: {
        CreateMessageSubscription: {
            subscribe: (_, __, {pubSub}) => { // {pubSub}: index.ts에 선언되어 context 안에 사용됨
                // pubSub에서 newMessage라는 신호를 받으면 그거에 해당하는 메세지를 return 해라.
                return pubSub.asyncIterator("newMessage"); // "newMessage": 임의
            }
        }
    }
}

export default resolvers;