package com.lotto.model;

public class LottoTicket {

    public static final int PRICE = 1000;
    private final LottoType lottoType;
    private final LottoNumbers lottoNumbers;

    public LottoTicket(LottoType lottoType, LottoNumbers lottoNumbers) {
        this.lottoType = lottoType;
        this.lottoNumbers = lottoNumbers;
    }

    public LottoNumbers getLottoNumbers() {
        return lottoNumbers;
    }

    public boolean isType(LottoType lottoType) {
        return this.lottoType == lottoType;
    }
}
