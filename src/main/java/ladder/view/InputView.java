package ladder.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView implements Input {

    private static final String INPUT_REWARDS_MESSAGE = "실행 결과를 입력하세요.";
    private static final String INPUT_NAMES_MESSAGE = "참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)";
    private static final String INPUT_TARGET_NAMES_MESSAGE = "결과를 보고 싶은 사람은?";
    private static final String INPUT_HEIGHT_MESSAGE = "최대 사다리 높이는 몇 개인가요?";
    private static final String INPUT_CONTINUE_MESSAGE = "다른 결과를 출력하시겠습니까? (y/n)";
    private static final String DELIMITER = ",";
    private static final String REGEX_ENGLISH_KOREAN_DIGIT_BLANK = "[a-zA-Z가-힣\\d\\s]+";
    private static final String CONTINUE = "Y";
    private static final String STOP = "N";

    private static final Scanner sc = new Scanner(System.in);

    @Override
    public List<String> inputPlayerNames() {
        System.out.println(INPUT_NAMES_MESSAGE);

        return splitInputByDelimiter(inputString());
    }

    @Override
    public int inputHeightOfLadder() {
        System.out.println(INPUT_HEIGHT_MESSAGE);
        try {
            return Integer.parseInt(inputString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력하세요.");
        }
    }

    @Override
    public List<String> inputRewards() {
        System.out.println(INPUT_REWARDS_MESSAGE);

        return splitInputByDelimiter(inputString());
    }

    @Override
    public List<String> inputTargetPlayerNames() {
        System.out.println(INPUT_TARGET_NAMES_MESSAGE);

        return splitInputByDelimiter(inputString());
    }

    @Override
    public String inputContinue() {
        System.out.println(INPUT_CONTINUE_MESSAGE);
        String input = inputString().toUpperCase();
        validateYOrN(input);
        return input;
    }

    private String inputString() {
        String input = sc.nextLine();
        validateNull(input);
        return input;
    }

    private List<String> splitInputByDelimiter(String input) {
        List<String> inputs = Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
        for (String element : inputs) {
            validateType(element);
        }
        return inputs;
    }

    private void validateType(String input) {
        if (!input.matches(REGEX_ENGLISH_KOREAN_DIGIT_BLANK)) {
            throw new IllegalArgumentException("입력은 공백, 숫자, 영어, 한글만 할 수 있습니다.");
        }
    }

    private void validateNull(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("값을 입력해주세요.");
        }
    }

    private static void validateYOrN(String inputContinue) {
        if ((!inputContinue.equals(CONTINUE)) && (!inputContinue.equals(STOP))) {
            throw new IllegalArgumentException("y 혹은 n만 입력해주세요.");
        }
    }

}
