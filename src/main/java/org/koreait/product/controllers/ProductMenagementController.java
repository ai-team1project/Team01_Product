package org.koreait.product.controllers;

import org.koreait.global.BeanContainer;
import org.koreait.global.Controller;
import org.koreait.global.Model;
import org.koreait.global.Router;
import org.koreait.global.libs.Utils;
import org.koreait.global.validators.RequiredValidator;
import org.koreait.global.validators.TypeValidator;
import org.koreait.main.controllers.MainController;
import org.koreait.product.entities.Product;
import org.koreait.product.services.ProductInfoService;
import org.koreait.product.templates.ProductList;

import java.util.List;
import java.util.function.Consumer;

public class ProductMenagementController extends Controller implements TypeValidator, RequiredValidator{
    private Object data;
    private Consumer<String> inputProcess;
    private Runnable promptProcess;
    public void setData(Object data) {
        this.data = data;
    }
    public Object getData() {
        return data;
    }
    protected void setInputProcess(Consumer<String> inputProcess) {
        this.inputProcess = inputProcess;
    }
    protected void setPromptProcess(Runnable promptProcess) {
        this.promptProcess = promptProcess;
    }
    protected void process(String input) {
        if (inputProcess != null) {
            inputProcess.accept(input);
        }
    }
    public ProductMenagementController() {}
    public void prompt() {
        Utils.drawLine('-', 30);
        System.out.print(getPromptText());

        if (promptProcess == null) {
            String input = Router.sc.nextLine();

            // 입력 데이터 중 Q(대소문자 구분 없음)가 유입 되면 콘솔 프로그램 종료
            if (input.toUpperCase().equals("Q")) {
                System.out.println("종료합니다.");
                System.exit(0);
            } else if (input.toUpperCase().equals("M")) {
                // 입력 데이터가 M(대소문자 구분 없음)가 유입되면 메인 메뉴로 이동
                Utils.loadController(MainController.class);
            }else if (input.toUpperCase().equals("S")) {
                // 입력 데이터가 M(대소문자 구분 없음)가 유입되면 메인 메뉴로 이동
                Utils.loadController(ProductCountController.class);
            }else if (input.toUpperCase().equals("SE")) {
                // 입력 데이터가 M(대소문자 구분 없음)가 유입되면 메인 메뉴로 이동
                Utils.loadController(ProductSaleController.class);
            }else if (input.toUpperCase().equals("D")) {
                // 입력 데이터가 M(대소문자 구분 없음)가 유입되면 메인 메뉴로 이동
                Utils.loadController(ProductDeleteController.class);
            }

            /**
             *  입력에 대한 처리는 컨트롤러 마다 달라질 수 있으므로 값만 넘겨주고
             *  각 상속 받은 컨트롤러에서 등록한 Consumer<String> inputProcess 에서 처리한다.
             *  따라서 process는 inputProcess.accept(String input)에 사용자가 입력한 값만 넘겨주면서 호출해 준다.
             */
            process(input); // 입력 처리

        } else {
            promptProcess.run();
        }
    }

    @Override
    public void view() {
    }
    @Override
    protected String getPromptText() {
        return "수정할 메뉴를 선택하세요 (메인메뉴: M, 종료: Q, 재고수정: S, 할인: SA, 삭제: D )";
    }
    }


