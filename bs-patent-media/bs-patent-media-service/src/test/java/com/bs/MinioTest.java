package com.bs;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @description 测试MinIO
 * @author Mr.M
 * @date 2022/9/11 21:24
 * @version 1.0
 */
public class MinioTest {

    static MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://47.101.53.233:9000")
                    .credentials("QPJWIM5V600FZ6GO2UVP", "z9RPbMuJRmK2gcIIPDP5+lAz7TIYHCE95YA+4tq+")
                    .build();

   //上传文件
    @Test
    public  void upload() {
        try {
            UploadObjectArgs testbucket = UploadObjectArgs.builder()
                    .bucket("test6")
//                    .object("test001.mp4")
                    .object("001/touxiang.jpg")//添加子目录
                    .filename("C:\\Users\\20259\\Desktop\\简历\\头像.jpg")
                    .contentType("video/mp4")//默认根据扩展名确定文件内容类型，也可以指定
                    .build();
            minioClient.uploadObject(testbucket);
            System.out.println("上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("上传失败");
        }

    }

}
