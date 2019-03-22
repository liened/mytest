package com.exm.config;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * @description 分库策略
 */
public class DemoDBShardingAlgorithm implements PreciseShardingAlgorithm<Long>{

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        for (String each:collection){
            if (each.endsWith(Long.parseLong(preciseShardingValue.getValue().toString()) %2+"")){
                return each;
            }
        }
        throw new IllegalArgumentException("分库策略异常");
    }
}
