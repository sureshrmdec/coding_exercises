###--------------------------------------------------------------------------------------------------###
### @author Shahzad Bhatti
### @doc sort an array using insertion sort
#
###--------------------------------------------------------------------------------------------------###



def insertion_sort(arr)
    (1...arr.length).each do |j|
       i = j - 1
       value = arr[j]
       while (i >= 0 && arr[i] > value)
         puts "storing i #{i} value #{arr[i]} into #{i+1} #{arr[i+1]}"
          arr[i+1] = arr[i]
          i -= 1
       end 
         puts "storing last value #{value} into #{i+1} #{arr[i+1]}"
       arr[i+1] = value 
       puts "round #{j} #{arr.inspect}"
    end
    arr
end

arr = [8, 3, 5, 10, 2, 1]
puts("before " << arr.inspect)
puts("after " << insertion_sort(arr).inspect)
