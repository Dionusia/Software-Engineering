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
          do{
             false_crend=true;
             System.out.println("-----------Ender your name------- :");
             String username = scaner.nextLine();
             System.out.println("-----------Ender your password--- :");
             String password =scaner.nextLine();
             System.out.println("Your gender is :");
             String gender = scaner.nextLine();
             System.out.println("You want to be level : ");
             String level = scaner.nextLine(); 
             System.out.println("Your Age is : ");
             int age = scaner.nextInt();
             scaner.nextLine();
          

                 if(username.isEmpty()){
                    System.out.println("Your username is not valid");
                    System.out.println("try again");
                    false_crend=false;
                 }
                 if(password.isEmpty()){
                   System.out.println("Your password is not valid");
                   System.out.println("try again");
                    false_crend=false;
                 }
                 if(false_crend){
                    users.put(username,new Entry(password,age,gender,level));
                    System.out.println("Your registration succesful!");
                    
                 }
           }while(!false_crend );
        }finally {
            //  scaner.close();
             }
    }

public void Login(){
        Scanner scaner = new Scanner(System.in);
          try{
              System.out.println("Your name IS :");
              String username =  scaner.nextLine();
              System.out.println("Your password is :");
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
        Scanner scaner = new Scanner(System.in);
    try{
           boolean isLogin=false;
        while(!isLogin){
            System.out.println("##############  MAKE YOUR BALANCE  ##############");
            System.out.println("-Enter 1 for register-");
            System.out.println("-Enter 2 for Login-1");
            int choise = scaner.nextInt();
          
          
            if(choise == 1){
               loginpage.register();
               System.out.println("You want to quit??? YES:press 1 NO:press2:");
          
             
               choise = scaner.nextInt();
               if(choise==1)  break;
               

            }else if(choise ==2){
               loginpage.Login();
                isLogin=true;
            }else{
                System.out.println("invalid choise");

            }
         }
        }finally{scaner.close();
        

     //print all user,if do registration you will see new user 
   /*   for (Map.Entry<String, Entry> entry : loginpage.users.entrySet()) {
        String key = entry.getKey();
        Entry value = entry.getValue();

        System.out.println("username: " + key);
        System.out.println("password: " + value.getPassword());
        System.out.println("Age: " + value.getAge());
        System.out.println("Gender: " + value.getGender());
        System.out.println("Level: " + value.getLevel());
        System.out.println("--------------------");
    }*/
  }
 
}




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




    