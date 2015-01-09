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
package org.nisheeth.ejb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Nisheeth Shah
 */
@Entity
public class RequestActionEntity implements BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int dataSourceId;
    private Long recordStart;
    private Long recordEnd;
    @ManyToOne
    private PageEntity pageEntity;
    private int readWriteType;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public int getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(int dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public Long getRecordStart() {
        return recordStart;
    }

    public void setRecordStart(Long recordStart) {
        this.recordStart = recordStart;
    }

    public Long getRecordEnd() {
        return recordEnd;
    }

    public void setRecordEnd(Long recordEnd) {
        this.recordEnd = recordEnd;
    }

    public PageEntity getPageEntity() {
        return pageEntity;
    }

    public void setPageEntity(PageEntity pageEntity) {
        this.pageEntity = pageEntity;
    }

    public int getReadWriteType() {
        return readWriteType;
    }

    public void setReadWriteType(int readWriteType) {
        this.readWriteType = readWriteType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RequestActionEntity)) {
            return false;
        }
        RequestActionEntity other = (RequestActionEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "RequestActionEntity{" + "id=" + id + ", dataSourceId=" + dataSourceId + ", recordStart=" + recordStart + ", recordEnd=" + recordEnd + ", pageEntity=" + pageEntity + ", readWriteType=" + readWriteType + '}';
    }

}
