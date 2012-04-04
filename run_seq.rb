###--------------------------------------------------------------------------------------------------###
### @author Shahzad Bhatti
### @doc There is a sequence {a1, a2, a3, a4, ..... aN}. A run is the maximal
###     strictly increasing or strictly decreasing continuous part of the sequence. Eg.
###     If we have a sequence {1,2,3,4,7,6,5,2,3,4,1,2} We have 5 possible runs
###     {1,2,3,4,7}, {7,6,5,2}, {2,3,4}, {4,1} and {1,2}.
#
### Given four numbers N, M, K, L. Count the number of possible sequences of N
### numbers that has exactly M runs, each of the number in the sequence is less
### than or equal to K and difference between the adjacent numbers is less than
### equal to L.
#
###--------------------------------------------------------------------------------------------------###

class RunSeq
    attr_accessor :sets

    # pos seq of n
    # with m runs
    # nums in seq <= k
    # adj diff <= l
    def seq(n, m, k, l)
        lists = []
        #Get all combinations of sequnces up to N
        (0...n).each do |j|
            #If lists is empty add the first up to K numbers
            size = lists.size
            if size == 0
                (1..k).each do |i|
                    lists << [i]
                end
            end
            # Iterate up to the current size of the list.
            # Add up to K to the end of each sublist
            # The iterations follow this pattern,
            # 1,2,3,4,... K
            # 11,21,31,41,12,22,...,13,23.....K1
            (0...size).each do |s|
                list = lists[s]
                newL = list.clone
                (1..k).each do |i|
                    temp = []
                    if i == 1
                        list << i
                    else 
                        for x in newL
                            temp << x
                        end
                        temp << i
                        lists << temp
                    end
                end
            end
        end
        pk = lists[-1]
        finalLists = []
        for list in lists
            index = 0
            sets = []
            while (list.size > 0 && (index + 1) < list.size)
                prev = list[index]
                nxt = list[index+1]
                #Check if pattern exists , if it doesnt then add the new set
                if (prev + l != nxt && prev - l != nxt) 
                    sub = list.slice(0, index+2)
                    sets << sub
                    if list.size() >= index+1
                        list = list.slice(index+1, list.size)
                        index = 0
                        if list.size == 1
                            sets << list
                        end
                    end
                else
                    index+=1
                    if index + 1 == list.size() || list.size()==1
                        sets << list
                    end
                end
            end
            if sets.size == m
                finalLists << sets
            end
        end
        finalLists 
    end
end

lists = RunSeq.new.seq(5, 2, 6, 1)
puts lists.size
for list in lists
    puts list.inspect
end
