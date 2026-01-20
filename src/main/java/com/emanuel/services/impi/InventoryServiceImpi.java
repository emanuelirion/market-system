package com.emanuel.services.impi;

import com.emanuel.mapper.InventoryMapper;
import com.emanuel.modal.Branch;
import com.emanuel.modal.Inventory;
import com.emanuel.modal.Product;
import com.emanuel.payload.dto.InventoryDTO;
import com.emanuel.repository.BranchRepository;
import com.emanuel.repository.InventoryRepository;
import com.emanuel.repository.ProductRepository;
import com.emanuel.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpi  implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;


    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception {
        Branch branch = branchRepository.findById(inventoryDTO.getBranchId()).orElseThrow(
                ()-> new Exception("branch not exist")
        );
        Product product= productRepository.findById(inventoryDTO.getProductId()).orElseThrow(
                ()-> new Exception("product not exist")
        );
        Inventory inventory = InventoryMapper.toEntity(inventoryDTO,branch,product);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryMapper.toDTO(savedInventory);

    }

    @Override
    public InventoryDTO updateInventory(Long id,InventoryDTO inventoryDTO) throws Exception {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("inventory not found")
        );
        inventory.setQuantity(inventoryDTO.getQuantity());
        Inventory updatedInventory = inventoryRepository.save(inventory);

        return InventoryMapper.toDTO(updatedInventory);
    }

    @Override
    public void deleteInventory(Long id) throws Exception {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("inventory not found")
        );
        inventoryRepository.delete(inventory);


    }

    @Override
    public InventoryDTO getInventoryById(Long id) throws Exception {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("inventory not found")
        );
        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long branchId) {
        Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId, branchId);
        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public List<InventoryDTO> getAllInventoryByBranchId(Long branchId) {
        List<Inventory> inventories = inventoryRepository.findByBranchId(branchId);
        return inventories.stream().map(
                InventoryMapper::toDTO
        ).collect(Collectors.toList());
    }
}
