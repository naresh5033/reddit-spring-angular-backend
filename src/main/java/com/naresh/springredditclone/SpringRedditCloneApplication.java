package com.naresh.springredditclone;

import com.naresh.springredditclone.config.OpenAPIConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync  //since the backend will send the mail to the smtp and the user has to wait for some time to get verified, so making it async we can send the async mail to user(in our mail service
@Import(OpenAPIConfiguration.class)
public class SpringRedditCloneApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringRedditCloneApplication.class, args);
    }

}
