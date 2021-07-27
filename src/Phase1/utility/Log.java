package Phase1.utility;


public class Log {

    public static void logError(String str) {
        System.out.println("Error-> " + str);
    }


    public static void logInfo(String str) {
        System.out.println("Info-> " + str);
    }

    public static void logCatchExe(String str) {
        System.out.println("Catch Exception-> " + str);
    }

    public static void log(String str) {
        System.out.println(str);
    }

    public static void logInt(int number){
        System.out.println(number);
    }
}
