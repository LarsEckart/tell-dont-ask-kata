package it.gabrieletondi.telldontaskkata.domain;

import it.gabrieletondi.telldontaskkata.useCase.SellItemRequest;
import java.math.BigDecimal;

public class OrderItem {
    private Product product;
    private int quantity;
    private BigDecimal taxedAmount;
    private BigDecimal tax;

    public static OrderItem create(SellItemRequest itemRequest, Product product,
        BigDecimal taxAmount, BigDecimal taxedAmount) {
      final OrderItem orderItem = new OrderItem();
      orderItem.setProduct(product);
      orderItem.setQuantity(itemRequest.getQuantity());
      orderItem.setTax(taxAmount);
      orderItem.setTaxedAmount(taxedAmount);
      return orderItem;
    }

    public Product getProduct() {
        return product;
    }

    private void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    private void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTaxedAmount() {
        return taxedAmount;
    }

    public void setTaxedAmount(BigDecimal taxedAmount) {
        this.taxedAmount = taxedAmount;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
}
