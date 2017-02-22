package com.milkyway.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class SubscribedItem {
	private long subscribedItemId;
	private String subscribedItemName;
	private String subscribedItemUnit;
	public long getSubscribedItemId() {
		return subscribedItemId;
	}
	
	public void setSubscribedItemId(long subscribedItemId) {
		this.subscribedItemId = subscribedItemId;
	}
	public String getSubscribedItemName() {
		return subscribedItemName;
	}
	public void setSubscribedItemName(String subscribedItemName) {
		this.subscribedItemName = subscribedItemName;
	}
	public String getSubscribedItemUnit() {
		return subscribedItemUnit;
	}
	public void setSubscribedItemUnit(String subscribedItemUnit) {
		this.subscribedItemUnit = subscribedItemUnit;
	}
}
