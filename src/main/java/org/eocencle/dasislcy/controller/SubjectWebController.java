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

    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping("/upload_subject")
    public Result<Boolean> fileUpload2(@RequestParam("file") MultipartFile file){
        Result<Boolean> result = new Result<>(Result.STATUS_SUCCESSED);

        try {
            long  startTime=System.currentTimeMillis();
            logger.debug("fileName："+file.getOriginalFilename());
            String path=uploadDir + "/" + new Date().getTime() + file.getOriginalFilename();

            File newFile=new File(path);
            //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
            file.transferTo(newFile);
            long endTime=System.currentTimeMillis();
            logger.debug("采用file.Transto的运行时间："+String.valueOf(endTime-startTime)+"ms");

            result.setData(true);
            result.setMsg("文件上传成功！");
        } catch (IOException e) {
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
        File file = new File(templateDir + "/" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "文件下载失败";
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "文件下载成功";
    }
}
