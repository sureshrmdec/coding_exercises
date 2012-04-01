# not yet complete
class Median
    attr_accessor :min_heap, :max_heap

    def initialize
        self.min_heap = []
        self.max_heap = []
    end

    def add n
        if min_heap.length == max_heap.length
            if min_heap.length > 0 && n > min_heap[0] 
                max_heap.insert(0, min_heap[0])
                min_heap.delete_at(0)
                min_heap.insert(0, n)
            else
                max_heap.insert(0, n)
            end
        else
            if max_heap.length > 0 && n < max_heap[0]
                min_heap.insert(0, max_heap[0])
                max_heap.delete_at(0)
                max_heap.insert(0, n)
            else
                min_heap.insert(0, n)
            end
        end
    end

    def median
        return nil if min_heap.length == 0 && max_heap.length == 0
        return min_heap[0] if max_heap.length == 0
        return max_heap[0] if min_heap.length == 0

        if max_heap.length == min_heap.length
            return (min_heap[0] + max_heap.length)  / 2
        elsif max_heap.length > min_heap.length
            return max_heap[0]
        else
            min_heap[0]
        end
    end
end

m = Median.new
m.add(3)
m.add(5)
m.add(7)
m.add(12)
m.add(13)
m.add(14)
m.add(21)
m.add(23)
m.add(23)
m.add(23)
m.add(23)
m.add(29)
m.add(39)
m.add(40)
m.add(56)

puts m.min_heap.inspect
puts m.max_heap.inspect
puts m.median
