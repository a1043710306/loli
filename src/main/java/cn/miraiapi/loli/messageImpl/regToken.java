package cn.miraiapi.loli.messageImpl;

import cn.miraiapi.loli.api.API;
import cn.miraiapi.loli.utils.BotConfig;
import cn.miraiapi.loli.utils.HttpUtils2;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class regToken implements API {
    BotConfig botConfig;
    @PostConstruct
    public void reg(){
        Map<String,String> verify=new HashMap<>();
        verify.put("verifyKey",botConfig.getVerifyKey());
        HashMap<String,Object> resp=null;
        if (StringUtils.isEmpty(botConfig.getHost())) {
            resp= HttpUtils2.httpByPost(getDefaultVerifyUrl(),verify,HashMap.class);
        }else {
            resp= HttpUtils2.httpByPost(getCustomizeConfig(API.verifyUrl,botConfig),verify,HashMap.class);
        }

        if(resp.get("code")!=null&&(int)resp.get("code") ==0){
            String Session=(String) resp.get("session");
            botConfig.setSessionKey(Session);
            Map<String,String> bindBody=new HashMap<>();
            bindBody.put("sessionKey", botConfig.getSessionKey());
            bindBody.put("qq", botConfig.getQq());
            if (StringUtils.isEmpty(botConfig.getHost())) {
                HttpUtils2.httpByPost(getDefaultBindUrl(),bindBody,String.class);
            }else {
                HttpUtils2.httpByPost(getCustomizeConfig(API.bindUrl,botConfig),bindBody,String.class);
            }

        }
    }
}
