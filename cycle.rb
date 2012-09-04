class Node
  attr_accessor :value, :next
  def initialize(val)
    self.value = val
  end

  def to_s
    self.value
  end

  def cycle?
    slow = fast = self
    while true
      slow = slow.next
      if fast.next
        fast = fast.next.next
      else
        return false
      end
      return false if slow.nil? || fast.nil?
      return true if slow == fast
    end
  end
end

head = Node.new(1)
current = head
(2..10).each do |i|
  newNode = Node.new(i)
  current.next = newNode
end
puts head.cycle?
current.next = head
puts head.cycle?
