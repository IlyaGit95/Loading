import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(10, 5, 3, 0.1);
        GameProgress gameProgress2 = new GameProgress(20, 15, 13, 0.3);
        GameProgress gameProgress3 = new GameProgress(30, 25, 23, 3.0);

        String pathToFile1 = "D:\\Games\\savegames\\gameProgress1.dat";
        String pathToFile2 = "D:\\Games\\savegames\\gameProgress2.dat";
        String pathToFile3 = "D:\\Games\\savegames\\gameProgress3.dat";
        String pathToZip = "D:\\Games\\savegames\\zipSave.zip";
 //       String pathToUnZip = "D:\\Games\\savegames";

        List<String> pathToFileList = Arrays.asList(pathToFile1, pathToFile2, pathToFile3);

        GameProgress.saveGame(pathToFile1, gameProgress1);
        GameProgress.saveGame(pathToFile2, gameProgress2);
        GameProgress.saveGame(pathToFile3, gameProgress3);

        GameProgress.zipFiles(pathToZip,pathToFileList);

        GameProgress.deleteNotZip(pathToFileList);

       // GameProgress.openZip(pathToZip,pathToUnZip);
    }
}
