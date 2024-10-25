package org.koreait.product.entities;

import java.io.Serializable;

public class ProductSale implements Serializable {
    private int cartNum; // 카테고리에서 세일 적용할 품목 선택
    private int saleSet; // 세일 몇 퍼센트 할건지(10% ~ 40%)
    private String saleName; // 세일 행사 이름

    public int getCartNum() {
        return cartNum;
    }

    public void setCartNum(int cartNum) {
        this.cartNum = cartNum;
    }

    public int getSaleSet() {
        return saleSet;
    }

    public void setSaleSet(int saleSet) {
        this.saleSet = saleSet;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    @Override
    public String toString() {
        return "ProductSale{" +
                "cartNum=" + cartNum +
                ", saleSet=" + saleSet +
                ", saleName='" + saleName + '\'' +
                '}';
    }
}
