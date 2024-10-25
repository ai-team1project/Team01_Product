package org.koreait.product.services;

import org.koreait.global.BeanContainer;
import org.koreait.product.entities.Product;
import org.koreait.product.entities.ProductRepository;
import org.koreait.product.entities.RepositoryImp;
import org.koreait.product.exceptions.DeleteException;

import java.util.ArrayList;
import java.util.List;

public class ProductDeleteService {
    private final ProductRepository productRepository;

    public ProductDeleteService() {
        productRepository = BeanContainer.getBean(RepositoryImp.class);
    }



    /**
     * 제품 삭제 메서드
     * @param productId 삭제할 제품의 ID
     * @return 삭제 성공 여부
     * @throws DeleteException 제품이 존재하지 않을 경우 예외를 던짐
     */
    public boolean deleteProduct(long productId) throws DeleteException {
        // 제품 존재 여부 확인
        Product product = productRepository.findById(productId);
        if (product == null) {
            throw new DeleteException("해당 ID의 제품이 존재하지 않습니다.");
        }

        // 제품 삭제
        productRepository.delete(productId);
        return true; // 삭제 성공
    }

    /**
     * 여러 제품을 seq 리스트로 받아 삭제하는 메서드
     * @param productSeqs 삭제할 제품의 seq 리스트
     * @return 삭제 결과 (O: 성공, X: 실패)의 리스트
     */
    public List<String> deleteProducts(List<Long> productSeqs) {
        List<String> deleteResults = new ArrayList<>();

        for (Long seq : productSeqs) {
            Product product = productRepository.findById(seq);
            if (product != null) {
                productRepository.delete(seq);
                deleteResults.add("O"); // 삭제 성공
            } else {
                deleteResults.add("X"); // 삭제 실패
            }
        }
        return deleteResults; // 삭제 결과 리스트 반환
    }

    /**
     * 모든 제품 목록을 가져오는 메서드
     * @return 제품 목록
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll(); // 빈 목록을 반환
    }
}