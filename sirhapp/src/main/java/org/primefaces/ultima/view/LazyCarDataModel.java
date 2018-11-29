/*
 * Copyright 2009-2011 Prime Technology.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.ultima.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.ultima.domain.Car;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * Dummy implementation of LazyDataModel that uses a list to mimic a real datasource like a database.
 */
public class LazyCarDataModel extends LazyDataModel<Car> {
    
    private List<Car> datasource;
    
    public LazyCarDataModel(List<Car> datasource) {
        this.datasource = datasource;
    }
    
    @Override
    public Car getRowData(String rowKey) {
        for(Car car : datasource) {
            if(car.getModel().equals(rowKey))
                return car;
        }

        return null;
    }

    @Override
    public Object getRowKey(Car car) {
        return car.getModel();
    }

    /**
     *
     * @param first
     * @param pageSize
     * @param sortField
     * @param sortOrder
     * @param filters
     * @return
     */
}