package com.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookstore.dao.BookDaoImpl;
import com.bookstore.dao.WishlistDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Wishlist;
import com.bookstore.response.WishlistResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WishlistServiceImpl implements IWishlistService {
	
	@Autowired
	BookDaoImpl bookDao;
	
	@Autowired
	WishlistDao wishlistDao;
	
	/*********************************************************************
     * To add book to wishlist by the user.  
     * @param int id, int quantity
     * @return ResponseEntity<WishlistResponse>
     ********************************************************************/
	@Override
	public ResponseEntity<WishlistResponse> makeOrder(int id, int userId) {
		Book book = bookDao.getBookByBookId(id);
		Wishlist wishlist = new Wishlist();
		wishlist.setBookId(id);
		wishlist.setUserId(userId);
		wishlist.setBookName(book.getBookName());
		wishlist.setPrice(book.getPrice());
		wishlist.setBookImage(book.getBookImage());
		wishlistDao.save(wishlist);
		log.info("added to wishlist successfully");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new WishlistResponse(202, "Book Added to wishlist"));
	}

	/*********************************************************************
     * To get all books for wishlist by the user.  
     * @param int userId
     * @return ResponseEntity<WishlistResponse>
     ********************************************************************/
	@Override
	public ResponseEntity<WishlistResponse> getCartList(int userId) {
		Optional<List<Wishlist>> wishlists=null;
		wishlists = Optional.ofNullable(wishlistDao.getWishList(userId));
		if (wishlists.isPresent()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new WishlistResponse(202, "total books in cart " + wishlists.get().size(), wishlists.get()));
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new WishlistResponse(202, "No any Books Added to cart"));
		}
	}

}
