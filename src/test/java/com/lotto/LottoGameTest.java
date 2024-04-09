package com.lotto;

import com.lotto.model.LottoGame;
import com.lotto.model.LottoPlayer;
import com.lotto.model.TargetLotto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoGameTest {

    @Test
    void 로또_게임을_생성할_수_있다() {
        LottoGame lottoGame = new LottoGame(new LottoPlayer(), new TargetLotto(List.of(1, 2, 3, 4, 5, 6), 7));
        assertThat(lottoGame).isNotNull();
    }
}
