package com.univer.lab.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountDrug {

    private Drug drug;

    private Long count;
}
