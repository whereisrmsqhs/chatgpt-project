package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.PlannerController;
import christmas.service.PlannerService;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.View;

public class Application {
    public static void main(String[] args) {
        View view = new View(new InputView(), new OutputView());
        PlannerController controller = new PlannerController(view, new PlannerService());
        controller.run();
    }
}
