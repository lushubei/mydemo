package com.example.imakeyouth.vo.req;


import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lushubei on 17/10/2.
 */
public class PictureReq {

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}

