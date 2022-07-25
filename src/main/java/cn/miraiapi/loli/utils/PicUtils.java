package cn.miraiapi.loli.utils;

import cn.miraiapi.loli.Resp.ImageReq;
import cn.miraiapi.loli.Resp.ImageResp;
import cn.miraiapi.loli.Resp.MessageType;
import cn.miraiapi.loli.api.API;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class PicUtils {
    public static List<MessageType> getPicPath(String aira2c,String outDir,int r18)  {
        //final String cmd="D:\\aria2\\aria2c.exe %s  -d D:\\aria2\\pic  -o %s";
        String cmd=String.format("%s %s  -d %s  -o %s",aira2c,"%s",outDir,"%s");
        //final String url="https://api.lolicon.app/setu/v2";
        String url=String.format(API.loliconApi,r18);
        ImageResp resp=HttpUtils2.httpByGet(url,ImageResp.class,true);

        if(resp.getData().size()!=0){
            String ext=resp.getData().get(0).getExt();
            String urld=resp.getData().get(0).getUrls().getOriginal();
            String fileName=String.format("%d.%s",System.currentTimeMillis(),ext);
            String dw=String.format(cmd,urld,fileName);
            try {
                CommandUtils.getInstance().execAndWait(dw);
                MessageType msg=new MessageType();
                List<MessageType> messageTypes = new ArrayList<>();
                msg.setPath(Paths.get(outDir,fileName).toString());
                msg.setType("Image");
                messageTypes.add(msg);
                return messageTypes;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static void main(String args[]){
       System.out.println(JSONObject.toJSONString(getPicPath("D:\\aria2\\aria2c.exe","D:\\aria2\\pic",2)));
    }
}
