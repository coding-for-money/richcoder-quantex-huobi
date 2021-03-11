package com.richcoder.code.generator.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class FileUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);


    public static void wirtToFile(String path, String fileName, StringBuffer content) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String target = path + File.separator + fileName + ".java";

        File file = new File(target);

        if (file.exists()) {
            if (path.contains("model")) {
                String fileBak = target + "-bak-" + DateUtil.time2String2(new Date());
                log.warn("此实体类文件已存在！ 保存副本路径为：" + fileBak);

                boolean result = file.renameTo(new File(fileBak));
                if (!result) {
                    log.warn("保存副本失败，跳过此文件！");
                    return;
                } else {
                    file = new File(target);
                }
            } else {
                log.warn(" {} 此类文件已存在!!! 跳过，如需重新生成，请删除原有文件...", target);
                return;
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(content.toString());
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static FileWriter getWriter(String path, String fileName) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String target = path + File.separator + fileName + ".java";

        File file = new File(target);

        if (file.exists()) {
            if (path.contains("model")) {
                String fileBak = target + "-bak-" + DateUtil.time2String2(new Date());
                log.warn("此实体类文件已存在！ 保存副本路径为：" + fileBak);

                boolean result = file.renameTo(new File(fileBak));
                if (!result) {
                    log.warn("保存副本失败，跳过此文件！");
                    return null;
                } else {
                    file = new File(target);
                }
            } else {
                log.warn(" {} 此类文件已存在!!! 跳过，如需重新生成，请删除原有文件...", target);
                return null;
            }
        }

        FileWriter fw = null;
        try {
            return new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * H:\projects_learn\framework
     *
     * @return
     */
    public static String getProjectPath() {
        return System.getProperty("user.dir");
    }
}
