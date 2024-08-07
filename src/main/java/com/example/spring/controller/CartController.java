package com.example.spring.controller;

import com.example.spring.dto.shoppingcart.CartItemRequestDto;
import com.example.spring.dto.shoppingcart.ShoppingCartDto;
import com.example.spring.dto.shoppingcart.UpdateCartItemRequestDto;
import com.example.spring.model.User;
import com.example.spring.service.cart.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping Cart management", description = "Endpoints for managing shopping carts")
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    @Operation(summary = "Add item to shopping cart",
            description = "Add a new item to the user's shopping cart")
    public ShoppingCartDto addBookToCart(@RequestBody @Valid CartItemRequestDto cartItemRequestDto,
                     Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return cartService.addBookToCart(user.getId(), cartItemRequestDto);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    @Operation(summary = "Get user's shopping cart",
            description = "Retrieve the user's shopping cart")
    public ShoppingCartDto getShoppingCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return cartService.getUsersCart(user.getId());
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/items/{id}")
    @Operation(summary = "Update item in shopping cart",
            description = "Update the quantity or other properties of an item in the shopping cart")
    public ShoppingCartDto updateById(@PathVariable Long id,
                                  @RequestBody @Valid UpdateCartItemRequestDto requestDto,
                                  Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return cartService.updateById(user.getId(), id, requestDto);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/items/{id}")
    @Operation(summary = "Remove item from shopping cart",
            description = "Delete an item from the user's shopping cart")
    public void deleteById(@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        cartService.deleteById(user.getId(), id);
    }
}
