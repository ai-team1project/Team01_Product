package org.koreait.product.entities;

import java.util.List;

public interface ProductRepository {
    /**
     * ID로 제품을 찾는 메서드
     * @param id 찾을 제품의 ID
     * @return Product 객체 또는 null
     */
    Product findById(long id);

    /**
     * ID로 제품을 삭제하는 메서드
     * @param id 삭제할 제품의 ID
     */
    void delete(long id);

    /**
     * 모든 제품을 반환하는 메서드
     * @return 제품의 List
     */
    List<Product> findAll();
}