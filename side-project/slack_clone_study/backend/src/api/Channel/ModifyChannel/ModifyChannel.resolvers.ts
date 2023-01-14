import { Resolvers } from "src/types/resolvers";
import { ModifyChannelMutationArgs, ModifyChannelResponse } from "src/types/graphql";
import Channel from "../../../../src/entities/Channel";

const resolvers:Resolvers = {
    Mutation: {
        ModifyChannel: async (_, args:ModifyChannelMutationArgs): Promise<ModifyChannelResponse> => {
            try {
                const { id, nextName } = args;

                const ExistChannel = await Channel.findOne({ id });

                // 아래 체크하는게 없으면 ExistChannel을 사용할때 undefinied 타입일 수도 있다고
                // 타입스크립트에서 알려준다.
                if (!ExistChannel) {
                    return {
                        ok: false,
                        error: "존재하지않는 채널입니다.",
                        changedName: null
                    }
                }

                // 중복검사
                if (ExistChannel.channelName === nextName) {
                    return {
                        ok: false,
                        error: "중복된 채널명입니다.",
                        changedName: null
                    }
                }

                // ExistChannel 안의 변수의 값을 바꾼것 (DB와는 무관)
                ExistChannel.channelName = nextName;
                ExistChannel.save(); // channelName update

                return {
                    ok: true,
                    error: null,
                    changedName: nextName
                }

                
            } catch (error) {
                return {
                    ok: false,
                    error: error.message,
                    changedName: null
                }
            }
        }
    }
}



export default resolvers;