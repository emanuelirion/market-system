package com.emanuel.services.impi;

import com.emanuel.exception.UserException;
import com.emanuel.mapper.StoreMapper;
import com.emanuel.modal.Store;
import com.emanuel.modal.StoreContact;
import com.emanuel.modal.User;
import com.emanuel.payload.dto.StoreDTO;
import com.emanuel.repository.StoreRepository;
import com.emanuel.services.StoreService;
import com.emanuel.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpi implements StoreService {

    private final StoreRepository storeRepository;
    private final UserService userService;


    @Override
    public StoreDTO createStoreDTO(StoreDTO storeDTO, User user) {

        Store store = StoreMapper.toEntity(storeDTO, user );

        return StoreMapper.toDTO(storeRepository.save(store));
    }

    @Override
    public StoreDTO getStoreById(Long id) throws Exception {
        Store store = storeRepository.findById(id).orElseThrow(
                ()-> new Exception("store not found")
        );
        return StoreMapper.toDTO(store);
    }

    @Override
    public List<StoreDTO> getAllStores() {
        List<Store> dtos = storeRepository.findAll();
        return dtos.stream().map(StoreMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Store getStoreByAdmin() throws UserException {
        User admin = userService.getCurrentUser();

        return storeRepository.findByStoreAdminId(admin.getId());
    }

    @Override
    public StoreDTO updateStore(Long id, StoreDTO storeDTO) throws Exception {
        User currentUser = userService.getCurrentUser();

        Store existing = storeRepository.findByStoreAdminId(currentUser.getId());

        if(existing == null) {
            throw new Exception("store not found");
        }
        existing.setBrand(storeDTO.getBrand());
        existing.setDescription(storeDTO.getDescription());
        if(storeDTO.getStoreType()!=null){
            existing.setStoreType(storeDTO.getStoreType());
        }
        if(storeDTO.getContact()!=null){
            StoreContact contact = StoreContact.builder().address(storeDTO.getContact().getAddress())
                    .phone(storeDTO.getContact().getPhone()).email(storeDTO.getContact().getEmail()).build();
            existing.setContact(contact);
        }
        Store updatedStore = storeRepository.save(existing);
        return StoreMapper.toDTO(updatedStore);
    }

    @Override
    public void deleteStore(Long id) throws UserException {
        Store store = getStoreByAdmin();

        storeRepository.delete(store);
    }

    @Override
    public StoreDTO getStoreByEmployee() throws UserException {
        User correntUser = userService.getCurrentUser();
        if(correntUser == null) {
            throw new UserException("you don't have permission to access this store");
        }
        return StoreMapper.toDTO(correntUser.getStore());
    }
}
