package ladder;

import ladder.controller.LadderController;
import ladder.domain.valueGenerator.RandomBooleanGenerator;
import ladder.view.InputView;
import ladder.view.ResultView;

public class Application {

    public static void main(String[] args) {
        LadderController ladderController = new LadderController(
                new InputView(),
                new ResultView(),
                new RandomBooleanGenerator());

        ladderController.run();
    }

}
