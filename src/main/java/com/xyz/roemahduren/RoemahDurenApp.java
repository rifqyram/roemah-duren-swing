package com.xyz.roemahduren;

import com.xyz.roemahduren.application.controller.ControllerFactory;
import com.xyz.roemahduren.application.controller.LoginController;
import com.xyz.roemahduren.application.controller.MainController;
import com.xyz.roemahduren.application.controller.RegisterController;
import com.xyz.roemahduren.application.service.ServiceFactory;
import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.infrastructure.config.ConnectionPool;
import com.xyz.roemahduren.infrastructure.repository.RepositoryFactory;
import com.xyz.roemahduren.infrastructure.security.SecurityFactory;
import com.xyz.roemahduren.presentation.ScreenFactory;
import com.xyz.roemahduren.presentation.component.dialog.CustomConfirmDialog;
import com.xyz.roemahduren.presentation.component.dialog.CustomDialogMessage;
import com.xyz.roemahduren.presentation.component.dialog.DetailTransactionHistoryDialog;
import com.xyz.roemahduren.presentation.screen.LoginScreen;
import com.xyz.roemahduren.presentation.screen.SplashScreen;
import com.xyz.roemahduren.util.ServiceWorker;
import com.xyz.roemahduren.util.SwingUtil;

import javax.swing.*;
import java.sql.*;
import java.time.Instant;
import java.util.concurrent.ExecutionException;

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
            SwingUtilities.invokeLater(() -> run(controllerFactory));
        } catch (RuntimeException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void run(ControllerFactory controllerFactory) {
        SplashScreen splashScreen = new SplashScreen();
        splashScreen.setVisible(true);
        LoginController loginController = controllerFactory.loginController();
        SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                MainController mainController = controllerFactory.mainController();
                RegisterController registerController = controllerFactory.registerController();
                loginController.setRegisterController(registerController);
                loginController.setMainController(mainController);
                registerController.setLoginController(loginController);
                mainController.setLoginController(loginController);
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    splashScreen.setVisible(false);
                    loginController.getLoginScreen().setVisible(true);
                } catch (InterruptedException | ExecutionException e) {
                    controllerFactory.getCustomDialog().getFailedMessageDialog(e.getMessage());
                }
            }
        };
        swingWorker.execute();
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
                repositoryFactory.categoryRepository(),
                repositoryFactory.productRepository(),
                repositoryFactory.customerRepository(),
                repositoryFactory.orderDetailRepository(),
                repositoryFactory.orderRepository(),
                repositoryFactory.supplierRepository(),
                repositoryFactory.supplierProductRepository(),
                repositoryFactory.persistence(),
                repositoryFactory.invoiceNumberRepository(),
                securityFactory.passwordEncoder(),
                connection);

        ScreenFactory screenFactory = new ScreenFactory();

        CustomDialog dialog = new CustomDialog(screenFactory.customDialogMessage(), screenFactory.customConfirmDialog());
        DetailTransactionHistoryDialog detailTransactionHistoryDialog = new DetailTransactionHistoryDialog(serviceFactory.reportService(), dialog);


        return new ControllerFactory(
                serviceFactory.authService(),
                serviceFactory.branchService(),
                serviceFactory.categoryService(),
                serviceFactory.productService(),
                serviceFactory.orderService(),
                serviceFactory.supplierService(),
                serviceFactory.supplierProductService(),
                serviceFactory.customerService(),
                serviceFactory.reportService(),
                serviceFactory.invoiceService(),
                screenFactory.loginScreen(),
                screenFactory.registerScreen(),
                screenFactory.branchScreen(),
                screenFactory.mainScreen(),
                screenFactory.categoryScreen(),
                screenFactory.productScreen(),
                screenFactory.orderScreen(),
                screenFactory.supplierScreen(),
                screenFactory.settingScreen(),
                screenFactory.customerScreen(),
                screenFactory.transactionHistoryScreen(),
                screenFactory.mainScreen().getDashboardScreen(),
                dialog,
                detailTransactionHistoryDialog,
                screenFactory.forgotPasswordDialog());
    }
}