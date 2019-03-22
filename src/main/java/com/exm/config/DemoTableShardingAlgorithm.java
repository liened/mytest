package com.exm.config;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * @description 分表策略，更分库的同
 * @author yyx
 * @version 1.0
 * @createDate 2019-03-20 11:48
 */
public class DemoTableShardingAlgorithm implements PreciseShardingAlgorithm<Long>{
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        for (String each:collection){
            if (each.endsWith(Long.parseLong(preciseShardingValue.getValue().toString()) %2+"")){
                return each;
            }
        }
        throw new IllegalArgumentException("分表策略错误");
    }
}
