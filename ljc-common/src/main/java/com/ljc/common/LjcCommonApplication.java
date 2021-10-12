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
        FastAutoGenerator.create("jdbc:mysql://10.225.68.34:3306/mall?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai", "root", "root")
                .globalConfig(builder -> {
                    builder.author("liujianchao") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://a/"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.ljc") // 设置父包名
                            .moduleName("member") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://a/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder
                            .addInclude("ums_member") // 设置需要生成的表名
                            .addInclude("ums_member_level") // 设置需要生成的表名
                            .addInclude("ums_member_login_log") // 设置需要生成的表名
                            .addInclude("ums_member_member_tag_relation") // 设置需要生成的表名
                            .addInclude("ums_member_product_category_relation") // 设置需要生成的表名
                            .addInclude("ums_member_receive_address") // 设置需要生成的表名
                            .addInclude("ums_member_rule_setting") // 设置需要生成的表名
                            .addInclude("ums_member_statistics_info") // 设置需要生成的表名
                            .addInclude("ums_member_tag") // 设置需要生成的表名
                            .addInclude("ums_member_task") // 设置需要生成的表名
                            .addTablePrefix("ums_"); // 设置过滤表前缀
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
