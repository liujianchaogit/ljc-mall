package com.ljc.common.dto.product;

import lombok.Data;

@Data
public class AttrInfo {

    private Long attrId;
    private String attrName;
    private Integer searchType;
    private String icon;
    private String valueSelect;
    private Integer attrType;
    private Long enable;
    private Long catelogId;
    private Integer showDesc;
    private Long attrGroupId;
    private String catelogName;
    private String groupName;
    private Long[] catelogPath;

}
