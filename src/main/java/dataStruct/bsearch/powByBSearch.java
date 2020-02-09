package dataStruct.bsearch;

public class powByBSearch {
    /**
     * 求平方根
     * @param x  被开方的数
     * @param precision 精度
     * @return
     */
    public static double sqr(double x,double precision){
        if(x<0){
            return Double.NaN;
        }

        double low = 0;
        double high = x;
        //小于1的情况
        if(x<1 && x>0){
            low = x;
            high = 1;
        }
        double mid = low+(high-low)/2;

        while(high-low > precision){
            //听说可以防止溢出
            if(mid > x/mid){
                high = mid;
            }else if(mid < x / mid){
                low = mid;
            }else{
                return mid;
            }
            mid = low +(high-low)/2;
        }
        return mid;
    }

    public static double sqrt(double x, double precision) {
        if (x < 0) {
            return Double.NaN;
        }
        double low = 0;
        double up = x;
        if (x < 1 && x > 0) {
/** 小于1的时候*/
            low = x;
            up = 1;
        }
        double mid = low + (up - low)/2;
        while(up - low > precision) {
            if (mid * mid > x ) {//TODO mid可能会溢出
                up = mid;
            } else if (mid * mid < x) {
                low = mid;
            } else {
                return mid;
            }
            mid = low + (up - low)/2;
        }
        return mid;
    }

    public static void main(String[] args) {
        System.out.println(sqr(3,0.001));

    }
}
