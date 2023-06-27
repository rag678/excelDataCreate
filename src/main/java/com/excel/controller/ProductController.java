package com.excel.controller;

import com.excel.entity.Product;
import com.excel.helper.Helper;
import com.excel.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/product/upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file){
        if (Helper.checkExcelFormat(file)){
            //true
            this.productService.save(file);
            return ResponseEntity.ok(("message : file is uploaded and data is save to db"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Upload Excel File");
    }

    @GetMapping("/product")
    public List<Product> getAllProduct()
    {
        return this.productService.getAllProduct();
    }
 }
