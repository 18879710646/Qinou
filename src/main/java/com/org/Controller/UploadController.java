package com.org.Controller;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.junit.Test;
import org.apache.log4j.*;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @Author HP
 * @Date 2021/11/27 14:24
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */
@Component
public class UploadController {
    String AccessKey="IVXJgcfHKRlsQ4fS2E533mK8ftxt4MBxfryFAnkO";
    String SecretKey="Svhs6OnzrBUsiMABhhZJo-_iAeB8MuAyEFYoVugA";
    String bucket="firstbbs";
    @Test
    public  void Test( ){

        Configuration cfg = new Configuration(Zone.zone0());
        String AccessKey="IVXJgcfHKRlsQ4fS2E533mK8ftxt4MBxfryFAnkO";
        String SecretKey="Svhs6OnzrBUsiMABhhZJo-_iAeB8MuAyEFYoVugA";
        UploadManager uploadManager = new UploadManager(cfg);
        String localFille="D:\\图片\\背景图\\1.PNG";
        String name="美女";
        String bucket="firstbbs";
        Auth auth = Auth.create(AccessKey, SecretKey);
        String uploadToken = auth.uploadToken(bucket);
        try {
            Response put = uploadManager.put(localFille, name, uploadToken);
            String address = put.address;
            System.out.println(address);
        } catch (QiniuException e) {
            e.printStackTrace();
            Response response = e.response;
            System.out.println(response.toString());
        }
    }


    //删除空间中的文件
    @Test
    public void Delete(){
        Configuration configuration = new Configuration(Zone.zone0());
        String name="美女";
        Auth auth = Auth.create(AccessKey, SecretKey);
        BucketManager manager = new BucketManager(auth, configuration);
        try {
            manager.delete(bucket,name);
        } catch (QiniuException e) {
            e.printStackTrace();
            System.out.println(e.code());
            System.out.println(e.response.toString());

        }
    }
//    获取空间文件列表
    @Test
    public  void QueryFile(){

        Configuration configuration = new Configuration(Zone.zone0());
        Auth auth = Auth.create(AccessKey, SecretKey);
        BucketManager bucketManager = new BucketManager(auth, configuration);
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, "");
        FileInfo[] next = fileListIterator.next();
        for (FileInfo fileInfo : next) {
//             输出的是文件名
            System.out.println("输出"+fileInfo.key);
        }

    }
}
