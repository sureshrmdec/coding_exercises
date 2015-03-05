#http://interactivepython.org/runestone/static/pythonds/Graphs/BuildingtheKnightsTourGraph.html
#The knight’s tour puzzle is played on a chess board with a single chess piece, the knight. 
#The object of the puzzle is to find a sequence of moves that allow the knight to visit 
#every square on the board exactly once. One such sequence is called a “tour.” 
#The knight’s tour puzzle has fascinated chess players, mathematicians and computer 
#scientists alike for many years. The upper bound on the number of possible legal 
#tours for an eight-by-eight chessboard is known to be 1.305×1035; however, 
#there are even more possible dead ends. Clearly this is a problem that requires 
#some real brains, some real computing power, or both.
#
#To represent the knight’s tour problem as a graph we will use the following two ideas: 
#Each square on the chessboard can be represented as a node in the graph. Each 
#legal move by the knight can be represented as an edge in the graph. 

class Vertex:
    def __init__(self,key):
        self.id = key
        self.connectedTo = {}

    def addNeighbor(self,nbr,weight=0):
        self.connectedTo[nbr] = weight

    def __str__(self):
        return str(self.id) + ' connectedTo: ' + str([x.id for x in self.connectedTo])

    def getConnections(self):
        return self.connectedTo.keys()

    def getId(self):
        return self.id

    def getWeight(self,nbr):
        return self.connectedTo[nbr]



class Graph:
    def __init__(self):
        self.vertList = {}
        self.numVertices = 0

    def addVertex(self,key):
        self.numVertices = self.numVertices + 1
        newVertex = Vertex(key)
        self.vertList[key] = newVertex
        return newVertex

    def getVertex(self,n):
        if n in self.vertList:
            return self.vertList[n]
        else:
            return None

    def __contains__(self,n):
        return n in self.vertList

    def addEdge(self,f,t,cost=0):
        if f not in self.vertList:
            nv = self.addVertex(f)
        if t not in self.vertList:
            nv = self.addVertex(t)
        self.vertList[f].addNeighbor(self.vertList[t], cost)

    def getVertices(self):
        return self.vertList.keys()

    def __iter__(self):
        return iter(self.vertList.values())

def knightGraph(bdSize):
    ktGraph = Graph()
    for row in range(bdSize):
       for col in range(bdSize):
           nodeId = posToNodeId(row,col,bdSize)
           newPositions = genLegalMoves(row,col,bdSize)
           for e in newPositions:
               nid = posToNodeId(e[0],e[1],bdSize)
               ktGraph.addEdge(nodeId,nid)
    return ktGraph
ves(x,y,bdSize):
    newMoves = []
    moveOffsets = [(-1,-2),(-1,2),(-2,-1),(-2,1),
                   ( 1,-2),( 1,2),( 2,-1),( 2,1)]
    for i in moveOffsets:
        newX = x + i[0]
        newY = y + i[1]
        if legalCoord(newX,bdSize) and \
                        legalCoord(newY,bdSize):
            newMoves.append((newX,newY))
    return newMoves

def legalCoord(x,bdSize):
    if x >= 0 and x < bdSize:
        return True
    else:
        return False

def posToNodeId(row, column, board_size):
    return (row * board_size) + column

def genLegalMoves(x,y,bdSize):
    newMoves = []
    moveOffsets = [(-1,-2),(-1,2),(-2,-1),(-2,1),
                   ( 1,-2),( 1,2),( 2,-1),( 2,1)]
    for i in moveOffsets:
        newX = x + i[0]
        newY = y + i[1]
        if legalCoord(newX,bdSize) and \
                        legalCoord(newY,bdSize):
            newMoves.append((newX,newY))
    return newMoves

def legalCoord(x,bdSize):
    if x >= 0 and x < bdSize:
        return True
    else:
        return False


#Whereas the breadth first search algorithm discussed in the previous section builds a 
#search tree one level at a time, a depth first search creates a search tree by 
#exploring one branch of the tree as deeply as possible. In this section we 
#will look at two algorithms that implement a depth first search. The first 
#algorithm we will look at directly solves the knight’s tour problem by explicitly 
#forbidding a node to be visited more than once. The second implementation is 
#more general, but allows nodes to be visited more than once as the tree is 
#constructed. The second version is used in subsequent sections to develop 
#additional graph algorithms.
#

#The knightTour function takes four parameters: n, the current depth in the search tree; path, 
#a list of vertices visited up to this point; u, the vertex in the graph we wish to explore; 
#and limit the number of nodes in the path. The knightTour function is recursive. When the 
#knightTour function is called, it first checks the base case condition. If we have a path 
#that contains 64 vertices, we return from knightTour with a status of True, indicating 
#that we have found a successful tour. If the path is not long enough we continue to 
#explore one level deeper by choosing a new vertex to explore and calling knightTour 
#recursively for that vertex.
#
#DFS also uses colors to keep track of which vertices in the graph have been visited. 
#Unvisited vertices are colored white, and visited vertices are colored gray. 
#If all neighbors of a particular vertex have been explored and we have not yet 
#reached our goal length of 64 vertices, we have reached a dead end. When we 
#reach a dead end we must backtrack. Backtracking happens when we return from 
#knightTour with a status of False. In the breadth first search we used a queue 
#to keep track of which vertex to visit next. Since depth first search is recursive, 
#we are implicitly using a stack to help us with our backtracking. When we return 
#from a call to knightTour with a status of False, in line 11, we remain inside 
#the while loop and look at the next vertex in nbrList.
#
def knightTour(n,path,u,limit):
        u.setColor('gray')
        path.append(u)
        if n < limit:
            nbrList = list(u.getConnections())
            i = 0
            done = False
            while i < len(nbrList) and not done:
                if nbrList[i].getColor() == 'white':
                    done = knightTour(n+1, path, nbrList[i], limit)
                i = i + 1
            if not done:  # prepare to backtrack
                path.pop()
                u.setColor('white')
        else:
            done = True
        return done



