package com.jwm.lexipol.service;

import com.jwm.lexipol.dto.FileUploadDto;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

  Logger logger = LoggerFactory.getLogger(FileUploadService.class);

  private final Executor executor;
  private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

  public FileUploadService(final Executor executor) {
    this.executor = executor;
  }

  public void uploadFiles(String userId, List<MultipartFile> files) {
    logger.info("FileUploadService.uploadFiles:  userId:  {} files:  {}", userId, files);
    List<FileUploadDto> statusList = new ArrayList<>();
    AtomicInteger i = new AtomicInteger();
    files.stream()
        .map(file -> CompletableFuture.supplyAsync(() -> uploadFile(userId, file), executor))
        .map(CompletableFuture::join)
        .forEach(status -> statusList.add(i.getAndIncrement(), status));
    logger.info("statusList:  {}", statusList);
  }

  private FileUploadDto uploadFile(String userId, MultipartFile file) {
    logger.info("FileUploadService.uploadFile:  userId:  {}  file:  {}", userId, file);
    FileUploadDto fileUploadDto = new FileUploadDto();
    fileUploadDto.setUserId(userId);
    fileUploadDto.setFile(file);
    fileUploadDto.setHttpStatus(doUpload(file));
    return fileUploadDto;
  }

  private HttpStatus doUpload(MultipartFile file) {
    if (file.isEmpty()) {
      return HttpStatus.BAD_REQUEST;
    }

    try {
      byte[] bytes = file.getBytes();
      Path path = Paths.get(TEMP_DIR + file.getOriginalFilename());
      Files.write(path, bytes);
      if (new Random().nextBoolean()) {
        throw new IOException("rando");
      }
    } catch (IOException e) {
      logger.error("Error uploading file: {}", file.getOriginalFilename());
      return HttpStatus.BAD_REQUEST;
    }

    return HttpStatus.OK;
  }

}
