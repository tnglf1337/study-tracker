package util;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Foo {
    /**
     * @param n seconds
     * @return Formated String
     */
    public static String convertSeconds(int n) {
        n = n % (24 * 3600);
        int hour = n / 3600;
      
        n %= 3600;
        int minutes = n / 60 ;
     
        return String.format("%dh, %dm.", hour, minutes);
    }

    public static int minutesToSeconds(String minutes) {
        int n = Integer.parseInt(minutes);
        return n * 60;
    }

    public static String convertToMinutesAndSeconds(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;

        String minutesString = String.format("%02d", minutes);  // Ensures leading zero if minutes < 10
        String secondsString = String.format("%02d", seconds);  // Ensures leading zero if seconds < 10

        return minutesString + ":" + secondsString;
    }



    public static List<String> getDataAsList(String pathname) {
        try {
            List<String> l = Files.readAllLines(Path.of(pathname));

            return l;
        } catch(IOException e) {
            System.out.println("file doesnt exist");
            return null;
        }
    }

    public static void addModuleToFile(String moduleName, String pathToFile) {
        try {
            List<String> oldData = getDataAsList(pathToFile);
            File myFoo = new File(pathToFile);
            FileWriter fooWriter = new FileWriter(myFoo, false);

            String toAdd = moduleName.trim() + ",0";

            String newline = System.getProperty("line.separator");

            for (int i = 0; i < oldData.size(); i++) {
                fooWriter.write(oldData.get(i) + newline);
            }

            fooWriter.write(toAdd);
            fooWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeModuleFromFile(String moduleName, String pathToFile) {
        try {
            List<String> oldData = getDataAsList(pathToFile);
            String[] names = getModuleNames(oldData);
            File myFoo = new File(pathToFile);
            FileWriter fooWriter = new FileWriter(myFoo, false);

            String newline = System.getProperty("line.separator");

            for (int i = 0; i < oldData.size(); i++) {
                if(names[i].equals(moduleName)) {
                    continue;
                } else {
                    fooWriter.write(oldData.get(i) + newline);
                }
            }

            fooWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] getModuleNames(List<String> l) {
        String[] arr = new String[l.size()];
        String[] currentElement;

        for (int i = 0; i < l.size(); i++) {
            currentElement = l.get(i).split(",");
            arr[i] = currentElement[0];
        }

        return arr;
    }

    public static String[] getModuleTimes(List<String> l) {
        String[] arr = new String[l.size()];
        String[] currentElement;

        for (int i = 0; i < l.size(); i++) {
            currentElement = l.get(i).split(",");
            arr[i] = currentElement[1];
        }

        return arr;
    }

    public static String getTotalTime(List<String> l) {
        String[] arr = getModuleTimes(l);
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
            int temp = Integer.parseInt(arr[i]);
            sum += temp;
        }
        System.out.println(sum);

        return convertSeconds(sum);
    }

    public static void updateDataInFile(String module, String pathname, int seconds) {
        try {
            List<String> data = getDataAsList(pathname);
            File myFoo = new File(pathname);
            FileWriter fooWriter = new FileWriter(myFoo, false);

            String newline = System.getProperty("line.separator");
            String rowToUpdate = getRowOfModule(data, module);
            String updatedRow = updateRowData(rowToUpdate, seconds);

            List<String> updatedList = replaceRow(data, rowToUpdate, updatedRow);

            for (int i = 0; i < updatedList.size(); i++) {
                fooWriter.write(updatedList.get(i) + newline);
            }

            fooWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateLearnDuration(String learnDuration, String pathname) {
        try {
            List<String> data = Foo.getDataAsList(pathname);
            String[] s = data.get(0).split(",");
            File myFoo = new File(pathname);
            FileWriter fooWriter = new FileWriter(myFoo, false);

            fooWriter.write(learnDuration + "," + s[1]);

            fooWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLearnDuration() {
        List<String> data = Foo.getDataAsList(Constants.PATH_FOR_POMODOROCONFIG);
        String[] s = data.get(0).split(",");

        return s[0];
    }

    public static void updateRestDuartion(String restDuration, String pathname) {
        try {
            List<String> data = Foo.getDataAsList(pathname);
            String[] s = data.get(0).split(",");
            File myFoo = new File(pathname);
            FileWriter fooWriter = new FileWriter(myFoo, false);

            fooWriter.write(s[0] + "," + restDuration);

            fooWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getRestDuration() {
        List<String> data = Foo.getDataAsList(Constants.PATH_FOR_POMODOROCONFIG);
        String[] s = data.get(0).split(",");

        return s[1];
    }

    public static String getRowOfModule(List<String> l , String modul) {
        String[] splitCurr;
        for (int i = 0; i < l.size(); i++) {
            String curr = l.get(i);
            splitCurr = curr.split(",");

            if(splitCurr[0].equals(modul)) {
                return curr;
            }
        }

        return null;
    }

    public static String updateRowData(String rowToUpdate, int time) {
        String[] split = rowToUpdate.split(",");
        int oldTime = Integer.parseInt(split[1]);

        return split[0] + "," + (oldTime + time);
    }

    public static List<String> replaceRow(List<String> l, String oldRow,  String newRow ) {
        int index = 0;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).equals(oldRow)) {
                index = i;
                l.remove(index);
                l.add(index, newRow);
            }
        }

        return l;
    }

    public static void playSound(String pathToFile) {
        try {
            // Öffne die Audio-Datei
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(pathToFile));

            // Erzeuge einen Clip
            Clip clip = AudioSystem.getClip();

            // Lade die Audiodaten in den Clip
            clip.open(audioInputStream);

            // Spiele den Clip ab
            clip.start();

            // Warte, bis der Clip beendet ist
            Thread.sleep(clip.getMicrosecondLength() / 1000);

            // Schließe den Clip und die Audio-Datei
            clip.close();
            audioInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printList(List<String> l ) {
        l.forEach(System.out::println);
    }

    @Deprecated
    private void setLocation() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

        //int x = (int) rect.getMaxX() - this.getWidth();
        int y = 0;
        //this.setLocation(x, y);
    }
}
