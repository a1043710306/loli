package cn.miraiapi.loli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class LoliApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(LoliApplication.class).web(WebApplicationType.NONE).run(args);
        //SpringApplication.run(LoliApplication.class, args);
    }

}
