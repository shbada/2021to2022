import { Resolvers } from "src/types/resolvers";
import Channel from "../../../entities/Channel";
import { CreateChannelMutationArgs, CreateChannelResponse } from "src/types/graphql";

const resolvers:Resolvers = {
    Mutation:{ // get 을 제외하고는 Mutation이다. get만 Query 이다.
        // 동기 함수의 return type (Promise)
        CreateChannel: async(_, args:CreateChannelMutationArgs, {pubSub}):Promise<CreateChannelResponse> => {
            try {
               const {channelName} = args; // 인자값을 args로부터 가져온다.

               // check. 채널이 있는지 먼저 조회한다. 
               const isExistChannel = await Channel.findOne({ channelName });
                
               // 채널이 중복되어있을경우 Error Return
               if (isExistChannel) {
                    return {
                        ok: false,
                        error: "중복된 채널명입니다."
                    }
               }
            
               // 위 if문을 타지 않았을경우엔 채널이 등록이 가능한 경우
               // create -> save(commit) 순서
               // DB 관련은 await을 써줘야함 (이 행이 끝날때까지 기다려라)
               // await Channel.create({ channelName }).save();
               // 0204. 채널이 실제로 만들어지는 부분 (subscription)
               const newChannel = await Channel.create({ channelName }).save();

               // pubSub에 신호를 준다. ("newChannel")
               pubSub.publish("newChannel", {
                    CreateChannelSubscription: newChannel
               });

               return {
                   ok: true,
                   error: null
               }

            } catch(error) {
                return {
                    ok: false,
                    error: error.message
                }
            }
        }
    }
};

export default resolvers;