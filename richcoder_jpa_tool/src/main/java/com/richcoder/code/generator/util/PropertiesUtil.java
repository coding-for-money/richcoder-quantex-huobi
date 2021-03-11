package com.richcoder.code.generator.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class PropertiesUtil {

    private static final Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

    /**
     * 加载配置文件
     *
     * @param path
     * @param propertiesName
     * @return
     */
    public static Properties loadProperties(String path, String propertiesName) {
        Properties p = new Properties();
        InputStream in = null;
        if (!propertiesName.endsWith(".properties")) {
            propertiesName = propertiesName + ".properties";
        }
        try {
            if (StringUtils.isEmpty(path)) {
                in = new BufferedInputStream(PropertiesUtil.class.getClassLoader()
                        .getResourceAsStream(propertiesName));
            } else {
                in = new BufferedInputStream(new FileInputStream(new File(path + propertiesName)));
            }
            p.load(in);
            return p;
        } catch (IOException e) {
            log.error("读取配置文件错误", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 加载配置文件中的int数据
     *
     * @param p
     * @param key
     */
    public static int LoadNum(Properties p, String key) {
        String value = p.getProperty(key);
        if (!StringUtils.isEmpty(value)) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                log.error("数据转换错误 key:" + key + "\t value:" + value, e);
            }
        }
        return 0;
    }

    /**
     * 加载配置文件中的int数据
     *
     * @param p
     * @param key
     */
    public static long LoadLong(Properties p, String key) {
        String value = p.getProperty(key);
        if (!StringUtils.isEmpty(value)) {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                log.error("数据转换错误 key:" + key + "\t value:" + value, e);
            }
        }
        return 0;
    }
}
