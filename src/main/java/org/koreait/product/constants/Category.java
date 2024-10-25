package org.koreait.product.constants;

import org.koreait.product.exceptions.CategoryNotFoundException;

import java.util.Arrays;

public enum Category {
    TOP("TOP",1),
    BOTTOM("BOTTOM",2),
    ACCESSORY("ACCESSORY",3),
    SHOES("SHOES",4),
    OUTER("OUTER",5);

    private final String title;
    private final int cateNum;

    Category(String title, int cateNum) {
        this.title = title;
        this.cateNum = cateNum;
    }

    public String getTitle() {
        return title;
    }
    public int getCateNum(){return cateNum;}
    public static Category getCategory(int cateNum){
        return Arrays.stream(Category.values()).filter(c->c.cateNum==cateNum).findFirst()
                .orElseThrow(CategoryNotFoundException::new);
    }

}
