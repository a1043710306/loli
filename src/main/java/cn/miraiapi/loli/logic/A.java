package cn.miraiapi.loli.logic;

import cn.miraiapi.loli.Resp.GroupMessage;
import cn.miraiapi.loli.Resp.MessageType;
import cn.miraiapi.loli.Resp.Sender;
import cn.miraiapi.loli.model.SendMessageModel;
import cn.miraiapi.loli.utils.BotConfig;
import cn.miraiapi.loli.utils.MessageUtils;
import cn.miraiapi.loli.utils.PicUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@EnableScheduling
@Component
@AllArgsConstructor
@Slf4j
@EnableAsync
public class A {
    final MessageUtils messageUtils;
    final BotConfig botConfig;

    final ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Scheduled(initialDelay = 1,fixedRate = 500)
    public void init(){
        List<GroupMessage> groupMessagesList=messageUtils.getGroupMessage();
        for(GroupMessage groupMessage: groupMessagesList){
            if(groupMessage.getType().equals("GroupMessage")){
                List<MessageType> messageTypes= groupMessage.getMessageChain();
                Sender sender= groupMessage.getSender();
                threadPoolTaskExecutor.submit(()->{
                    sendPic(messageTypes,sender);
                });
            }
        }
    }
    @Async
    public void sendPic(List<MessageType> messageTypes,Sender sender){
        if(StringUtils.isNotEmpty(botConfig.getNoSend()) && botConfig.getNoSend().indexOf(sender.getGroup().getId()+"")!=-1){
            log.warn("This {} is on the blacklist ",sender.getId());
            return;
        }
        for(MessageType messageType:messageTypes){
            if("Plain".equals(messageType.getType())&& messageType.getText().equals(botConfig.getIns())){
                SendMessageModel sendMessageModel=new SendMessageModel();
                List<MessageType> picMsg= PicUtils.getPicPath(botConfig.getAira2c(), botConfig.getOutDir(),  botConfig.getR18());
                sendMessageModel.setTarget(sender.getGroup().getId()+"");
                sendMessageModel.setMessageChain(picMsg);
                messageUtils.sendGroupMessage(sendMessageModel);
            }
        }
    }

}
