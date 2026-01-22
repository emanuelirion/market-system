package com.emanuel.services;

import com.emanuel.domain.StoreStatus;
import com.emanuel.payload.adminAnalytics.DashboardSummaryDTO;
import com.emanuel.payload.adminAnalytics.StoreRegistrationStateDTO;
import com.emanuel.payload.adminAnalytics.StoreStatusDistributionDTO;

import java.util.List;

public interface AdminDashboardService {

    DashboardSummaryDTO getDashboardSummary();
    List<StoreRegistrationStateDTO> getLast7DayRegistrationStats();
    StoreStatusDistributionDTO getStoreDistribution();
}
