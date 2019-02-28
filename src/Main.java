import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        String filename = "c_memorable_moments";
        ArrayList<Photo> photos = readPhotos(filename + ".txt");
        ArrayList<Integer> slides = evaluatePhotos(photos);
        writePhotos(filename + "_output.txt", slides);
    }

    private static ArrayList<Photo> readPhotos(String filePath) throws FileNotFoundException {
        Scanner in = new Scanner(new File(filePath));
        int photosNumber = Integer.parseInt(in.next());
        ArrayList<Photo> photos = new ArrayList<>();
        for(int i = 0 ; i < photosNumber; i++){
            String orient = in.next();
            int tagsNum = Integer.parseInt(in.next());
            ArrayList<String> tags = new ArrayList<>();
            for(int j = 0; j < tagsNum; j++){
                tags.add(in.next());
            }
            Photo p = new Photo(orient, tags, i);
            photos.add(p);
        }
        return photos;
    }

    private static ArrayList<Integer> evaluatePhotos(ArrayList<Photo> photos) throws IOException {
        ArrayList<Integer> slides = new ArrayList<>();
        for(int i = 0; i < photos.size() - 1; i++){
            Photo p = photos.get(i);
            HashMap<Integer, Integer> photosSameTags = new HashMap<>();
            for(int j = i + 1; j < photos.size(); j++){
                Photo p1 = photos.get(j);
                ArrayList<String> intersection = new ArrayList<>(p1.getTags());
                intersection.retainAll(p.getTags()); //calculate the same tags
                int sameTags = intersection.size();
                photosSameTags.put(p1.getId(), sameTags);
            }
            int minTagsNumber = Collections.min(photosSameTags.values());
            int maxTagsNumber = Collections.max(photosSameTags.values());
            int medium = (minTagsNumber + maxTagsNumber) / 2;
            int mediumPhotoId = medium; //todo get the nearest to the medium photo
            // todo think about 2 photos on one slide
            if(mediumPhotoId!=p.getId() && slides.indexOf(p.getId()) == -1 && slides.indexOf(mediumPhotoId) == -1) {
                slides.add(p.getId());
                slides.add(mediumPhotoId);
            }
        }
        return slides;
    }

    private static void writePhotos(String filePath, ArrayList<Integer> slides) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(filePath));
        out.println(slides.size());
        for(int i = 0; i < slides.size(); i++){
            out.println(slides.get(i));
        }
        out.flush();
    }
}
