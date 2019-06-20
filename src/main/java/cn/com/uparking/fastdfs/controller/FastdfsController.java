package cn.com.uparking.fastdfs.controller;

import cn.com.uparking.fastdfs.domain.ReturnMessage;
import cn.com.uparking.fastdfs.service.FastdfsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by QinMing on 2017/06/27.
 */
@RestController
@RequestMapping("/fdfs")
@Api(value = "1.FastDFS Controller", description = " ")
public class FastdfsController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private FastdfsService fastdfsService;

    @PostMapping(value = "/upload", produces = "application/json")
    @ApiOperation("/fdfs/upload")
    public ReturnMessage upload(@RequestParam MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return new ReturnMessage("000001", "失败：没有上传文件！");
        }
        InputStream ins = null;
        try {
            ins = file.getInputStream();
            long fileSize = file.getSize();
            String filename = file.getOriginalFilename();
            String extName = filename.substring(filename.lastIndexOf(".") + 1);

            String result = fastdfsService.upload(ins, fileSize, extName);
            if (StringUtils.isEmpty(result)) {
                return new ReturnMessage("000002", "失败：文件上传失败！");
            }

            Map<String, Object> context = new HashMap<String, Object>();
            context.put("fileURL", result);
            return new ReturnMessage("000000", "文件上传成功！", context);
        } catch (Exception e) {
            logger.error("FastdfsController 接收文件失败！", e);
            return new ReturnMessage("000003", "失败：文件服务器错误！");
        } finally {
            if (ins != null)
                try {
                    ins.close();
                } catch (IOException e) {
                    // do nothing
                }
        }
    }
}
