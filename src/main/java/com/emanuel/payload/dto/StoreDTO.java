package com.emanuel.payload.dto;

import com.emanuel.domain.StoreStatus;
import com.emanuel.modal.StoreContact;
import com.emanuel.modal.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class StoreDTO {

    private Long id;

    private String brand;

    private UserDto storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private StoreStatus status;

    private StoreContact contact;





}
