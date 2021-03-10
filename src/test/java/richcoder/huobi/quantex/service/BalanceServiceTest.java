package richcoder.huobi.quantex.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class BalanceServiceTest {
    @Resource
    private BalanceService balanceService;

    @Test
    void queryBalance() {
        balanceService.queryBalance();
    }
}