package com.cy.framework.freemarker.test;

import com.cy.framework.freemarker.FreemarkerDTO;
import com.cy.framework.freemarker.FreemarkerUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ErpCodeGenerator {

    public static void main(String[] args) {
        String projectName = "erp";
        String author = "ChenY";
        FreemarkerDTO[] models = new FreemarkerDTO[]{
                new FreemarkerDTO("Company",""),
        };

        FreemarkerUtil util = new FreemarkerUtil();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());

        String baseDir = "FreemarkerTemp/"+projectName+"/";
        String mapperPath = baseDir + "mapper/";
        String servicePath = baseDir + "service/";
        String voPath = baseDir + "vo/";
        String dtoPath = baseDir + "dto/";
        String controllerPath = baseDir + "controller/";
        try {
            FileUtils.deleteDirectory(new File(baseDir));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (FreemarkerDTO model : models) {
            // 首字母小写
            String firstSmallModelName = model.getName().substring(0,1).toLowerCase()+model.getName().substring(1);

            Map<String,Object> map = new HashMap<String, Object>();
            map.put("projectName", projectName);
            map.put("author", author);
            map.put("modelName", model.getName());
            map.put("firstSmallModelName", firstSmallModelName);
            map.put("time", time);


            util.fprint(projectName+"/Mapper.ftl", map, mapperPath+model.getName()+"Mapper.java");
            util.fprint(projectName+"/Service.ftl", map, servicePath+model.getName()+"Service.java");
            util.fprint(projectName+"/VO.ftl", map, voPath+model.getName()+"VO.java");
            util.fprint(projectName+"/DTO.ftl", map, dtoPath+model.getName()+"DTO.java");
            util.fprint(projectName+"/SearchDTO.ftl", map, dtoPath+model.getName()+"SearchDTO.java");
            util.fprint(projectName+"/Controller.ftl", map, controllerPath+model.getName()+"Controller.java");
        }
        System.out.println("项目【"+projectName+"】生成完毕");


    }


}
