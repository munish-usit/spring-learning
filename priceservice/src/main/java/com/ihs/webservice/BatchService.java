package com.ihs.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Logger;

import com.ihs.constant.BatchStatus;
import com.ihs.constant.ResponseStatus;
import com.ihs.core.PriceProducerTask;
import com.ihs.db.GlobalMemory;
import com.ihs.utils.AppLogger;

@Path("/batch")
@Produces(MediaType.TEXT_PLAIN)
public class BatchService {
	
	private static final Logger logger = AppLogger.getMainLogInstance(BatchService.class);
	
	@POST
	@Path("/start/{id}/{path}")
	public Response startBatchTask(@PathParam("id") int id,@PathParam("path")String filePath) {
		try {
			logger.info("request to start producer with batch {} ",id);
			if(GlobalMemory.getGlobalMemory().getTaskMap().containsKey(id)) {
				logger.error("batch {} already exist in the system ",id);
				return Response.status(Status.BAD_REQUEST).entity(ResponseStatus.DUPLICATE_BATCH_ID.toString()).build();
			}
			PriceProducerTask task = new PriceProducerTask(id,filePath);
			GlobalMemory.getGlobalMemory().getTaskMap().put(id,task);
			new Thread(task).start();
			logger.info("batch {} successfully started",id);
			return Response.ok().entity(ResponseStatus.BATCH_START_SUCCESSFULL.toString()).build();

		} catch (Exception e) {
			logger.error("Not able to start batch id {} {} ",id,e);
			return Response.status(Status.BAD_REQUEST).entity(ResponseStatus.BATCH_START_FAILED.toString()).build();
		}
	}
	
	@POST
	@Path("/stop/{id}")
	public Response stopBatchTask(@PathParam("id")int id) {
		try {
			logger.info("request to stop producer with batch {} ",id);
			if(!GlobalMemory.getGlobalMemory().getTaskMap().containsKey(id)) {
				logger.error("batch {} doesn't exist in the system ",id);
				return Response.status(Status.NOT_FOUND).entity(ResponseStatus.BATCH_NOT_FOUND.toString()).build();
			}
			PriceProducerTask task = (PriceProducerTask) GlobalMemory.getGlobalMemory().getTaskMap().get(id);
			if(task.getStatus().equals(BatchStatus.COMPLETED)) {
				logger.error("batch {} task already completed",id);
				return Response.status(Status.BAD_REQUEST).entity(ResponseStatus.TASK_ALREADY_COMPLETED.toString()).build();
			}
			task.setStatus(BatchStatus.CANCELLED);
			logger.info("batch {} successfully stopped",id);
			return Response.ok().entity(ResponseStatus.BATCH_STOP_SUCCESSFULL.toString()).build();
		} catch (Exception e) {
			logger.error("Not able to stop batch id {} {} ",id,e);
			return Response.status(Status.BAD_REQUEST).entity(ResponseStatus.BATCH_STOP_FAILED.toString()).build();
		}
	}
	
	@GET
	@Path("/ping")
	public Response ping() {
		logger.trace("test batch service ping");
		return Response.ok().entity("pong").build();
	}


}
