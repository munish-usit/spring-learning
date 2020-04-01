package com.parkinglot.core;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.parkinglot.constant.WeekdayRate;
import com.parkinglot.constant.WeekendRate;

@Service
public class RateCalculator {

	public int calculateParkingRate(LocalDateTime entryTime, LocalDateTime exitTime) {
		int rate = -1;
		Duration duration = Duration.between(entryTime, exitTime);
		double hours = duration.getSeconds()/3600;
		DayOfWeek w = exitTime.getDayOfWeek();
		if(w.SATURDAY == DayOfWeek.SATURDAY || w.SUNDAY == DayOfWeek.SUNDAY) {
			rate = getWeekEndRate(hours);
		} else {
			rate = getWeekDayRate(hours);
		}
		return rate;
		
	}
	
	public int getWeekDayRate(double hours) {
		if(hours <2 ) return WeekdayRate.TWO_HOURS.getRate();
		else if(hours >2 && hours <=5 ) return WeekdayRate.FIVE_HOURS.getRate();
		else if(hours >5 && hours <=10 ) return WeekdayRate.TEN_HOURS.getRate();
		else if(hours >10 && hours <=15) return WeekdayRate.FIFTEEN_HOURS.getRate();
		else return WeekdayRate.TWENTYFOUR_HOURS.getRate();
	}
	
	public int getWeekEndRate(double hours) {
		if(hours <2 ) return WeekendRate.TWO_HOURS.getRate();
		else if(hours >2 && hours <=5 ) return WeekendRate.FIVE_HOURS.getRate();
		else if(hours >5 && hours <=10 ) return WeekendRate.TEN_HOURS.getRate();
		else if(hours >10 && hours <=15) return WeekendRate.FIFTEEN_HOURS.getRate();
		else return WeekendRate.TWENTYFOUR_HOURS.getRate();
	}
	
		
}
