package cn.com.uparking.fastdfs.service;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.UploadCallback;
import org.csource.fastdfs.UploadStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;

/**
 * Created by QinMing on 2017/06/27.
 */
@Service
public class FastdfsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${IMAGE_SERVER_BASE_URL}")
    private String IMAGE_SERVER_BASE_URL;

    @PostConstruct
    private void initConfig() throws Exception {
        ClientGlobal.initByProperties("fastdfs-client.properties");
        logger.info("ClientGlobal.configInfo(): \n" + ClientGlobal.configInfo());
        logger.info(IMAGE_SERVER_BASE_URL);
    }

    public String upload(InputStream ins, long fileSize, String extName) {
        long t0 = System.currentTimeMillis();
        StorageClient1 client = new StorageClient1();
        long t1 = System.currentTimeMillis();
        UploadCallback callback = new UploadStream(ins, fileSize);
        String result;
        try {
            result = client.upload_file1(null, fileSize, callback, extName, null);
        } catch (Exception e) {
            logger.error("FastdfsService 上传文件失败！", e);
            return null;
        }
        result = IMAGE_SERVER_BASE_URL + result;
        long t2 = System.currentTimeMillis();
        logger.info(result + " init client: " + (t1 - t0) + ", upload file: " + (t2 - t1));
        return result;
    }
}
