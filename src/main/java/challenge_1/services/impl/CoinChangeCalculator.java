package challenge_1.services.impl;

import challenge_1.services.ChangeCalculator;
import org.springframework.stereotype.Service;

@Service
public class CoinChangeCalculator implements ChangeCalculator {
    @Override
    public int[] calculate(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int j = 1; j <= amount; j++) {
            if (coins[0] > j || j % coins[0] != 0) {
                dp[0][j] = -1;
            } else {
                dp[0][j] = j / coins[0];
            }
        }
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (coins[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    int prev = dp[i - 1][j];
                    int cur = 1 + dp[i][j - coins[i]];
                    if (cur == 0) {
                        dp[i][j] = dp[i - 1][j];
                    } else if (prev == -1) {
                        dp[i][j] = 1 + dp[i][j - coins[i]];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coins[i]]);
                    }
                }
            }
        }
        return materialize(coins, amount, dp);
    }

    private int[] materialize(int[] coins, int amount, int[][] dp) {
        int[] result = new int[coins.length + 1];
        int i = coins.length - 1;
        int j = amount;
        result[0] = dp[i][j];
        if (result[0] == 0 || result[0] == -1)
            return result;
        while (j > 0) {
            if (i - 1 >= 0 && dp[i][j] == dp[i - 1][j]) {
                i = i - 1;
            } else {
                j = j - coins[i];
                result[i + 1] += 1;
            }
        }
        return result;
    }
}