package com.example.mirarifox;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bot")
public class BotConfig {
    private String verifyKey,
    verifyUrl,
    bindUrl,
    sessionKey,qq,
    sendMessageUrl,target,heroUrl,
    getMessageUrl;
    long sqq,sqqGroup;

    public String getVerifyKey() {
        return verifyKey;
    }

    public void setVerifyKey(String verifyKey) {
        this.verifyKey = verifyKey;
    }

    public String getVerifyUrl() {
        return verifyUrl;
    }

    public void setVerifyUrl(String verifyUrl) {
        this.verifyUrl = verifyUrl;
    }

    public String getBindUrl() {
        return bindUrl;
    }

    public void setBindUrl(String bindUrl) {
        this.bindUrl = bindUrl;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getSendMessageUrl() {
        return sendMessageUrl;
    }

    public void setSendMessageUrl(String sendMessageUrl) {
        this.sendMessageUrl = sendMessageUrl;
    }

    public String getGetMessageUrl() {
        return getMessageUrl;
    }

    public void setGetMessageUrl(String getMessageUrl) {
        this.getMessageUrl = getMessageUrl;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public long getSqq() {
        return sqq;
    }

    public void setSqq(long sqq) {
        this.sqq = sqq;
    }

    public long getSqqGroup() {
        return sqqGroup;
    }

    public void setSqqGroup(long sqqGroup) {
        this.sqqGroup = sqqGroup;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getHeroUrl() {
        return heroUrl;
    }

    public void setHeroUrl(String heroUrl) {
        this.heroUrl = heroUrl;
    }
}
