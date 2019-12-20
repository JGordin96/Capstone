package com.techelevator.model;

import java.util.List;

public interface RestaurantDAO {
	
	//"Like" method- adds to users_favorites
	void addFavoriteRestaurant(int id, String resId);
	//"Unmatch" method- removes a restaurant from users's favorites
	void removeFavoriteRestaurant(User user, String resId);
	//removes restaurant from restaurant database
	void dislikeRestaurant(String resId);
	//writes all matches to the database
	void addAllMatchesToDatabase(List<Restaurant> matches);
	//called when a user is done matching- will delete all from "matches"
	void removeAllMatchesFromDatabase ();
	
	Restaurant getRestaurantByResId(String resId);
	List<Restaurant> displayFavorites(User user);

	

	
	

}
