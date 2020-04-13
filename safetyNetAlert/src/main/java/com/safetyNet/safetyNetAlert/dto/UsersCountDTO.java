package com.safetyNet.safetyNetAlert.dto;

import java.util.List;

public class UsersCountDTO {

    private List<UserDTO> userList;
    private long countChildren;
    private long countAdult;

    public List<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDTO> userList) {
        this.userList = userList;
    }

    public long getCountChildren() {
        return countChildren;
    }

    public void setCountChildren(long countChildren) {
        this.countChildren = countChildren;
    }

    public long getCountAdult() {
        return countAdult;
    }

    public void setCountAdult(long countAdult) {
        this.countAdult = countAdult;
    }

    public UsersCountDTO(List<UserDTO> userList, long countChildren, long countAdult) {
        this.userList = userList;
        this.countChildren = countChildren;
        this.countAdult = countAdult;
    }
}
