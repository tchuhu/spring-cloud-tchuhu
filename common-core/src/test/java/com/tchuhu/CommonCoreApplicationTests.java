package com.tchuhu;


import com.alibaba.fastjson.JSONObject;
import com.tchuhu.common.core.App;
import com.tchuhu.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = App.class)
class CommonCoreApplicationTests {

    @Test
    void contextLoads() {
        User user = new User();
        user.setPhone("18329376069");
        String s = JSONObject.toJSONString(user);
        System.out.println("结果："+s);
        System.out.println("结果："+user.getPhone());

    }

}
