package org.koreait.product.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Router;
import org.koreait.global.libs.Utils;
import org.koreait.product.constants.Category;
import org.koreait.product.entities.Product;
import org.koreait.product.services.ProductSaveService;

import java.util.Scanner;

// 겨울 아우터 할인 진행
public class ProductSaleController extends Controller {

    public ProductSaleController() {
        setPromptProcess(() -> {
            Utils.drawLine('-', 30);

            Scanner sc = Router.sc;
            Product items = new Product();

            // 세일 품목 선택
            int cateNum = Utils.getNumber("세일품목", "세일 품목을 선택하세요.");
            Category category = Category.getCategory(cateNum);
            items.setCategory(category);

            // 세일 퍼센트
            int saleSet = Utils.getNumber("세일퍼센트", "몇 퍼센트 세일 하시겠습니까?");
            items.setSaleSet(saleSet);

            // 세일 행사 이름
            String saleName = Utils.getString("행사이름", "세일 행사 이름을 입력하세요");
            items.setSaleName(saleName);

            // 상품 정보 저장 처리
            ProductSaveService saveService = BeanContainer.getBean(ProductSaveService.class);

//            saveService.save(item);1

            saveService.save(items);


            System.out.println("상품이 저장되었습니다.");
            // 저장 이후에 상품 목록으로 페이지 이동

            Utils.loadController(ProductListController.class);
        });
    }

    @Override
    protected String getPromptText() {
        return "세일 정보를 입력하세요(메인 메뉴: M, 종료: Q).\n";
    }

    @Override
    public void view()  {

    }
}
