package com.rman.youfood.converter;

import java.util.Date;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.rman.youfood.dao.DaoFactory;
import com.rman.youfood.entity.Instruction;

public class JsonInstructionConverter {

	public static final String ID_FIELD = "id";
	public static final String DATE_FIELD = "creationDate";
	public static final String MENUS_FIELD = "menus";
	public static final String TABLE_FIELD = "table";
	public static final String SERVED_FIELD = "served";
	
	public static Instruction JsonToInstruction(JSONObject json) throws JSONException {
		Instruction instruction = new Instruction();
		instruction.setCreationDate(new Date());
		instruction.setMenus(JsonInstructionMenuConverter.JsonToInstructionMenuList(json.getJSONArray(MENUS_FIELD)));
		instruction.setTable(DaoFactory.getInstance().getTableDao().getTabulaById(json.getLong(TABLE_FIELD)));
		instruction.setServed(json.getBoolean(SERVED_FIELD));
		return instruction;
	}
}
