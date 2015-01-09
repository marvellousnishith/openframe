/*
 * Copyright (C) 2015 Nisheeth Shah
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
package org.nisheeth.web.helper;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import org.nisheeth.ejb.session.AbstractFacade;

/**
 *
 * @author Nisheeth Shah
 */
public class EJBLookupHelper {

    private static final Logger logger = Logger.getLogger(EJBLookupHelper.class);

    public AbstractFacade lookupEntityFacadeBean(String beanName, String beanClass) {
        try {
            Context c = new InitialContext();
            return (AbstractFacade) c.lookup("java:global/TestEE/TestEE-ejb/" + beanName + "!" + beanClass);
        } catch (NamingException ne) {
            logger.error(ne.getMessage(), ne);
            return null;
        }
    }
}
