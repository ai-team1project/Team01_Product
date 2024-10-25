package org.koreait.product.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.libs.Utils;
import org.koreait.product.constants.Category;
import org.koreait.product.entities.Product;
import org.koreait.product.services.ProductSaveService;
import org.koreait.product.templates.ProductForm;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 상품 등록/수정 컨트롤러
 *
 */
public class ProductController extends Controller {

    public ProductController() {
       setPromptProcess(() -> {
           String categorySelection = Arrays.stream(Category.values()).map(c->c.getCateNum()+"."+c.getTitle()).collect(Collectors.joining(" "));
           Utils.drawLine('-', 30);


           Product item = new Product();
           /*분류 처리 S*/
           System.out.println("카테고리를 선택하세요");
           Utils.drawLine('-',30);
           System.out.println(categorySelection);
           Utils.drawLine('-',30);
           int cateNum = Utils.getNumber("카테고리","카테고리를 선택하세요");
           Category category = Category.getCategory(cateNum);
           item.setCategory(category);
            /*분류처리 E*/
           // 상품명
           String name = Utils.getString("상품명", "상품명을 입력하세요.");
           item.setName(name);

           // 판매가
           int price = Utils.getNumber("판매가", "판매가를 입력하세요.");
           item.setPrice(price);

            // 재고
           int stock = Utils.getNumber("재고", "재고를 입력하세요.");
           item.setStock(stock);

           // 상품 정보 저장 처리
           ProductSaveService saveService = BeanContainer.getBean(ProductSaveService.class);
           saveService.save(item);

           System.out.println("상품이 저장되었습니다.");
           // 저장 이후에 상품 목록으로 페이지 이동
           
           Utils.loadController(ProductListController.class);
       });
    }

    @Override
    protected String getPromptText() {
        return "등록할 상품 정보를 입력하세요(메인 메뉴: M, 종료: Q).\n";
    }

    @Override
    public void view()  {
        Utils.loadTpl(ProductForm.class);
    }
}
