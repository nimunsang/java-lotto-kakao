package com.lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets = new ArrayList<>();

    public LottoTickets() {
    }

    public LottoTickets(List<LottoTicket> lottoTickets) {
        this.lottoTickets.addAll(lottoTickets);
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }

    public int size() {
        return lottoTickets.size();
    }

    public List<LottoRank> matchAll(TargetLotto targetLotto) {
        return lottoTickets.stream()
                .map(LottoTicket::getLottoNumbers)
                .map(targetLotto::match)
                .collect(Collectors.toList());
    }

    public void add(LottoTicket lottoTicket) {
        lottoTickets.add(lottoTicket);
    }

    public void addAll(LottoTickets other) {
        lottoTickets.addAll(other.getLottoTickets());
    }

    public int getTicketTypeCount(LottoType lottoType) {
        return (int) lottoTickets.stream()
                .filter(lottoTicket -> lottoTicket.isType(lottoType))
                .count();
    }

    public int getPrice() {
        return lottoTickets.size() * LottoTicket.PRICE;
    }
}
