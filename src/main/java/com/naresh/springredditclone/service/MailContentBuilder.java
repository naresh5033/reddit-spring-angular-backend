package com.naresh.springredditclone.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class MailContentBuilder {

    private final TemplateEngine templateEngine;

    public String build(String message) {
        Context context = new Context();
        context.setVariable("message", message); // we will set this email msg inside the thymeleaf context obj
        return templateEngine.process("mailTemplate", context); //finally we will pass the html file name and the and the context to the template engine thru the template engine process method
    } // so at the runtime thymeleaf will automatically add the email template to the html message
}