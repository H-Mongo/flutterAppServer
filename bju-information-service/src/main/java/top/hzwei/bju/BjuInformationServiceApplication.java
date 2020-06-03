package top.hzwei.bju;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScans;


/**
 * @author hzuwei
 * @version 1.0
 * @date 2020/2/10 14:35
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BjuInformationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BjuInformationServiceApplication.class, args);
	}

}
