package com.hzn.sales.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileUtil {
    private static final Log logger = LogFactory.getLog(PropertiesFileUtil.class);

    private static Properties props;
    static{
        loadProps();
    }

    synchronized static private void loadProps(String fileName){
        logger.info("开始加载"+fileName+"文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
            in = PropertiesFileUtil.class.getClassLoader().getResourceAsStream(fileName);
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error(fileName+"文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error(fileName+"文件流关闭出现异常");
            }
        }
        logger.info("加载"+fileName+"文件内容完成...........");
    }
    synchronized static private void loadProps()
    {
        loadProps("config.properties");
    }
    public static String getProperty(String key,String fileName){
        if(null == props) {
            loadProps(fileName);
        }
        return props.getProperty(key);
    }
    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }
}

