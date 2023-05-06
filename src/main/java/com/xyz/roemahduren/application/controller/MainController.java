package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.presentation.component.menu.MenuItem;
import com.xyz.roemahduren.presentation.screen.MainScreen;

import java.awt.*;
import java.util.List;

public class MainController {

    private final MainScreen mainScreen;
    private final BranchController branchController;

    public MainController(MainScreen mainScreen, BranchController branchController) {
        this.mainScreen = mainScreen;
        this.branchController = branchController;

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
                menuItems.get(j).setForeground(new Color(0xFFFFFE));
                menuItems.get(j).setColorOver(new Color(0x786745));
                menuItems.get(j).setBorderColor(new Color(0x786745));
                selectMenuIndex(index);

            } else {
                menuItems.get(j).setForeground(new Color(0xFFFFFE));
                menuItems.get(j).setColor(new Color(0x8C7851));
                menuItems.get(j).setBorderColor(new Color(0x8C7851));
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
                    setViewport(branchController.getBranchScreen());
                    break;
                case 2:
                    break;
                case 3:
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
