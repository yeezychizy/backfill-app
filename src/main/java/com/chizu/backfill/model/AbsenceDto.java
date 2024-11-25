package com.chizu.backfill.model;

import lombok.Data;

/**
 * @author : chizubeokwuosa
 * @Description :
 * @created : chizubeokwuosa, Sunday
 **/
@Data
public class AbsenceDto {
    private String startDate;
    private String endDate;
    private String reason;
}
