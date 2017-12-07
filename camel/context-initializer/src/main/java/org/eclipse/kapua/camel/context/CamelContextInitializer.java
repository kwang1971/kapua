/*******************************************************************************
 * Copyright (c) 2017 Eurotech and/or its affiliates and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eurotech - initial API and implementation
 *******************************************************************************/
package org.eclipse.kapua.camel.context;

import org.apache.camel.CamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class CamelContextInitializer {

    private static final Logger logger = LoggerFactory.getLogger(CamelContextInitializer.class);

    private CamelContextInitializer() {
    }

    public static void initializeCamelContext(String configurationFile) {
        logger.info("Starting camel context");
        // ApplicationContext appContext = new ClassPathXmlApplicationContext(configurationFile.toString());
        ApplicationContext appContext = new FileSystemXmlApplicationContext(configurationFile);
        logger.info("Starting camel context... initializing");
        CamelContext camelContext = appContext.getBean(CamelContext.class);
        logger.info("Starting camel context... initializing DONE");
        try {
            logger.info("Starting camel context... starting");
            camelContext.start();
            logger.info("Starting camel context... staring DONE");
            Thread.currentThread().join();
        } catch (Exception e) {
            logger.error("Cannot start camel context {}", e.getMessage(), e);
        }
    }

}
