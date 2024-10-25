package org.koreait.product.constants;

import org.koreait.product.exceptions.CategoryNotFoundException;

import java.util.Arrays;

public enum Category {
    TOP("상의",1),
    BOTTOM("하의",2),
    ACCESSORY("액세서리",3),
    SHOES("신발",4),
    OUTER("아우터",5);

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
