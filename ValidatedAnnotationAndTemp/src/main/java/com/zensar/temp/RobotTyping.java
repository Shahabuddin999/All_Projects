package com.zensar.temp;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class RobotTyping {
    public static void main(String[] args) throws Exception {
        Robot robot = new Robot();
        Thread.sleep(2000); // Time to switch to Notepad or text editor

        String text = "Hello from Java Robot!";
        for (char c : text.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (KeyEvent.CHAR_UNDEFINED == keyCode) {
                throw new RuntimeException(
                    "Key code not found for character '" + c + "'");
            }
            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);
            Thread.sleep(100); // Slow typing effect
        }
    }
}