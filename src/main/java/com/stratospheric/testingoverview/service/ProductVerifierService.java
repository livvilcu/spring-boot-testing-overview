package com.stratospheric.testingoverview.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVerifierService {
  private static final List<String> productsInStock = List.of("book1");

  public boolean isCurrentlyInStockOfCompetitor(String productName) {
    return productsInStock.contains(productName);
  }

}
