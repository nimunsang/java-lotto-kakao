package com.lotto.model;

public class LottoPlayer {

    private final LottoTickets lottoTickets = new LottoTickets();
    private Money balance;

    public LottoPlayer() {
        this.balance = new Money();
    }

    private static void validateMoney(Money money) {
        if (money.isLessThan(Money.valueOf(LottoTicket.PRICE))) {
            throw new IllegalArgumentException("로또 구매 최소 금액은 " + LottoTicket.PRICE + "원 입니다.");
        }
    }

    public Money getBalance() {
        return balance;
    }

    public void inputMoney(Money money) {
        validateMoney(money);
        this.balance = balance.add(money);
    }

    public void buyLottoTickets(LottoTickets lottoTickets) {
        Money lottoTicketsPrice = Money.valueOf(lottoTickets.getPrice());
        validateBalance(lottoTicketsPrice);

        this.lottoTickets.addAll(lottoTickets);
        balance = balance.subtract(lottoTicketsPrice);
    }

    public LottoTickets getLottoTickets() {
        return lottoTickets;
    }

    public int getTicketTypeCount(LottoType lottoType) {
        return lottoTickets.getTicketTypeCount(lottoType);
    }

    private void validateBalance(Money lottoTicketsPrice) {
        if (balance.isLessThan(lottoTicketsPrice)) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
    }
}
