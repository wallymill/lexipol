package com.jwm.lexipol.dto;

import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadDto {

  private String userId;

  private MultipartFile file;

  private HttpStatus httpStatus;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileUploadDto that = (FileUploadDto) o;
    return Objects.equals(userId, that.userId) && Objects.equals(file, that.file)
        && httpStatus == that.httpStatus;
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, file, httpStatus);
  }

  @Override
  public String toString() {
    return "FileUploadDto{" +
        "userId='" + userId + '\'' +
        ", file=" + file +
        ", httpStatus=" + httpStatus +
        '}';
  }
}
