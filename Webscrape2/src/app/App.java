package app;

import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.io.IOCase;

public class App {
    public static void main(String[] args) throws Exception {

        
        File folder = new File("D:/Otaku Fan Arts/Pictures");               //Location where I keep the fanarts
        
        
        // Original is a bit different, where it can have multiple specific artist folder, such initial folder must be initiated before the whole sorting algorithm starts
        File OriFolder=new File("D:/Otaku Fan Arts/Pictures/#オリジナル");   
        if (!OriFolder.exists()) { 
            OriFolder.mkdir();
        }

        //This section filters out only the JPG,PNG,and GIF from any other type of files
        String[] type = { "*.JPG", "*.PNG", "*GIF" }; // Filters and only accepts these 3 file types.
        FileFilter filter = new WildcardFileFilter(type, IOCase.INSENSITIVE); // case insensitive
        File[] listOFiles = folder.listFiles(filter); // All of the filtered files goes into the listOFiles
        
        Connection.Response response = null;
        String FileFullName = null;

        //For loop where it starts to organize all the possible fanarts (This would not organize the ones that doesn't have any live links)
        for (int i = 0; i < listOFiles.length; i++) {

            // ListOfFiles==list of file names
            //Each pixiv files are saved with 8-digit codes(Pincode) and log number, so split is used to seperate these 2. (ex: 12345678_p0)
            String[] temp = listOFiles[i].getName().split("_"); 

            //Pixiv art can be found by attaching the Pincode to the url, thus combining the url and the pincode would provide the info of that fanarts.
            String url = "https://www.pixiv.net/artworks/";
            String PicCode = temp[0];
            url = url.concat(PicCode);

            response = Jsoup.connect(url).followRedirects(false).ignoreHttpErrors(true).execute();
            int koko = response.statusCode();

            //koko provides if the url is alive or dead. If alive, it provides 200, if it's dead, it provides 404
            if (koko == 200) {
                //Document provides data from the website.
                Document doc = Jsoup.connect(url).maxBodySize(0).get();

                //.title() provides the title of that websites. 
                String[] titletemp = doc.title().split(" ");    // splits the web's title to different sections <Theme, Title, -, Artist, -, pixiv>

                FileFullName = listOFiles[i].getName();         // File name
                String title = titletemp[0];                    // Main theme of that art
                String artist = titletemp[3];                   // Artist of that art

                title = remover(title); // Removes specific char that prevents it from creating new folder (ex: *, /, ?, etc.)

                
                String destination = mover(title, FileFullName, artist);
                String target = "D:/Otaku Fan Arts/Pictures/" + FileFullName;

                //Moves the file from the target to the destination
                Files.move(Paths.get(target), Paths.get(destination));

            }

            // If the url connection fails:
            else {
                //It prints out the fanart with defective url. 
                System.out.println("Failed file: " + listOFiles[i].getName());

            }

        }

        System.out.println("All the possible ones are complete!");

        //Due to many folders with just one files, I might as well combine all the 1.1 into 1 folder... make it into 1.1.1

    }

    // Removes specific char that inturrupts the program from creating a new folder.
    public static String remover(String word) {

        String test = word;
        test = test.replaceAll("/", "_");
        test = test.replaceAll("\\*", "_");
        test = test.replaceAll("\\?", "_");
        test = test.replaceAll(":", "_");
        test = test.replaceAll(">", "_");
        test = test.replaceAll("<", "_");
        test = test.replaceAll("\\|", "_");

        return test;
    }

    //Sets the destination of the file. 
    public static String mover(String title, String FileFullName, String artist) {
        String OG = "#オリジナル";
        String destination = null;

        //If the fanart is considered as Original, than it would start checking if it's one of my favourite artist, if not it would just put it in the overall folder
        if (title.equals(OG)) {
            String temp = Orignal(artist);
            destination = temp;
        } else {
            destination = "D:/Otaku Fan Arts/Pictures/" + title; // listOFiles[i].getName();
        }

        // Check if the file exist:
        File file = new File(destination);
        if (!file.exists()) { // If the location folder doesn't exist, it creates a new folder of that destination. 
            file.mkdir();
        }

        //Set the destination and returns it.
        destination = destination + "/" + FileFullName;
        return destination;

    }

    // In this method, this will check for specific artist that I follow.
    public static String Orignal(String artist) {
        String temp = artist;
        String destination = "D:/Otaku Fan Arts/Pictures/#オリジナル/";

        switch (temp) {
            case "鴨見カモミ@バーチャルの絵描きのイラスト":
                destination = destination + "鴨見カモミ@バーチャルの絵描き";
                break;

            case "わたあめのイラスト":
                destination = destination + "わたあめ";
                break;

            case "jonsunのイラスト":
                destination = destination + "jonsun";
                break;

            case "たぬま＠単行本出ましたのイラスト":
                destination = destination + "たぬま＠単行本出ました";
                break;

            case "そふら(sofra)のイラスト":
                destination = destination + "そふら(sofra)";
                break;

            case "SWAVのイラスト":
                destination = destination + "SWAV";
                break;

            case "純白可憐のイラスト":
                destination = destination + "純白可憐";
                break;

            case "大原ロロンのイラスト":
                destination = destination + "大原ロロン";
                break;

            case "はる雪のイラスト":
                destination = destination + "はる雪";
                break;

            case "覺醒のイラスト":
                destination = destination + "覺醒";
                break;

            case "ななかぐらのイラスト":
                destination = destination + "ななかぐら";
                break;

            case "しろまんたのイラスト":
                destination = destination + "しろまんた";
                break;

            case "Miv4tのイラスト":
                destination = destination + "Miv4t";
                break;

            case "秋タカのイラスト":
                destination = destination + "秋タカ";
                break;

            case "らんふのイラスト":
                destination = destination + "らんふ";
                break;

            case "ぶーたのイラスト":
                destination = destination + "ぶーた";
                break;

            case "SUKJAのイラスト":
                destination = destination + "SUKJA";
                break;

            case "エイリゾのイラスト":
                destination = destination + "エイリゾ";
                break;

            case "林けゐのイラスト":
                destination = destination + "林けゐ";
                break;

            case "遠坂あさぎのイラスト":
                destination = destination + "遠坂あさぎ";
                break;

            case "チャイのイラスト":
                destination = destination + "チャイ";
                break;

            case "Lpipのイラスト":
                destination = destination + "Lpip";
                break;

            case "necoのイラスト":
                destination = destination + "neco";
                break;

            case "凪白みとのイラスト":
                destination = destination + "凪白みと";
                break;

            case "倉澤もこのイラスト":
                destination = destination + "倉澤もこ";
                break;

            case "ペケこのイラスト":
                destination = destination + "ペケこ";
                break;

            case "室埴 ポコのイラスト":
                destination = destination + "室埴 ポコ";
                break;

            case "和武はざののイラスト":
                destination = destination + "和武はざの";
                break;

            default:
                break;

        }

        return destination;
    }

}
