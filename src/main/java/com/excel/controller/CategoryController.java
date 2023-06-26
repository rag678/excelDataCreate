package com.excel.controller;

import com.excel.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ExcelService excelService;
    @RequestMapping("/excel")
    public ResponseEntity<Resource> download() throws IOException {
        String fileName = "categories.xlsx";
        ByteArrayInputStream actualData = excelService.getActualData();
        InputStreamResource file = new InputStreamResource(actualData);
        ResponseEntity<Resource> body = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment: fileName =" + fileName).contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
        return body;
    }
}
