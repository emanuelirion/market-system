package com.emanuel.services.impi;

import com.emanuel.mapper.ProductMapper;
import com.emanuel.modal.Category;
import com.emanuel.modal.Product;
import com.emanuel.modal.Store;
import com.emanuel.modal.User;
import com.emanuel.payload.dto.ProductDTO;
import com.emanuel.repository.CategoryRepository;
import com.emanuel.repository.ProductRepository;
import com.emanuel.repository.StoreRepository;
import com.emanuel.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductServiceImpi implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDTO createProduct(ProductDTO prodictDTO, User user) throws Exception {
        Store store = storeRepository.findById(prodictDTO.getStoreId()).orElseThrow(
                ()-> new Exception("Store not found")
        );
        Category category = categoryRepository.findById(prodictDTO.getCategoryId()).orElseThrow(
                ()-> new Exception("Category not found")
        );
        Product product = ProductMapper.toEntity(prodictDTO, store, category);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("product not found")
        );

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSku(productDTO.getSku());
        product.setImage(productDTO.getImage());
        product.setImage(productDTO.getImage());
        product.setMrp(productDTO.getMrp());
        product.setSellingPrice(productDTO.getSellingPrice());
        product.setBrand(productDTO.getBrand());
        product.setUpdatedAt(LocalDateTime.now());
        if(productDTO.getCategoryId() != null){
            Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(
                    ()-> new Exception("category not found")
            );
            product.setCategory(category);
        }


        Product savedProduct = productRepository.save(product);

        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {

        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("product not found")
        );
        productRepository.delete(product);

    }

    @Override
    public List<ProductDTO> getProductsByStoreId(Long storeId) {
        List<Product>products =  productRepository.findByStoreId(storeId);
        return products.stream().map(ProductMapper::toDTO).toList();
    }

    @Override
    public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {
        List<Product>products =  productRepository.searchByKeyword(storeId, keyword);
        return products.stream().map(ProductMapper::toDTO).toList();
    }
}
