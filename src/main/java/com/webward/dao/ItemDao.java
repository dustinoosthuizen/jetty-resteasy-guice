package com.webward.dao;

import com.webward.entities.Item;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by dustinosthzn on 2014/11/29.
 */
public class ItemDao {

    @Inject
    EntityManager entityManager;

    public Item findById(int id)
    {
        return first(entityManager.createNamedQuery("Item.findById").setParameter("id",id).getResultList());

    }

    public Item findByName(String aName)
    {
        return first(entityManager.createNamedQuery("Item.findByName").setParameter("name",aName).getResultList());
    }

    public void save(Item anItem)
    {
        entityManager.persist(anItem);

    }

    public void deleteById(int id)
    {
        entityManager.createNamedQuery("Item.deleteById").setParameter("id",new Integer(id)).executeUpdate();

    }

    public Item first(List<Item> items)
    {
        if(items.size()>0)
        {
            return items.get(0);

        }else
        {
            return null;

        }

    }

}
