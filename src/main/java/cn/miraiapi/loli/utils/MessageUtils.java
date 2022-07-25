package cn.miraiapi.loli.utils;

import cn.miraiapi.loli.Resp.GroupMessage;
import cn.miraiapi.loli.Resp.GroupMessageResp;
import cn.miraiapi.loli.api.API;
import cn.miraiapi.loli.model.SendMessageModel;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
@Component
@AllArgsConstructor
public class MessageUtils implements API{
    final BotConfig botConfig;
    public List<GroupMessage> getGroupMessage(){
        GroupMessageResp g=null;
        if(StringUtils.isEmpty(botConfig.getHost())){
            String url=String.format(API.getGroupMessage,botConfig.getHost(),botConfig.getPort(),botConfig.getSessionKey());
            g=HttpUtils2.httpByGet(url, GroupMessageResp.class,botConfig.isDebug());
        }
        else {
            String url=String.format(API.getGroupMessage,botConfig.getHost(),botConfig.getPort(),botConfig.getSessionKey());
            g=HttpUtils2.httpByGet(url, GroupMessageResp.class,botConfig.isDebug());
        }
        return g.getData();
    }

    public void sendGroupMessage(SendMessageModel sendMessageModel){
        sendMessageModel.setSessionKey(botConfig.getSessionKey());
        if(StringUtils.isEmpty(botConfig.getHost())){
            HttpUtils2.httpByPost(getDefaultSendGroupMessage(),sendMessageModel,String.class);
        }
        else {
            HttpUtils2.httpByPost(getCustomizeConfig(API.sendGroupMessage,botConfig),sendMessageModel,String.class);
        }

    }
}
