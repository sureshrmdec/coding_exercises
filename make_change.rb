###--------------------------------------------------------------------------------------------------###
### @author Shahzad Bhatti
### @doc Given two words in a large text file, find make_change distance (in terms of words) 
###     between them in the file.
###--------------------------------------------------------------------------------------------------###

def make_change(n, denom)
    next_denom = 0
    case denom
    when 25
        next_denom = 10
    when 10
        next_denom = 5
    when 5
        next_denom = 1
    when 1
        return 1
    end

    ways = 0
    i = 0
    while (i*denom <= n)
        ways += make_change(n - i * denom, next_denom)
        i += 1
    end
    ways
end

puts make_change(100, 25)
