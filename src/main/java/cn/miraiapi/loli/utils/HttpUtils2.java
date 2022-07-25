package com.example.mirarifox.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.mirarifox.pojo.Data;
import com.example.mirarifox.pojo.Elements;
import com.example.mirarifox.pojo.EpicResp;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;



public class HttpUtils2 {
    private static Logger log= LoggerFactory.getLogger(HttpUtils2.class);
    /**
     *
     * @param url 请求url
     * @param tClass 返回实体类型
     * @param <T>
     * @return
     */
    public static <T> T httpByGet(String url,Class<T> tClass) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36");
        requestHeaders.add("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        HttpEntity<T> request = new HttpEntity<>(null, requestHeaders);
        return doRequest(url,request, HttpMethod.GET,tClass);
    }

    public static <T> T httpByGet(String url,String token, Class<T> tClass) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("report-to",token);
        requestHeaders.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.64 Safari/537.36 Edg/101.0.1210.47");
        requestHeaders.add("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        HttpEntity<T> request = new HttpEntity<>(null, requestHeaders);
        return doRequest(url,request,HttpMethod.GET,tClass);
    }

    public static <T> T httpByGet(String url,Map<String,String> header, Class<T> tClass) {
        HttpHeaders requestHeaders = new HttpHeaders();
        for(Map.Entry<String,String> entry:header.entrySet()){
            requestHeaders.add(entry.getKey(),entry.getValue());
        }
        HttpEntity<T> request = new HttpEntity<>(null, requestHeaders);
        return doRequest(url,request,HttpMethod.GET,tClass);
    }

    public static void d(){
        log.info("!___________");
    }
    /**
     *
     * @param url
     * @param header
     * @param jsonParams
     * @param tClass
     * @param <T>
     * @return
     */
    public static<T > T httpByPost(String url, Map<String,String> header, Object jsonParams, Class<T>tClass){
        HttpHeaders requestHeaders = new HttpHeaders();
        for (Map.Entry<String,String> entry:header.entrySet()){
            requestHeaders.add(entry.getKey(),entry.getValue());
        }
        HttpEntity<Object> request = new HttpEntity<>(jsonParams, requestHeaders);
        return doRequest(url,request,HttpMethod.POST,tClass);
    }

    public static<T > T httpByPost(String url, Map<String,String> header, Object jsonParams, Class<T>tClass,boolean debug){
        HttpHeaders requestHeaders = new HttpHeaders();
        for (Map.Entry<String,String> entry:header.entrySet()){
            requestHeaders.add(entry.getKey(),entry.getValue());
        }
        HttpEntity<Object> request = new HttpEntity<>(jsonParams, requestHeaders);
        return doRequest(url,request,HttpMethod.POST,tClass,debug);
    }


    public static<T > T httpByPost(String url,Map<String,String> header,Object jsonParams,int ms,Class<T>tClass){
        HttpHeaders requestHeaders = new HttpHeaders();
        for (Map.Entry<String,String> entry:header.entrySet()){
            requestHeaders.add(entry.getKey(),entry.getValue());
        }
        HttpEntity<Object> request = new HttpEntity<>(jsonParams, requestHeaders);
        return doRequest(url,request,ms,HttpMethod.POST,tClass);
    }

    /***
     *
     * @param url
     * @param token
     * @param jsonParams
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T httpByPost(String url,String token, Object jsonParams, Class<T> tClass) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("token",token);
//        requestHeaders.add("Content-Type", ContentTypeEnum.JSON.getValue());
        HttpEntity<Object> request = new HttpEntity<>(jsonParams, requestHeaders);
//        long startTime = System.currentTimeMillis();
//        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, request, tClass);
//        T body = responseEntity.getBody();
//        log.info("request info to {} in time:{} resp:{}",url,System.currentTimeMillis()-startTime, JSONObject.toJSONString(body));
        return doRequest(url,request,HttpMethod.POST,tClass);
    }

    public static <T> T httpByPost(String url,String token, Object jsonParams,int ms, Class<T> tClass) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("token",token);
        HttpEntity<Object> request = new HttpEntity<>(jsonParams, requestHeaders);
//        long startTime = System.currentTimeMillis();
//        ResponseEntity<T> responseEntity = restTemplate.postForEntity(url, request, tClass);
//        T body = responseEntity.getBody();
//        log.info("request info to {} in time:{} resp:{}",url,System.currentTimeMillis()-startTime, JSONObject.toJSONString(body));
        return doRequest(url,request,ms,HttpMethod.POST,tClass);
    }

    private static<T> T doRequest(String url,HttpEntity request,HttpMethod method,Class<T> tClass){
        RestTemplate restTemplate = new RestTemplate();
        long startTime=System.currentTimeMillis();
        log.info("request info to {}   request body:[{}] ",url, JSONObject.toJSONString(request.getBody()));
        ResponseEntity<T> resp = restTemplate.exchange(url, method, request, tClass);
        T body = resp.getBody();
        log.info(" in time:{}ms resp:[{}]",System.currentTimeMillis()-startTime,JSONObject.toJSONString(body));
        return body;
    }

    private static<T> T doRequest(String url,HttpEntity request,HttpMethod method,Class<T> tClass,boolean debug){
        RestTemplate restTemplate = new RestTemplate();
        long startTime=System.currentTimeMillis();
        log.info("request info to {}   request body:[{}] ",url, JSONObject.toJSONString(request.getBody()));
        ResponseEntity<T> resp = restTemplate.exchange(url, method, request, tClass);
        T body = resp.getBody();
        if(debug){
            log.info(" in time:{}ms resp:[{}]",System.currentTimeMillis()-startTime,JSONObject.toJSONString(body));
        }else {
            log.info("request info to {}   request body:[{}]  time:{}ms",url, JSONObject.toJSONString(request.getBody()),System.currentTimeMillis()-startTime);
        }
        return body;
    }

    private static<T> T doRequest(String url,HttpEntity request,int ms,HttpMethod method,Class<T> tClass){
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory=new SimpleClientHttpRequestFactory();
        simpleClientHttpRequestFactory.setConnectTimeout(ms);
        simpleClientHttpRequestFactory.setReadTimeout(ms);
        RestTemplate restTemplate = new RestTemplate(simpleClientHttpRequestFactory);
        long startTime=System.currentTimeMillis();
        log.info("request info to {}    request body:[{}] ",url,JSONObject.toJSONString(request.getBody()));
        ResponseEntity<T> resp = restTemplate.exchange(url, method, request, tClass);
        T body = resp.getBody();
        log.info(" in time:{}ms resp:[{}]",System.currentTimeMillis()-startTime,JSONObject.toJSONString(body));
        return body;
    }

    public static String urlParamsInstall(final String url,Map<String,String> params){
        StringBuilder stringBuilder = new StringBuilder(url + "?");

        Iterator<Map.Entry<String, String>> it =  params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            if (it.hasNext()) {
                stringBuilder.append(entry.getKey()).append("=").append(entry.getValue());
                stringBuilder.append("&");
            } else {
                stringBuilder.append(entry.getKey()).append("=").append(entry.getValue());
            }

        }

        return stringBuilder.toString();
    }


    public static void main(String args[]){
        String url="https://store-site-backend-static-ipv4.ak.epicgames.com/freeGamesPromotions?locale=zh-CN&country=CN&allowCountries=CN";
        String data=HttpUtils2.httpByGet(url,String.class);
        EpicResp epicResp=JSONObject.parseObject(data,EpicResp.class);

        Elements elements=epicResp.getData().getCatalog().getSearchStore().getElements().get(0);


        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(simpleDateFormat.format(elements.getEffectiveDate()));
    }
}
