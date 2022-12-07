## Create Channel
mutation {
  CreateChannel(channelName:"자유시장") {
    ok
    error
  }
}	

## Get Channel
{
  GetChannels{
    ok
    error
    channels{
      id	
      channelName
    }
  }
}

## Send Message
mutation{
  SendMessage(nickname: "dev4us", contents:"하이염!", thumbnail:"1", innerChannelId:1){
    ok
    error
  }
}

## Get Message
{
  GetMessages(innerChannelId : 1) {
    ok
  	error
    messages {
      contents
    }
  }
}

## Modify Channel
mutation {
  ModifyChannel(id: 2, nextName:"서해") {
    ok
    error
    changedName
  }
}	

## Modify Message
mutation {
  ModifyMessage(id: 3, nextMessage:"서해") {
    ok
    error
    changedMessage
  }
}	

## Subscribe Create Channel
subscription{
  reateChannelSubscription {
    channelName
  }
}
