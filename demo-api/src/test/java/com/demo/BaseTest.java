package com.demo;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

@TestExecutionListeners(listeners = {TestListener.class},mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {com.demo.api.DemoApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTest {

    protected HttpEntity<?> httpEntity;

    @Before
    public void login() {
//        String accessToken = TokenUtil.genAccessToken(3L);
//        String refreshToken = TokenUtil.genRefreshToken(3L);
//        String registerToken = TokenUtil.genRegisterToken("1234", 1);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("accessToken", accessToken);
//        headers.add("refreshToken", refreshToken);
//        headers.add("registerToken", registerToken);
//        httpEntity = new HttpEntity<>(headers);
    }


}