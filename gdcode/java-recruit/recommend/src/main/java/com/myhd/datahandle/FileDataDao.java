package com.myhd.datahandle;

import lombok.extern.slf4j.Slf4j;
import org.lenskit.data.dao.DataAccessObject;
import org.lenskit.data.dao.file.StaticDataSource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author TARZAN
 */
@Slf4j
public class FileDataDao {

    private static DataAccessObject dao;

    public static DataAccessObject get(){
        if(dao==null){
          return load();
        }
        return dao;
    }

    public static DataAccessObject load(){
        String path = null;
        try {
            path = new ClassPathResource("data/recruitlens.yml").getURI().getPath();
            log.info("FileDataDao.读取的配置文件路径是:{}", path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Path dataFile = Paths.get(path);
        try {
            StaticDataSource data= StaticDataSource.load(dataFile);
            log.info("FileDataDao.data:{}",data);
            dao= data.get();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return dao;
    }

}
