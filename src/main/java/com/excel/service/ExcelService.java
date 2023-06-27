package com.excel.service;

import com.excel.entity.Category;
import com.excel.helper.Helper;
import com.excel.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
@Service
public class ExcelService {
    @Autowired
    private CategoryRepo categoryRepo;

    public ByteArrayInputStream getActualData() throws IOException {
        List<Category> all = categoryRepo.findAll();
        ByteArrayInputStream byteArrayInputStream = Helper.dataToExcel(all);
        return byteArrayInputStream;
    }
}
