package com.rman.youfood.servlet;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.LoopTagStatus;

import com.rman.youfood.dao.DaoFactory;
import com.rman.youfood.dao.InstructionDao;
import com.rman.youfood.dao.ProductDao;
import com.rman.youfood.dao.RestaurantDao;
import com.rman.youfood.entity.Instruction;
import com.rman.youfood.entity.InstructionMenu;
import com.rman.youfood.entity.Product;
import com.rman.youfood.entity.Restaurant;


public class StatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InstructionDao instructionDao = DaoFactory.getInstance().getInstructionDao();
		RestaurantDao restaurantDao = DaoFactory.getInstance().getRestaurantDao();
		ProductDao productDao = DaoFactory.getInstance().getProductDao();
		List<Restaurant> restaurants = restaurantDao.getAllRestaurant();
		
		Integer numberInstruction = 0;
		Integer numberInstructionMenu = 0;
		Hashtable<Long, Integer> numberInstructionByRestaurant = new Hashtable<Long,Integer>();
		Hashtable<Long, List<Instruction>> instructionsByRestaurant = new Hashtable<Long,List<Instruction>>();
		Integer thisNumberInstructionMenu = null;
		Hashtable<Long, Integer> numberInstructionMenuByRestaurant = new Hashtable<Long,Integer>();
		
		List<Instruction> thisListInstruction = null;		
			
		Hashtable<Long, Integer> popularityProduct = new Hashtable<Long,Integer>();
		List<Product> bestStarter = productDao.getProductByType(0);
		List<Product> bestPrincipal = productDao.getProductByType(1);
		List<Product> bestDesert = productDao.getProductByType(2);
		
		Hashtable<Long, Hashtable<Long, Integer>>  popularityProductByRestaurant = new Hashtable<Long, Hashtable<Long, Integer>>();
		Hashtable<Long, List<Product>>  bestStarterByRestaurant = new Hashtable<Long, List<Product>>();
		Hashtable<Long, List<Product>>  bestPrincipalByRestaurant = new Hashtable<Long, List<Product>>();
		Hashtable<Long, List<Product>>  bestDesertByRestaurant = new Hashtable<Long, List<Product>>();
		
		Hashtable<Long, Integer> thisPopularityProduct = null;	
		List<Product> thisBestStarter = null;
		List<Product> thisBestPrincipal = null;
		List<Product> thisBestDesert = null;
		
		/*init hashtable product popularity*/
		for (Product product : productDao.getAllProduct()) {
			popularityProduct.put(product.getId(), 0);
		}
		
		
		for (Restaurant restaurant : restaurants) {
			thisNumberInstructionMenu = 0;
			thisListInstruction = instructionDao.getInstructionsByRestaurant(restaurant.getId());
			thisPopularityProduct = new Hashtable<Long,Integer>();
			for (Product product : productDao.getAllProduct()) {
				thisPopularityProduct.put(product.getId(), 0);
			}
			thisBestStarter = productDao.getProductByType(0);
			thisBestPrincipal = productDao.getProductByType(1);
			thisBestDesert = productDao.getProductByType(2);
			instructionsByRestaurant.put(restaurant.getId(),thisListInstruction);
			numberInstructionByRestaurant.put(restaurant.getId(), thisListInstruction.size());
			/*product popularity completion*/
			for (Instruction instruction : thisListInstruction) {
				numberInstruction ++;
				for(InstructionMenu instructionMenu :instruction.getMenus()){
					numberInstructionMenu ++;
					thisNumberInstructionMenu ++;
						if(instructionMenu.getStarter() != null){
							popularityProduct.put(instructionMenu.getStarter().getId(), popularityProduct.get(instructionMenu.getStarter().getId())+1);
							thisPopularityProduct.put(instructionMenu.getStarter().getId(), thisPopularityProduct.get(instructionMenu.getStarter().getId())+1);
						}
						if(instructionMenu.getPrincipal() != null){
							popularityProduct.put(instructionMenu.getPrincipal().getId(), popularityProduct.get(instructionMenu.getPrincipal().getId())+1);
							thisPopularityProduct.put(instructionMenu.getPrincipal().getId(), thisPopularityProduct.get(instructionMenu.getPrincipal().getId())+1);
						}
						if(instructionMenu.getDesert() != null){
							popularityProduct.put(instructionMenu.getDesert().getId(), popularityProduct.get(instructionMenu.getDesert().getId())+1);
							thisPopularityProduct.put(instructionMenu.getDesert().getId(), thisPopularityProduct.get(instructionMenu.getDesert().getId())+1);
						}
				}
			}
			Boolean thisSwapped = true;
			while (thisSwapped == true) {
				thisSwapped = false;
				for (int i = 0; i < (thisBestStarter.size()-1); i++) {
					if( thisPopularityProduct.get(thisBestStarter.get(i).getId()) <  thisPopularityProduct.get(thisBestStarter.get(i+1).getId())){
						Product toChange = thisBestStarter.get(i+1);
						thisBestStarter.set(i+1, thisBestStarter.get(i));
						thisBestStarter.set(i, toChange);
						thisSwapped=true;
					}				
				}
			}
			thisSwapped = true;
			while (thisSwapped == true) {
				thisSwapped = false;
				for (int i = 0; i < (thisBestPrincipal.size()-1); i++) {
					if( thisPopularityProduct.get(thisBestPrincipal.get(i).getId()) <  thisPopularityProduct.get(thisBestPrincipal.get(i+1).getId())){
						Product toChange = thisBestPrincipal.get(i+1);
						thisBestPrincipal.set(i+1, thisBestPrincipal.get(i));
						thisBestPrincipal.set(i, toChange);
						thisSwapped=true;
					}				
				}
			}
			thisSwapped = true;
			while (thisSwapped == true) {
				thisSwapped = false;
				for (int i = 0; i < (thisBestDesert.size()-1); i++) {
					if( thisPopularityProduct.get(thisBestDesert.get(i).getId()) <  thisPopularityProduct.get(thisBestDesert.get(i+1).getId())){
						Product toChange = thisBestDesert.get(i+1);
						thisBestDesert.set(i+1, thisBestDesert.get(i));
						thisBestDesert.set(i, toChange);
						thisSwapped=true;
					}				
				}
			}
			popularityProductByRestaurant.put(restaurant.getId(), thisPopularityProduct);
			bestStarterByRestaurant.put(restaurant.getId(), thisBestStarter);
			bestPrincipalByRestaurant.put(restaurant.getId(), thisBestPrincipal);
			bestDesertByRestaurant.put(restaurant.getId(), thisBestDesert);
			numberInstructionMenuByRestaurant.put(restaurant.getId(), thisNumberInstructionMenu);
		}
		/*tri a bulle*/
		Boolean swapped = true;
		while (swapped == true) {
			swapped = false;
			for (int i = 0; i < (bestStarter.size()-1); i++) {
				if( popularityProduct.get(bestStarter.get(i).getId()) <  popularityProduct.get(bestStarter.get(i+1).getId())){
					Product toChange = bestStarter.get(i+1);
					bestStarter.set(i+1, bestStarter.get(i));
					bestStarter.set(i, toChange);
					swapped=true;
				}				
			}
		}
		swapped = true;
		while (swapped == true) {
			swapped = false;
			for (int i = 0; i < (bestPrincipal.size()-1); i++) {
				if( popularityProduct.get(bestPrincipal.get(i).getId()) <  popularityProduct.get(bestPrincipal.get(i+1).getId())){
					Product toChange = bestPrincipal.get(i+1);
					bestPrincipal.set(i+1, bestPrincipal.get(i));
					bestPrincipal.set(i, toChange);
					swapped=true;
				}				
			}
		}
		swapped = true;
		while (swapped == true) {
			swapped = false;
			for (int i = 0; i < (bestDesert.size()-1); i++) {
				if( popularityProduct.get(bestDesert.get(i).getId()) <  popularityProduct.get(bestDesert.get(i+1).getId())){
					Product toChange = bestDesert.get(i+1);
					bestDesert.set(i+1, bestDesert.get(i));
					bestDesert.set(i, toChange);
					swapped=true;
				}				
			}
		}
		
		request.setAttribute("bestStarterByRestaurant", bestStarterByRestaurant);
		request.setAttribute("bestPrincipalByRestaurant", bestPrincipalByRestaurant);
		request.setAttribute("bestDesertByRestaurant", bestDesertByRestaurant);
		request.setAttribute("bestStarter", bestStarter);
		request.setAttribute("bestPrincipal", bestPrincipal);
		request.setAttribute("bestDesert", bestDesert);
		request.setAttribute("popularityProduct", popularityProduct);
		request.setAttribute("popularityProductByRestaurant", popularityProductByRestaurant);
		
		request.setAttribute("restaurants", restaurants);
		request.setAttribute("numberInstructionByRestaurant", numberInstructionByRestaurant);
		request.setAttribute("numberInstructionMenuByRestaurant", numberInstructionMenuByRestaurant);
		request.setAttribute("numberInstruction", numberInstruction);
		request.setAttribute("numberInstructionMenu", numberInstructionMenu);
		//request.setAttribute("instructionsByRestaurant", instructionsByRestaurant);
		request.getRequestDispatcher("/WEB-INF/stat.jsp").forward(request, response);
	}
}
