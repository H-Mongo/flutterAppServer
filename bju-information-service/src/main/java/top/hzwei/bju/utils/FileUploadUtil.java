package top.hzwei.bju.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 文件上传工具类
 * @author hzuwei
 * @version 1.0
 * @date 2020/3/3 16:30
 */
@Slf4j
public class FileUploadUtil {

    /**
     * 文件保存的父路径
     */
    private static final String FILE_PATH = "/home/projects/bju-services/static-resource/";
    /**
     *  文件存入DB后URL访问时的后缀地址链接(例如：images/moving/43a97094-b478-4149-9bff-772085d568a0.jpg)
     */
    private static final String FILE_URL_PREFIX = "/images/moving/";

    /**
     * 头像保存的位置后缀，也是URL路径后缀(例如：images/avatar/43a97094-b478-4149-9bff-772085d568a0.jpg)
     */
    private static final String AVATAR_URL_SAVE_PATH = "/images/avatars/";

    public static String uploadFile2Local(MultipartFile[] files){
        // 无文件待上传
        if(files == null || (files.length == 0)){
            log.info("#### 文件上传工具，无文件等待上传！");
            return null;
        }
        log.info("#### 文件上传工具，入参：files length={}",files.length);
        // 创建文件夹
        File parentPath = new File(FILE_PATH);
        // 不存在文件路径则创建
        if(!parentPath.exists()){
            // 多级创建
            boolean b = parentPath.mkdirs();
            // 文件夹创建失败
            if(!b){
                // 抛出异常
                throw new RuntimeException("文件夹创建失败！");
            }
        }

        // 用于存放文件名
        List<String> fileNames = new ArrayList<>(9);
        // 遍历文件数组
        for(MultipartFile file : files){
            if(!file.isEmpty()){
                // 获取文件的原始名称
                String originalFilename = file.getOriginalFilename();
                // 取出文件后缀名
                String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
                log.info("#### 文件上传工具，文件原始名称：originalFilename={}，文件后缀名：fileSuffix={}",originalFilename,fileSuffix);
                // 为了防止文件覆盖的情况，采用UUID作为前缀
                String fileName = FILE_URL_PREFIX + UUID.randomUUID().toString() + fileSuffix;
                log.info("#### 文件上传工具，：生成的文件名：fileName={}",fileName);
                try {
                    // 创建文件
                    final File destFile = new File(parentPath,fileName);
                    // 判断文件是否存在，不存在则创建
                    if(!destFile.exists()){
                        // 创建文件是否成功！
                        if(!destFile.mkdirs()){
                            log.info("#### 文件上传工具，创建目标文件失败！destFile={}",destFile.toString());
                            continue;
                        }
                    }
                    // 保存文件
                    file.transferTo(destFile);
                    // 保存文件名
                    fileNames.add(fileName);
                } catch (IOException e) {
                    log.info("#### 文件上传工具，单文件存入IO异常！");
                    log.error("#### 文件上传工具，单文件存入异常信息，e=",e);
                }
            }
        }
        // 转为字符串
       return CollectionUtils.isEmpty(fileNames) ? null : fileNames.stream().collect(Collectors.joining(","));
    }

    /**
     *  单文件上传
     * @param file  文件
     * @return  返回保存的文件URL字符串
     */
    public static String singleFileUpload(MultipartFile file){
        log.info("#### 文件上传工具，单文件上传，入参：file={}",file);
        if(ObjectUtils.isEmpty(file)){
            log.info("#### 文件上传工具，单文件上传，没有文件等待上传...");
            return null;
        }
        // 原始文件名
        final String originalFilename = file.getOriginalFilename();
        // 获取文件的后缀名
        final String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
        log.info("#### 文件上传工具，文件原始名称：originalFilename={}，文件后缀名：fileSuffix={}",originalFilename,fileSuffix);
        // 为了防止文件覆盖的情况，采用UUID作为前缀
        String fileName = AVATAR_URL_SAVE_PATH + UUID.randomUUID().toString() + fileSuffix;
        log.info("#### 文件上传工具，：生成的文件名：fileName={}",fileName);
        try {
            // 创建文件夹
            File parentPath = new File(FILE_PATH);
            // 文件路径是否存在，不存在则创建
            if(!parentPath.exists()){
                parentPath.mkdirs();
            }
            // 创建文件
            final File destFile = new File(parentPath,fileName);
            // 判断文件是否存在，不存在则创建
            if(!destFile.exists()){
                // 创建文件是否成功！
                if(!destFile.mkdirs()){
                    log.info("#### 文件上传工具，创建目标文件失败！destFile={}",destFile.toString());
                }
            }
            // 保存文件
            file.transferTo(destFile);
            // 保存文件名
           return fileName;
        } catch (IOException e) {
            log.info("#### 文件上传工具，单文件存入IO异常！");
            log.error("#### 文件上传工具，单文件存入异常信息，e=",e);
            return null;
        }
    }


}
