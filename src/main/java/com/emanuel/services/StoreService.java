package com.emanuel.services;

import com.emanuel.exception.UserException;
import com.emanuel.modal.Store;
import com.emanuel.modal.User;
import com.emanuel.payload.dto.StoreDTO;

import java.util.List;

public interface StoreService {

    StoreDTO createStoreDTO(StoreDTO storeDTO, User user);
    StoreDTO getStoreById(Long id) throws Exception;
    List<StoreDTO> getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDTO updateStore(Long id, StoreDTO storeDTO) throws Exception;
    void deleteStore(Long id) throws UserException;
    StoreDTO getStoreByEmployee() throws UserException;


}
