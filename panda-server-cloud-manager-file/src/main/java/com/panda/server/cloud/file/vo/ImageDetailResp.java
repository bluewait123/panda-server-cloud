package com.panda.server.cloud.file.vo;

import lombok.Data;

@Data
public class ImageDetailResp{
    private String imageId;
    private String imageTitle;
    private String imageFileName;
    private String imageFileUrl;
    private int imageFileSize;
}
