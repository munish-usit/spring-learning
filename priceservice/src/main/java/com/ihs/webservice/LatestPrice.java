package com.ihs.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Logger;

import com.ihs.constant.ResponseStatus;
import com.ihs.db.GlobalMemory;
import com.ihs.model.Instrument;
import com.ihs.model.Price;
import com.ihs.utils.AppLogger;

@Path("/price")
@Produces(MediaType.TEXT_PLAIN)
public class LatestPrice {

	private static final Logger logger = AppLogger.getMainLogInstance(LatestPrice.class);
	
	@GET
	@Path("/latestprice/{isin}")
	public Response getLatestPrice(@PathParam("isin") String isin) {
		try {
			logger.info("request to fetch price for instrument id {} ",isin);
			if(GlobalMemory.getGlobalMemory().getInstrumentPriceMap().isEmpty()) {
				logger.error("instrument {} doesn't exist in the system ",isin);
				return Response.status(Status.NOT_FOUND).entity(ResponseStatus.INSTRUMENT_NOT_FOUND.toString()).build();
				
			}
			if(!GlobalMemory.getGlobalMemory().getInstrumentPriceMap().containsKey(isin)) {
				logger.error("instrument {} doesn't exist in the system ",isin);
				return Response.status(Status.NOT_FOUND).entity(ResponseStatus.INSTRUMENT_NOT_FOUND.toString()).build();
			}
			Instrument instrument = GlobalMemory.getGlobalMemory().getInstrumentPriceMap().get(isin);
			Price price = instrument.getPrice();
			logger.info("instrument {} latest price {} on date {}",isin,price.toString(),instrument.getDate().toString());
			return Response.ok().entity(price.toString()).build();
		} catch (Exception e) {
			logger.error("Not able to fetch latest price for isin {} {} ",isin,e);
			return Response.status(Status.BAD_REQUEST).entity(ResponseStatus.LATESTPRICE_FAILED.toString()).build();
			
		}
		
	}
	
	@GET
	@Path("/ping")
	public Response ping() {
		
		return Response.ok().entity("pong").build();
	}


}
