package com.pardot.service.tools.cobject.shardingstrategy;

import java.text.SimpleDateFormat;

/**
 * Pardot, An ExactTarget Company
 * User: robrighter
 * Date: 4/15/13
 */
public class ShardingStrategyNone extends TimebasedShardingStrategy {

	public ShardingStrategyNone(){
	}

	public long getShardKey(long timestamp){
		return this.offset + 1;
	}
}