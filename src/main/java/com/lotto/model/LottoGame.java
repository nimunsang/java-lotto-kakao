package com.lotto.model;

import java.util.List;

public class LottoGame {

    private final LottoPlayer lottoPlayer;

    private final TargetLotto targetLotto;

    public LottoGame(LottoPlayer lottoPlayer, TargetLotto targetLotto) {
        this.lottoPlayer = lottoPlayer;
        this.targetLotto = targetLotto;
    }

    public LottoResults play() {
        LottoResults lottoResults = new LottoResults();
        LottoTickets lottoTickets = lottoPlayer.getLottoTickets();
        List<LottoRank> lottoRanks = lottoTickets.matchAll(targetLotto);
        lottoResults.applyLottoRanks(lottoRanks);
        return lottoResults;
    }
}
