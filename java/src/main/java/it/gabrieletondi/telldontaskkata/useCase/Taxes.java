package it.gabrieletondi.telldontaskkata.useCase;

import java.math.BigDecimal;

public record Taxes(BigDecimal taxedAmount, BigDecimal taxAmount) {

}
