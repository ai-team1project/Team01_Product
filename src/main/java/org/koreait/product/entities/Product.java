package org.koreait.product.entities;

import org.koreait.product.constants.Category;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 상품 정보를 담는 데이터 클래스 정의
 *
 */
public class Product implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private long seq;  // 상품 등록번호
    private String name; // 상품이름
    private int price; // 판매가
    private int stock; // 재고
    private Category category; // 카테고리 선택

    // 현재 날짜와 시간 가져오기
    LocalDateTime now = LocalDateTime.now();



    private LocalDateTime regDt; // 상품등록일시
    private LocalDateTime modDt; // 상품수정일시



    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDateTime getModDt() {
        return modDt;
    }

    public void setModDt(LocalDateTime modDt) {
        this.modDt = modDt;
    }

    public LocalDateTime getRegDt() {
        return regDt;
    }

    public void setRegDt(LocalDateTime regDt) {
        this.regDt = regDt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return
                "상품번호: " + seq +
                ", 악세서리: " + category +
                ", 상품명: '" + name + '\'' +
                ", 가격: " + price +
                ", 재고: " + stock +
                ", 등록일: " + LocalDate.now() ;
    }
}
