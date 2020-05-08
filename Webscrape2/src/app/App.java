package app;

import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.io.IOCase;

public class App {
    public static void main(String[] args) throws Exception {

        File folder = new File("C:/Users/hotja/OneDrive/Desktop/wallpaper 3");
        FileFilter filter = new WildcardFileFilter("*.JPG", IOCase.INSENSITIVE);
        File[] listOFiles = folder.listFiles(filter);

        // for(int k=0;k<listOFiles.length;k++){
        // if(listOFiles[k].isFile()){
        // System.out.println("File"+listOFiles[k].getName());

        // }
        // else if(listOFiles[k].isDirectory()){
        // System.out.println("Directory"+listOFiles[k].getName());
        // }
        // }

        // for (int i = 0; i < listOFiles.length; i++) {
            //ListOfFiles==file names of that fanart
            String[] temp = listOFiles[12].getName().split("_"); //change the 12 to i when activating the loop

            String url = "https://www.pixiv.net/artworks/";
            String PicCode = temp[0];
            url = url.concat(PicCode);
            Document doc = Jsoup.connect(url).get();

            String[] titletemp = doc.title().split(" ");
            System.out.println(titletemp[0]);
            

        // }
        String target="C:/Users/hotja/OneDrive/Desktop/wallpaper 3/"+listOFiles[12].getName();
        // String target=base.concat(listOFiles[12].getName());
        String destination="C:/Users/hotja/OneDrive/Desktop/wallpaper 3/"+titletemp[0]+"/"+listOFiles[12].getName();
        System.out.println(target);
        System.out.println(destination);
        
        
        Files.move(Paths.get(target),Paths.get(destination));
    }

}

// System.out.println("Title is: " + title);

// char[] titlelist=title.toCharArray();

// int counter=0;
// // int i=0;
// String TT;
// boolean trig=false;

// StringBuffer FolderTitle=new StringBuffer();

// for(int i=0;i<titlelist.length;i++){
// if(titlelist[i]==' '&&trig==false){
// for(int j=0;j<=i;j++){
// FolderTitle.append(titlelist[j]);

// }
// trig=true;
// }
// }

// System.out.println(FolderTitle);