package com.techelevator.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.Preference;
import com.techelevator.model.PreferenceDAO;
import com.techelevator.model.Restaurant;
import com.techelevator.model.RestaurantDAO;
import com.techelevator.model.User;
import com.techelevator.model.UserDAO;

///capstone/users/match/jackiebob (DELETE)

@Controller
public class APIController {
	
	@Autowired
	UserDAO userDAO;
	@Autowired
	PreferenceDAO preferenceDAO;
	@Autowired
	RestaurantDAO restaurantDAO;
	
	@RequestMapping (path= "users/match/{userName}", method=RequestMethod.GET)
	public String testAPI(@PathVariable String userName, HttpServletRequest request, ModelMap map) throws JsonProcessingException, IOException {
	String keyValue = "95f8a2b561b6846d7b9beeb93b70d576";
	User user = userDAO.getUserByUserName(userName);
	String apiUserAddress = user.getAddress().replace(" ", "%20");
	List<Preference>prefList = preferenceDAO.getUserPreferences(user.getId());
	String apiPrefs = user.getPrefsForAPI(prefList);
	String requestAPI = "https://developers.zomato.com/api/v2.1/search?q=" +apiPrefs + "&start=0&count=100&radius=" +
			user.getMilerangeForAPI(user.getMilerange()) + "&apikey=" + keyValue + "&address=" + apiUserAddress;
	HttpEntity<String> httpEntity = new HttpEntity<>("");
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<String> response = restTemplate.exchange(requestAPI, HttpMethod.GET, httpEntity, String.class);
	ObjectMapper objectMapper = new ObjectMapper();
	JsonNode jsonNode = objectMapper.readTree(response.getBody());

	    List<Restaurant> restaurants = new ArrayList<>();
	    for (int i=0; i < jsonNode.path("restaurants").size(); i++) {
	        Restaurant restaurant = new Restaurant();
	        restaurant.setResId(jsonNode.path("restaurants").path(i).path("restaurant").path("R").path("res_id").asText());
	        restaurant.setName(jsonNode.path("restaurants").path(i).path("restaurant").path("name").asText());
	        restaurant.setCuisine(jsonNode.path("restaurants").path(i).path("restaurant").path("cuisines").asText());
	        if(jsonNode.path("restaurants").path(i).path("restaurant").path("photos").path(0).path("photo").path("url").asText().contentEquals("")) {
	        	
	        	restaurant.setPhotoUrl("http://localhost:8080/capstone/img/noimg.png");
	        }
	        else {
	        	restaurant.setPhotoUrl(jsonNode.path("restaurants").path(i).path("restaurant").path("photos").path(0).path("photo").path("url").asText());
	        }
	        restaurant.setDisplay(false);
	        restaurant.setAddress(jsonNode.path("restaurants").path(i).path("restaurant").path("location").path("address").asText());
	        restaurant.setLatitude(jsonNode.path("restaurants").path(i).path("restaurant").path("location").path("latitude").asText());
	        restaurant.setLongitude(jsonNode.path("restaurants").path(i).path("restaurant").path("location").path("longitude").asText());
	        restaurants.add(restaurant);
	    }
	    	restaurantDAO.addAllMatchesToDatabase(restaurants);
	        request.setAttribute("match", restaurants);
	        map.put("user", userDAO.getUserByUserName(userName));
System.out.println("i am in the get.");
	    
	    return "match";
	    
	}
	
	@RequestMapping(path = "users/match/{userName}", method = RequestMethod.POST)
	public String addToFavorites(@PathVariable String userName, @RequestParam String resId){
		restaurantDAO.addFavoriteRestaurant(userDAO.getUserIdWithName(userName), resId);
		return "favorites";
	}
	
	@RequestMapping(path="users/match/{userName}", method = RequestMethod.DELETE)
	public String dislike (@PathVariable String userName, @RequestParam String resId) {
		restaurantDAO.dislikeRestaurant(resId);
		return"favorites";
	}
	
	@RequestMapping (path= "/restaurantDetails/{resId}/{userName}", method = RequestMethod.POST)
	public String addLikeFromDetails(@PathVariable String userName, @PathVariable String resId) {
		restaurantDAO.addFavoriteRestaurant(userDAO.getUserIdWithName(userName), resId);
		return "favorites";
	}
	
	@RequestMapping (path= "/restaurantDetails/{resId}/{userName}")
	public String getRestaurantDetails(@PathVariable String resId, @PathVariable String userName, HttpServletRequest request, ModelMap map) throws JsonProcessingException, IOException {
		String apiURL = "https://developers.zomato.com/api/v2.1/restaurant?res_id=";
		String apiKey = "95f8a2b561b6846d7b9beeb93b70d576";
		String requestAPI = apiURL + resId + "&apikey=" + apiKey;
		HttpEntity<String> httpEntity = new HttpEntity<>("");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(requestAPI, HttpMethod.GET, httpEntity, String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(response.getBody());
		
		Restaurant restaurant = new Restaurant();
		restaurant.setResId(jsonNode.path("R").path("res_id").asText());
		restaurant.setName(jsonNode.path("name").asText());
		if(jsonNode.path("R").path("photos_url").asText().contentEquals("")) {
			restaurant.setPhotoUrl("http://localhost:8080/capstone/img/noimg.png");
		}
		else {
			restaurant.setPhotoUrl(jsonNode.path("photos").path(0).path("photo").path("url").asText());
		}
	
		restaurant.setPhoneNumber(jsonNode.path("phone_numbers").asText());
		restaurant.setCuisine(jsonNode.path("cuisines").asText());
		restaurant.setHours(jsonNode.path("timings").asText());
		restaurant.setRating(jsonNode.path("user_rating").path("aggregate_rating").asText());
		restaurant.setAddress(jsonNode.path("location").path("address").asText());
		restaurant.setLatitude(jsonNode.path("location").path("latitude").asText());
		restaurant.setLongitude(jsonNode.path("location").path("longitude").asText());
		restaurant.setRatingText(jsonNode.path("user_rating").path("rating_text").asText());
		restaurant.setCostForTwo(jsonNode.path("average_cost_for_two").asText());
		request.setAttribute("details", restaurant);
		map.put("user",userDAO.getUserByUserName(userName));
		return"details";
	}
	
	@RequestMapping (path= "/users/favorites/{userName}", method=RequestMethod.POST)
		public String displayUserFavorites(@PathVariable String userName, ModelMap map) {
		map.put("favorites", restaurantDAO.displayFavorites(userDAO.getUserByUserName(userName)));
		map.put("user",userDAO.getUserByUserName(userName));
		return "favorites";
		
	}
	
	@RequestMapping (path= "/users/favorites/{userName}", method=RequestMethod.DELETE)
	public String dislike(@PathVariable String userName, ModelMap map) {
	System.out.println("in the delete.");
//	map.put("favorites", restaurantDAO.displayFavorites(userDAO.getUserByUserName(userName)));
//	map.put("user",userDAO.getUserByUserName(userName));
	return "favorites";
	
}
	

	
	
	

}
