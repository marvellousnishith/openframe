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

import javax.persistence.metamodel.SingularAttribute;
import org.nisheeth.constant.ColumnConstant;
import org.nisheeth.constant.DataSourceConstant;
import org.nisheeth.ejb.entity.EventEntity_;
import org.nisheeth.ejb.entity.UserEntity_;

/**
 *
 * @author Nisheeth Shah
 */
public class EntityColumnHelper {

    public SingularAttribute getColumnAttributeById(int dsId, int columnId) {
        switch (dsId) {
            case DataSourceConstant.GET_ENTITY_LOG_LIST: {
                switch (columnId) {
                    case ColumnConstant.EVENT_ENTITY_ID:
                        return EventEntity_.id;
                    case ColumnConstant.EVENT_ENTITY_AUTH_TYPE:
                        return EventEntity_.authType;
                    case ColumnConstant.EVENT_ENTITY_REMOTE_ADDR:
                        return EventEntity_.remoteAddr;
                    case ColumnConstant.EVENT_ENTITY_REMOTE_HOST_NAME:
                        return EventEntity_.remoteHostName;
                    case ColumnConstant.EVENT_ENTITY_REQUEST_URL:
                        return EventEntity_.requestURL;
                    case ColumnConstant.EVENT_ENTITY_USER_AGENT:
                        return EventEntity_.userAgent;
                    default:
                        return EventEntity_.requestTime;
                }
            }
            case DataSourceConstant.GET_USER_LIST: {
                switch (columnId) {
                    case ColumnConstant.USER_ENTITY_FIRST_NAME:
                        return UserEntity_.firstName;
                    case ColumnConstant.USER_ENTITY_LAST_NAME:
                        return UserEntity_.lastName;
                    case ColumnConstant.USER_ENTITY_EMAIL:
                        return UserEntity_.email;
                    default:
                        return EventEntity_.id;
                }
            }
        }
        return null;
    }
}
