/*
 * Copyright 2009-2014 PrimeTek.
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
package org.primefaces.ultima.view.data.datatable;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.ultima.domain.Car;
import org.primefaces.ultima.service.CarService;

@Named("dtContextMenuView")
@ViewScoped
public class ContextMenuView implements Serializable {
    
    private List<Car> cars;
    
    private Car selectedCar;
    
    @Inject
    private CarService carService;

    @PostConstruct
    public void init() {
        cars = carService.createCars(10);
    }
    
    public List<Car> getCars() {
        return cars;
    }

    public Car getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }

    public void setService(CarService service) {
        this.carService = service;
    }
    
    public void deleteCar() {
        cars.remove(selectedCar);
        selectedCar = null;
    }
}
