package stringFeature;

public class StringsDemo {

    public static void main(String[] args) {
        String str1 = new String("String Demo");
        String str2 = new String("String Demo");

        if(str1 == str2){ //== is a reference comparison, i.e. both objects point to the same memory location
            System.out.println("Str1 and str2 points to same reference.");
        }else{
            System.out.println("Str1 and str2 points to different reference.");
        }

        String str3 = "String Demo";
        String str4 = "String Demo";

        if(str3 == str4){
            System.out.println("Str3 and str4 points to same reference.");
        }else{
            System.out.println("Str3 and str4 points to different reference.");
        }
    }
}
