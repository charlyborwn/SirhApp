package com.hrm.sirhapp.service;

import com.hrm.sirhapp.model.Event43;
import com.hrm.sirhapp.service.exception.Event43AlreadyExists;
import com.hrm.sirhapp.service.exception.Event43NotFound;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Carlos Bernal carlose.bernalh@gmail.com
 * Copyright 2018 Carlos Ernesto Bernal Hernández.
 * SirhApp
 * 
 */
@Local
public interface Event43ManagerLocal {

    /**
     *
     * @param nueve
     * @return
     * @throws Event43NotFound
     */
    public Event43 getEvent43(Integer nueve) throws Event43NotFound;

    /**
     *
     * @return
     */
    public List<Event43> getListEvent43();

    /**
     *
     * @return
     */
    public List<Event43> retrieveEvent43();

    /**
     *
     * @param event43
     * @return
     * @throws Event43AlreadyExists
     */
    public Event43 registerEvent43(Event43 event43) throws Event43AlreadyExists;

    /**
     *
     * @param event43
     * @throws Event43NotFound
     */
    public void removeEvent43(Event43 event43) throws Event43NotFound;

    /**
     *
     * @param event43
     * @throws Event43NotFound
     */
    public void deleteEvent43(Event43 event43) throws Event43NotFound;

    /**
     *
     * @param event43
     * @throws Event43NotFound
     */
    public void updateEvent43(Event43 event43) throws Event43NotFound;
}
