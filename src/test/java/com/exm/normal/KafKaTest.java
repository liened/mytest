package com.exm.normal;

import com.exm.util.KafKaUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;

/**
 * @author yyx
 * @version 1.0
 * @createDate 2019-01-29 17:17
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class KafKaTest {

    @Resource
    private KafKaUtil kafKaUtil;

    private String topic = "cashloan_risk_result";

    @Test
    public void sendMessage() throws Exception{
        File file = new File("C:\\Users\\yangyunxiang\\Desktop\\a.txt");
        Reader reader = new FileReader(file);
        String content = FileCopyUtils.copyToString(reader);
        boolean b = kafKaUtil.sendMessage(topic,content);
        System.out.println("=================end-b="+b);
    }
}
