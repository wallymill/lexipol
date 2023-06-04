package com.jwm.lexipol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileUploadController {

  Logger logger = LoggerFactory.getLogger(FileUploadController.class);

  @GetMapping(value = "/upload")
  public void uploadFiles() {
    logger.info("hello");
  }
}
