package cn.miraiapi.loli.Resp;

import lombok.Data;

import java.util.List;
@Data
public class ImAgeInfo {
    long pid;
    long p;
    long uid;
    String title;
    String author;
    boolean r18;
    int width;
    int height;
    List<String> tags;
    String ext;
    long uploadDate;
    UrlsInfo urls;
}
