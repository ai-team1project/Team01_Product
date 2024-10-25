import org.koreait.global.BeanContainer;
import org.koreait.global.Model;
import org.koreait.global.libs.Utils;
import org.koreait.product.controllers.ProductMenagementController;
import org.koreait.product.controllers.ProductViewController;
import org.koreait.product.entities.Product;
import org.koreait.product.services.ProductInfoService;
import org.koreait.product.templates.ProductList;

import java.util.List;

public class ProductDeleteController extends ProductMenagementController {
    public ProductDeleteController() {
        setInputProcess(input -> {
            // 유효성 검사 S
            if (!check(input)) { // 필수 항목 체크
                return;
            }

            if (!isNumber(input)) {
                System.out.println("재고 수량은 숫자만 입력하세요.");
                return;
            }

            long productId = Long.parseLong(input);
            ProductInfoService service = BeanContainer.getBean(ProductInfoService.class);
            Product product = service.getProductById(productId);

            if (product == null) {
                System.out.println("해당 상품이 존재하지 않습니다.");
                return;
            }

            // 재고가 0인 경우 삭제
            if (product.getStock() <= 0) {
                service.deleteProduct(productId);
                System.out.println("재고가 0이 되어 상품이 삭제되었습니다.");
            } else {
                System.out.println("재고가 0이 아닙니다. 삭제할 수 없습니다.");
            }

            // 상품 목록 다시 로드
            Utils.loadController(ProductViewController.class, new Model());
        });
    }

    @Override
    public void view() {
        ProductInfoService service = BeanContainer.getBean(ProductInfoService.class);
        List<Product> items = service.getList();

        // 템플릿 로드 및 상품 목록 데이터 전송
        Utils.loadTpl(ProductList.class, new Model(items));
    }
}