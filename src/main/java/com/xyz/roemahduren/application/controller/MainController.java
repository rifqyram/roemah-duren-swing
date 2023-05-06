package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.presentation.component.menu.MenuItem;
import com.xyz.roemahduren.presentation.screen.MainScreen;
import com.xyz.roemahduren.presentation.theme.SystemColor;

import java.awt.*;
import java.util.List;

public class MainController {

    private final MainScreen mainScreen;

    private final BranchController branchController;
    private final CategoryController categoryController;
    private final ProductController productController;

    public MainController(MainScreen mainScreen, BranchController branchController, CategoryController categoryController, ProductController productController) {
        this.mainScreen = mainScreen;
        this.branchController = branchController;
        this.categoryController = categoryController;
        this.productController = productController;

        initController();

        mainScreen.setVisible(true);
    }

    private void initController() {
        runEvent();
    }

    private void runEvent() {
        List<MenuItem> menuItems = mainScreen.getSideMenuPanel().getMenuItems();

        for (int i = 0; i < menuItems.size(); i++) {
            int index = i;

            menuItems.get(i).addActionListener(e -> {
                setSelected(menuItems, index);
                selectedMenu(menuItems, index);
            });
        }
    }

    private void selectedMenu(List<MenuItem> menuItems, int index) {
        for (int j = 0; j < menuItems.size(); j++) {
            if (j == index) {
                menuItems.get(j).setForeground(SystemColor.BUTTON_TEXT_COLOR);
                menuItems.get(j).setColorOver(SystemColor.PRIMARY_COLOR_ACTIVE_BUTTON);
                menuItems.get(j).setBorderColor(SystemColor.PRIMARY_COLOR_ACTIVE_BUTTON);
                selectMenuIndex(index);
            } else {
                menuItems.get(j).setForeground(SystemColor.BUTTON_TEXT_COLOR);
                menuItems.get(j).setColor(SystemColor.PRIMARY_COLOR_BUTTON);
                menuItems.get(j).setBorderColor(SystemColor.PRIMARY_COLOR_BUTTON);
            }
        }
    }

    private void selectMenuIndex(int index) {
        try {
            switch (index) {
                case 0:
                    setViewport(mainScreen.getDashboardScreen());
                    break;
                case 1:
                    break;
                case 2:
                    setViewport(branchController.getBranchScreen());
                    break;
                case 3:
                    setViewport(productController.getProductScreen());
                    break;
                case 4:
                    setViewport(categoryController.getCategoryScreen());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setViewport(Component component) {
        mainScreen.getContentMenuScroll().setViewportView(component);
    }

    private static void setSelected(List<MenuItem> menuItems, int index) {
        menuItems.get(index).setSelected(true);
        for (int j = 0; j < menuItems.size(); j++) {
            if (j != index) {
                menuItems.get(j).setSelected(false);
            }
        }
    }

}
