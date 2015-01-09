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

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.nisheeth.ejb.entity.EventEntity;
import org.nisheeth.ejb.entity.UserEntity;
import org.nisheeth.ejb.entity.UserEntity_;
import org.nisheeth.ejb.session.AbstractFacade;
import org.nisheeth.util.MapUtil;

/**
 *
 * @author Nisheeth Shah
 */
public class DataHelper {

    private static final Logger logger = Logger.getLogger(DataHelper.class);
    AbstractFacade abstractFacade;

    public DataHelper() {
    }

    public void serveReqeust(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Map<String, String[]> requestParamMap = request.getParameterMap();
        int dataSourceId = (int) MapUtil.getParamValueFromStringArray(requestParamMap.get("dsid"), Integer.class);
        int start = (int) MapUtil.getParamValueFromStringArray(requestParamMap.get("start"), Integer.class);
        int end = (int) MapUtil.getParamValueFromStringArray(requestParamMap.get("end"), Integer.class);
        int order = (int) MapUtil.getParamValueFromStringArray(requestParamMap.get("order"), Integer.class);
        int columnId = (int) MapUtil.getParamValueFromStringArray(requestParamMap.get("columnid"), Integer.class);
        out.print(serveReqeust(dataSourceId, start, end, order, columnId, request));
        out.close();
    }

    public String serveReqeust(int dataSourceId, int start, int end, int order, int columnId, HttpServletRequest request) {
        Map<String, Object> responseMap = new HashMap<>();
        EJBLookupHelper lookupHelper = new EJBLookupHelper();
        EntityColumnHelper helper = new EntityColumnHelper();
        SingularAttribute attribute = helper.getColumnAttributeById(dataSourceId, columnId);
        try {

        } catch (Exception e) {

        }
        switch (dataSourceId) {
            case 1: {
                abstractFacade = lookupHelper.lookupEntityFacadeBean("EventEntityFacade", "org.nisheeth.ejb.session.EvenEntityFacade");
                List<EventEntity> list = abstractFacade.getRangeSortBy(new int[]{start, end}, order, attribute, EventEntity.class);
                responseMap.put("total", abstractFacade.count());
                responseMap.put("records", list);
                responseMap.put("currentrecords", list.size());
                break;
            }
            case 2: {
                abstractFacade = lookupHelper.lookupEntityFacadeBean("UserEntityFacade", "org.nisheeth.ejb.session.UserEntityFacade");
                List<UserEntity> list = abstractFacade.getRangeSortBy(new int[]{start, end}, order, attribute, UserEntity.class);
                responseMap.put("total", abstractFacade.count());
                responseMap.put("records", list);
                responseMap.put("currentrecords", list.size());
                break;
            }
            case 3: {
                logger.info("It is getting in");
                UserEntity userEntity = new UserEntity();
                userEntity.setFirstName(request.getParameter("firstname"));
                userEntity.setLastName(request.getParameter("lastname"));
                userEntity.setEmail(request.getParameter("email"));
                abstractFacade = lookupHelper.lookupEntityFacadeBean("UserEntityFacade", "org.nisheeth.ejb.session.UserEntityFacade");
                List<UserEntity> list = abstractFacade.getRecordsByColumnValue(UserEntity_.email, userEntity.getEmail(), UserEntity.class);
                logger.info(list.size());
                if (list.isEmpty()) {
                    abstractFacade.create(userEntity);
                    responseMap.put("record", userEntity);
                } else {
                    responseMap.put("error", "duplicate Entry not allowed");
                }
                break;
            }
        }
        responseMap.put("start", start);
        responseMap.put("end", end);
        responseMap.put("order", order);
        responseMap.put("columnid", columnId);
        return new Gson().toJson(responseMap);
    }
}
