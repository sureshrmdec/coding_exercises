###--------------------------------------------------------------------------------------------------###
### @author Shahzad Bhatti
### @doc Given two numbers m and n, write a method to return the first number r that is
### divisible by both (e.g., the least common multiple) string. 
#
###--------------------------------------------------------------------------------------------------###

def gcd(a, b)
    tmp = 0
    while(b != 0)
        tmp = b
        b = a % b
        a = tmp
    end
    tmp
end

def lcm(a, b)
  gcd = gcd(a,b)
  a*b/gcd
end
puts lcm(10, 24)
puts lcm(3, 6)
