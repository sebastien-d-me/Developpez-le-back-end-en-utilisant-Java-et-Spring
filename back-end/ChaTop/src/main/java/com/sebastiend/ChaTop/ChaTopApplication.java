package com.sebastiend.ChaTop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sebastiend.ChaTop.models.HelloWorld;
import com.sebastiend.ChaTop.services.BusinessService;

@SpringBootApplication
public class ChaTopApplication implements CommandLineRunner {
    @Autowired
    private BusinessService bs;

	public static void main(String[] args) {
		SpringApplication.run(ChaTopApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        HelloWorld hw = bs.getHelloWorld();
        System.out.println(hw);
    }
}
