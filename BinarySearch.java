public class BinarySearch {
    public static final int NOT_IN_ARRAY = -1;
    public static final int ARRAY_UNORDERED = -2;
    public static final int LIMITS_REVERSED = -3;


    int binarySearch( int[] array, int lower, int upper, int target ){ 
        int center, range;
        range = upper - lower;
        if (range < 0) {
            return LIMITS_REVERSED;
        } else if( range == 0 && array[lower] != target ){
            return NOT_IN_ARRAY;
        }
        if( array[lower] > array[upper] ) {
            return ARRAY_UNORDERED;
        }
        //int mid = low + ((high - low) / 2);
        //int mid = (low + high) >>> 1;
        //mid = ((unsigned int)low + (unsigned int)high)) >> 1;
        center = ((range)/2) + lower;
        if( target == array[center] ){
            return center;
        } else if( target < array[center] ){
            return binarySearch( array, lower, center - 1, target );
        } else {
            return binarySearch( array, center + 1, upper, target );
        }
    }
}
