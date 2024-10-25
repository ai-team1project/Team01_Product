package org.koreait.product.templates;
import org.koreait.global.Template;

// 세일상품 등록 양식 출력
public class ProductSale implements Template {
    @Override
    public void print() {
        StringBuffer sb = new StringBuffer();
        sb.append("세일 항목 안내 작성...");
        System.out.println(sb);
    }

}
