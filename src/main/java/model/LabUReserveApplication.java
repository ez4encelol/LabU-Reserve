package model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

/**
 * Entry point for the LabU Reserve desktop application.
 * Boots a Spring Boot context (DI container + JPA + Flyway + H2)
 * before launching the Swing UI.
 */
@SpringBootApplication(scanBasePackages = "model")
public class LabUReserveApplication {

    public static void main(String[] args) {
        // Force AWT to run in non-headless mode so the Swing UI can launch.
        // Must be set BEFORE Spring Boot starts (some environments detect headless
        // by default even when a display is available).
        System.setProperty("java.awt.headless", "false");

        ConfigurableApplicationContext ctx = SpringApplication.run(
            LabUReserveApplication.class, args
        );
        SpringContext.setContext(ctx);

        SwingUtilities.invokeLater(() -> {
            statsVisualiser.gui.MainUI mainUI = statsVisualiser.gui.MainUI.getInstance();
            mainUI.setVisible(true);
        });
    }
}
