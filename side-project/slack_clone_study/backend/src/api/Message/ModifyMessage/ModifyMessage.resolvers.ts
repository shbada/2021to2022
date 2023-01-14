import { Resolvers } from "src/types/resolvers";
import { ModifyMessageMutationArgs, ModifyMessageResponse } from "src/types/graphql";
import Message from "../../../../src/entities/Message";

const resolvers:Resolvers = {
    Mutation: {
        ModifyMessage: async (_, args:ModifyMessageMutationArgs): Promise<ModifyMessageResponse> => {
            try {
                const { id, nextMessage } = args;

                const ExistMessage = await Message.findOne({ id });

                if (!ExistMessage) {
                    return {
                        ok: false,
                        error: "존재하지않는 메세지입니다.",
                        changedMessage: null
                    }
                }

                // ExistMessage 안의 변수의 값을 바꾼것 (DB와는 무관)
                ExistMessage.contents = nextMessage;
                ExistMessage.save(); // channelName update

                return {
                    ok: true,
                    error: null,
                    changedMessage: nextMessage
                }

                
            } catch (error) {
                return {
                    ok: false,
                    error: error.message,
                    changedMessage: null
                }
            }
        }
    }
}



export default resolvers;