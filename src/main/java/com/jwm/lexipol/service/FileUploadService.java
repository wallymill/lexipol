package com.jwm.lexipol.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

  Logger logger = LoggerFactory.getLogger(FileUploadService.class);

  public void uploadFiles(String userId, List<MultipartFile> files) {
    logger.info("FileUploadService.uploadFiles:  userId:  {} files:  {}", userId, files);
  }

}
