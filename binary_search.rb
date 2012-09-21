def search(arr, key)
   lo = 0
   hi = arr.length 
   while (lo <= hi) 
      mid = lo + (hi - lo) / 2
      if (key < arr[mid])
         hi = mid - 1
      elsif (key > arr[mid])
         lo = mid + 1
      else
         return mid 
      end
   end 
   return -1
end 

arr = [2, 3, 5, 8, 11, 30, 55, 88, 233, 534, 553]
puts search(arr, 11)
puts search(arr, 233)
puts search(arr, 12)

