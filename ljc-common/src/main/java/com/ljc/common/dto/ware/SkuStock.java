package com.ljc.common.dto.ware;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SkuStock {

    private Long skuId;
    private Boolean hasStock;

}
