require 'set'

class TestDataSet
  def self.create_graph() 
    vertices = []
    edges = []
    (0..11).each do |i|
      loc = Vertex.new("Node_#{i}")
      vertices << loc
    end

    edges << Edge.new(vertices[0], vertices[1], 85)
    edges << Edge.new(vertices[0], vertices[2], 217)
    edges << Edge.new(vertices[0], vertices[4], 173)
    edges << Edge.new(vertices[2], vertices[6], 186)
    edges << Edge.new(vertices[2], vertices[7], 103)
    edges << Edge.new(vertices[3], vertices[7], 183)
    edges << Edge.new(vertices[5], vertices[8], 250)
    edges << Edge.new(vertices[8], vertices[9], 84)
    edges << Edge.new(vertices[7], vertices[9], 167)
    edges << Edge.new(vertices[4], vertices[9], 502)
    edges << Edge.new(vertices[9], vertices[10], 40)
    edges << Edge.new(vertices[1], vertices[10], 600)
    return Graph.new(vertices, edges)
  end  
end 


class Vertex 
  attr_accessor :name 
  def initialize(name)
    self.name = name 
  end  
  def hash
    name.hash
  end
  def <=>(other)
    name <=> other.name
  end
  def to_s
    "#{name}"
  end
end  


class Edge
  attr_accessor :source
  attr_accessor :destination
  attr_accessor :weight
  def initialize(source, destination, weight)
    self.source = source 
    self.destination = destination
    self.weight = weight
  end 
  def hash
    source.hash ^ destination.hash
  end
  def <=>(other)
    to_key <=> other.to_key
  end
  def to_s
    "#{source} #{destination} = #{weight}"
  end
  def to_key
    "#{source} #{destination}"
  end
end  

class Graph 
  attr_accessor :vertices
  attr_accessor :edges 
  def initialize(vertices, edges)
    self.vertices = vertices
    self.edges = edges 
  end
end

class DijkstraAlgorithm 
  attr_accessor :nodes #Vertex array
  attr_accessor :edges #Edge array 
  attr_accessor :settledNodes #set of vertices
  attr_accessor :unSettledNodes #set of vertices
  attr_accessor :predecessors # dictionary of vertex and its predcessor (both Vertex)
  attr_accessor :distance # dictionary of vertex and distance (vertex to integer)

  def initialize(graph)
    self.nodes = Array.new(graph.vertices)
    self.edges = Array.new(graph.edges)
  end

  def execute(source) #source is Vertex
    self.settledNodes = Set.new # set of vertices
    self.unSettledNodes = Set.new # set of vertices
    self.distance = {} #dictionary of vertex and distance (vertex to integer)
    self.predecessors = {} # dictionary of vertex and its predcessor (both Vertex)
    self.distance[source] = 0
    self.unSettledNodes << source
    while unSettledNodes.count > 0
      node = getMinimum(unSettledNodes) #vertex
      self.settledNodes << node
      self.unSettledNodes.delete(node)
      self.findMinimalDistances(node)
    end 
  end

  def findMinimalDistances(node) #node is Vertex, returns nothing
    adjacentNodes = getNeighbors(node) # returns list of vertices
    adjacentNodes.each do |target|
      d = getShortestDistance(node) + getDistance(node, target)
      if getShortestDistance(target) > d
        self.distance[target] = d
        self.predecessors[target] = node
        self.unSettledNodes << target
      end
    end
  end

  def getDistance(node, target) # node and target are Vertex and it returns distance in number
    self.edges.each do |edge|
      return edge.weight if edge.source == node && edge.destination == target
    end 
    raise Exception.new("failed to find distance!")
  end

  def getNeighbors(node) # takes a vertex and return list of vertices 
    neighbors = []  # array of vertices
    self.edges.each do |edge|
      if edge.source == node && !isSettled(edge.destination)
        neighbors << edge.destination
      end
    end
    return neighbors
  end

  def getMinimum(vertices) #takes set of vertices and returns minimum vertex
    minimum = nil
    vertices.each do |vertex|
      if minimum == nil
        minimum = vertex
      else
        minimum = vertex if getShortestDistance(vertex) < getShortestDistance(minimum)
      end
    end
    return minimum
  end

  def isSettled(vertex) #takes vertex and returns true/false
    return settledNodes.include?(vertex)
  end

  def getShortestDistance(destination)  # takes vertex and returns distance number
    return distance[destination] || Float::INFINITY
  end

   # This method returns the path from the source to the selected target and
   # nil if no path exists
  def getPath(target)  # takes vertex and return list (linked) of vertices
    path = []
    step = target
    # check if a path exists 
    return path if self.predecessors[step] == nil
    path << step
    while self.predecessors[step] != nil
      step = predecessors[step]
      path << step
    end 
    # Put it into the correct order 
    return path.reverse
  end
end


graph = TestDataSet.create_graph()
dijkstra = DijkstraAlgorithm.new(graph) 
from = graph.vertices[0]
dijkstra.execute(from)
 
path = dijkstra.getPath(graph.vertices[10]) 
if path 
  path.each do |vertex|
    puts(vertex)
  end 
else 
  puts("Could not find path from #{from}")
end

