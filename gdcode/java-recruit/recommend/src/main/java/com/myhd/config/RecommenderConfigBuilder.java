package com.myhd.config;

import lombok.extern.slf4j.Slf4j;
import org.lenskit.LenskitConfiguration;
import org.lenskit.RecommenderConfigurationException;
import org.lenskit.config.ConfigHelpers;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @Classname LenskitConfiguration
 * @Description
 * @Date 2024/4/2 下午8:25
 * @Created by HmEJ
 */
@Slf4j
public class RecommenderConfigBuilder {

    /**
     * 获取LenskitConfiguration
     *
     * @param type 推荐类型
     * @return ITokenGranter
     */
    public static LenskitConfiguration createConfig(String type) {
        try {
            String path = new ClassPathResource("etc/" + type + ".groovy").getURI().getPath();
            log.info("RecommenderConfigBuilder.读取的配置文件路径是:{}", path);
            return ConfigHelpers.load(new File(path));
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
