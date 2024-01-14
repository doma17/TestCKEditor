package com.example.testckeditor.service;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.testckeditor.config.S3Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class ImageService {

    private final S3Config s3Config;

    @Autowired
    public ImageService(S3Config s3Config) {
        this.s3Config = s3Config;
    }

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${image.path}")
    private String localLocation;

    /**
     * 이미지 파일 -> 서버 로컬 저장 -> S3 업로드 -> 로컬 이미지 삭제 -> 이미지 URL 반환
     */
    public String imageUpload(MultipartRequest request) throws IOException {
        // request -> 이미지 파일 추출
        MultipartFile file = request.getFile("upload");

        // 이미지 파일 이름, 확장자 추출
        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.indexOf("."));

        // 파일 이름 유일성을 위한 UUID 추가
        String uuidFileName = UUID.randomUUID() + ext;
        // 서버 환경 저장 경로 생성
        String localPath = localLocation + uuidFileName;

        // 서버 환경에 이미지 저장
        File localFile = new File(localPath);
        file.transferTo(localFile);

        // 이미지 -> S3 업로드
        s3Config.amazonS3Client().putObject(
                new PutObjectRequest(bucket, uuidFileName, localFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead) // 외부에서 이미지 Read 기능하게 해주는 보안 설정
        );

        // S3 이미지 URL 반환
        String s3Url = s3Config.amazonS3Client().getUrl(bucket, uuidFileName).toString();
        log.info("[imageUpload] s3Url = {}", s3Url);

        // 서버에 저장한 이미지 삭제
        localFile.delete();

        return s3Url;
    }
}
