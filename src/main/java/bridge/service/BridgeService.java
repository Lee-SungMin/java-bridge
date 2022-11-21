package bridge.service;

import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.domain.BridgeGame;
import bridge.view.InputView;
import bridge.view.OutputView;

import java.util.ArrayList;
import java.util.List;

import static bridge.view.Message.*;

public class BridgeService {
    private static int inputSize;
    private static List<String> answerBridge;
    private static String inputMoveRow;

    private static List<String>[] presentMoveBridge;

    public static int getInputSize() {
        return inputSize;
    }

    public static List<String> getAnswerBridge() {
        return answerBridge;
    }

    public static int getInitBridgeSize() {
        while (true) {
            String input = InputView.readBridgeSize();
            try {
                inputSize = checkInteger(input);
                checkRange(inputSize);
                return inputSize;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<String> makeBridge() {
        BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
        answerBridge = bridgeMaker.makeBridge(inputSize);
        resetBridge();
        return answerBridge;
    }

    public static void resetBridge() {
        presentMoveBridge = new ArrayList[2];
        for (int i = 0; i < 2; i++) {
            presentMoveBridge[i] = new ArrayList<String>();
        }
    }

    public static String moveBridge() {
        String moveAnswer = BridgeGame.move(inputMoveRow);
        if (inputMoveRow.equals("U")) {
            presentMoveBridge[0].add(moveAnswer);
            presentMoveBridge[1].add(" ");
        } else if (inputMoveRow.equals("D")) {
            presentMoveBridge[1].add(moveAnswer);
            presentMoveBridge[0].add(" ");
        }
        return moveAnswer;
    }

    public static void compareMoveBridge() {
        BridgeGame.compareMove(inputMoveRow);
    }

    public static void viewBridge() {
        OutputView.printMap(presentMoveBridge);
    }


    public static String getInitMoveRow() {
        while (true) {
            String input = InputView.readMoving();
            try {
                inputMoveRow = String.valueOf(checkUpDown(input));
                return inputMoveRow;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Character checkUpDown(String input) {
        if (input.equals("U") || input.equals("D")) {
            return input.charAt(0);
        }
        throw new IllegalArgumentException(ERROR_NOT_UP_DOWN);
    }

    public static int checkInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_INTEGER);
        }
    }

    public static void checkRange(int input) {
        if (input < 3 || input > 21) {
            throw new IllegalArgumentException(ERROR_OVER_RANGE);
        }
    }
}