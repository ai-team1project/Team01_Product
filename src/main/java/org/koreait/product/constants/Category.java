package org.koreait.product.constants;

public enum Category {
    TOP("상의"),
    BOTTOM("하의"),
    ACCESSORY("액세서리"),
    SHOES("신발"),
    OUTER("아우터");

    private final String title;

    Category(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
