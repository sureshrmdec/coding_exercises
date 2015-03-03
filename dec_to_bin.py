# http://interactivepython.org/runestone/static/pythonds/BasicDS/ConvertingDecimalNumberstoBinaryNumbers.html
#
def divideBy2(decNumber):
    remstack = []

    while decNumber > 0:
        rem = decNumber % 2
        remstack.insert(0, rem)
        decNumber = decNumber // 2

    binString = ""
    while len(remstack) > 0:
        binString = binString + str(remstack.pop())

    return binString

def baseConverter(decNumber,base):
    digits = "0123456789ABCDEF"

    remstack = []

    while decNumber > 0:
        rem = decNumber % base
        remstack.insert(0, rem)
        decNumber = decNumber // base

    newString = ""
    while len(remstack) > 0:
        newString = newString + digits[remstack.pop()]

    return newString

print(divideBy2(233))
print(baseConverter(25,2))
print(baseConverter(25,16))
