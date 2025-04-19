package com.example.cfaBackend.Service;
import com.example.cfaBackend.Entity.Category;
import com.example.cfaBackend.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);  // Guardar categoría
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);  // Obtener categoría por ID
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();  // Obtener todas las categorías
    }
    public void deleteCategoryById(Long id){
        categoryRepository.deleteById(id);

    }
}