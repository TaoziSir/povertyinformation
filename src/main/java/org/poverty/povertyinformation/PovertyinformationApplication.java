package org.poverty.povertyinformation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//重点
@ServletComponentScan
@MapperScan("org.poverty.povertyinformation.mapper")
public class PovertyinformationApplication {

	public static void main(String[] args) {
		SpringApplication.run(PovertyinformationApplication.class, args);

	}

}
