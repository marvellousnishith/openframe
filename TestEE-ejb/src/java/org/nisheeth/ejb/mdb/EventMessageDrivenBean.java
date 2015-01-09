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
package org.nisheeth.ejb.mdb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.nisheeth.ejb.entity.BaseEntity;

/**
 *
 * @author Nisheeth Shah
 */
@MessageDriven(mappedName = "jms/EventLogMessage", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class EventMessageDrivenBean implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;

    @PersistenceContext(unitName = "TestEE-ejbPU")
    private EntityManager em;

    public EventMessageDrivenBean() {
    }

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage;
        try {
            if (message instanceof ObjectMessage) {
                objectMessage = (ObjectMessage) message;
                BaseEntity entity = (BaseEntity) objectMessage.getObject();
                save(entity);
            }
        } catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        }
    }

    private void save(Object obj) {
        em.persist(obj);
    }

}
