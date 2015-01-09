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
package org.nisheeth.util;

import java.io.IOException;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

/**
 *
 * @author Nisheeth Shah
 */
public class ApplicationUtil {

    private static final Logger logger = Logger.getLogger(ApplicationUtil.class);

    public static void initLogger() {
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.ALL);
        PatternLayout layout = new PatternLayout("%d{ISO8601} [%t] %-5p %c %x - %m%n");
        rootLogger.addAppender(new ConsoleAppender(layout));
        try {
            RollingFileAppender fileAppender = new RollingFileAppender(layout, "application.log");
            rootLogger.addAppender(fileAppender);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("Logger initialized");
    }
}
