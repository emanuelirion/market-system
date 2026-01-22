package com.emanuel.payload.adminAnalytics;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardSummaryDTO {

    private Long totalStore;
    private Long activeStore;
    private Long blockStore;
    private Long pendingStore;
}
