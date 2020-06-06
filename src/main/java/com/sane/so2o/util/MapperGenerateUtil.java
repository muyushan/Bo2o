package com.sane.so2o.util;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

public class MapperGenerateUtil {
    public static void main(String[] args){
        MapperGenerateUtil generateUtil=new MapperGenerateUtil();
        AutoGenerator autoGenerator=new AutoGenerator();
        autoGenerator.setTemplateEngine(new VelocityTemplateEngine());
        autoGenerator.setConfig(generateUtil.generageConfigBuilder());
        autoGenerator.execute();


    }

    private ConfigBuilder generageConfigBuilder(){

        GlobalConfig globalConfig=new GlobalConfig();
        globalConfig.setAuthor("母玉山");
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setFileOverride(false);//避免覆盖
        globalConfig.setOpen(true);
        globalConfig.setEnableCache(false);
        globalConfig.setActiveRecord(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setBaseResultMap(true);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        globalConfig.setControllerName("%sController");
        // 默认service接口名IXXXService 自定义指定之后就不会用I开头了
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setMapperName("%sMapper");
        globalConfig.setXmlName("%sMapper");
        globalConfig.setMapperName("%sDao");
        globalConfig.setXmlName("%sDao");
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setDateType(DateType.ONLY_DATE);//使用最新的LocalDateTime

        PackageConfig packageConfig=new PackageConfig();
        packageConfig.setEntity("entity");
        packageConfig.setMapper("dao");
        packageConfig.setXml("dao.xml");
        packageConfig.setController("web");
        packageConfig.setParent("com.sane.so2o");

        StrategyConfig strategyConfig=new StrategyConfig();
        strategyConfig.setCapitalMode(true);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//驼峰命名
        strategyConfig.setInclude("user_comment");//设置要生成的表明

        DataSourceConfig dataSourceConfig=new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setSchemaName("blog");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=utf8");

        ConfigBuilder configBuilder=new ConfigBuilder(packageConfig,dataSourceConfig,strategyConfig,null,globalConfig);
        return configBuilder;
    }

}
