package com.jwm.lexipol.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jwm.lexipol.service.FileUploadService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
class FileUploadControllerTest {

  @InjectMocks
  private FileUploadController uut;

  @Mock
  private FileUploadService fileUploadService;

  private List<MultipartFile> fileList;

  @BeforeEach
  void setUp() {
    fileList = new ArrayList<>();
    MockMultipartFile file = new MockMultipartFile("file", "file1.docx", MediaType.TEXT_PLAIN_VALUE,
        "File1 text".getBytes());
    fileList.add(file);
    file = new MockMultipartFile("file", "file2.pdf", MediaType.TEXT_PLAIN_VALUE,
        "File1 pdf".getBytes());
    fileList.add(file);
  }

  @ParameterizedTest
  @DisplayName("uut uploadFiles will return http 200 due to having no validations")
  @CsvSource({
      "jwm, true",
      "null, true",
      "null, false",
      "abc, false"
  })
  void test1(String userId, boolean useFileList) {
    final var status = uut.uploadFiles(userId, useFileList ? fileList : null);
    assertEquals(HttpStatus.OK, status.getStatusCode());
  }

}