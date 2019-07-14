package utils;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HelperMethods {

    public static void checkStatusIs200(Response res) {
        Assert.assertEquals("Status Check Failed!", 200, res.getStatusCode());
    }

    public static ArrayList getVideoIdList (JsonPath jp) {
        ArrayList videoIdList = jp.get("items.id");
        return  videoIdList;
    }

    public static ArrayList getRelatedVideoIdList (JsonPath jp) {

        ArrayList relatedVideoList = jp.get("items.related.id");

        ArrayList splittedRelatedVideoList = (ArrayList) relatedVideoList.get(0);
        return splittedRelatedVideoList;
    }

    public  static ArrayList mergeLists (ArrayList videoList, ArrayList relatedVideoList){
        ArrayList mergedVideoList = new ArrayList(videoList);
        mergedVideoList.addAll(relatedVideoList);
        return mergedVideoList;
    }

    //Find Duplicate Videos
    public static boolean findDuplicateVideos (List<Integer> videoIdList) {
        for (int i=0; i< videoIdList.size(); i++) {
            if(Collections.frequency(videoIdList, videoIdList.get(i)) > 1){
                System.out.println("This video id is duplicated: " + videoIdList.get(i));
                return false;
            }
        }
        return true;
    }
}
