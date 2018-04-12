package dirandfileprog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
// В данной программе я реализовал рекурсивный метод обхода всех подкаталогов корневого каталога:
// внутри этого метода программа собирает все файлы в отдельную папку, где они сортируются по имени;
// следующий метод поочередно читает файлы из этой папки и записывает их содержимое в отдельный файл.

public class DirAndFileProg {

    public static void main(String[] args) throws IOException {
        DirAndFileProg s = new DirAndFileProg();
        String oldWay = "D:\\Programm"; // путь к корневому каталогу.
        String answerWay = "D:\\Programm\\Answer"; // путь к каталогу с отсортированными файлами.
        File dir = new File(oldWay);
        String filesFromOldWay = s.openDirAndGetFiles(oldWay, ""); // используется метод openDirAndGetFiles. 

        if (dir.isDirectory())// если объект представляет каталог.
        {

            for (File item : dir.listFiles()) { // все вложенные объекты в каталоге.
                if (item.isDirectory()) {
                    String openDirAndGetFiles = s.openDirAndGetFiles(item.getName(), oldWay); // используеется метод openDirAndGetFiles.
                    for (File myDirectory : new File(answerWay).listFiles()) { // удаляются все папки, попавшие в папку "Answer".
                        if (myDirectory.isDirectory()) {
                            myDirectory.delete();
                        }

                    }

                }

            }
            String readAndSaveFiles = s.readAndSaveFiles(answerWay); // используется метод readAndSaveFiles.

        }
    }

    public String openDirAndGetFiles(String dirName, String oldWay) throws IOException { // в данном методе программа идет по подкаталогам. Если в подкаталоге есть текстовые файлы, 
                                                                                         // то программа копирует их в папку "Answer". Если в подкаталоге есть свои подкаталоги, 
                                                                                         // то программа поступает с ними по аналогии с описанным методом: применяется рекурсию.   

        String newWay = "" + oldWay + "\\" + dirName; // старый путь + "\\" + имя подкаталога = путь к содержимому подкаталога.
        File dir = new File(newWay);
        String a = "";
        for (File item : dir.listFiles()) {
            if (item.isFile()) {
                File[] listOfFiles = dir.listFiles();
                Path destDir = Paths.get("D:\\Programm\\Answer"); // путь, где будут лежать все файлы каталога и подкаталогов.  
                if (listOfFiles != null) {
                    for (File file : dir.listFiles()) {
                        Files.copy(file.toPath(), destDir.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }
        }
        if (dir.isDirectory()) {
            for (File item : dir.listFiles()) {
                if (item.isDirectory()) {
                    a = openDirAndGetFiles(item.getName(), newWay); // рекурсия. 
                }
            }
        }
        return a;
    }

    public String readAndSaveFiles(String answerWay) throws IOException { // в данном методе программа читает все файлы из папки "Answer".
                                                                          // И сохраняет содержимое всех файлов в один файл "D:\\Programm\\!!!answerFile!!!.txt". 

        File dir = new File(answerWay);
        String q = "";
        for (File file : dir.listFiles()) {
            String fileName = file.getName();

            FileReader fr = new FileReader(file);
            char[] a = new char[10000]; // Количество символов, которое будем считывать
            fr.read(a);
            FileWriter fw = new FileWriter("D:\\Programm\\!!!answerFile!!!.txt", true);
            BufferedWriter bufferWritter = new BufferedWriter(fw);
            bufferWritter.newLine();
            bufferWritter.write(a);
            bufferWritter.flush();
            bufferWritter.close();
        }

        return q;

    }

}
