package com.food.serviceImplementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.dto.FoodItemDto;
import com.food.dto.FoodItemDtoMapperService;
import com.food.entities.FoodItems;
import com.food.entities.ItemType;
import com.food.exceptions.EmptyFoodItemListException;
import com.food.exceptions.ItemNotFoundException;
import com.food.exceptions.NegativePriceException;
import com.food.exceptions.OfferPriceException;
import com.food.exceptions.QuantityNegativeException;
import com.food.repository.FoodListRepository;
import com.food.service.IFoodService;

@Service
public class FoodServiceImpl implements IFoodService {

	@Autowired
	FoodListRepository fooditemrepository;
	
	
	
	@Autowired(required = false)
	FoodItemDtoMapperService fidms;
	
	/*Adding a new item into menu list*/
	@Override
	public String addAnItem(FoodItems fooditem) {
		if (fooditem.getActualPrice() <0 || fooditem.getOfferPrice() < 0) {
			throw new NegativePriceException("Price Can't be negative");
//			message = new NegativePriceException("Price can't be negative or Zero").getMessage();
		} 
		else if(fooditem.getActualPrice()<fooditem.getOfferPrice()) {
			throw new OfferPriceException("Office price should be less than Actual price");
		}
		else if (fooditem.getQuantityAvailable() < 0) {
			throw new QuantityNegativeException("Quantity can't be negative or zero");
//			message =  new  QuantityNegativeException("Quantity can't be negative or zero").getMessage();
		}
		fooditemrepository.save(fooditem);
		return "Item Added Successfully";
	}
	
	/*Getting all food item list */
	@Override
	public List<FoodItemDto> allFoodItems() {
		
		List<FoodItems> foodItemList=fooditemrepository.findAll();
		
		List<FoodItemDto> foodItemDtoList=new ArrayList<FoodItemDto>();
		for(FoodItems i:foodItemList) {
			foodItemDtoList.add(fidms.convertFoodListToDto(i));
		}

		return foodItemDtoList;
	}
	
	/*Getting food item list by catageory*/
	@Override
	public List<FoodItems> getFoodItemByCategory(ItemType catageory) {
		System.out.println("hello");
		if(fooditemrepository.findByCategory(catageory).isEmpty()) {
			
			throw new EmptyFoodItemListException("No Item found with Selected Catageory");	
		}
		

		return (fooditemrepository.findByCategory(catageory));
	}
	/*Deleting an item by id*/
	@Override
	public String deleteFoodItem(int id) {
	
		
		if (!fooditemrepository.existsById(id)) {
//			message = "Item Not Found With Id " + id;
			throw new ItemNotFoundException("Item Not Found to delete");
		} 
			fooditemrepository.deleteById(id);
//			message = "Deleted Successfully";
		
		
		return "Deleted Successfully";
	}
	/*Updating an existing item*/
	@Override
	public String updateExistingItem(FoodItems foodItem) {
		
		
	
		if (!fooditemrepository.existsById(foodItem.getId())) {
//			message = "Item Not Found";
			throw new ItemNotFoundException("Item Not Found to Update");
		} 
		fooditemrepository.save(foodItem);
		return "Updated Successfully";	
	}
	
//	@Override
//	public List<FoodItemDto> foodItemsBydto1() {
//		System.out.println(fooditemrepository.findAll());
//
//		List<FoodItems> fd=fooditemrepository.findAll();
//		
//		List<FoodItemDto> fid=new ArrayList<FoodItemDto>();
//		for(FoodItems i:fd) {
//			fid.add(fidms.convertFoodListToDto(i));
//		}
//		return fid;
//	}

}
