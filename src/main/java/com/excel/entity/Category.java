package com.excel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "id")
    private String categoryId;
    @Column(name = "category_title",length = 60,nullable = false)
    private String title;
    @Column(name = "category_desc",length = 500)
    private String description;
    private String coverImage;

    public Category() {
    }

    public Category(String categoryId, String title, String description, String coverImage) {
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.coverImage = coverImage;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
