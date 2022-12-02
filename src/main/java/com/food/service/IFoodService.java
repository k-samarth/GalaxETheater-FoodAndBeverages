package com.food.service;

import java.util.List;

import com.food.dto.FoodItemDto;
import com.food.entities.FoodItems;
import com.food.entities.ItemType;


public interface IFoodService {

	public String addAnItem(FoodItems fooditem);
	public List<FoodItemDto> allFoodItems();
	public String deleteFoodItem(int id);
	public String updateExistingItem(FoodItems foodItem);
	List<FoodItems> getFoodItemByCategory(ItemType catageory);
//	public List<FoodItemDto> foodItemsBydto1();

}
