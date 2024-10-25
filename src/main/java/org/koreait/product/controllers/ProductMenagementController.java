package org.koreait.product.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Model;
import org.koreait.global.libs.Utils;
import org.koreait.global.validators.RequiredValidator;
import org.koreait.global.validators.TypeValidator;
import org.koreait.product.entities.Product;
import org.koreait.product.services.ProductInfoService;
import org.koreait.product.templates.ProductList;

import java.util.List;

public class ProductMenagementController extends Controller implements TypeValidator, RequiredValidator{
    public ProductMenagementController() {
        setInputProcess(input -> {
            /* 유효성 검사 S */
            if (!check(input)) { // 필수 항목 체크
                return;
            }

            if (!isNumber(input)) {
                System.out.println("재고 수량은 숫자만 입력하세요.");
                return;
            }

            /* 유효성 검사 E */

            // 선택한 상품 번호와 함께 상품 상세로 이동
            Utils.loadController(ProductViewController.class, new Model(Long.parseLong(input)));


        });
    }

    @Override
    public void view() {
        ProductInfoService service = BeanContainer.getBean(ProductInfoService.class);
        List<Product> items = service.getList();

        // 템플릿 로드 및 상품 목록 데이터 전송
        Utils.loadTpl(ProductList.class, new Model(items));
    }
    @Override
    protected String getPromptText() {
        return "수정할 메뉴를 선택하세요 (메인메뉴: M, 종료: Q, 재고수정: S, 할인: SA, 삭제: D ):";
    }

}
