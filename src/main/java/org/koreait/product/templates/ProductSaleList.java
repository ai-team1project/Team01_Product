package org.koreait.product.templates;

import org.koreait.global.Model;
import org.koreait.global.Template;
import org.koreait.global.libs.Utils;
import org.koreait.product.entities.Product;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProductSaleList implements Template {
    private List<Product> items;



    @Override
    public void print() {
       System.out.println("할인 목록");
       Utils.drawLine('-', 30);
        if (items != null && !items.isEmpty()) { // 아이템이 널이 아니거나 : 아이템에 값이 들어갔을때
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

            for (Product item : items) {
                System.out.printf("%s 진행 중  %s의 품목은 %d%% 할인된 가격으로 조정됩니다.", item.getSaleName(), item.getCategory().getTitle(), item.getSaleSet());
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
