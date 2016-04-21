// This method will return index of array if search item is found
// Otherwise it will return -(insertion position + 1)
func binarySearch<T:Comparable>(inputArr:Array<T>, searchItem: T)->Int{
    return binarySearch0(inputArr, fromIndex: 0, toIndex: inputArr.count, searchItem: searchItem)
}

private func binarySearch0<T:Comparable>(inputArr:Array<T>, fromIndex: Int, toIndex: Int, searchItem: T)-> Int {
    var low = fromIndex
    var high = toIndex - 1
    
    while (low <= high) {
        let mid = low + (high - low) / 2
        let midVal = inputArr[mid]
        
        if (midVal < searchItem) {
            low = mid + 1
        } else if midVal > searchItem {
            high = mid - 1
        } else {
            return mid // key found
        }
    }
    return -(low + 1)  // key not found.
}
