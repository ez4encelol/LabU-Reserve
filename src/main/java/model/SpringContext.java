package model;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * Static holder for the Spring {@link ConfigurableApplicationContext}.
 * Needed because Swing GUI classes are not created by Spring's DI
 * container and require programmatic bean lookup.
 */
public class SpringContext {
    private static ConfigurableApplicationContext context;

    public static void setContext(ConfigurableApplicationContext ctx) {
        context = ctx;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name, Class<T> clazz) {
        return context.getBean(name, clazz);
    }
}
