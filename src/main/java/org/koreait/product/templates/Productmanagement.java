package org.koreait.product.templates;

import org.koreait.global.Model;
import org.koreait.global.Template;

public class Productmanagement implements Template {

    @Override
    public void print() {
        StringBuffer sb = new StringBuffer();
        sb.append("1. 재고 관리\n").append("2. 할인율\n").append("3. 삭제\n");

        System.out.println(sb);
    }

}
