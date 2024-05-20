package it.gabrieletondi.telldontaskkata.domain;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

import it.gabrieletondi.telldontaskkata.useCase.SellItemRequest;
import it.gabrieletondi.telldontaskkata.useCase.Taxes;
import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;
    private Category category;

  public Taxes calculateTaxes(SellItemRequest itemRequest) {
    final BigDecimal taxedAmount = calculateTaxedAmount(itemRequest.getQuantity());
    final BigDecimal taxAmount = calculateTaxAmount(itemRequest.getQuantity());
    return new Taxes(taxedAmount, taxAmount);
  }

  private BigDecimal calculateTaxAmount(int quantity) {
      return calculateUnitaryTax().multiply(valueOf(quantity));
    }

  private BigDecimal calculateTaxedAmount(int quantity) {
        return calculateUnitaryTaxedAmount(calculateUnitaryTax()).multiply(BigDecimal.valueOf(quantity))
            .setScale(2, HALF_UP);
    }

  private BigDecimal calculateUnitaryTaxedAmount(BigDecimal unitaryTax) {
    return price.add(unitaryTax).setScale(2, HALF_UP);
    }

  private BigDecimal calculateUnitaryTax() {
    return price.divide(valueOf(100))
            .multiply(category.getTaxPercentage()).setScale(2, HALF_UP);
    }

  public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

  public void setCategory(Category category) {
        this.category = category;
    }
}
