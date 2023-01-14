const resolvers = {
    Subscription: {
        CreateChannelSubscription: {
            subscribe: (_, __, {pubSub}) => { // {pubSub}: index.ts에 선언되어 context 안에 사용됨
                // pubSub에서 newChannel라는 신호를 받으면 그거에 해당하는 채널을 return 해라.
                return pubSub.asyncIterator("newChannel"); // "newChannel": 임의
            }
        }
    }
}

export default resolvers;