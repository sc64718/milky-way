package com.milkyway.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Subscription {
	private long subscriptionId;
	private long userId;
	private long subscribedItemId;
	private long subscribedQty;
	private long tempModifiedQty;
	private String subscribedQtyUnit;
    private SubscribedItem subscribedItem;
    
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
	public SubscribedItem getSubscribedItem() {
		return subscribedItem;
	}
	public void setSubscribedItem(SubscribedItem subscribedItem) {
		this.subscribedItem = subscribedItem;
	}
	public long getTempModifiedQty() {
		return tempModifiedQty;
	}
	public void setTempModifiedQty(long tempModifiedQty) {
		this.tempModifiedQty = tempModifiedQty;
	}
}
