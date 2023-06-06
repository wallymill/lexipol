package com.jwm.lexipol.controller;

import com.jwm.lexipol.service.FileUploadService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController()
public class FileUploadController {

  Logger logger = LoggerFactory.getLogger(FileUploadController.class);

  private final FileUploadService fileUploadService;

  public FileUploadController(final FileUploadService fileUploadService) {
    this.fileUploadService = fileUploadService;
  }


  @PostMapping("/upload")
  public ResponseEntity<HttpStatus> uploadFiles(@RequestParam String userId,
      @RequestParam List<MultipartFile> files) {
    logger.info("FileUploadController.uploadFiles:  userId:  {}   files:  {}", userId, files);
    fileUploadService.uploadFiles(userId, files);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
