package com.ljc.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）
 * </p>
 *
 * @author liujianchao
 * @since 2021-10-13
 */
@TableName("pms_product_category_attribute_relation")
public class ProductCategoryAttributeRelation implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productCategoryId;

    private Long productAttributeId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getProductAttributeId() {
        return productAttributeId;
    }

    public void setProductAttributeId(Long productAttributeId) {
        this.productAttributeId = productAttributeId;
    }

    @Override
    public String toString() {
        return "ProductCategoryAttributeRelation{" +
        "id=" + id +
        ", productCategoryId=" + productCategoryId +
        ", productAttributeId=" + productAttributeId +
        "}";
    }
}
