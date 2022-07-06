package com.ejzblog.shopping;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.ejzblog.shopping.domain.AbstractBaseDO;

import java.util.Collections;

/**
 * <p>
 * Description：
 * </p>
 *
 * @author Mango
 * @version v1.0.0
 * @date 2022-05-30 13:42
 * @see com.ejzblog.shopping
 */
public class CodeGenerator {

    /**
     * 数据库账号
     */
    private static final String USERNAME = "root";

    /**
     * 数据库密码
     */
    private static final String PASSWORD = "";

    /**
     * 数据库URL
     */
    private static final String URL = "jdbc:mysql:///shopping_center_master?useAffectedRows=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";


    private static final String projectPath = System.getProperty("user.dir");


    public static void main(String[] args) {

        final String path = "\\shopping-business\\shopping-ams-business\\src\\main\\java";
        final String xmlpath = "D:\\DevelopmentTools\\Project\\Zr\\git\\shopping-center-master\\shopping-beans\\src\\main\\resources\\mapper";
        String projectPath = System.getProperty("user.dir");

        FastAutoGenerator.create(URL, USERNAME, PASSWORD).globalConfig(builder -> {
                    builder.author("Mango")// 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath + path); // 指定输出目录

                }).packageConfig(builder -> {
                    builder.parent("com.ejzblog.shopping")
                            .entity("domain")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, xmlpath)); // 设置mapperXml生成路径
                })
                //实体策略配置
                .strategyConfig(builder -> {
                    builder.entityBuilder()
                            .superClass(AbstractBaseDO.class)
                            .enableTableFieldAnnotation()
                            .versionColumnName("version")
                            .versionPropertyName("Integer")
                            .logicDeleteColumnName("is_deleted")
                            .logicDeletePropertyName("Integer")
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .addSuperEntityColumns("id", "creator_id", "creator_name", "gmt_create", "modifier_id", "modifier_name", "gmt_modified", "version", "is_deleted")
                            .formatFileName("%sDO")
                            .enableLombok();

                })
                //控制类策略配置
                .strategyConfig(builder -> {
                    builder.controllerBuilder()

                            //如果没有父类请注释掉
                            .enableRestStyle();
                })
                //服务类策略配置
                .strategyConfig(builder -> {
                    builder.serviceBuilder().formatServiceFileName("%sService").formatServiceImplFileName("%sServiceImpl");
                }).strategyConfig(builder -> {
                    // 设置数据库中表名，按其中属性内容进行生成
                    builder.addInclude("ams_admin_account")
                            .addInclude("ams_app_version")
                            .addInclude("ams_app_version_item")
                            .addInclude("ams_banner")
                            .addTablePrefix("cms_", "ams_", "ums_", "ims_"); // 设置过滤表前缀进行生成
                })

                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
