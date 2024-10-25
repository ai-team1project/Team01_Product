package org.koreait.product.templates;

import org.koreait.global.Model;
import org.koreait.global.Template;
import org.koreait.global.libs.Utils;
import org.koreait.product.entities.Product;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 상품 목록 템플릿
 *
 */
public class ProductList implements Template {
    private List<Product> items;
    @Override
    public void print() {
        System.out.println("상품목록");
        Utils.drawLine('-', 30);
        DecimalFormat format = new DecimalFormat("#,###");
        if (items != null && !items.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            for (Product item : items) {

           if (item.getSaleSet() > 0) { /* sale의 cateNum과 조회된 상품명이 일치하면*/
               int discounted = (int)(item.getPrice() * (item.getSaleSet() / 100.0));
               System.out.printf("상품번호: %d / 상품명: %s / 판매가: %s원 -> 할인가: %s원 / 등록일: %s%n", item.getSeq(), item.getName(), format.format(item.getPrice()), format.format(discounted), item.getRegDt());
           }else {
               System.out.printf("상품번호: %d | 카테고리: %s | 상품명: %s | 판매가: %s원 | 등록일: %s%n", item.getSeq(), item.getCategory(), item.getName(), format.format(item.getPrice()), formatter.format(item.getRegDt()));
           }


            }
            return;
        }
        System.out.println("등록된 상품이 없습니다.");
    }

    @Override
    public void print(Model model) {
        Object data = model.getData();
        if (data != null) {
            items = (List<Product>)data;
        }

        print();
    }
}
