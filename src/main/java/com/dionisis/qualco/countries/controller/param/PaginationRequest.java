package com.dionisis.qualco.countries.controller.param;

import lombok.Data;

@Data
public class PaginationRequest {
    int page;
    int size;
}
