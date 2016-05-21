package com.itexpertnepal.simpleinvoice.api.common;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author binay
 */
public interface CrudService<T extends Serializable, Pk extends Serializable> {

    /**
     * *
     * Add new record
     *
     * @param t
     * @return object if successfully done otherwise return null
     */
    public T addNew(T t);

    public void addAll(List<T> list);

    /**
     * *
     * Update record with given data
     *
     * @param t
     */
    public void update(T t);

    /**
     * *
     * remove given record
     *
     * @param t
     */
    public void remove(T t);

    /**
     * *
     * remove by given ID/Code .
     *
     * @param id which is must be unique
     */
    public void removeBy(Pk id);

    /**
     * *
     * Find record with given id
     *
     * @param id
     * @return record if found otherwise return null
     */
    public T find(Pk id);

    /**
     * *
     * Find all records
     *
     * @return
     */
    public List<T> findAll();

    /**
     * *
     * Find all record with paging
     *
     * @param pageSize
     * @param index
     * @return
     */
    public List<T> findWithPaging(int pageSize, int index);
}
