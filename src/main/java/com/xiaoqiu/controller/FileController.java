package com.xiaoqiu.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.xiaoqiu.config.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Description :
 * @Time : 2023/1/24 19:26
 * @Author : xiaoqiuxx
 */

@RestController
@RequestMapping("/files")
public class FileController {

    private static final String IP = "http://localhost";
    @Value("${server.port}")
    private String port;

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return Result.error("-1", "请上传一张图片");
        }
        String fileName = file.getOriginalFilename();
        String flag = IdUtil.fastSimpleUUID();
        String fileName_1 = FileUtil.extName(fileName);
        if (!(fileName_1.equals("jpg") || fileName_1.equals("png"))) {
            return Result.error("-1", "只能上传jpg和png");
        }
        String rootFilePath = System.getProperty("user.dir") + "/src/main/resources/files/" + flag + "." + fileName_1;
        FileUtil.writeBytes(file.getBytes(), rootFilePath);
        return Result.success(IP + ":" + port + "/files/" + flag);
    }

    /*
下载接口
 */
    @GetMapping("/{flag}")
    public void getFiles(@PathVariable String flag, HttpServletResponse response) {
        OutputStream os;//新建一个输出流对象
        String basePath = System.getProperty("user.dir") + "/src/main/resources/files/"; //定义文件上传的根路径
        System.out.println(basePath);
        List<String> fileNames = FileUtil.listFileNames(basePath);//获取所有的文件名称
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");//找到跟参数一致的文件
        try {
            if (StrUtil.isNotEmpty(fileName)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName);//通过文件的路径读取文件字节流
                os = response.getOutputStream();//通过response的输出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下载失败");
        }

    }
}
