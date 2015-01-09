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
package org.nisheeth.web.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import org.nisheeth.web.helper.DataHelper;

/**
 * REST Web Service
 *
 * @author Nisheeth Shah
 */
@Path("dataservice")
public class DataSourceServices {

    @Context
    private UriInfo context;
    @Context
    private ServletContext sc;

    /**
     * Creates a new instance of DataSourceServices
     */
    public DataSourceServices() {
    }

    /**
     * Retrieves representation of an instance of
     * org.nisheeth.web.service.DataSourceServices
     *
     * @param dsid
     * @param start
     * @param end
     * @param order
     * @param columnId
     * @param request
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson(@QueryParam("dsid") int dsid, @QueryParam("start") int start, @QueryParam("end") int end, @QueryParam("order") int order, @QueryParam("columnid") int columnId, @Context HttpServletRequest request) {
        DataHelper helper = new DataHelper();
        System.out.println(request.getSession().getId());
        return helper.serveReqeust(dsid, start, end, order, columnId, request);
    }

    /**
     * PUT method for updating or creating an instance of DataSourceServices
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
