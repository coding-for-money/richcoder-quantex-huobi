package com.richcoder.code.generator.impl;


import com.richcoder.code.generator.conf.GenerateConfig;
import com.richcoder.code.generator.vo.TableInfo;

public interface GeneratoraAdapter {

    String getTemplateName();

    default String getPackageName(GenerateConfig config, TableInfo tableInfo) {
        return getPackageName(config);
    }

    default String getPackageName(GenerateConfig config) {
        return null;
    }

    String getClassName(String modelName);
}
