package com.jokerchen.pinellia.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**   
 * @description: 这里扫描注解需添加包的路径，否则会使用当前的文件的路径，导致其他引入的包的注解没有被扫描
 * @author Joker Chen 
 * @date 2019-03-13 11:24:45  
 */
@EntityScan(basePackages = {"com.jokerchen.pinellia"})
@ComponentScan(basePackages = {"com.jokerchen.pinellia"})
@SpringBootApplication
public class ConsoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsoleApplication.class, args);
	}

}
