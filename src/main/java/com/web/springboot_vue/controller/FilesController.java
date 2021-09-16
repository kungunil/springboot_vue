package com.web.springboot_vue.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.web.springboot_vue.common.api.CommonResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 黄龙
 * @Date: 2021/08/15/15:34
 * @Description:
 */
@Controller
@CrossOrigin
@RequestMapping("/files")
public class FilesController {
    @Value("${server.port}")
    private String port;

    private static String ip = "http://localhost";


    @PostMapping("/upload")
    public CommonResult<String> upload(MultipartFile file)  {
        String originalFilename = file.getOriginalFilename();
        /*定义文件唯一标识*/
        String flag = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir")+"/src/main/resources/files/"+flag + "_"+ originalFilename;
        try {
            File file1 = FileUtil.writeBytes(file.getBytes(), rootFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CommonResult<String> success = CommonResult.success(ip + ":" + port + "/files/" + flag);
        return success;
    }

    @GetMapping("/{flag}")
    public void downLoad(HttpServletResponse response, @PathVariable String flag) {
        OutputStream os;
        String basePath = System.getProperty("user.dir")+"/src/main/resources/files/";//定位文件根路径
        List<String> fileNames = FileUtil.listFileNames(basePath);//获取该目录下的所有文件名称
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");//找到名字中有flag参数的文件
        try {
        if(StrUtil.isNotEmpty(fileName)){

                response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"utf-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName);//读取该文件的字节流
                os = response.getOutputStream();//获取响应的输出流
                os.write(bytes);//利用输出流把文件字节流写回前端
                os.flush();
                os.close();
        }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("下载文件失败");
        }


    }
}
