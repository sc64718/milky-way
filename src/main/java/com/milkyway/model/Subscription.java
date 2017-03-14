package com.milkyway.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Subscription {
	private long subscriptionId;
	private long userId;
	private long subscribedQty;
	private long tempModifiedQty;
	private String subscribedQtyUnit;
	private long subscribedItemId;
	private String subscribedItemName;
	private String subscribedItemUnit;

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
    
	public long getSubscriptionId() {
		return subscriptionId;
	}
	public void setSubscriptionId(long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getSubscribedItemId() {
		return subscribedItemId;
	}
	public void setSubscribedItemId(long subscribedItemId) {
		this.subscribedItemId = subscribedItemId;
	}
	public long getSubscribedQty() {
		return subscribedQty;
	}
	public void setSubscribedQty(long subscribedQty) {
		this.subscribedQty = subscribedQty;
	}
	public String getSubscribedQtyUnit() {
		return subscribedQtyUnit;
	}
	public void setSubscribedQtyUnit(String subscribedQtyUnit) {
		this.subscribedQtyUnit = subscribedQtyUnit;
	}

	public long getTempModifiedQty() {
		return tempModifiedQty;
	}
	public void setTempModifiedQty(long tempModifiedQty) {
		this.tempModifiedQty = tempModifiedQty;
	}
}
