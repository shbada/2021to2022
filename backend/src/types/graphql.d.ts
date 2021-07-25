export const typeDefs = ["type CreateChannelResponse {\n  ok: Boolean!\n  error: String\n}\n\ntype Mutation {\n  CreateChannel(channelName: String!): CreateChannelResponse!\n  ModifyChannel(id: Int!, nextName: String!): ModifyChannelResponse!\n  ModifyMessage(id: Int!, nextMessage: String!): ModifyMessageResponse!\n  SendMessage(nickname: String!, contents: String!, thumbnail: String!, innerChannelId: Int!): SendMessageResponse!\n}\n\ntype Subscription {\n  CreateChannelSubscription: Channel\n  CreateMessageSubscription: Message\n}\n\ntype GetChannelsResponse {\n  ok: Boolean!\n  error: String\n  channels: [Channel]\n}\n\ntype Query {\n  GetChannels: GetChannelsResponse!\n  GetMessages(innerChannelId: Int!): GetMessagesResponse!\n}\n\ntype ModifyChannelResponse {\n  ok: Boolean!\n  error: String\n  changedName: String\n}\n\ntype Channel {\n  id: Int!\n  channelName: String!\n  messages: [Message]\n  createdAt: String!\n  updatedAt: String\n}\n\ntype GetMessagesResponse {\n  ok: Boolean!\n  error: String\n  messages: [Message]\n}\n\ntype ModifyMessageResponse {\n  ok: Boolean!\n  error: String\n  changedMessage: String\n}\n\ntype SendMessageResponse {\n  ok: Boolean!\n  error: String\n}\n\ntype Message {\n  id: Int!\n  nickname: String!\n  contents: String!\n  thumbnail: String!\n  innerChannel: Channel!\n  innerChannelId: Int!\n  createdAt: String!\n  updatedAt: String\n}\n"];
/* tslint:disable */

export interface Query {
  GetChannels: GetChannelsResponse;
  GetMessages: GetMessagesResponse;
}

export interface GetMessagesQueryArgs {
  innerChannelId: number;
}

export interface GetChannelsResponse {
  ok: boolean;
  error: string | null;
  channels: Array<Channel> | null;
}

export interface Channel {
  id: number;
  channelName: string;
  messages: Array<Message> | null;
  createdAt: string;
  updatedAt: string | null;
}

export interface Message {
  id: number;
  nickname: string;
  contents: string;
  thumbnail: string;
  innerChannel: Channel;
  innerChannelId: number;
  createdAt: string;
  updatedAt: string | null;
}

export interface GetMessagesResponse {
  ok: boolean;
  error: string | null;
  messages: Array<Message> | null;
}

export interface Mutation {
  CreateChannel: CreateChannelResponse;
  ModifyChannel: ModifyChannelResponse;
  ModifyMessage: ModifyMessageResponse;
  SendMessage: SendMessageResponse;
}

export interface CreateChannelMutationArgs {
  channelName: string;
}

export interface ModifyChannelMutationArgs {
  id: number;
  nextName: string;
}

export interface ModifyMessageMutationArgs {
  id: number;
  nextMessage: string;
}

export interface SendMessageMutationArgs {
  nickname: string;
  contents: string;
  thumbnail: string;
  innerChannelId: number;
}

export interface CreateChannelResponse {
  ok: boolean;
  error: string | null;
}

export interface ModifyChannelResponse {
  ok: boolean;
  error: string | null;
  changedName: string | null;
}

export interface ModifyMessageResponse {
  ok: boolean;
  error: string | null;
  changedMessage: string | null;
}

export interface SendMessageResponse {
  ok: boolean;
  error: string | null;
}

export interface Subscription {
  CreateChannelSubscription: Channel | null;
  CreateMessageSubscription: Message | null;
}
