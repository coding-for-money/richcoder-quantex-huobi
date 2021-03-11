package com.richcoder.code.generator.conf;


import com.richcoder.code.generator.util.FileUtil;
import com.richcoder.code.generator.vo.TableInfo;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author richcoder
 */
@Data
@Builder
public class GenerateConfig {

    private String templateBasePath = "/template/";

    private List<TableInfo> tableInfos;
    /**
     * 如果不为空，则跳过包含的表
     */
    private Set<String> skipTables;
    /**
     * 如果不为空，则只初始化包含的表， 如果表也存在于skipTables中，则跳过
     */
    private Set<String> initTables;
    /**
     * 去除的表字段头
     */
    private Set<String> skipTablePrefix;

    /**
     * 工程路径
     */
    private String filePath;

    /**
     * model包名
     */
    private String modelPackageName = "model";

    /**
     * vo包名
     */
    private String dtoPackageName = "dto.response";
    /**
     * vo包名
     */
    private String pageDtoPackageName = "dto.request";
    /**
     * vo包名
     */
    private String modifyDtoPackageName = "dto.request";

    /**
     * dao包名
     */
    private String daoPackageName = "dao";
    /**
     * daoImpl包名
     */
    private String daoImplPackageName = "dao.impl";
    /**
     * service包名
     */
    private String servicePackageName = "service";
    /**
     * serviceImpl包名
     */
    private String serviceImplPackageName = "service.impl";
    /**
     * 控制器包名
     */
    private String controllerPackageName = "ctrl";

    /**
     * remote包名
     */
    private String remotePackageName = "remote";

    public GenerateConfig() {
    }

    public GenerateConfig(String templateBasePath, List<TableInfo> tableInfos, Set<String> skipTables,
                          Set<String> initTables, Set<String> skipTablePrefix, String filePath,
                          String modelPackageName, String dtoPackageName, String pageDtoPackageName,
                          String modifyDtoPackageName, String daoPackageName, String daoImplPackageName,
                          String servicePackageName, String serviceImplPackageName,
                          String controllerPackageName, String remotePackageName) {
        this.templateBasePath = templateBasePath == null ? this.templateBasePath : templateBasePath;
        this.skipTables = skipTables == null ? this.skipTables : skipTables;
        this.initTables = initTables == null ? this.initTables : initTables;
        this.skipTablePrefix = skipTablePrefix == null ? this.skipTablePrefix : skipTablePrefix;
        this.modelPackageName = modelPackageName == null ? this.modelPackageName : modelPackageName;
        this.dtoPackageName = dtoPackageName == null ? this.dtoPackageName : dtoPackageName;
        this.pageDtoPackageName =
                pageDtoPackageName == null ? this.pageDtoPackageName : pageDtoPackageName;
        this.modifyDtoPackageName =
                modifyDtoPackageName == null ? this.modifyDtoPackageName : modifyDtoPackageName;
        this.daoPackageName = daoPackageName == null ? this.daoPackageName : daoPackageName;
        this.daoImplPackageName =
                daoImplPackageName == null ? this.daoImplPackageName : daoImplPackageName;
        this.servicePackageName =
                servicePackageName == null ? this.servicePackageName : servicePackageName;
        this.serviceImplPackageName =
                serviceImplPackageName == null ? this.serviceImplPackageName : serviceImplPackageName;
        this.controllerPackageName =
                controllerPackageName == null ? this.controllerPackageName : controllerPackageName;
        this.remotePackageName = remotePackageName == null ? this.remotePackageName : remotePackageName;
        this.filePath = FileUtil.getProjectPath();
        this.tableInfos = LoadDBInfo.queryTableInfos(skipTables, initTables, skipTablePrefix);
    }
}
