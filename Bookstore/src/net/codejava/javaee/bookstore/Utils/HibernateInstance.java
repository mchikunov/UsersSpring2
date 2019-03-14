package net.codejava.javaee.bookstore.Utils;

import net.codejava.javaee.bookstore.dao.UserDAO;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class HibernateInstance implements Condition {
    @Override
    public boolean matches(ConditionContext context,
                           AnnotatedTypeMetadata metadata) {
        String instance = PropertyReader.getPropertyStr("config");
        return instance.equals("hibernate");

    }
}

