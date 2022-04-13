import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }

    static void saveGame(String pathToFile, GameProgress gameProgress) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(pathToFile);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(gameProgress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void zipFiles(String pathToFile, List<String> pathToFileList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathToFile))) {
            for (String pathToZip : pathToFileList) {
                FileInputStream fis = new FileInputStream(pathToZip);
                File file = new File(pathToZip);
                ZipEntry entry = new ZipEntry(file.getName());
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void deleteNotZip(List<String> pathToFileList) {
        for (String notZip : pathToFileList) {
            File notZipFile = new File(notZip);
            notZipFile.delete();
        }
    }

    static void openZip(String pathToZip, String pathToUnZip) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(pathToZip))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(pathToUnZip + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static GameProgress openProgress(String pathToFile) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(pathToFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(gameProgress);
        return gameProgress;
    }
}
