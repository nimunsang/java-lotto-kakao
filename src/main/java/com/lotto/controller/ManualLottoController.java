package com.lotto.controller;

import com.lotto.model.*;
import com.lotto.view.LottoGameInputView;

import java.util.List;
import java.util.stream.Collectors;

public class ManualLottoController {

    private final LottoGameInputView lottoGameInputView;

    private final LottoPlayer lottoPlayer;

    public ManualLottoController(LottoPlayer lottoPlayer, LottoGameInputView lottoGameInputView) {
        this.lottoGameInputView = lottoGameInputView;
        this.lottoPlayer = lottoPlayer;
    }

    public void run() {
        int manualLottoTicketSize = lottoGameInputView.inputManualLottoTicketSize();
        List<List<Integer>> manualLottoTicketNumbers = lottoGameInputView.inputManualLottoTicketNumbers(manualLottoTicketSize);

        LottoTickets manualLottoTickets = parseLottoTicketNumbersToLottoTickets(manualLottoTicketNumbers);
        lottoPlayer.buyLottoTickets(manualLottoTickets);
    }

    private LottoTickets parseLottoTicketNumbersToLottoTickets(List<List<Integer>> manualLottoTicketNumbers) {
        List<LottoTicket> lottoTickets = manualLottoTicketNumbers.stream()
                .map(LottoNumbers::valueOf)
                .map(lottoNumbers -> new LottoTicket(LottoType.MANUAL, lottoNumbers))
                .collect(Collectors.toList());

        return new LottoTickets(lottoTickets);
    }
}
