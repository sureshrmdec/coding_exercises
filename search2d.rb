###--------------------------------------------------------------------------------------------------###
### @author Shahzad Bhatti
### @doc Given 2D array of character and character pattern. WAP to find if pattern exists in 2d array
###--------------------------------------------------------------------------------------------------###

def search2d(array, str)
   return false if str.length > array.length*2
   visited = Hash.new {|h,k| h[k] = Hash.new {|hh,kk| hh[kk] = false} }
   return search2d_pattern(array, str, visited, 0, 0, array.length, array.length, 0)
end

def search2d_pattern(array, str, visited, x, y, nrow, ncol, level)
   return true if level == str.length
   return false if x == nrow || y == ncol
   return false if visited[x][y]
   if array[x][y] != str[level].chr && level == 0
      if x < nrow - 1
         return search2d_pattern(array, str, visited, x+1, y, nrow, ncol, level); # same row
      elsif y < ncol - 1
         return search2d_pattern(array, str, visited, 0, y+1, nrow, ncol, level); # first element from same column
      else 
         return false;
      end
   elsif array[x][y] == str[level].chr
      visited[x][y] = true
      # finding subpattern in 8 neighbours     
      res = (x > 0                                    ? search2d_pattern(array, str, visited, x-1, y, nrow, ncol, level+1) :   false) ||
            (res = x < (nrow - 1)                     ? search2d_pattern(array, str, visited, x+1, y, nrow, ncol, level+1) :   false) ||
            (res = y > 0                              ? search2d_pattern(array, str, visited, x, y-1, nrow, ncol, level+1) :   false) ||
            (res = y < (ncol - 1)                     ? search2d_pattern(array, str, visited, x, y+1, nrow, ncol, level+1) :   false) ||
            (res = x < (nrow - 1) && (y < ncol -1)    ? search2d_pattern(array, str, visited, x+1, y+1, nrow, ncol, level+1) : false) ||
            (res = x < (nrow - 1) && y > 0            ? search2d_pattern(array, str, visited, x+1, y-1, nrow, ncol, level+1) : false) ||
            (res = x > 0 && y < (ncol - 1)            ? search2d_pattern(array, str, visited, x-1, y+1, nrow, ncol, level+1) : false) ||
            (res = x > 0 && y > 0                     ? search2d_pattern(array, str, visited, x-1, y-1, nrow, ncol, level+1) : false);

      visited[x][y] = false
      res;
    else
      false;
    end
end
array = [
['A','C','P','R','C'],
['X','S','O','P','C'],
['V','O','V','N','I'],
['W','G','F','M','N'],
['Q','A','T','I','T']
]   
   
puts search2d(array, "MICROSOFT")



