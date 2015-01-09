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
package org.nisheeth.web.helper;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.Logger;
import org.nisheeth.ejb.entity.EventEntity;

/**
 *
 * @author Nisheeth Shah
 */
public class JMSHelper {

    private static final Logger logger = Logger.getLogger(JMSHelper.class);

    public void sendJMSMessageToEventMessage(EventEntity entity) throws JMSException, NamingException {
        Context c = new InitialContext();
        ConnectionFactory cf = (ConnectionFactory) c.lookup("java:comp/env/jms/EventLogMessageFactory");
        Connection conn = null;
        Session s = null;
        try {
            conn = cf.createConnection();
            s = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = (Destination) c.lookup("java:comp/env/jms/EventLogMessage");
            ObjectMessage objectMessage = s.createObjectMessage(entity);
            MessageProducer mp = s.createProducer(destination);
            mp.send(objectMessage);
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (JMSException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
