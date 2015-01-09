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
public class PermissionMapEntity implements BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private PageEntity pageEntity;

    @ManyToOne
    private PermissionEntity permissionEntity;

    @ManyToOne
    private ACLRoleEntity aCLRoleEntity;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PermissionMapEntity)) {
            return false;
        }
        PermissionMapEntity other = (PermissionMapEntity) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    public PageEntity getPageEntity() {
        return pageEntity;
    }

    public void setPageEntity(PageEntity pageEntity) {
        this.pageEntity = pageEntity;
    }

    public PermissionEntity getPermissionEntity() {
        return permissionEntity;
    }

    public void setPermissionEntity(PermissionEntity permissionEntity) {
        this.permissionEntity = permissionEntity;
    }

    public ACLRoleEntity getaCLRoleEntity() {
        return aCLRoleEntity;
    }

    public void setaCLRoleEntity(ACLRoleEntity aCLRoleEntity) {
        this.aCLRoleEntity = aCLRoleEntity;
    }

    @Override
    public String toString() {
        return "PermissionMapEntity{" + "id=" + id + ", pageEntity=" + pageEntity + ", permissionEntity=" + permissionEntity + ", aCLRoleEntity=" + aCLRoleEntity + '}';
    }

}
