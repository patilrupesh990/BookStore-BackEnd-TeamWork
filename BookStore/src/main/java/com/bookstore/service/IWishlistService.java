package com.bookstore.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.bookstore.response.WishlistResponse;

@Component
public interface IWishlistService {

	ResponseEntity<WishlistResponse> makeOrder(int id, int userId);

	ResponseEntity<WishlistResponse> getCartList(int userId);

}
