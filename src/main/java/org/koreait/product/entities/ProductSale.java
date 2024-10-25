package org.koreait.product.entities;

import org.koreait.product.constants.Category;

import java.io.Serializable;

public class ProductSale extends Product  implements Serializable {
    private Category category; // 카테고리에서 세일 적용할 품목 선택
    private int saleSet; // 세일 몇 퍼센트 할건지(10% ~ 40%)
    private String saleName; // 세일 행사 이름

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
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
                "category=" + category +
                ", saleSet=" + saleSet +
                ", saleName='" + saleName + '\'' +
                '}';
    }
}
