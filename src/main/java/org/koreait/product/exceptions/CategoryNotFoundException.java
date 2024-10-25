package org.koreait.product.exceptions;

import org.koreait.global.exceptions.CommonException;

public class CategoryNotFoundException extends CommonException {
  public CategoryNotFoundException() {
    super("카테고리를 찾을 수 없습니다",404);
  }
}
