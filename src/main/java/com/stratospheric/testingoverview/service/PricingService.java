package com.stratospheric.testingoverview.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class PricingService {

  private final ProductVerifierService productVerifierService;
  private final ProductReporterService productReporterService;

  public PricingService(ProductVerifierService productVerifierService, ProductReporterService productReporterService) {
    this.productVerifierService = productVerifierService;
    this.productReporterService = productReporterService;
  }

  public BigDecimal calculatePrice(String productName) {
    if (productVerifierService.isCurrentlyInStockOfCompetitor(productName)) {
      productReporterService.notify(productName);
      return new BigDecimal("99.99");
    }

    return new BigDecimal("149.99");
  }
}
