package top.hzwei.bju.information;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import javafx.beans.binding.ObjectExpression;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootTest
class BjuInformationServiceApplicationTests {

	public static void main(String[] args) {
//		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
//		String s = DigestUtils.md5DigestAsHex("654321".getBytes()).toUpperCase();
//		System.out.println(s);
//		String s2 = new String(DigestUtils.md5Digest("123456".getBytes())).toUpperCase();
//		System.out.println(s2);
//		StringBuilder s3 = DigestUtils.appendMd5DigestAsHex("123456".getBytes(),new StringBuilder("123456"));
//		System.out.println(s3);

	}



	@Test
	void contextLoads() {
	}


}
