package com.icoolle.tool;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.StrBuilder;
import com.google.common.collect.Lists;
import com.icoolle.model.code.dto.TableDesignSaveDTO;
import com.icoolle.model.code.dto.TableInfoDTO;
import com.icoolle.model.code.po.TableColumnInfo;
import com.icoolle.model.code.po.TableVMInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.system.ApplicationHome;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.icoolle.common.constant.consts.BeanTableFieldConst.*;
import static com.icoolle.common.constant.consts.SystemConst.*;


@Slf4j
public class GeneratorUtil {

    /**
     * vm模板
     *
     * @return
     */
    public static List<String> getTemplates() {
        String[] templates = {
                "document/template/Controller.vm",
                "document/template/Service.vm",
                "document/template/ServiceImpl.vm",
                "document/template/Mapper.vm",
                "document/template/Mapper.xml.vm",
                "document/template/PO.vm",
                "document/template/SaveDTO.vm",
                "document/template/UpdateDTO.vm",
                "document/template/ListDTO.vm",
                "document/template/ListVO.vm",
        };
        return Arrays.asList(templates);
    }

    /**
     * 解析代码数据
     */
    public static void generatorCode(TableDesignSaveDTO tableDesignSaveDTO) {
        Properties properties = getProperties();
        TableVMInfo tableVMInfo = new TableVMInfo();
        BeanUtils.copyProperties(tableDesignSaveDTO, tableVMInfo);
        String className = getClassName(tableVMInfo.getTableName());
        List<TableInfoDTO> tableInfoDTOList = tableDesignSaveDTO.getTableInfoDTOList();
        String localDateTime = properties.getProperty(LOCAL_DATE_TIME);
        String bigDecimal = properties.getProperty(BIG_DECIMAL);
        List<TableColumnInfo> columnInfoList = Lists.newArrayList();
        List<TableColumnInfo> columnInfoListTwo = Lists.newArrayList();
        StrBuilder builder = new StrBuilder();
        boolean flag = true, flag1 = true;
        long count = tableInfoDTOList.stream().map(TableInfoDTO::getColumnName)
                .filter(s -> ID.equals(s) || CREATEDBY.equals(s) || CREATEDTIME.equals(s) || UPDATEDBY.equals(s) || UPDATEDTIME.equals(s) || DELFLAG.equals(s))
                .count();
        boolean b = count == 6L;
        for (TableInfoDTO tableInfoDTO : tableInfoDTOList) {
            TableColumnInfo columnInfo = new TableColumnInfo();
            String columnName = tableInfoDTO.getColumnName();
            String attrName = StringUtil.getAttrName(columnToJava(columnName));
            columnInfo.setColumnName(columnName)
                    .setAttrName(attrName)
                    .setComment(tableInfoDTO.getColumnComment())
                    .setDataType(tableInfoDTO.getJavaType());
            if (b) {
                if (!(ID.equals(columnName) || CREATEDBY.equals(columnName) || CREATEDTIME.equals(columnName) || UPDATEDBY.equals(columnName)
                        || UPDATEDTIME.equals(columnName) || DELFLAG.equals(columnName))) {
                    columnInfoListTwo.add(columnInfo);
                }
            } else {
                columnInfoListTwo.add(columnInfo);
            }
            if (LOCAL_DATE_TIME.equals(tableInfoDTO.getJavaType()) && flag) {
                builder.append("import ").append(localDateTime).append("\n");
                flag = false;
            }
            if (BIG_DECIMAL.equals(tableInfoDTO.getJavaType()) && flag1) {
                builder.append("import ").append(bigDecimal).append("\n");
                flag1 = false;
            }
            columnInfoList.add(columnInfo);
        }
        tableVMInfo.setPackageName(properties.getProperty("packageName"))
                .setNowDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DF_DATE_TIME)))
                .setClassName(className)
                .setPathName(StringUtil.getAttrName(className))
                .setJavaTypeList(builder.toString())
                .setAuthor(tableVMInfo.getAuthor().toUpperCase())
                .setFlagExtends(count == 6L)
                .setTableColumnInfoList(columnInfoList)
                .setTableColumnInfoListTwo(columnInfoListTwo);
        generatorTemplate(tableVMInfo, properties);
    }

    /**
     * 渲染模板 写入文件
     *
     * @param tableVMInfo
     * @param properties
     */
    public static void generatorTemplate(TableVMInfo tableVMInfo, Properties properties) {
        String systemPath = getClassPathRoot(properties.getProperty("packagePath"));
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
        Map<String, Object> map = BeanUtil.beanToMap(tableVMInfo);
        VelocityContext context = new VelocityContext(map);
        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template veTemplate = ve.getTemplate(template, "UTF-8");
            veTemplate.merge(context, sw);
            try (InputStream inputStream = new ByteArrayInputStream(sw.toString().getBytes(StandardCharsets.UTF_8))) {
                String filePath = getFileName(template, tableVMInfo, systemPath, properties);
                File file = new File(filePath);
                FileUtil.inputStreamToFile(inputStream, file, tableVMInfo.getFlag());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取项目地址
     *
     * @param path
     * @return
     */
    public static String getClassPathRoot(String path) {
        return new ApplicationHome(GeneratorUtil.class).getSource().getParentFile().getParentFile().getPath() + path;
    }

    /**
     * 转换实体类名
     *
     * @param tableName
     * @return
     */
    public static String getClassName(String tableName) {
        return columnToJava(tableName.substring(tableName.indexOf("_") + 1));
    }


    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 获取配置信息
     */
    public static Properties getProperties() {
        Properties pps = new Properties();
        try (InputStream in = Properties.class.getResourceAsStream("/generator.properties")) {
            pps.load(in);
        } catch (IOException e) {
            log.error("获取属性文件失败", e);
        }
        return pps;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, TableVMInfo tableVMInfo, String systemPath, Properties properties) {
        String className = tableVMInfo.getClassName();
        String moduleName = tableVMInfo.getModuleName();
        String provider = properties.getProperty("provider");
        String model = properties.getProperty("model");
        String mapperXml = properties.getProperty("mapperXml");

        String controllerPath = systemPath + properties.getProperty("consumer") + File.separator + moduleName + File.separator + className;
        String servicePath = systemPath + provider + File.separator + moduleName + File.separator + "service" + File.separator + className;
        String serviceImplPath = systemPath + provider + File.separator + moduleName + File.separator + "service" + File.separator + "impl" + File.separator + className;
        String mapperPath = systemPath + provider + File.separator + moduleName + File.separator + "mapper" + File.separator + className;
        String dtoPath = systemPath + model + File.separator + moduleName + File.separator + "dto" + File.separator + className;
        String poPath = systemPath + model + File.separator + moduleName + File.separator + "po" + File.separator + className;
        String voPath = systemPath + model + File.separator + moduleName + File.separator + "vo" + File.separator + className;
        String mapperXmlPath = getClassPathRoot(mapperXml) + moduleName + File.separator + className;

        return Switch.in(template.substring(template.lastIndexOf("/") + 1)).out(String.class)
                .is("Controller.vm").thenSupply(() -> controllerPath + "Controller.java")
                .is("Service.vm").thenSupply(() -> servicePath + "Service.java")
                .is("ServiceImpl.vm").thenSupply(() -> serviceImplPath + "ServiceImpl.java")
                .is("Mapper.vm").thenSupply(() -> mapperPath + "Mapper.java")
                .is("Mapper.xml.vm").thenSupply(() -> mapperXmlPath + "Mapper.xml")
                .is("PO.vm").thenSupply(() -> poPath + ".java")
                .is("SaveDTO.vm").thenSupply(() -> dtoPath + "SaveDTO.java")
                .is("UpdateDTO.vm").thenSupply(() -> dtoPath + "UpdateDTO.java")
                .is("ListDTO.vm").thenSupply(() -> dtoPath + "ListDTO.java")
                .is("ListVO.vm").thenSupply(() -> voPath + "ListVO.java")
                .elseSupply(() -> "");
    }


}
