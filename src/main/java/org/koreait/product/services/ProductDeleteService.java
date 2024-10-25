import org.koreait.product.entities.Product;

public class ProductDeleteService {
    private final ProductRepository productRepository;

    public ProductDeleteService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 제품 삭제 메서드
     * @param productId 삭제할 제품의 ID
     * @return 삭제 성공 여부
     */
    public boolean deleteProduct(long productId) {
        // 제품 존재 여부 확인
        Product product = productRepository.findById(productId);
        if (product == null) {
            System.out.println("해당 ID의 제품이 존재하지 않습니다.");
            return false;
        }

        // 재고가 0인 경우에만 삭제
        if (product.getStock() <= 0) {
            productRepository.delete(productId);
            System.out.println("제품이 삭제되었습니다.");
            return true;
        } else {
            System.out.println("재고가 0이 아닙니다. 삭제할 수 없습니다.");
            return false;
        }
    }
}
