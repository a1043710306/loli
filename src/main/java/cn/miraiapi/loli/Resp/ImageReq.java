package cn.miraiapi.loli.Resp;

import lombok.Data;

import java.util.List;

@Data
public class ImageReq {
    Integer r18;
    Integer num;
    List<Integer> uid;
    String keyword;
    List<String> tag;
    List<String> size;
    String proxy;

}
