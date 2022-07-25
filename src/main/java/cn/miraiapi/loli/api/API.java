package cn.miraiapi.loli.api;

import cn.miraiapi.loli.utils.BotConfig;

public interface API {
    String loliconApi="https://api.lolicon.app/setu/v2?r18=%d";
    String getGroupMessage="http://%s:%s/fetchLatestMessage?sessionKey=%s&count=100000";
    String sendGroupMessage="http://%s:%s/sendGroupMessage";
    String verifyUrl="http://%s:%s/verify";
    String bindUrl="http://%s:%s/bind";
    default String getDefaultGetGroupMessage(){
        return String.format(getGroupMessage,"localhost","8080");
    }
    default String getDefaultSendGroupMessage(){
        return String.format(sendGroupMessage,"localhost","8080");
    }
    default String getDefaultVerifyUrl(){
        return String.format(verifyUrl,"localhost","8080");
    }
    default String getDefaultBindUrl(){
        return String.format(bindUrl,"localhost","8080");
    }
    default String getCustomizeConfig(String src, BotConfig botConfig){
        return String.format(src,botConfig.getHost(),botConfig.getPort());
    }
}
