package cn.miraiapi.loli.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bot")
@Data
public class BotConfig {
    private String host,port,sessionKey,verifyKey,qq,noSend,aira2c,outDir,ins,size;
    private Integer r18;
    private boolean debug;
}
