#!/bin/ruby
time = gets.strip
nums = time.gsub(/[a-zA-Z]/, '').split(':').map {|s|s.to_i}
nums[0] = (nums[0] + 12) if nums[0] < 12 && time =~ /PM/
nums[0] = 0 if nums[0] == 12 && time =~ /AM/
new_time = nums.map {|n|"%02d" % n}.join(':')
puts(new_time)

