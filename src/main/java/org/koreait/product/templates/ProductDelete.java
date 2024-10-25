package org.koreait.product.templates;



import org.koreait.product.entities.Product;
import org.koreait.product.entities.ProductRepository;

import java.util.List;

public class ProductDelete {
    private final ProductRepository productRepository;

    public ProductDelete(ProductRepository productRepository) {
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

        // 제품 삭제
        productRepository.delete(productId);
        System.out.println("제품이 삭제되었습니다.");
        return true;
    }

    /**
     * 모든 제품 목록을 가져오는 메서드
     * @return 제품 목록
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}