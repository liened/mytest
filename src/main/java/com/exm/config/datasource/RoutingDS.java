package com.exm.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author yyx
 * @version 1.0
 * @createDate 2018-12-12 15:40
 * @description
 */
public class RoutingDS extends AbstractRoutingDataSource{
    @Override
    protected Object determineCurrentLookupKey() {
        return DSKeyHolder.get();
    }
}
