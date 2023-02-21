package ladder.domain.reward;

import ladder.domain.ladder.Ladder;
import ladder.domain.player.Name;
import ladder.domain.player.Player;
import ladder.domain.player.Players;
import ladder.domain.valueGenerator.MockBooleanGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayName("GameResult ")
public class GameResultTest {

    @Test
    @DisplayName("생성 테스트")
    void createJudgeTest() {
        Players players = Players.create(List.of("a", "b", "c"));
        Ladder ladder = Ladder.create(3, 5, new MockBooleanGenerator(List.of(false)));
        Rewards rewards = Rewards.create(List.of("보상1", "보상2", "보상3"), 3);
        assertDoesNotThrow(()-> GameResult.create(players, ladder, rewards));
    }

    @Test
    @DisplayName("플레이어 목록의 결과 가져오는 테스트")
    void findGameResult() {
        //given
        Players createdPlayers = Players.create(List.of("a", "b", "c"));
        Players targetPlayers = Players.create(List.of("a", "b", "c"));
        Ladder ladder = Ladder.create(3, 5, new MockBooleanGenerator(List.of(false)));
        Rewards rewards = Rewards.create(List.of("보상1", "보상2", "보상3"), 3);
        //when
        GameResult gameResult = GameResult.create(createdPlayers, ladder, rewards);
        Map<Player, Reward> result = gameResult.findResultByPlayers(targetPlayers);
        //then
        assertThat(result.get(new Player(new Name("a"))).getReward()).isEqualTo("보상1");
        assertThat(result.get(new Player(new Name("b"))).getReward()).isEqualTo("보상2");
        assertThat(result.get(new Player(new Name("c"))).getReward()).isEqualTo("보상3");
    }

}