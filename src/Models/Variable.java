package Models;


import Models.Users;

public class Variable {
    
    private static Users Users =null;
 

    
    public static Users getUsers(){
        return Users;
    }
    
    public static void setUsers(Users user){
        Variable.Users = user;
        System.out.println(Variable.getUsers().getId());
    }
    

    
}
