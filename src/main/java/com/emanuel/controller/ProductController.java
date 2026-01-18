package com.emanuel.controller;

import com.emanuel.modal.User;
import com.emanuel.payload.dto.ProductDTO;
import com.emanuel.payload.response.ApiResponse;
import com.emanuel.services.ProductService;
import com.emanuel.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO,
                                             @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(
                productService.createProduct(
                        productDTO,user
                )
        );
    }

    @GetMapping("store//{storeId}")
    public ResponseEntity<List<ProductDTO>> getByStoreId(@PathVariable Long storeId,
                                                        @RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(
                productService.getProductsByStoreId(
                        storeId
                )
        );
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO productDTO,
                                             @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(
                productService.updateProduct(id,
                        productDTO,user
                )
        );
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id,
                                                    @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.getUserFromJwtToken(jwt);

        productService.deleteProduct(id
                ,user
        );
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Product deleted successfully");
        return ResponseEntity.ok(apiResponse);

    }
    @GetMapping("store//{storeId}/search")
    public ResponseEntity<List<ProductDTO>> searchByKeyword(@PathVariable Long storeId,@RequestParam String keyword,
                                                         @RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(
                productService.searchByKeyword(
                        storeId,
                        keyword
                )
        );
    }
}
