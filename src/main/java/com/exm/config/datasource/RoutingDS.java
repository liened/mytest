package com.exm.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDS extends AbstractRoutingDataSource{
    @Override
    protected Object determineCurrentLookupKey() {
        return DSKeyHolder.get();
    }
}
