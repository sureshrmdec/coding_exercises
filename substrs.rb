###--------------------------------------------------------------------------------------------------###
### @author Shahzad Bhatti
### @doc Generate all the possible substrings using the characters of a given
#string. 
#
###--------------------------------------------------------------------------------------------------###

def do_substrs(prefix, rest, list)
    if rest.length == 0
        list << prefix if prefix.length > 0
    else
        do_substrs(prefix + rest.slice(0, 1), rest.slice(1, rest.length), list)
        do_substrs(prefix, rest.slice(1, rest.length), list)
    end
    list
end

def substrs(str)
  str != nil ? do_substrs("", str, []) : []
end
puts substrs("abc").inspect
