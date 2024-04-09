package com.lotto.model;

import com.lotto.util.LottoGenerateStrategy;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoMachine {


    private final LottoGenerateStrategy lottoGenerateStrategy;

    public AutoLottoMachine(LottoGenerateStrategy lottoGenerateStrategy) {
        this.lottoGenerateStrategy = lottoGenerateStrategy;
    }

    public LottoTickets generateLottoTickets(Money money) {
        int lottoTicketSize = money.divide(Money.valueOf(LottoTicket.PRICE)).intValue();
        return IntStream.rangeClosed(1, lottoTicketSize)
                .mapToObj(i -> new LottoTicket(LottoType.AUTO, lottoGenerateStrategy.generate()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), LottoTickets::new));
    }
}
