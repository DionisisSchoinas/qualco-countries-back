package com.dionisis.qualco.countries.controller.param;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RegionStatsParams {
    private Integer regionId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
