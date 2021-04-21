package com.lagou.domain;


import java.util.List;

//作为中间表
public class RoleResourceRelationVO {
    private Integer roleId;
    private List<Integer> resourceIdList;
    private String person;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(List<Integer> resourceIdList) {
        this.resourceIdList = resourceIdList;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "RoleResourceRelationVO{" +
                "roleId=" + roleId +
                ", resourceIdList=" + resourceIdList +
                ", person='" + person + '\'' +
                '}';
    }
}
