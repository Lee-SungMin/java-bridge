package bridge.controller;

import bridge.service.BridgeService;

public class BridgeController {

    public static void initBridgeSize() {
        BridgeService.getInitBridgeSize();
    }

    public static void makeBridge() {
        BridgeService.makeBridge();
    }
}
