package org.koreait.product.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Model;
import org.koreait.global.libs.Utils;
import org.koreait.product.entities.Product;
import org.koreait.product.services.ProductInfoService;
import org.koreait.product.templates.ProductList;

import java.util.List;

public class ProductManagementController extends Controller {

    public ProductManagementController() {
        setInputProcess(input -> {
            if (input.toUpperCase().equals("S")) {
                Utils.loadController(ProductSaleController.class);
            } else if (input.toUpperCase().equals("D")) {
                Utils.loadController(ProductDeleteController.class);
            }
        });
    }

    @Override
    protected String getPromptText() {
        return "수정할 메뉴를 선택하세요 (메인메뉴: M, 종료: Q, 할인: S, 삭제: D)";
    }

    @Override
    public void view() {
        ProductInfoService infoService = BeanContainer.getBean(ProductInfoService.class);
        List<Product> items = infoService.getList();
        Utils.loadTpl(ProductList.class, new Model(items));
    }
}

/*
import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Router;
import org.koreait.global.libs.Utils;
import org.koreait.main.controllers.MainController;
import org.koreait.product.entities.Product;
import org.koreait.product.services.ProductInfoService; // 상품 정보를 가져오는 서비스

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Consumer;

public class ProductManagementController extends Controller {
    private Object data;
    private Consumer<String> inputProcess;
    private Runnable promptProcess;
    protected List<Product> items; // 상품 리스트
    private final ProductInfoService productInfoService; // 상품 정보를 가져오는 서비스 추가

    public ProductManagementController() {
        this.productInfoService = BeanContainer.getBean(ProductInfoService.class); // 상품 정보 서비스 초기화
        this.items = productInfoService.getList(); // 상품 리스트 초기화
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    protected void setInputProcess(Consumer<String> inputProcess) {
        this.inputProcess = inputProcess;
    }

    protected void setPromptProcess(Runnable promptProcess) {
        this.promptProcess = promptProcess;
    }

    protected void process(String input) {
        if (inputProcess != null) {
            inputProcess.accept(input);
        }
    }

    public void prompt() {
        Utils.drawLine('-', 30);
        System.out.print(getPromptText());

        if (promptProcess == null) {
            String input = Router.sc.nextLine();

            if (input.toUpperCase().equals("Q")) {
                System.out.println("종료합니다.");
                System.exit(0);
            } else if (input.toUpperCase().equals("M")) {
                Utils.loadController(MainController.class);
            } else if (input.toUpperCase().equals("S")) {
                Utils.loadController(ProductSaleController.class);
            } else if (input.toUpperCase().equals("D")) {
                Utils.loadController(ProductDeleteController.class);
            }

            process(input);
        } else {
            promptProcess.run();
        }
    }

    @Override
    public void view() {
        System.out.println("상품목록");
        Utils.drawLine('-', 30);

        if (items != null && !items.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            for (Product item : items) {
                System.out.printf("상품번호: %d / 상품명: %s / 판매가: %d원 / 등록일: %s%n",
                        item.getSeq(), item.getName(), item.getPrice(), formatter.format(item.getRegDt()));
            }
        } else {
            System.out.println("등록된 상품이 없습니다.");
        }
    }

    @Override
    protected String getPromptText() {
        return "수정할 메뉴를 선택하세요 (메인메뉴: M, 종료: Q, 할인: S, 삭제: D)";
    }
}
 */