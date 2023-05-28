package com.xyz.roemahduren.application.controller;

import com.xyz.roemahduren.constant.CustomDialog;
import com.xyz.roemahduren.domain.entity.Order;
import com.xyz.roemahduren.domain.entity.OrderDetail;
import com.xyz.roemahduren.domain.model.response.ProductResponse;
import com.xyz.roemahduren.domain.service.OrderService;
import com.xyz.roemahduren.domain.service.ProductService;
import com.xyz.roemahduren.presentation.screen.OrderScreen;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class OrderController {

    private final OrderScreen orderScreen;
    private final OrderService orderService;
    private final ProductService productService;
    private final CustomDialog dialog;

    private DefaultTableModel productTableModel;
    private DefaultTableModel detailOrderTableModel;
    private Order order;
    private OrderDetail orderDetail;
    private List<ProductResponse> productResponse;

    public OrderController(OrderScreen orderScreen, OrderService orderService, ProductService productService, CustomDialog dialog) {
        this.orderScreen = orderScreen;
        this.orderService = orderService;
        this.productService = productService;
        this.dialog = dialog;
    }

    public OrderScreen getOrderScreen() {
        return orderScreen;
    }

    private void initTableProduct() {
        productTableModel = new DefaultTableModel();
        int counter = 0;
    }
}
