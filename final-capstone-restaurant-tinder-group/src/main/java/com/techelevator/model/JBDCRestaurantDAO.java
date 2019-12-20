package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JBDCRestaurantDAO implements RestaurantDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JBDCRestaurantDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void addFavoriteRestaurant(int id, String resId) {
		
		resId = resId.charAt(0) == ' '? resId.substring(1) : resId;
		
		String sql = "INSERT INTO users_favorites (user_id, res_id) VALUES (?,?)";
		jdbcTemplate.update(sql, id, resId);
	}

	@Override
	public void removeFavoriteRestaurant(User user, String resId) {
		
		resId = resId.charAt(0) == ' '? resId.substring(1) : resId;
		String sql = "DELETE FROM users_favorites WHERE user_id = ? AND res_id = ?";
		jdbcTemplate.update(sql, user.getId(), resId);
		
	}

	@Override
	public void addAllMatchesToDatabase(List<Restaurant> matches) {
		for (Restaurant r : matches) {
			String sql = "INSERT INTO restaurants (res_id, res_name, photo_url, cuisines, display, address, latitude, longitude ) VALUES (?,?,?,?,?,?,?,?)";
			jdbcTemplate.update(sql, r.getResId(), r.getName(), r.getPhotoUrl(), r.getCuisine(), r.isDisplay(), r.getAddress(), r.getLatitude(), r.getLongitude());
		}
		
	}

	@Override
	public void removeAllMatchesFromDatabase() {
			String sql = "DELETE FROM restaurants";
			jdbcTemplate.update(sql);
		
	}


	@Override
	public void dislikeRestaurant(String resId) {
		
		resId = resId.charAt(0) == ' '? resId.substring(1) : resId;
		String sql = "DELETE FROM restaurants WHERE res_id = ?";
		jdbcTemplate.update(sql, resId);
		
	}

	@Override
	public Restaurant getRestaurantByResId(String resId) {
		
		resId = resId.charAt(0) == ' '? resId.substring(1) : resId;
		String sql = "SELECT * FROM restaurants WHERE res_id = ?";
		SqlRowSet restaurant = jdbcTemplate.queryForRowSet(sql, resId); 
		Restaurant thisRestaurant = new Restaurant();
		if(restaurant.next()) {
			thisRestaurant.setName(restaurant.getString("res_name"));
			thisRestaurant.setResId(restaurant.getString("res_id"));
			thisRestaurant.setPhotoUrl(restaurant.getString("photo_url"));
			thisRestaurant.setCuisine(restaurant.getString("cuisines"));
		}
		return thisRestaurant;
	}

	@Override
	public List<Restaurant> displayFavorites(User user) {
		List<Restaurant> favorites = new ArrayList<>();
		String sql = "SELECT * FROM restaurants A INNER JOIN users_favorites B ON A.res_id = B.res_id WHERE B.user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user.getId());
		while (results.next()) {
			Restaurant r = new Restaurant();
			r.setResId(results.getString("res_id").trim());
			r.setName(results.getString("res_name"));
			r.setPhotoUrl(results.getString("photo_url"));
			r.setCuisine(results.getString("cuisines"));
			r.setAddress(results.getString("address"));
			r.setLongitude(results.getString("longitude"));
			r.setLatitude(results.getString("latitude"));
			favorites.add(r);
		}
		return favorites;
	}


}
