package com.example.cfaBackend.Controller;

import com.example.cfaBackend.Entity.Category;
import com.example.cfaBackend.Service.CategoryService;
import com.example.cfaBackend.User.User;
import com.example.cfaBackend.User.UserIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class UserCategoryController {

    @Autowired
    private UserIService userIService;

    @Autowired
    private CategoryService categoryService;

    // Asignar una categoría a un usuario
    @PostMapping("/users/{userId}/categories/{categoryId}")
    public User addCategoryToUser(@PathVariable Long userId, @PathVariable Long categoryId) {
        User user = userIService.buscaUserPorId(userId.intValue());
        Category category = categoryService.getCategoryById(categoryId);
        user.getCategories().add(category);
        return userIService.guardarUser(user);
    }

    // Crear una nueva categoría
    @PostMapping("/create")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    // Obtener todas las categorías
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Eliminar una categoría por ID
@DeleteMapping("/delete/{categoryId}")
public void deleteCategory(@PathVariable Long categoryId) {
    categoryService.deleteCategoryById(categoryId);
}

}
