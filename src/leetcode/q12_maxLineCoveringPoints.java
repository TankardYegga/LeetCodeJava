package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: JVM
 * Creator: levin
 * Date: 9/9/2022
 * Time: 6:59 PM
 * Email: levinforward@163.com
 */
public class q12_maxLineCoveringPoints {

    public static class Point{

        public int x;
        public int y;

        Point(){
            x = 0;
            y = 0;
        }

        Point(int a, int b){
            x = a;
            y = b;
        }


        static String pointSlope(Point p1, Point p2){
            int xDist = p1.x - p2.x;
            int yDist = p1.y - p2.y;
            int xDistAbs = Math.abs(xDist);
            int yDistAbs = Math.abs(yDist);

            int largestGcd = gcd(xDistAbs, yDistAbs);
            int xSymbol = 1;
            int ySymbol = 1;
            if(xDist == xDistAbs){
                xSymbol = 1;
            }else{
                xSymbol = -1;
            }
            if(yDist == yDistAbs){
                ySymbol = 1;
            }else{
                ySymbol = -1;
            }

            int finalSymbol = xSymbol * ySymbol;
            String slope = "";
            if(finalSymbol == -1){
                slope += "-";
            }
            slope += ( (int)(yDistAbs / largestGcd) + "/" + (int) (xDistAbs / largestGcd));
            return slope;
        }
    }

    public static int gcd(int a, int b){
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int maxLinePoints(int[] arrx, int[] arry){
        if(arrx == null || arry == null){
            return 0;
        }

        if(arrx.length <= 2){
            return arrx.length;
        }
        Point[] points = new Point[arrx.length];
        for(int i = 0; i < arrx.length; i++){
            points[i] = new Point(arrx[i], arry[i]);
        }

        int maxPointsNum = 0;
        for(int i = 0; i < points.length; i++){
            int repeated = 0;
            int sameX = 0;
            int sameY = 0;
            String pointSlope = "";
            Map<String, Integer> slopeMap = new HashMap<String, Integer>();
            for(int j = i + 1; j < points.length; j++){
                if(points[j].x == points[i].x && points[j].y == points[i].y){
                    repeated++;
                }else if(points[j].x == points[i].x){
                    sameX++;
                }else if(points[j].y == points[i].y){
                    sameY++;
                }else{
                    pointSlope = Point.pointSlope(points[i], points[j]);
                    if(!slopeMap.containsKey(pointSlope)){
                        slopeMap.put(pointSlope, 1);
                    }else{
                        slopeMap.put(pointSlope, slopeMap.get(pointSlope) + 1);
                    }
                }
            }
            int slopeMaxPoints = Integer.MIN_VALUE;
            for(String key: slopeMap.keySet()){
                slopeMaxPoints = Math.max(slopeMaxPoints, slopeMap.get(key));
            }
            int curMaxPointsNum = Math.max(slopeMaxPoints, Math.max(sameX, sameY)) + repeated;
            maxPointsNum = Math.max(maxPointsNum, curMaxPointsNum);
        }
        return maxPointsNum + 1;
    }

    public static int maxLinePoints2(int[] arrx, int[] arry){
        int ans = Integer.MIN_VALUE;

        Map<Integer, HashMap<Integer, Integer>> slopeMap = new HashMap<Integer, HashMap<Integer, Integer>>();
        for(int i = 0; i < arrx.length; i++){
            int samePosition = 0;
            int sameX = 0;
            int sameY = 0;
            int line = 0;
            slopeMap.clear();

            for(int j = i + 1; j < arrx.length; j++){
                int xDist = arrx[i] - arrx[j];
                int yDist = arry[i] - arry[j];

                if(xDist == 0 && yDist == 0){
                    samePosition++;
                }else if(xDist == 0){
                    sameX++;
                }else if(yDist == 0){
                    sameY++;
                }else{
                    int gcd = gcd(xDist, yDist);
                    xDist /= gcd;
                    yDist /= gcd;
                    if(!slopeMap.containsKey(xDist)){
                        slopeMap.put(xDist, new HashMap<Integer, Integer>());
                    }
                    if(!slopeMap.get(xDist).containsKey(yDist)){
                        slopeMap.get(xDist).put(yDist, 0);
                    }
                    slopeMap.get(xDist).put(yDist, slopeMap.get(xDist).get(yDist) + 1);
                    line = Math.max(line, slopeMap.get(xDist).get(yDist));
                }
            }
            int curAns = Math.max(line, Math.max(sameX, sameY)) + samePosition;
            ans = Math.max(curAns, ans);
        }
        return ans + 1;
    }

    public static void main(String[] args){

        Point p1 = new Point(3, 9);
        Point p2 = new Point(9, 4);

        int[] arrx = new int[]{3, 3, 3, 3, 9, 3, 3, 6, 9, 4, 10, 6, 2, 10};
        int[] arry = new int[]{4, 9, 9, 9, 9, 8, 9, 5, 5, 12, 6, 6, 3, 12};

        System.out.println(Point.pointSlope(p1, p2));
        System.out.println(maxLinePoints(arrx, arry));
        System.out.println(maxLinePoints2(arrx, arry));
    }
}
