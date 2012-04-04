###--------------------------------------------------------------------------------------------------###
### @author Shahzad Bhatti
### @doc Generate permutations of a string
###--------------------------------------------------------------------------------------------------###

def perms(str)
    return [""] if str == nil || str.length == 0
    first = str.slice(0, 1)
    words = perms(str.slice(1,str.length))
    perms = []
    for word in words
        (0..word.length).each do |j|
            perms << word.slice(0,j) + first + word.slice(j,word.length)
        end
    end
    perms
end

puts perms("abc").inspect
