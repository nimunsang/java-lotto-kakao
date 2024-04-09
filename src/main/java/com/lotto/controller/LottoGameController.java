package com.lotto.controller;

import com.lotto.model.*;
import com.lotto.service.AutoLottoService;
import com.lotto.util.LottoNumbersGenerator;
import com.lotto.view.LottoGameInputView;
import com.lotto.view.LottoGameOutputView;

import java.util.List;

public class LottoGameController {
    private final LottoGameInputView lottoGameInputView;
    private final LottoGameOutputView lottoGameOutputView;

    private final LottoPlayer lottoPlayer;
    private final ManualLottoController manualLottoController;
    private final AutoLottoService autoLottoService;


    public LottoGameController(LottoGameInputView lottoGameInputView, LottoGameOutputView lottoGameOutputView) {
        this.lottoGameInputView = lottoGameInputView;
        this.lottoGameOutputView = lottoGameOutputView;
        this.lottoPlayer = new LottoPlayer();
        this.manualLottoController = new ManualLottoController(lottoPlayer, lottoGameInputView);
        this.autoLottoService = new AutoLottoService(lottoPlayer, new LottoNumbersGenerator());
    }

    // 목표 : 수동 / 자동을 손쉽게 떼어낼 수 있다.
    public void run() {
        Money money = inputMoney();
        lottoPlayer.inputMoney(money);

        manualLottoController.run();
        autoLottoService.run();

        printLottoTickets();

        TargetLotto targetLotto = inputTargetLotto();
        LottoGame lottoGame = new LottoGame(lottoPlayer, targetLotto);
        LottoResults lottoResults = lottoGame.play();

        printStatistics(lottoResults);
    }

    public void printStatistics(LottoResults lottoResults) {
        lottoGameOutputView.printLottoResults(lottoResults);
        lottoGameOutputView.printProfitRate(lottoResults);
    }

    private TargetLotto inputTargetLotto() {
        List<Integer> winningNumbers = lottoGameInputView.inputWinningNumbers();
        int bonusNumber = lottoGameInputView.inputBonusNumber();
        return new TargetLotto(winningNumbers, bonusNumber);
    }

    private Money inputMoney() {
        int moneyInteger = lottoGameInputView.inputMoney();
        return Money.valueOf(moneyInteger);
    }

    private void printLottoTickets() {
        lottoGameOutputView.printLottoTicketSize(lottoPlayer.getTicketTypeCount(LottoType.MANUAL), lottoPlayer.getTicketTypeCount(LottoType.AUTO));
        lottoGameOutputView.printLottoTicketList(lottoPlayer);
    }
}
