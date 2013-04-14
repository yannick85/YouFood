package com.rman.youfood.converter;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.rman.youfood.entity.Product;

public class JsonProductConverter {

	public static final String ID_FIELD = "id";
	public static final String TITLE_FIELD = "title";
	public static final String TYPE_FIELD = "type";
	public static final String PRICE_FIELD = "price";
	
	
	public static Product JsonToProduct(JSONObject json) throws JSONException {
		Product product = new Product();
		product.setId(json.getLong(ID_FIELD));
		product.setTitle(json.getString(TITLE_FIELD));
		product.setType(json.getInt(TYPE_FIELD));
		product.setPrice(Float.valueOf(json.getString(PRICE_FIELD)));
		
		return product;
	}
}
