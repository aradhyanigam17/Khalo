package com.aradhya.controller;

import com.aradhya.model.Category;
import com.aradhya.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    CategoryService categoryService ;

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome" ;
    }

    @GetMapping("/admin/categories")
    public String getCategories(Model model){  // model is the interface where user pass the value
        model.addAttribute("categories", categoryService.getAllCategory()) ;  //it will show the list of categories via for each loop in html page
        return "categories" ; //this direct to html pages categories
    }

    @GetMapping("/admin/categories/add") //for saving category adding category
    public String getCatAdd(Model model){  // model is the interface where user pass the value
        model.addAttribute("category",new Category()); // here model is passing id and name to category class instance
        return "categoriesAdd" ; // redirect to same name as page
    }

    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category){ //@ModelAttribute fetch the category from list
        categoryService.addCategory(category); //this adds the category in repository
        return "redirect:/admin/categories" ; // and again redirect to categories page
    }

//    @PutMapping("/admin/categories")
//    public void

    @GetMapping("/admin/categories/delete/{id}") // here we are getting the id of the item which gonna delete
    public String deleteCategory(@PathVariable int id){ // use @PathVariable to get id, it find by itself
        categoryService.removeCategoryById(id); //we create a removeCategoryById(id) in CategoryService by using deleteById()
        return "redirect:/admin/categories" ; //and then redirect to admin/categories
    }

    @GetMapping("/admin/categories/update/{id}")  //@get mapping is used bcz we are working with view page i.e html page
    public String updateCategory(@PathVariable int id , Model model){
       Optional<Category> category = categoryService.getCategoryById(id);
       if(category.isPresent()){
           model.addAttribute("category", category.get()) ;
           return "categoriesAdd" ;
       }
       else {
           return "404" ;
       }
    }
}
