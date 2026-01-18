package com.emanuel.services;

import com.emanuel.modal.User;
import com.emanuel.payload.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductDTO prodictDTO, User user) throws Exception;
    ProductDTO updateProduct(Long id,ProductDTO prodictDTO, User user) throws Exception;
    void deleteProduct(Long id, User user) throws Exception;
    List<ProductDTO> getProductsByStoreId(Long storeId);
    List<ProductDTO>searchByKeyword(Long storeId, String keyword);
}
