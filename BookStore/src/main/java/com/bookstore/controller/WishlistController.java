package com.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.response.WishlistResponse;
import com.bookstore.service.IWishlistService;

import io.swagger.annotations.ApiOperation;

/************************************************************************************************************************************************
 * WishlistController By using the object reference of WishlistService class. This Controller Has fuctionallity to 
 *
 * @author Sudeep Kumar Katiar
 * @version 1.0
 * @created 2020-05-12
 * @see {@link IWishlistService} implementation of all the required services & functionality
 *
 ************************************************************************************************************************************/

@RestController
@CrossOrigin("*")
@RequestMapping("/wishlist")
public class WishlistController {
	
	@Autowired
	IWishlistService wishlistService;
	
	/*
	 * API to add book to wishlist
	 */
	@PostMapping("/add")
	@ApiOperation(value = "Api to add book to wishlist", response = WishlistResponse.class)
	private ResponseEntity<WishlistResponse> add(@RequestParam("bookId") int id, @RequestParam("userId") int userId)
			throws Exception {

		return wishlistService.AddWishlist(id, userId);

	}
	
	/*
	 * API to add book to wishlist with user
	 */
	@PostMapping("/userAdd")
	@ApiOperation(value = "Api to add book to wishlist with user", response = WishlistResponse.class)
	private ResponseEntity<WishlistResponse> userAdd(@RequestParam("bookId") int id, @RequestHeader String token)
			throws Exception {

		return wishlistService.AddWishlistWithUser(id, token);

	}
	
	/*
	 * API to get all books of wishlist
	 */
	@GetMapping("/allWishlistBooks")
	@ApiOperation(value = "Api to get all books added to wishlist", response = WishlistResponse.class)
	public ResponseEntity<WishlistResponse> getWishList(@RequestParam("userId") int userId) {
		
		return wishlistService.getWishlist(userId);
		
	}
	
	/*
	 * API to get all books of wishlist with user
	 */
	@GetMapping("/allUserWishlistBooks")
	@ApiOperation(value = "Api to get all books added to wishlist with user", response = WishlistResponse.class)
	public ResponseEntity<WishlistResponse> getWishList(@RequestHeader String token) {
		
		return wishlistService.getUserWishlist(token);
		
	}
	
	/*
	 * API to remove book from wishlist
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "Api to remove books from wishlist", response = WishlistResponse.class)
public ResponseEntity<WishlistResponse> remove(@RequestParam("userId") int userId, @RequestParam("bookId") int id) {
		
		return wishlistService.removeWishlist(userId, id);
		
	}
	
	
	/*
	 * API to remove book from wishlist with token
	 */
	@DeleteMapping("/userRemove")
	@ApiOperation(value = "Api to remove books from wishlist with token", response = WishlistResponse.class)
	public ResponseEntity<WishlistResponse> removeWishlist(@RequestParam("token") String token, @RequestParam("bookId") int id) {
		
		return wishlistService.removeFromWishlist(token, id);
		
	}

}
