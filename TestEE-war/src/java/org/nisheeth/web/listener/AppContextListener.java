/*
 * Copyright (C) 2014 Nisheeth Shah
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.nisheeth.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.Logger;
import org.nisheeth.util.ApplicationUtil;

/**
 *
 * @author Nisheeth Shah
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(AppContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationUtil.initLogger();
        logger.info("Context Initialized...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Context Destroyed...");
    }

}
