package org.eocencle.dasislcy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(value="org.eocencle.dasislcy.dao")
public class DasislcyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DasislcyApplication.class, args);
	}

}
