package com.ljc.common;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

//@SpringBootApplication
public class LjcCommonApplication {

    public static void main(String[] args) {
//        SpringApplication.run(LjcCommonApplication.class, args);
        FastAutoGenerator.create("jdbc:mysql://10.225.68.34:3306/gulimall_pms?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai", "root", "root")
                .globalConfig(builder -> {
                    builder.author("liujianchao") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://a/"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.ljc") // 设置父包名
                            .moduleName("product") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://a/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder
//                            .addInclude("pms_attr") // 设置需要生成的表名
                            .addTablePrefix("pms_"); // 设置过滤表前缀
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
