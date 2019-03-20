package com.exm;

/**
 * https://www.cnblogs.com/pejsidney/p/9687291.html mybatis-plus 3.0
 */

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.exm.mapper")
@Slf4j
public class MytestApplication implements CommandLineRunner{

	public static void main(String[] args) {
		log.info("开始main方法里的启动");
		SpringApplication.run(MytestApplication.class, args);
		log.info("结束main方法里的启动");
	}

	@Override
	public void run(String... strings) throws Exception {
		//log.info("项目启动完毕,打开默认浏览器..");
		/*
		String url = "http://localhost:8081/doc.html";
		if (Desktop.isDesktopSupported()){
			URI uri = URI.create(url);
			Desktop desktop = Desktop.getDesktop();
			if (desktop.isSupported(Desktop.Action.BROWSE)){
				desktop.browse(uri);
			}
		}else{
			Runtime runtime = Runtime.getRuntime();
			try {
				String explorPath = "C:\\Users\\yangyunxiang\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe ";
				runtime.exec(explorPath + url);
			} catch (IOException e) {
				log.info("打开浏览器异常...");
				e.printStackTrace();
			}
		}
*/
	}
}
