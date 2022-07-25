package cn.miraiapi.loli.Resp;

import lombok.Data;

import java.util.List;

@Data
public class ImageResp {
    String error;
    List<ImAgeInfo> data;
}
