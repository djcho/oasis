package com.oasis.common.constant;

// TODO :: common/constant의 성격과 맞을까요?(계속 성격타령해서 죄송합니다)
public enum Role {
    
    ADMIN("ADMIN"),
    NORMAL("NORMAL"),
    MANAGER("MANAGER");
    
    String role;
    
    Role(String role){
        this.role = role;
    }
    
    public String value(){
        return role;
    }
    
}
