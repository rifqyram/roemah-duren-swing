package com.xyz.roemahduren;

import com.xyz.roemahduren.application.controller.ControllerFactory;
import com.xyz.roemahduren.application.service.ProductPriceServiceImpl;
import com.xyz.roemahduren.application.service.ProductServiceImpl;
import com.xyz.roemahduren.application.service.ServiceFactory;
import com.xyz.roemahduren.domain.model.request.ProductPriceRequest;
import com.xyz.roemahduren.domain.model.request.ProductRequest;
import com.xyz.roemahduren.domain.repository.Persistence;
import com.xyz.roemahduren.domain.repository.ProductPriceRepository;
import com.xyz.roemahduren.domain.service.ProductPriceService;
import com.xyz.roemahduren.domain.service.ProductService;
import com.xyz.roemahduren.infrastructure.config.ConnectionPool;
import com.xyz.roemahduren.infrastructure.repository.DbPersistence;
import com.xyz.roemahduren.infrastructure.repository.ProductPriceRepositoryImpl;
import com.xyz.roemahduren.infrastructure.repository.ProductRepositoryImpl;
import com.xyz.roemahduren.infrastructure.repository.RepositoryFactory;
import com.xyz.roemahduren.infrastructure.security.SecurityFactory;
import com.xyz.roemahduren.presentation.ScreenFactory;
import com.xyz.roemahduren.presentation.screen.LoginScreen;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class RoemahDurenApp {

    private static final String username = System.getenv("DB_USERNAME");
    private static final String password = System.getenv("DB_PASSWORD");
    private static final String dbName = System.getenv("DB_NAME");
    private static final String dbHost = System.getenv("DB_HOST");
    private static final String dbPort = System.getenv("DB_PORT");

    public static void main(String[] args) {
        setLookAndFeel();
        ConnectionPool connectionPool = new ConnectionPool(10, username, password, dbName, dbHost, dbPort);
        try {
            Connection connection = connectionPool.acquireConnection();

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    connectionPool.closeAllConnections();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }));

            ControllerFactory controllerFactory = factoryManager(connection);
            SwingUtilities.invokeLater(() -> {
//                LoginController loginController = controllerFactory.loginController();
//                RegisterController registerController = controllerFactory.registerController();
//                loginController.setRegisterController(registerController);
//                registerController.setLoginController(loginController);
//
//                loginController.setVisible(true);

                controllerFactory.mainController();
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void setLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private static ControllerFactory factoryManager(Connection connection) {
        RepositoryFactory repositoryFactory = new RepositoryFactory(connection);

        SecurityFactory securityFactory = new SecurityFactory();

        ServiceFactory serviceFactory = new ServiceFactory(
                repositoryFactory.userCredentialRepository(),
                repositoryFactory.branchRepository(),
                repositoryFactory.categoryRepository(), securityFactory.passwordEncoder(),
                connection);

        ScreenFactory screenFactory = new ScreenFactory();

        return new ControllerFactory(
                serviceFactory.authService(),
                serviceFactory.branchService(),
                serviceFactory.categoryService(), screenFactory.loginScreen(),
                screenFactory.registerScreen(),
                screenFactory.mainScreen(),
                screenFactory.branchScreen(), screenFactory.categoryScreen());
    }
}