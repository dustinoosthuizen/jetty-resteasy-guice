package com.webward.modules;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.webward.resources.ItemResource;

/**
 * Created by dustinosthzn on 2014/11/29.
 */
public class ResourcesModule implements Module{

    @Override
    public void configure(Binder binder) {
        binder.bind(ItemResource.class);



    }
}
