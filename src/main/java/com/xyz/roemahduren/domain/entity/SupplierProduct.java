package com.xyz.roemahduren.domain.entity;

import com.xyz.roemahduren.domain.annotation.db.Column;
import com.xyz.roemahduren.domain.annotation.db.Id;
import com.xyz.roemahduren.domain.annotation.db.Table;

@Table(name = "m_supplier_product")
public class SupplierProduct {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "supplier_id")
    private String supplierId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "price")
    private Long price;
    @Column(name = "stock")
    private Integer stock;

    @Column(name = "init_stock")
    private Integer initStock;


    public SupplierProduct(String id, String supplierId, String productName, Long price, Integer stock, Integer initStock) {
        this.id = id;
        this.supplierId = supplierId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.initStock = initStock;
    }

    public SupplierProduct(String id, String supplierId, String productName, Long price, Integer stock) {
        this.id = id;
        this.supplierId = supplierId;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public SupplierProduct() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getInitStock() {
        return initStock;
    }

    public void setInitStock(Integer initStock) {
        this.initStock = initStock;
    }
}
