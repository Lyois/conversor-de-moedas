package org.example;

// Registro que representa os dados retornados pela API
public record Currency(String base_code, CurrencyCode conversion_rates) {
}

