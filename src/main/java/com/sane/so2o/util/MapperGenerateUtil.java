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
        globalConfig.setFileOverride(true);
        globalConfig.setOpen(true);
        globalConfig.setEnableCache(false);
        globalConfig.setActiveRecord(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setBaseResultMap(true);
        globalConfig.setMapperName("%sDao");
        globalConfig.setXmlName("%sDao");
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setDateType(DateType.ONLY_DATE);//使用最新的LocalDateTime

        PackageConfig packageConfig=new PackageConfig();
        packageConfig.setEntity("entity");
        packageConfig.setMapper("dao");
        packageConfig.setXml("dao.xml");
        packageConfig.setParent("com.sane.so2o");

        StrategyConfig strategyConfig=new StrategyConfig();
        strategyConfig.setCapitalMode(true);
        strategyConfig.setEntityLombokModel(true);

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
