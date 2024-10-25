package org.koreait.global.validators;

public interface CategoryValidator {
    default boolean isInCategory(String str){return str != null && !str.isBlank();}

}
