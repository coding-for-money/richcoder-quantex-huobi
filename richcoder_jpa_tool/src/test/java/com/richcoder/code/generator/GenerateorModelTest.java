package com.richcoder.code.generator;


import com.richcoder.code.generator.conf.GenerateConfig;
import com.richcoder.code.generator.impl.*;
import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

public class GenerateorModelTest extends TestCase {

    public void testGenerator() {

        Set<String> setPrefix = new HashSet<>();
        setPrefix.add("oms_");
        Set<String> initTables = new HashSet<>();
        initTables.add("oms_channel_fund_history");

        GenerateConfig config = GenerateConfig.builder()
                .templateBasePath("/template/richcoder/")
                .modelPackageName("com.richcoder.fund.order.service.provider.model")
                .daoPackageName("com.richcoder.fund.order.service.provider.dao")
                .daoImplPackageName("com.richcoder.fund.order.service.provider.dao.impl")
                .servicePackageName("com.richcoder.fund.order.service.provider.service")
                .serviceImplPackageName("com.richcoder.fund.order.service.provider.service.impl")
                .controllerPackageName("com.richcoder.fund.order.service.provider.ctrl")
                .dtoPackageName("com.richcoder.fund.order.service.api.dto")
                .remotePackageName("com.richcoder.fund.order.service.api.remote")
                .skipTablePrefix(setPrefix)
                .initTables(initTables)
                .build();

        new GeneratorModeFreeMark().generator(config);
        new GeneratorDaoFreeMark().generator(config);
        new GeneratorServiceFreeMark().generator(config);
        new GeneratorServiceImplFreeMark().generator(config);
        new GeneratorControllerFreeMark().generator(config);
        new GeneratorDTOFreeMark().generator(config);
        new GeneratorModifyDTOFreeMark().generator(config);
        new GeneratorPageableDTOFreeMark().generator(config);
        new GeneratorFeignFreeMark().generator(config);
    }
}
