package com.demo.common.utils;

import com.demo.common.exception.StandardException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.List;

@Slf4j
public class CsvUtil {

    /**
     * 解析csv文件并转成bean
     * @param file csv文件
     * @param clazz 类
     * @param <T> 泛型
     * @return 泛型bean集合
     */
    public <T> List<T> getCsvToBean(MultipartFile file, Class<T> clazz) {
        InputStreamReader in;
        try {
            in = new InputStreamReader(file.getInputStream(), "utf-8");
        } catch (Exception e) {
            throw new StandardException();
        }

        HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(clazz);

        CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(in)
                .withSeparator(',')
                .withQuoteChar('\'')
                .withMappingStrategy(strategy).build();
        return csvToBean.parse();
    }

}

