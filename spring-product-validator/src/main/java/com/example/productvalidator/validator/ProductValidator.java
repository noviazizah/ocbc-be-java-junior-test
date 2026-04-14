package com.example.productvalidator.validator;

import com.example.productvalidator.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class ProductValidator implements Validator {

    private static final Pattern SKU_PATTERN = Pattern.compile("^SKU-\\d{8}$");

    private static final Set<String> ALLOWED_CATEGORIES = new HashSet<>(
            Arrays.asList("Electronics", "Books", "Apparel", "Home Goods")
    );

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;

        if (product.getSku() == null || product.getSku().trim().isEmpty()) {
            errors.rejectValue("sku", "sku.required", "The sku is a mandatory field");
        } else if (!SKU_PATTERN.matcher(product.getSku()).matches()) {
            errors.rejectValue("sku", "sku.invalid", "The sku must be in the format SKU-XXXXXXXX");
        }

        if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
            errors.rejectValue("productName", "productName.required", "The productName is a mandatory field");
        }

        if (product.getQuantityInStock() < 0) {
            errors.rejectValue("quantityInStock", "quantityInStock.invalid", "The quantityInStock cannot be negative");
        }

        if (product.getPrice() <= 0) {
            errors.rejectValue("price", "price.invalid", "The price must be greater than zero");
        }

        if (product.getCategory() == null || product.getCategory().trim().isEmpty()) {
            errors.rejectValue("category", "category.required", "The category is a mandatory field");
        } else if (!ALLOWED_CATEGORIES.contains(product.getCategory())) {
            errors.rejectValue("category", "category.invalid", "Invalid product category");
        }
    }
}
