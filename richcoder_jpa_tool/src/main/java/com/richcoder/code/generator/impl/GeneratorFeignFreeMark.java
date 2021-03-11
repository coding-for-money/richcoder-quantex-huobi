package com.richcoder.code.generator.impl;


import com.richcoder.code.generator.conf.GenerateConfig;
import com.richcoder.code.generator.vo.TableInfo;

public class GeneratorFeignFreeMark extends AbstractGeneratorFreeMark implements IGenerator {

    @Override
    public void generator(final GenerateConfig config) {
        generator(config, new GeneratoraAdapter() {
            @Override
            public String getTemplateName() {
                return "feign.ftl";
            }

            @Override
            public String getPackageName(GenerateConfig config, TableInfo tableInfo) {
                return config.getRemotePackageName();
            }

            @Override
            public String getClassName(String modelName) {
                return "Remote" + modelName + "Service";
            }
        });
    }
}
