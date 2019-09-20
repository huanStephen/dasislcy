package org.eocencle.dasislcy.controller;

import org.eocencle.dasislcy.service.ISubjectService;
import org.eocencle.dasislcy.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@RestController
@RequestMapping("/web/subject")
public class SubjectWebController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${main.upload-dir}")
    private String uploadDir;

    @Value("${main.template-dir}")
    private String templateDir;

    @Autowired
    private ISubjectService subjectService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping("/upload_subject")
    public Result<Boolean> fileUpload2(@RequestParam("file") MultipartFile file){
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        try {
            String fileName = file.getOriginalFilename();
            if (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls")) {
                throw new RuntimeException("文件格式错误！");
            }
            String path=uploadDir + "/" + new Date().getTime() + file.getOriginalFilename();
            File newFile=new File(path);
            //通过CommonsMultipartFile的方法直接写文件
            file.transferTo(newFile);
            subjectService.importSubjectFile(path);

            result.setData(true);
            result.setMsg("文件上传成功！");
        } catch (Exception e) {
            result.setStatus(Result.STATUS_FAILED);
            result.setData(false);
            result.setMsg("文件上传失败！");
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 文件下载
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/download_template")
    @ResponseBody
    public String download(HttpServletResponse response) {
        String fileName = "template.xlsx";
        //设置文件路径
        File file = new File(templateDir + "/" + fileName);
        //File file = new File(realPath , fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "下载失败";
    }
}
