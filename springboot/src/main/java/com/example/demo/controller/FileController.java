package com.example.demo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.example.demo.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {
    @Value("${server.port}")
    private String port;

    private static final String ip = "http://localhost";

    /**
     * 上傳接口
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {

        String originalFilename = file.getOriginalFilename();    // 獲取傳來的檔案名稱

        String flag = IdUtil.fastSimpleUUID();                  // 定義文件的唯一標示 (前綴)

        String rootFilePath = System.getProperty("user.dir")    // 獲取Filej文件夾路徑
                + "/springboot/src/main/resources/files/"
                + flag + "_" + originalFilename;

        FileUtil.writeBytes(file.getBytes(), rootFilePath);     // 透過 hutool 工具類 寫入檔案 (記得 要拋出異常)

        return Result.success(ip + ":" + port + "/files/" + flag);    // 返回結果 url

    }

    /**
     * 富文本文件上传接口
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/editor/upload")
    public JSON editorUpload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();    // 獲取傳來的檔案名稱
        String flag = IdUtil.fastSimpleUUID();                  // 定義文件的唯一標示 (前綴)
        String rootFilePath = System.getProperty("user.dir")    // 獲取Filej文件夾路徑
                + "/springboot/src/main/resources/files/"
                + flag + "_" + originalFilename;
        FileUtil.writeBytes(file.getBytes(), rootFilePath);     // 透過 hutool 工具類 寫入檔案 (記得 要拋出異常)


        String url = ip + ":" + port + "/files/" + flag;
        JSONObject json = new JSONObject();
        json.set("errno", 0);
        JSONArray arr = new JSONArray();
        JSONObject data = new JSONObject();
        arr.add(data);
        data.set("url", url);
        json.set("data", arr);
        return json;  // 返回结果 url
    }


    /**
     * 下載接口
     *
     * @param flag
     * @param response
     */
    @GetMapping("/{flag}")
    public void getFiles(@PathVariable String flag, HttpServletResponse response) {
        OutputStream os;  // 新建一個輸出流對象
        String basePath = System.getProperty("user.dir")
                + "/springboot/src/main/resources/files/";              // 於文件上傳的根路徑
        List<String> fileNames = FileUtil.listFileNames(basePath);      // 獲取所有的文件名稱
        String fileName = fileNames.stream()
                .filter(name -> name.contains(flag)).findAny().orElse("");
        // 找到跟參數一致的文件
        try {
            if (StrUtil.isNotEmpty(fileName)) {
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName);  // 通過文件的路徑讀取文件字節流
                os = response.getOutputStream();   // 通過輸出流返回文件
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            System.out.println("文件下載失敗");
        }
    }

}
