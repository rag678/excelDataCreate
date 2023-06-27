package com.excel;

import com.excel.repo.CategoryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExcelsheetApplicationTests {

	@Autowired
	private CategoryRepo categoryRepo;
	@Test
	void contextLoads() {
	}
	@Test
	public void testCategoryRepo(){
		System.out.println("Test started");
		categoryRepo.findAll().forEach(category -> System.out.println(category.getTitle()));
	}

}
