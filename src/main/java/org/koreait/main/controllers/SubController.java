package org.koreait.main.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Model;
import org.koreait.global.libs.Utils;
import org.koreait.global.validators.RequiredValidator;
import org.koreait.global.validators.TypeValidator;
import org.koreait.product.constants.Category;
import org.koreait.product.controllers.ProductViewController;
import org.koreait.product.entities.Product;
import org.koreait.product.services.ProductInfoService;
import org.koreait.product.templates.ProductList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 상품 목록
 *  - 상품목록에서 상품 번호를 입력하면 상품 상세보기로 이동
 *
 */
public class SubController extends Controller implements TypeValidator, RequiredValidator {

    public SubController() {


        setPromptProcess(() -> {
            int selected = Utils.getNumber("1. 카테고리 2. 상품번호 3.전체 목록", "1,2,3번 중에서 선택하세요.");
            switch (selected) {
                case 1: // 카테고리별
                    Category[] categories = Category.values();
                    String categorySelection = Arrays.stream(categories).map(c -> String.format("%d. %s", c.getCateNum(), c.getTitle()))
                            .collect(Collectors.joining(" "));
                    System.out.println(categorySelection);

                    int cateNum = Utils.getNumber("분류번호", "분류번호를 선택하세요.");
                    Category category = Category.getCategory(cateNum);
                    Utils.loadController(SubController.class, new Model(category)); // 선택 분류와 함께 전달

                    break;
                case 2: // 상품 번호별
                    String input = Utils.getString("상품번호:", "상품번호를 입력하세요.");
                    if (!isNumber(input)) {
                        System.out.println("숫자 형식으로 입력하세요.");
                        return;
                    }

                    Utils.loadController(ProductViewController.class, new Model(Long.parseLong(input)));
                    break;
                case 3: // 전체 목록
                    Utils.loadController(SubController.class, new Model(null));
                    break;
                default:
                    System.out.println("1,2,3번 중에서 선택하세요.");
            }

       });
    }

    @Override
    protected String getPromptText() {

        return " (조회 방법을 선택하세요(메인메뉴: M, 종료: Q)\n";
    }

    @Override
    public void view() {
        Object data = getData();
        ProductInfoService service = BeanContainer.getBean(ProductInfoService.class);

        List<Product> items = data == null ? service.getList() : service.getList((Category)data, false);


        // 템플릿 로드 및 상품 목록 데이터 전송
        Utils.loadTpl(ProductList.class, new Model(items));
    }
}
