package org.koreait.product.controllers;


import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Model;
import org.koreait.global.exceptions.BadRequestException;
import org.koreait.global.exceptions.CommonException;
import org.koreait.global.libs.Utils;
import org.koreait.global.validators.TypeValidator;
import org.koreait.product.entities.Product;
import org.koreait.product.services.ProductDeleteService;
import org.koreait.product.services.ProductInfoService;
import org.koreait.product.templates.ProductList;
import org.koreait.product.templates.ProductView;

import java.util.List;

public class ProductDeleteController extends Controller implements TypeValidator {

    public ProductDeleteController() {
        ProductInfoService infoService = BeanContainer.getBean(ProductInfoService.class);
        ProductDeleteService deleteService = BeanContainer.getBean(ProductDeleteService.class);

        setPromptProcess(() -> {
            try {
                /* 상품 번호 선택 S */
                String input = Utils.getString("상품번호", "상품번호를 입력하세요.");
                if (!isNumber(input)) {
                    throw new BadRequestException("상품번호는 숫자만 입력하세요.");
                }

                long seq = Long.parseLong(input);
                Product item = infoService.get(seq);
                Utils.loadTpl(ProductView.class, new Model(item));
                /* 상품 번호 선택 E */

                /* 상품 삭제 여부 S */
                String message = "O,X 중에서 선택하세요.";
                while(true) {
                    String confirm = Utils.getString("정말 삭제하겠습니까?(O - 삭제, X - 취소)", message);
                    if (confirm.toUpperCase().equals("O")) {

                        break;
                    } else if (confirm.toUpperCase().equals("X")) {

                        break;
                    } else {
                        System.out.println(message);
                    }
                }
                /* 상품 삭제 여부 E */

                // 삭제 처리
                deleteService.deleteProduct(seq);


                // 삭제 완료 후 상품 관리 메인으로 이동
                Utils.loadController(ProductManagementController.class);

            } catch (CommonException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    @Override
    protected String getPromptText() {
        return "삭제할 상품번호를 입력하세요(메인:M, 종료: Q)\n";
    }

    @Override
    public void view() {
        ProductInfoService infoService = BeanContainer.getBean(ProductInfoService.class);

        List<Product> items = infoService.getList();
        Utils.loadTpl(ProductList.class, new Model(items));
    }
}

/*
public class ProductDeleteController extends ProductManagementController {
    private final ProductDeleteService productDeleteService;
    private List<Product> items;

    public ProductDeleteController() {
        super();
        this.productDeleteService = BeanContainer.getBean(ProductDeleteService.class);

        // ProductDeleteService가 null일 경우 DeletException 던짐
        if (this.productDeleteService == null) {
            throw new DeleteException("삭제 서비스가 초기화되지 않았습니다. 서비스가 등록되어 있는지 확인하세요.");
        }

        // 초기화 시 모든 제품 목록을 가져옵니다.
        this.items = productDeleteService.getAllProducts();
    }
    /*
    @Override
    public void prompt() {
        Scanner scanner = Router.sc;

        while (true) {
            items = productDeleteService.getAllProducts(); // 항상 최신 제품 목록을 가져옴
            if (items.isEmpty()) {
                System.out.println("삭제할 제품이 없습니다.");
                return;
            }

            System.out.println("삭제할 제품 목록:");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i).getName());
            }

            System.out.print("삭제할 제품 번호를 입력하세요 (종료: Q): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("Q")) {
                System.out.println("종료합니다.");
                System.exit(0);
            }

            try {
                int productIndex = Integer.parseInt(input) - 1;
                if (productIndex < 0 || productIndex >= items.size()) {
                    System.out.println("유효하지 않은 제품 번호입니다.");
                    continue;
                }

                Product productToDelete = items.get(productIndex);
                System.out.print("제품 " + productToDelete.getName() + "을(를) 삭제하시겠습니까? (O: 삭제, X: 취소): ");
                String confirmation = scanner.nextLine();

                // 입력이 O 또는 X일 경우만 처리
                if (confirmation.equalsIgnoreCase("O")) {
                    boolean success = productDeleteService.deleteProduct(productToDelete.getSeq());
                    if (success) {
                        System.out.println("제품이 삭제되었습니다.");
                    } else {
                        System.out.println("제품 삭제에 실패하였습니다.");
                    }
                } else if (confirmation.equalsIgnoreCase("X")) {
                    System.out.println("삭제가 취소되었습니다. 메인 메뉴로 돌아갑니다.");
                    return;
                } else {
                    System.out.println("유효하지 않은 입력입니다. 'O' 또는 'X'만 입력해주세요.");
                }

            } catch (NumberFormatException e) {
                System.out.println("유효하지 않은 입력입니다.");
            } catch (DeleteException e) {
                System.out.println(e.getMessage()); // DeletException 처리
            }
        }
    }
    */
/*
    @Override
    public void view() {
        /*
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
} */