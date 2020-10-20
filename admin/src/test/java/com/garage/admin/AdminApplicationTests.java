package com.garage.admin;

import com.garage.admin.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminApplicationTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void contextLoads() {
        emailService.sendSimpleEmail("397954976@qq.com","this is simple mail"," hello Yueyou");

    }

}
