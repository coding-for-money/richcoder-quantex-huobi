package richcoder.huobi.quantex.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
@SpringBootTest
class AccountServiceTest {
    @Resource
    private AccountService accountService;

    @Test
    void accountInfo() {
        accountService.accountInfo();
    }
}