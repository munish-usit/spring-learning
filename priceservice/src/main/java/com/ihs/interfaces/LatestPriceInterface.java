package com.ihs.interfaces;

import com.ihs.model.Price;

public interface LatestPriceInterface {

	public Price getLatestPrice(String iscn);
}
