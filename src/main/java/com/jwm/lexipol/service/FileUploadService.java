package com.jwm.lexipol.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

  Logger logger = LoggerFactory.getLogger(FileUploadService.class);

  private final Executor executor;

  public FileUploadService(final Executor executor) {
    this.executor = executor;
  }

  public void uploadFiles(String userId, List<MultipartFile> files) {
    logger.info("FileUploadService.uploadFiles:  userId:  {} files:  {}", userId, files);
    files.stream().map(file -> CompletableFuture.supplyAsync(() -> uploadFile(userId, file), executor))
        .map(CompletableFuture::join).forEach(s -> logger.info(s.toString()));
  }

  public HttpStatus uploadFile(String userId, MultipartFile file) {
    logger.info("here I am:  userId:  {}  file:  {}", userId, file);
    return HttpStatus.OK;
  }

}
