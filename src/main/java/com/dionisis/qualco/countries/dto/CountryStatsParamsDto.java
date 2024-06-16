package com.dionisis.qualco.countries.dto;

import com.dionisis.qualco.countries.controller.param.PaginationRequest;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;

@Value
public class CountryStatsParamsDto {
    Integer regionId;
    LocalDate dateFrom;
    LocalDate dateTo;
    int page;
    int size;
}
