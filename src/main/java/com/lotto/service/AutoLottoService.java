package com.lotto.service;

import com.lotto.model.AutoLottoMachine;
import com.lotto.model.LottoPlayer;
import com.lotto.model.LottoTickets;
import com.lotto.model.Money;
import com.lotto.util.LottoGenerateStrategy;

public class AutoLottoService {

    private final LottoPlayer lottoPlayer;
    private final AutoLottoMachine autoLottoMachine;

    public AutoLottoService(LottoPlayer lottoPlayer, LottoGenerateStrategy lottoGenerateStrategy) {
        this.lottoPlayer = lottoPlayer;
        this.autoLottoMachine = new AutoLottoMachine(lottoGenerateStrategy);
    }

    public void run() {
        Money balance = lottoPlayer.getBalance();
        LottoTickets lottoTickets = autoLottoMachine.generateLottoTickets(balance);
        lottoPlayer.buyLottoTickets(lottoTickets);
    }
}
