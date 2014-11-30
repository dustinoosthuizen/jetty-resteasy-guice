package com.webward.modules;

import com.webward.jaxrs.ClientErrorExceptionMapper;
//import com.webward.jaxrs.GsonMessageBodyHandler;
import org.jboss.resteasy.plugins.guice.ext.RequestScopeModule;

/**
 * Created by dustinosthzn on 2014/11/30.
 */
public class MainModule extends RequestScopeModule {


    @Override
    protected void configure() {
        super.configure();
//        bind(GsonMessageBodyHandler.class);
        bind(ClientErrorExceptionMapper.class);
    }
}
