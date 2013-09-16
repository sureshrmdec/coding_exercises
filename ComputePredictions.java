/* 
Every week the number of unique visitors grow with 7% compared to the previous week.

Giving an integer number N representing the number of unique visitors at the end of this week and an integer number W

Your task is to

    write a function that prints to the standard output (stdout) the number of unique visitors we are going to have after W weeks
    please round the final result downwards to the nearest integer (e.g both 7.1 and 7.9 are rounded to 7)

Note that your function will receive the following arguments:

    n
        which is an integer representing the number N described above
    w
        which is an integer representing the number W described above

Data constraints

    the value for N will not exceed 10000
    the value for W will not exceed 50

*/

class ComputePredictions {
    public static void compute_prediction(Integer n, Integer w) {
        double sum = n;
        for (int i=0; i<w; i++) {
            sum = sum * 1.07;
        }
        
        int result = (int) Math.floor(sum);
        System.out.println(result);
        
    }
}
