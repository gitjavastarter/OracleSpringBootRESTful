package pas.ctp.bluemix.psring.oracle.amap;

public class POIDistance {
    private static double countDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        double radLat1 = Math.toRadians(latitude1);
        double radLat2 = Math.toRadians(latitude2);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(longitude1) - Math.toRadians(longitude2);
        double s = 2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static void main(String args[]){

        System.out.println(countDistance(113.1968830000,36.0671580000,112.8430040000,35.4991870000));

    }

}
