package com.aradhya.service;

import com.aradhya.model.Category;
import com.aradhya.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository ;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public void removeCategoryById(int id){
        categoryRepository.deleteById(id);
    }

    public void updateCategoryById(int id){
        categoryRepository.findById(id);
    }

    public void addCategory(Category category){ //adding into table
        categoryRepository.save(category) ;
    }



}
