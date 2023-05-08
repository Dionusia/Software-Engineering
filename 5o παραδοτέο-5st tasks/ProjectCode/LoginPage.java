import java.util.Scanner;



import java.util.HashMap;
import java.util.Map;

public class LoginPage{

    private Map<String, Entry> users = new HashMap<>();
      //Entry e= new Entry();
     public LoginPage(){
        users =  new HashMap<String,Entry>();
        users.put("stelios",new Entry("1234",42,"Male","H"));
        users.put("Dionisia",new Entry("1234",21,"female","H"));
        users.put("Aggelos",new Entry("1234",21,"Male","H"));
     }

     //ηλικία, το φύλο, το ύψος, το βάρος και το επίπεδο δραστηριότητας, το 
  public void register(){
       Scanner scaner= new Scanner(System.in);
       Boolean false_crend;//an den exei eisagei tipota false
       try{   
        String username;   
        String password;  
     
           do{
            false_crend=true;
             System.out.println("-----------Ender your name------- :");
             username = scaner.nextLine();
          
             if(username.isEmpty()){
                    System.out.println("Your username is not valid");
                    System.out.println("try again");
                    false_crend=false;
                 }
            }while(!false_crend);
             
          do{
              false_crend=true;
              System.out.println("-----------Ender your password--- :");
               password =scaner.nextLine();
               if(password.isEmpty()){
                    System.out.println("Your password is not valid");
                    System.out.println("try again");
                    false_crend=false;
              }
             }while(!false_crend);

             System.out.println("Your gender is :");
             String gender = scaner.nextLine();
             System.out.println("You want to be level : ");
             String level = scaner.nextLine(); 
             System.out.println("Your Age is : ");
             int age= scaner.nextInt();

        
               
                 if(false_crend){
                    users.put(username,new Entry(password,age,gender,level));
                    System.out.println("Your registration succesful!");
                    
                 }
           
        }finally {
          // scaner.close();
             }
                
    }

public void Login(){
        Scanner scaner = new Scanner(System.in);
          try{
              System.out.println("Ender your name  :");
              String username =  scaner.nextLine();
              System.out.println("Ender your password  :");
              String password = scaner.nextLine();
             
               if( !users.containsKey(username )){
               System.out.println("Your username or password is invalid");
               }else{
                   if(users.containsKey(username ) & users.get(username).getPassword().equals(password) ){
                        System.out.println("You Login succesful");
                   }else System.out.println("Your username or password is invalid");
           }}finally{
            scaner.close();
        }
  }

 public static void main(String[] args){
        LoginPage loginpage=  new LoginPage();
        Scanner scaner= new Scanner(System.in);
    try{
           
           String  choise1=null;
           int choise=1;
           boolean isLogin=false;
           boolean reg=false;

        while(!isLogin){
            System.out.println("##############  MAKE YOUR BALANCE  ##############");
            if(!reg){
            System.out.println("-Enter 1 for register-");
            }
            System.out.println("-Enter 2 for Login-");
            System.out.println("-Ënder 3 for exit-");
            
        
             choise1 = scaner.nextLine();
             choise = Integer.parseInt(choise1); 
           
        
             
            
            if(choise == 1){
               loginpage.register();
               reg=true;
             }else if(choise ==2){
               loginpage.Login();
               isLogin=true;
            }else if(choise ==3){
                System.out.println("======== by ===========");
                break;
            }else{
                System.out.println("invalid choise");

            }
         }

        }finally{
            scaner.close();
        }
        
    }
     //print all user,if do registration you will see new user 
   /*   for (Map.Entry<String, Entry> entry : loginpage.users.entrySet()) {
        String key = entry.getKey();
        Entry value = entry.getValue();

        System.out.println("username: " + key);
        System.out.println("password: " + value.getPassword());
        System.out.println("Age: " + value.getAge());
        System.out.println("Gender: " + value.getGender());
        System.out.println("Level: " + value.getLevel());
        System.out.println("--------------------");*/
    
  
 





    // Προσαρμοσμένη κλάση για την αποθήκευση των πεδίων
    public  class Entry {
        private String password;
        private int age;
        private String gender;
        private String level;

        public Entry(){}
        public Entry(String password, int age,String  gender ,String level) {
            this.password = password;
            this.age = age;
            this.gender = gender;
            this.level=level;
        }

        public String getPassword() {
            return password;
        }

        public int getAge() {
            return age;
        }

        public String getGender() {
            return gender;
        }
        public String getLevel(){
            return level;
        }
        public void setPassword(String password){
            this.password=password;
        }
        public void setAge(int age){
            this.age=age;
        }
    }
}




    