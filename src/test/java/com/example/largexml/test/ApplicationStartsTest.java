package com.example.largexml.test;

import com.example.largexml.LargeXML;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LargeXML.class)
public class ApplicationStartsTest {

    @Test
    public void contextLoads() throws Exception {
    }

}