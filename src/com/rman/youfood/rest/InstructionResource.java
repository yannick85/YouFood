package com.rman.youfood.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.rman.youfood.converter.JsonInstructionConverter;
import com.rman.youfood.dao.DaoFactory;
import com.rman.youfood.dao.InstructionDao;
import com.rman.youfood.dao.InstructionMenuDao;
import com.rman.youfood.entity.Instruction;

@Path("/instruction")
public class InstructionResource {

	@GET @Path("/one/{id}")
	public Instruction getInstruction(@PathParam("id") String id){
		InstructionDao instructionDao = DaoFactory.getInstance().getInstructionDao();
		return instructionDao.getInstructionById(Long.valueOf(id));
	}
	
	@GET @Path("/{id}")
	public List<Instruction> getInstructionForZone(@PathParam("id") String id){
		InstructionDao instructionDao = DaoFactory.getInstance().getInstructionDao();
		return instructionDao.getInstructionsForZone(Long.valueOf(id));
	}
	
	@POST
	public Response addInstruction(String jsonInstruction){
		try {
			JSONObject json = new JSONObject(jsonInstruction);
			Instruction instruction = JsonInstructionConverter.JsonToInstruction(json);
			InstructionDao instructionDao = DaoFactory.getInstance().getInstructionDao();
			InstructionMenuDao instructionMenuDao = DaoFactory.getInstance().getInstructionMenuDao();
			instructionDao.addInstruction(instruction);
			
			for (int i = 0; i < instruction.getMenus().size(); i++){
				instruction.getMenus().get(i).setInstruction(instruction);
				instructionMenuDao.addInstructionMenu(instruction.getMenus().get(i));
			}
		} catch (JSONException e) {
			return Response.serverError().build();
		}
		return Response.ok().build();
		//return Response.created(URI.create("/instruction/one/"+instruction.getId())).build();
	}
	
	@PUT @Path("/finish/{id}")
	public Response finishInstruction(@PathParam("id") String id){
		Long instructionId = Long.valueOf(id);
		
		InstructionDao instructionDao = DaoFactory.getInstance().getInstructionDao();
		Instruction i = instructionDao.getInstructionById(instructionId);
		if(i != null){
			i.setServed(true);
			if(!instructionDao.finishInstruction(i)){
				return Response.serverError().build();
			}
		} else {
			return Response.serverError().build();
		}
		return Response.ok().build();
	}
}
