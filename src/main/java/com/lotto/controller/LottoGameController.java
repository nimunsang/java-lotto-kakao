package com.lotto.controller;

import com.lotto.model.*;
import com.lotto.util.LottoNumbersGenerator;
import com.lotto.view.LottoGameInputView;
import com.lotto.view.LottoGameOutputView;

import java.util.List;

public class LottoGameController {
    private final LottoGameInputView lottoGameInputView;
    private final LottoGameOutputView lottoGameOutputView;

    private final LottoPlayer lottoPlayer;
    private final AutoLottoMachine autoLottoMachine;


    public LottoGameController(LottoGameInputView lottoGameInputView, LottoGameOutputView lottoGameOutputView) {
        this.lottoGameInputView = lottoGameInputView;
        this.lottoGameOutputView = lottoGameOutputView;
        this.lottoPlayer = new LottoPlayer();
        this.autoLottoMachine = new AutoLottoMachine(new LottoNumbersGenerator());
    }

    // 목표 : 수동 / 자동을 손쉽게 떼어낼 수 있다.
    public void run() {
        Money money = inputMoney();
        lottoPlayer.inputMoney(money);

        int manualLottoTicketSize = playManualLottoGame();
        int autoLottoTicketSize = playAutoLottoGame();
        printLottoTickets(manualLottoTicketSize, autoLottoTicketSize);

        TargetLotto targetLotto = inputTargetLotto();
        LottoGame lottoGame = new LottoGame(lottoPlayer, targetLotto);
        LottoResults lottoResults = lottoGame.play();

        printStatistics(lottoResults);
    }

    private int playAutoLottoGame() {
        Money balance = lottoPlayer.getBalance();
        int autoLottoTicketSize = balance.divide(Money.valueOf(LottoTicket.PRICE)).intValue();
        for (int i = 0; i < autoLottoTicketSize; i++) {
            LottoNumbers lottoNumbers = autoLottoMachine.generateLottoNumbers();
            lottoPlayer.buyLottoTicket(lottoNumbers);
        }
        return autoLottoTicketSize;
    }

    private int playManualLottoGame() {
        int manualLottoTicketSize = lottoGameInputView.inputManualLottoTicketSize();
        for (int i = 0; i < manualLottoTicketSize; i++) {
            List<Integer> manualLottoTicketNumbers = lottoGameInputView.inputNumbers();
            LottoNumbers lottoNumbers = LottoNumbers.valueOf(manualLottoTicketNumbers);
            lottoPlayer.buyLottoTicket(lottoNumbers);
        }
        return manualLottoTicketSize;
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

    private void printLottoTickets(int manualLottoTicketSize, int autoLottoTicketSize) {
        lottoGameOutputView.printLottoTicketSize(manualLottoTicketSize, autoLottoTicketSize);
        lottoGameOutputView.printLottoTicketList(lottoPlayer);
    }
}
