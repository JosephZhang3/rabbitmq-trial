package com.zhangjh;

import com.zhangjh.api_core.ApiCoreSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhangjianghao on 2018-08-28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiCoreSenderTests {

    @Autowired
    private ApiCoreSender apiCoreSender;

    @Test
    public void testUser() {
        apiCoreSender.user("用户管理*。*");
    }

    @Test
    public void testUserQuery() {
        apiCoreSender.userQuery("用户查询%。%");
    }
}
