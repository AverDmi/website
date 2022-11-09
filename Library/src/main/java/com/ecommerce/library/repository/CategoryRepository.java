package com.ecommerce.library.repository;

import com.ecommerce.library.dto.CategoryDto;
import com.ecommerce.library.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.is_activated = true AND c.is_deleted = false")
    List<Category> findAllByActivated();

    /*Customer*/
    @Query("SELECT new com.ecommerce.library.dto.CategoryDto(c.id, c.name, count(p.category.id)) FROM Category c INNER JOIN Product p ON p.category.id = c.id" +
            " WHERE c.is_activated = true AND c.is_deleted = false GROUP BY c.id")
    List<CategoryDto> getCategoryAndProduct();
}
