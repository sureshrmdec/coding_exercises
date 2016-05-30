#
# Suppose you’re a farmer with a plot of land.
# You want to divide this farm evenly into square 
# plots. 
# 
def square_size(width, height) 
  size = width * height
  min = [width, height].min 
  minSize = min * min 
  remaining = size - minSize 
  if remaining > 0 
    if width == min 
      return square_size(width, height - min)
    else
      return square_size(width - min, height)
    end
  else    
    return min 
  end 
end 

puts square_size(400, 640)
