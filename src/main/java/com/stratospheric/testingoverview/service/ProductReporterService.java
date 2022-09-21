package com.stratospheric.testingoverview.service;

import org.springframework.stereotype.Service;

@Service
public class ProductReporterService {
  public void notify(String productName) {
    System.out.println("Notify about product name " + productName);
  }
}
