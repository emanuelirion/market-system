package com.emanuel.services.impi;

import com.emanuel.domain.StoreStatus;
import com.emanuel.payload.adminAnalytics.DashboardSummaryDTO;
import com.emanuel.payload.adminAnalytics.StoreRegistrationStateDTO;
import com.emanuel.payload.adminAnalytics.StoreStatusDistributionDTO;
import com.emanuel.repository.StoreRepository;
import com.emanuel.services.AdminDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminDashboardServiceImpi implements AdminDashboardService {


    private final StoreRepository storeRepository;

    @Override
    public DashboardSummaryDTO getDashboardSummary() {
        Long total= storeRepository.count();
        Long active= storeRepository.countByStatus(StoreStatus.ACTIVE);
        Long pending= storeRepository.countByStatus(StoreStatus.PENDING);
        Long blocked= storeRepository.countByStatus(StoreStatus.BLOCKED);

        return DashboardSummaryDTO.builder()
                .totalStore(total)
                .activeStore(active)
                .pendingStore(pending)
                .blockStore(blocked)
                .build();
    }

    @Override
    public List<StoreRegistrationStateDTO> getLast7DayRegistrationStats() {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime sevenDaysAgo = today.minusDays(6);

        List<Object[]> rawStats=storeRepository.getStoreRegistrationStats(sevenDaysAgo);
        Map<String, Long> dataMap=new LinkedHashMap<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        for(int i = 0; i<7; i++){
            LocalDateTime date = sevenDaysAgo.plusDays(i);
            dataMap.put(date.format(dateTimeFormatter),0L);
        }
        for(Object[] row:rawStats){
            LocalDateTime date = (LocalDateTime)row[0];
            Long count = (Long)row[1];
            dataMap.put(date.format(dateTimeFormatter),count);
        }
        List<StoreRegistrationStateDTO> results=new ArrayList<>();
        dataMap.forEach((date,count)-> results.add(
                StoreRegistrationStateDTO.builder()
                        .date(date)
                        .count(count)
                        .build()
        ));
        return results;
    }

    @Override
    public StoreStatusDistributionDTO getStoreDistribution() {
        Long active= storeRepository.countByStatus(StoreStatus.ACTIVE);
        Long pending= storeRepository.countByStatus(StoreStatus.PENDING);
        Long blocked= storeRepository.countByStatus(StoreStatus.BLOCKED);
        return StoreStatusDistributionDTO.builder()
                .active(active)
                .pending(pending)
                .blocked(blocked)
                .build();
    }
}
