package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Order;
import it.gabrieletondi.telldontaskkata.domain.OrderItem;
import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;
import java.math.BigDecimal;

public class OrderCreationUseCase {

  private final OrderRepository orderRepository;
  private final ProductCatalog productCatalog;

  public OrderCreationUseCase(OrderRepository orderRepository, ProductCatalog productCatalog) {
    this.orderRepository = orderRepository;
    this.productCatalog = productCatalog;
  }

  public void run(SellItemsRequest request) {
    Order order = Order.createNewOrder();

    for (SellItemRequest itemRequest : request.getRequests()) {
      Product product = productCatalog.getByName(itemRequest.getProductName());

      if (product == null) {
        throw new UnknownProductException();
      } else {
        final BigDecimal taxedAmount = product.calculateTaxedAmount(itemRequest.getQuantity());
        final BigDecimal taxAmount = product.calculateTaxAmount(itemRequest.getQuantity());

        final OrderItem orderItem = OrderItem.create(itemRequest,
            product, taxAmount, taxedAmount);
        order.getItems().add(orderItem);

        order.setTotal(order.getTotal().add(taxedAmount));
        order.setTax(order.getTax().add(taxAmount));
      }
    }

    orderRepository.save(order);
  }

}
