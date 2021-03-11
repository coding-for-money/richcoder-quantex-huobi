package com.richcoder.code.generator.impl;

import com.richcoder.code.generator.conf.GenerateConfig;

public class GeneratorServiceFreeMark extends AbstractGeneratorFreeMark implements IGenerator {

    @Override
    public void generator(GenerateConfig config) {
        super.generator(config, new GeneratoraAdapter() {
            @Override
            public String getTemplateName() {
                return "service.ftl";
            }

            @Override
            public String getPackageName(GenerateConfig config) {
                return config.getServicePackageName();
            }

            @Override
            public String getClassName(String modelName) {
                return modelName + "Service";
            }
        });
    }
}
