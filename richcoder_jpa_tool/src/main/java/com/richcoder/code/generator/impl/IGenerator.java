package com.richcoder.code.generator.impl;


import com.richcoder.code.generator.conf.GenerateConfig;

/**
 * @author richcoder
 */
public interface IGenerator {

    default void generator(GenerateConfig config) {
    }

    default void generator(GenerateConfig config, GeneratoraAdapter adapter) {
    }
}
