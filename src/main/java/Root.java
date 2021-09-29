import io.github.techgnious.IVCompressor;
import io.github.techgnious.dto.ResizeResolution;
import io.github.techgnious.dto.VideoFormats;
import io.github.techgnious.exception.VideoException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Root {
    static IVCompressor compressor = new IVCompressor();
    static Scanner sc = new Scanner(System.in);
    static String filePath;
    static String cmpPath;
    static VideoFormats format = VideoFormats.MP4;
    static ResizeResolution resolution = ResizeResolution.R480P;

    public static void main(String[] args) throws IOException, VideoException {
        compressorInterface();
        File file = new File(filePath);
        boolean prgoress = true;
        while (prgoress) {
            System.out.println("-=-=-=-=-=-=-=-");
            compressThisFile(file, format, resolution, cmpPath);
            prgoress = false;
        }
    }

    public static void compressorInterface(){

        ArrayList<Character> fpth = new ArrayList<>();
        String formatEnd;

        System.out.print("Enter file path: ");
        filePath = sc.nextLine();
        char[] holding = filePath.toCharArray();
        for (int i = 0; i < holding.length; i++) {
            fpth.add(i,holding[i]);
        }
       List<Character> formatList = fpth.subList(fpth.indexOf('.'), fpth.size());
       formatEnd = getStringRepresentation(formatList);
       format = chooseFormat(formatEnd);

        System.out.print("Compressed resolution: ");
        int compedres = sc.nextInt();
        resolution = chooseResolution(compedres);

        sc.nextLine();

        System.out.println("Export dir: ");
        cmpPath = sc.nextLine();

    }
    private static VideoFormats chooseFormat(String frmt){

        switch (frmt){
            case ".mp4" -> {
                System.out.println("mp4 switched");
                return VideoFormats.MP4;
            }
            case ".mov" -> {
                System.out.println("mov switched");
                return VideoFormats.MOV;
            }
            case ".avi"-> {
                System.out.println("avi switched");
                return VideoFormats.AVI;
            }
            default -> {
                System.out.println("Error: non format selected.");
                return VideoFormats.MOV;
            }
        }

    }
    private static ResizeResolution chooseResolution(int res){
        switch (res){
            case 240 -> {
                System.out.println("240p");
                return ResizeResolution.R240P;
            }
            case 360 -> {
                System.out.println("360p");
                return ResizeResolution.R360P;
            }
            case 480 -> {
                System.out.println("480p");
                return ResizeResolution.R480P;
            }
            case 720 -> {
                System.out.println("720p");
                return ResizeResolution.R720P;
            }
            case 1080 -> {
                System.out.println("1080p");
                return ResizeResolution.R1080P;
            }
            case 1440 -> {
                System.out.println("1440p");
                return ResizeResolution.R1440P;
            }
            default -> {
                System.out.println("Error: Resolution selected is not available.");
                return ResizeResolution.R1080P;
            }
        }
    }


    public static void compressThisFile(File file, VideoFormats formats, ResizeResolution res, String path) throws IOException, VideoException {
        compressor.reduceVideoSizeAndSaveToAPath(file, formats, res,path);
        System.out.println("DONE \n\n File is at "+cmpPath);
    }
    private static String getStringRepresentation(List<Character> list) {
        StringBuilder builder = new StringBuilder(list.size());
        for(Character ch: list)
        {
            builder.append(ch);
        }
        return builder.toString();
    }
}
