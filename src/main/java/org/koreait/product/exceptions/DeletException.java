package org.koreait.product.exceptions;

import org.koreait.global.exceptions.CommonException;

public class DeletException extends CommonException {
    public DeletException(){this("삭제할 수량이 재고 수량을 넘어갑니다.");}
    public DeletException(String message) {
        super(message,400);
    }
}
