package com.emanuel.mapper;

import com.emanuel.modal.Category;
import com.emanuel.payload.dto.CategoryDTO;

public class CategoryMapper {
    public static CategoryDTO toDTO(Category category) {

        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .storeId(category.getStore()!= null?category.getStore().getId():null)
                .build();
    }
}
