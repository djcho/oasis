package com.oasis.common.constant;

public enum MemberRole {
    
    ADMIN("ADMIN"),
    NORMAL("NORMAL"),
    MANAGER("MANAGER");
    
    String role;
    
    MemberRole(String role){
        this.role = role;
    }
    
    public String value(){
        return role;
    }
    
}
