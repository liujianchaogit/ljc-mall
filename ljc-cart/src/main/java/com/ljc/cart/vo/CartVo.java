package com.ljc.cart.vo;

import com.ljc.cart.po.Cart;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartVo {

    List<Cart> cartList;
    private Integer countNum;
    private Integer countType;
    private BigDecimal totalAmount;
    private BigDecimal reduce = new BigDecimal("0.00");;

    public Integer getCountNum() {
        int count = 0;
        if (cartList != null) {
            for (Cart cart : cartList) {
                count += cart.getCount();
            }
        }
        return count;
    }

    public Integer getCountType() {
        int count = 0;
        if (cartList != null) {
            for (Cart cart : cartList) {
                count += 1;
            }
        }
        return count;
    }


    public BigDecimal getTotalAmount() {
        BigDecimal amount = new BigDecimal("0");
        // 计算购物项总价
        if (!CollectionUtils.isEmpty(cartList)) {
            for (Cart cart : cartList) {
                if (cart.getCheck()) {
                    amount = amount.add(cart.getTotalPrice());
                }
            }
        }
        // 计算优惠后的价格
        return amount.subtract(getReduce());
    }

}
