package com.rman.youfood.converter;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.rman.youfood.entity.InstructionMenu;

public class JsonInstructionMenuConverter {

	public static final String ID_FIELD = "id";
	public static final String STARTER_FIELD = "starter";
	public static final String PRINCIPAL_FIELD = "principal";
	public static final String DESERT_FIELD = "desert";
	

	
	public static InstructionMenu JsonToInstructionMenu(JSONObject json) throws JSONException {
		InstructionMenu menu = new InstructionMenu();
		menu.setStarter(json.optJSONObject(STARTER_FIELD) != null ? JsonProductConverter.JsonToProduct(json.getJSONObject(STARTER_FIELD)) : null);
		menu.setPrincipal(json.optJSONObject(PRINCIPAL_FIELD) != null ? JsonProductConverter.JsonToProduct(json.getJSONObject(PRINCIPAL_FIELD)) : null);
		menu.setDesert(json.optJSONObject(DESERT_FIELD) != null ? JsonProductConverter.JsonToProduct(json.getJSONObject(DESERT_FIELD)) : null);
		return menu;
	}
	
	public static List<InstructionMenu> JsonToInstructionMenuList(JSONArray json) throws JSONException {
		ArrayList<InstructionMenu> menus = new ArrayList<InstructionMenu>();
		for (int i = 0; i < json.length(); i++){
			menus.add(JsonInstructionMenuConverter.JsonToInstructionMenu(json.getJSONObject(i)));
		}
		return menus;
	}
}
