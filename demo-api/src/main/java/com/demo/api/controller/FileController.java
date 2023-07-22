package com.demo.api.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/demo/v1/file")
public class FileController {



    private String uplodaPath;

    private Map<String, List<File>> shardMap = new ConcurrentHashMap<>();
    @PostMapping("upload")
    public void shardingUpload(@RequestParam int currentSharding, @RequestParam int totalSharding,
                               @RequestParam MultipartFile file, @RequestParam String fileName) {
        //分片临时文件
        String shardName = file.getOriginalFilename() + "." + currentSharding;
        File shardFile = new File(uplodaPath, shardName);
        //记录分片上传状态
        List<File> shardList =shardMap.get(shardFile);
        if (CollectionUtils.isEmpty(shardList)) {
            shardList = new ArrayList<>(totalSharding);
            shardMap.put(shardName, shardList);
        }
        shardList.add(shardFile);
    }

    /**
     * 获取所有分片，并按照分片的顺序将它们合并成一个文件
     * @param fileName
     * @return
     */
    @PostMapping("/merge")
    public String merge(@RequestParam String fileName) throws Exception {
        List<File> shardList = shardMap.get(fileName);
        if (CollectionUtils.isEmpty(shardList)) {
            throw new RuntimeException("分片不存在");
        }
        File outputFile = new File(uplodaPath, fileName);
        try(FileChannel outChannel = new FileInputStream(outputFile).getChannel()) {
            for (int i = 0; i < shardList.size(); i++) {
                try (FileChannel inChannel = new FileInputStream(shardList.get(i)).getChannel()) {
                    inChannel.transferTo(0, inChannel.size(), outChannel);
                }
                shardList.get(i).delete();
            }
        }
        shardMap.remove(fileName);
        return null;
    }
}
