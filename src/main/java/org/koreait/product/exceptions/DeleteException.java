package org.koreait.product.exceptions;

import org.koreait.global.exceptions.CommonException;

public class DeleteException extends CommonException {
    public DeleteException() {
        this("삭제할 상품이 없습니다.");
    }



    public DeleteException(String message) {
        super(message,400);
    }
}
