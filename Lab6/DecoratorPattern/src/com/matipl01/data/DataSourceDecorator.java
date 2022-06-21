package com.matipl01.data;

abstract public class DataSourceDecorator implements DataSource {
    protected final DataSource wrapper;

    public DataSourceDecorator(DataSource wrapper) {
        this.wrapper = wrapper;
    }
}
