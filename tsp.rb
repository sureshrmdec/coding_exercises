class HeapElement
   attr_accessor :index, :key 
   def initialize(index, key)
      self.index = index 
      self.key = key
   end
end


class Bheap {
   attr_accessor :heap, :size, :watch 
   def watch_init(max)
   # initialize the watch array wich keeps truck of the place
   # of every point inside the heep tree.
   # Every point is represented by it's index in the xarray.
      self.watch = []
      (0...max).each do |i|
         self.watch[i] = i+1
      end
   end

  # computing the indices of parent and childs of node i
  def parent(i)
      (int)i/2
  }

  def left(i)
      i*2
  end

  def right(i)
      i*2 + 1
  end

  # the constuctor of the Heap -
  # creates a new structure of binary Heap (an array) and 
  # intializes it.
  def initialize(num_of_elem, key0, keyall) 
    self.heap = Array.new(num_of_elem+1)
    self.heap[1] = HeapElement.new(0,key0)
    (2..num_of_elem).each do |i|
       self.heap[i] = HeapElement.new(i-1,keyall)
    end
    self.size = num_of_elem
    watch_init()
  end

  def heapify(i)
   smallest = i
   l = left(i)
   r = right(i)
   if l <= self.size && self.heap[l].key < self.heap[i].key
      smallest = l
   elsif r <= self.size && self.heap[r].key < self.heap[smallest].key
      smallest = r 
   end
   if smallest != i
     exchange(i,smallest)
     heapify(smallest)
   end
  end

  # performs Heap[ind1] <-> Heap[ind2]
  def exchange(ind1, ind2) 
    self.watch[self.heap[ind1].index] = ind2
    self.watch[self.heap[ind2].index] = ind1
    temp = HeapElement.new(self.heap[ind1].index,self.heap[ind1].key)
    self.heap[ind1].index = Heap[ind2].index
    self.heap[ind1].key = Heap[ind2].key
    self.heap[ind2].index = temp.index
    self.heap[ind2].key = temp.key
  end

  def move(dest, source) 
   self.heap[dest].index = self.heap[source].index
   self.heap[dest].key = self.heap[source].key
   self.watch[self.heap[source].index] = dest
  end


  #returns the index of point with smallest value of key
  def extractMin
   ind = self.heap[1].index
   move(1,heapSize)
   self.size--;
   heapify(1);
   ind;
  end

  # changes key value of specified node to smaller one
  def decreaseKey(ind, key) 
    i = self.watch[ind]  # get position of node in the heap
    p = parent(i)
    while i > 1 && self.heap[p].key > key
       move(i,p)
       i = p
       p = parent(i)
    end 
    self.heap[i].key = key
    self.heap[i].index = ind
    watch[ind] = i
  end

  def empty?
    self.size == 0
   end
end


class Node  {
  attr_accessor :index, :degree, :edge_list, :circleNum

  def initialize(ind)
   self.index = ind
   self.degree = 0
   self.edgeList = []
   self.circleNum = -1
  end

  def addEdge(endpoint) {
   edgeList.push(endpoint)
   degree+=1
  end

  def getEdge
   return -1 if self.degree = 0
     int size = edgeList.size();
     Integer N = (Integer) (edgeList.firstElement());//get next neighbor
     int n = N.intValue();

     edgeList.removeElementAt(0); // remove the edge
     degree--;
     // remove same edge from the neighbor's list
     TspAlg.Graph[n].edgeList.removeElement(new Integer(index));
     TspAlg.Graph[n].degree--;

     return n;
    }
   else return (-1);
  }
} // end Node class



/******************** class Couple **********************************/
class Couple  {
  int node1;
  int node2;
  double distance;

  public Couple(int ind1,int ind2) {
    node1=ind1;
    node2=ind2;
    distance=TspAlg.distMatr[ind1][ind2];
  }
}

/******************** class circleElement *******************************/
class circleElement  {
 int index;
 int another_circle;

 public circleElement(int ind)  {
  index=ind;
  another_circle=-1;
 }
}

/******************** class Line *******************************/
class Line  {
  // y=ax+b
  int point1,point2;
  int x1,y1,x2,y2;
  int xmin,xmax,ymin,ymax;
  float a;
  float b;
  boolean slopeUndefine = false;

  public Line(int node1,int node2) {
     point1=node1;
     point2=node2;

     x1=TspAlg.xarr[node1];
     x2=TspAlg.xarr[node2];
     y1=TspAlg.yarr[node1];
     y2=TspAlg.yarr[node2];
     xmin=Math.min(x1,x2);
     xmax=Math.max(x1,x2);
     ymin=Math.min(y1,y2);
     ymax=Math.max(y1,y2);

     if (x1==x2)
         slopeUndefine=true;
     else if (y1==y2)
        {
         a = (float)0;
         b=y1;
        }
     else
        {
         a = (float) (y2-y1)/(x2-x1);
         b=y1-a*x1;
        }
  }

  boolean intersection(Line line) {
    float x,y;
    if (this.slopeUndefine)
      {
       if (line.slopeUndefine)
          return false;
       else if (line.a==0)
          {x=this.x1; y=line.y1;}
       else
          {
          x=this.x1;
          y=line.a*x+line.b;
          }
      }

    else if (line.slopeUndefine)
      {
       if (this.a==0)
          {x=line.x1; y=this.y1;}
       else
          {
          x=line.x1;
          y=this.a*x+this.b;
          }
      }
    else if (this.a==line.a)  //moth slopes defined
       return false;
    else
       {
        x= (float) (line.b-this.b)/(this.a-line.a) ;
        y= (float) (this.a*line.b-line.a*this.b)/(this.a-line.a);
       }
    // check if intersection point (x,y) is on lines
    if ((x<=this.xmax && x<=line.xmax) &&
        (x>=this.xmin && x>=line.xmin) &&
        (y<=this.ymax && y<=line.ymax) &&
        (y>=this.ymin && y>=line.ymin))
        return true;
    else
       return false;
  }
}
/******************** class MyChoice ********************************/
/* Extend Choice class with an identifer,so each Choice may be 
  distinguished in action func. */
 class MyChoice extends java.awt.Choice{ 
    String Ident;
 }                       


/******************** class TspAlg *******************************/
public class TspAlg extends Applet
 {
  public static final int STOP=1,PREODER=2,ALG1=3,ALG2=4,ALG3=6,FINAL=5,CHANGED=7,SKIP=8,BEST=9,INTERSEC=20,WAIT=21;
  public static final int MST=10,TSP1=11,FINAL1=12,MATCH=13,TSP2=14,ODDS=15,EULER=16,BOTH=17,IMPROVE=18,INITSOL=19;
  public static final int GLOBALMATCH=25;
  int Mode = 0,AlgMode=ALG1;
  public static final int PAINT=40,NOPAINT=41,REPAINT=42;
  public static final int BOTTON=30,UPPER=31,CENTER=32;
  double MAX=100000;
  boolean exhastMode=false;
  // these arrays hold input points coordinates
  static int xarr[]=new int[100];
  static int yarr[]=new int[100];
  int Order[];// Order index of cities in iterative solution

  // these arrays hold points in order,representing
  int xfinal1[],yfinal1[]; //first Algorithm's output
  int xfinal2[],yfinal2[]; //second Algorithm's output
  double alg1Weight=MAX, alg2Weight=MAX, alg3Weight=MAX;
  double alg1Wimproved=MAX, alg2Wimproved=MAX;
  double alg1WnoIntersec=MAX,alg2WnoIntersec=MAX;
  double Oldcost  =    0;  // for improve iterations
  double Newcost        =    0;
  int odd[];
  Couple Match[],globalMatch[];
  static int cityNum = 0;
  int oddNum=0,matchNum=0;  // number of odd nodes in MST
  int numOfEdges=0;  // initial number of edges in eulerian graph
  int restofEdges; // number of edged in graph that are still not in circle
  Vector Circles;  // holds arrays which represent circles in graph
  int outerIndex;    //for
  int eulertour[];  //    TraverseCircle
  int tspPath[],tsp1NoIntersec[],tsp2NoIntersec[];
  int tsp1Improved[],tsp2Improved[];
  static double distMatr[][];
  int edgesMatr[][];   // this matritz will indicate wether each edge is double or not
  boolean tsp1Matr[][],tsp2Matr[][];
  int parent[], preoderList[];
  int list_index=0;
  static Node Graph[];  // holds all the nodes of graph
  int Iterations        = 1000;

  Button stopButton,cleanButton,gostepButton,originalButton;
  Button pathButton,nextButton,startButton,improveButton;
  Button beginButton,bothButton,intersecButton,bestButton;
  Button matchButton;
  Label lab1,lab2,lab3,lab4;
  MyChoice algorithms,iterations;
  CheckboxGroup choseAlg;
  Checkbox algor1,algor2,algor3,algor4;
  Color BackGround = Color.green;
  int x00               =  200;
  int y00               =   30;
  int window_height = 300;
  int window_width  = 400;

/*-----------support for both algorithms------------*/
  void calculateDist()    // Calculate the distance
    {
     distMatr=new double[cityNum][cityNum];
     for (int i=0;i< cityNum;i++)
       for (int j=0;j< cityNum;j++)
        {
         if (i<j)
           distMatr[i][j]=Math.sqrt((xarr[i]-xarr[j])*(xarr[i]-xarr[j])
                                    +(yarr[i]-yarr[j])*(yarr[i]-yarr[j]));
         else if (i>j)
           distMatr[i][j]=distMatr[j][i];
         else
           distMatr[i][j]=0;
        }
    }

  void MSTcreate() {
    //creating binary heap with node 0 as a root
    Bheap Q = new Bheap(cityNum,0,1000);
    /*
      Parent array holds definition of node's parent during construction of
      the minimum spanning tree.
    */
    parent = new int[cityNum];
    parent[0]=-1;  // -1 for null as parent of root


    /*
      Initialize Key array.
      The value of key is the distance of the node from the current
      subtree of MST.
      1000 equals to infinity in this case.
    */
    double Key[] = new double[cityNum];
    Key[0]=0;
    
    for (int i=1; i<cityNum;Key[i++]=1000);


    /*
       bitvector array is used to indicate wich nodes are in Q
       bitvector[i]=true <=> node i is in heap Q
       all bitvector elements intialized to true
    */
    boolean bitvector[] = new boolean[cityNum];
    for (int i=0;i < cityNum;bitvector[i++]=true);
    if (distMatr==null)
       calculateDist();
    // the body of Prim's algorithm for finding minimum spanning tree
    int u;
    while (!Q.empty?())
     {
      u=Q.ExtractMin();
      bitvector[u]=false;  // u isn't in Q any more
      for (int v=0;v < cityNum;v++)
       {
        if (bitvector[v] && (distMatr[v][u] < Key[v]) )
          {
           parent[v]=u;
           Key[v]=distMatr[v][u];
           Q.decreaseKey(v,Key[v]);
          }
       }
     }
  }


double calculateTotal(int arr[]) {
    int ind1,ind2;
    double weight=0;
    ind1=arr[0];
    for(int i=1;i<=cityNum && i<arr.length;i++)
     {
       ind2=arr[i];
       weight+=distMatr[ind1][ind2];
       ind1=ind2;
     }
    return weight;
}

  // finds solution with mimnimum weight and returns it's code
  int findMinSolution() {
    double min1=Math.min(alg1Weight,alg2Weight);
    double min2=Math.min(alg1Wimproved,alg2Wimproved);
    double min3=Math.min(alg1WnoIntersec,alg2WnoIntersec);
    double min4=Math.min(min1,min2);
    double min5 =Math.min(min3,min4);
    double min =Math.min(min5,alg3Weight);

    if (min==alg1Weight)    return 1;
    if (min==alg2Weight)    return 2;
    if (min==alg3Weight)    return 3;
    if (min==alg1Wimproved) return 4;
    if (min==alg2Wimproved) return 5;
    if (min==alg1WnoIntersec) return 6;
    if (min==alg2WnoIntersec) return 7;

    return 0;
  }

  void TSPpath(int array[],int mode)  {
    if (mode==ALG1)
     {
      xfinal1 = new int[cityNum+1];
      yfinal1 = new int[cityNum+1];
      for(int i=0;i <= cityNum;i++)
       {
       xfinal1[i]=xarr[array[i]];
       yfinal1[i]=yarr[array[i]];
       }
     }

    if (mode==ALG2)
     {
      xfinal2 = new int[cityNum+1];
      yfinal2 = new int[cityNum+1];
      for(int i=0;i <= cityNum;i++)
       {
       xfinal2[i]=xarr[array[i]];
       yfinal2[i]=yarr[array[i]];
       }
     }

  }

// draws line from node with index'i' to node with index 'j'
void DrawLine(Graphics g,int i,int j,Color theColor,int mode) {
    int delta=0;
    g.setColor(theColor);

    if (mode==CENTER)
      {
       g.drawLine(xarr[i],yarr[i],xarr[j],yarr[j]);
       return;
      }

    if (mode==BOTTON)
      delta=3;
    else if (mode==UPPER)
      delta=-3;

    if (xarr[i]==xarr[j])
       g.drawLine(xarr[i]-delta,yarr[i],xarr[j]-delta,yarr[j]);
    else
       {
       float dx= xarr[i]-xarr[j];
       float dy= -(yarr[i]-yarr[j]); // because pixel y coordinate grows down
       float a = dy/dx;
       if ((a>=0 && a<=1) || (a<0 && a>=-1))
         g.drawLine(xarr[i],yarr[i]+delta,xarr[j],yarr[j]+delta);
       else if (a>1)
         g.drawLine(xarr[i]+delta,yarr[i],xarr[j]+delta,yarr[j]);
       else if (a< (-1))
         g.drawLine(xarr[i]-delta,yarr[i],xarr[j]-delta,yarr[j]);
       }
 }

  void tspMatrRepresent() {
    tsp1Matr = new boolean[cityNum][cityNum];
    tsp2Matr = new boolean[cityNum][cityNum];
    int ind1,ind2;
    // initialize both matriz
    for (int i=0;i<cityNum;i++)
      for (int j=0;j<cityNum;j++)
        {
         tsp1Matr[i][j]=false;
         tsp2Matr[i][j]=false;
        }
    // build tsp1 tour matritz representation
    for (int i=0;i<cityNum;i++)
      {
       ind1=preoderList[i];
       ind2=preoderList[i+1];
       tsp1Matr[ind1][ind2]=true;
       tsp1Matr[ind2][ind1]=true;
      }
    // build tsp1 tour matritz representation
    for (int i=0;i<cityNum;i++)
      {
       ind1=tspPath[i];
       ind2=tspPath[i+1];
       tsp2Matr[ind1][ind2]=true;
       tsp2Matr[ind2][ind1]=true;
      }
  }

 /****** for removing intersections************/
  void pathToLines(Line tspLines[],int path[]) {
      for (int i=0;i<cityNum;i++)
        tspLines[i] = new Line(path[i],path[i+1]);
  }

  int[] linesToPath(Line tspLines[]) {
     int path[] = new int[cityNum+1];

     for (int i=0;i<cityNum;i++)
        path[i]=tspLines[i].point1;
     path[cityNum]=0;
     return path;
  }



  void intersecTreet(Line tspLines[],int ind1,int ind2) {
    int temp,i11,i12,i21,i22;
    Line tempLines[] = new Line[cityNum];
    if (ind2<ind1)
      {
       temp=ind2;
       ind2=ind1;
       ind1=temp;
      }                         // ind1 should be lowest;

    i11=tspLines[ind1].point1;
    i12=tspLines[ind1].point2;
    i21=tspLines[ind2].point1;
    i22=tspLines[ind2].point2;

    tspLines[ind1]=new Line(i11,i21);
    for (int j=ind1+1;j<ind2;j++)
        tempLines[j]= new Line(tspLines[j].point2,tspLines[j].point1);
    for (int j=ind1+1;j<ind2;j++)
        tspLines[j]=tempLines[ind2+ind1-j];
    tspLines[ind2]=new Line(i12,i22);
  }

  int[] findIntersections() {
    Line tspLines[] = new Line[cityNum];
    if (AlgMode==ALG1)
       pathToLines(tspLines,preoderList);
    if (AlgMode==ALG2)
       pathToLines(tspLines,tspPath);
    boolean found = true;
    int run=0;
    while (found)
      {
         run++;
         found = false;
         for (int i=0; i<cityNum;i++)
           for (int j=0; j<cityNum; j++)
             {
              if ((i==j) || (i==j+1) || (j==i+1) || 
                  (i==0 && j==(cityNum-1)) || (j==0 && i==(cityNum-1)))
                 continue;
              if (tspLines[i].intersection(tspLines[j]))
                  {
                   intersecTreet(tspLines,i,j);
                   found=true;
                  }
                
             } //end for
        } // end while
    return linesToPath(tspLines);
  }


/**************************/
public void solve() {
   int i1,i2,temp;
   int i11,i12,i21,i22;
   boolean more;
   int tempOrder[] = new int[cityNum+1];
   int improved[] = new int[cityNum+1];

   // improve over previos improve
   if (AlgMode==ALG1 && Mode==IMPROVE)
     for (int i=0;i<=cityNum;i++)
        improved[i]=tsp1Improved[i];

   else if (AlgMode==ALG2 && Mode==IMPROVE)
     for (int i=0;i<=cityNum;i++)
        improved[i]=tsp2Improved[i];

   else if (AlgMode==ALG3)       // use Order[] in iterative alg.
     for (int i=0;i<=cityNum;i++)
        improved[i]=Order[i];

   // improve first time
   else if (AlgMode==ALG1)
     {
      Oldcost=alg1Weight;
      Newcost=0;
      for (int i=0;i<=cityNum;i++)
        improved[i]=preoderList[i];
     }

   else if (AlgMode==ALG2)
     {
      Oldcost=alg2Weight;
      Newcost=0;
      for (int i=0;i<=cityNum;i++)
        improved[i]=tspPath[i];
     }

   for (int i=1;i<=Iterations && !exhastMode;i++)
     {
      i1= (int)(cityNum*Math.random());// Find two random cities
      i2= (int)(cityNum*Math.random());

      if (i2<i1)
        {temp=i2;i2=i1;i1=temp;}        // i1 should be lowest;
      if (i1==i2)
        {
         if (i2==(cityNum-1))
             i1--;
         else i2++;
        }               // check ranges


      i11=improved[i1];
      i12=improved[i1+1];       // Find the city after city i1
      i21=improved[i2]; // Find the city after city i2
      if (i2==(cityNum-1))
         i22=improved[0];
      else
         i22=improved[i2+1];

      if ( (distMatr[i11][i12]+distMatr[i21][i22]) >
             (distMatr[i11][i21]+distMatr[i12][i22]) )
           {
           for (int j=i1+1;j<=i2;j++)
             tempOrder[j]=improved[j];
           for (int j=i1+1;j<=i2;j++)
             improved[j]=tempOrder[1+i2+i1-j];
          }
     }

    more=true;
    while (more)
     {
     more=false;   
     for (int i=0;i<cityNum && exhastMode;i++)
      for (int j=0;j<cityNum && exhastMode;j++)
       {
        i1=i;
        i2=j;
        if (i2<i1)
          {temp=i2;i2=i1;i1=temp;}      // i1 should be lowest;
        if (i1==i2)
          {
           if (i2==(cityNum-1))
              i1--;
           else i2++;
          }             // check ranges

        i11=improved[i1];
        i12=improved[i1+1];     // Find the city after city i1
        i21=improved[i2];       // Find the city after city i2
        if (i2==(cityNum-1))
           i22=improved[0];
        else
           i22=improved[i2+1];

        if ( (distMatr[i11][i12]+distMatr[i21][i22]) >
             (distMatr[i11][i21]+distMatr[i12][i22]) )
           {
           more=true;
           for (int k=i1+1;k<=i2;k++)
             tempOrder[k]=improved[k];
           for (int k=i1+1;k<=i2;k++)
             improved[k]=tempOrder[1+i2+i1-k];
          }
       }
     }            
    if (AlgMode==ALG1)
      {
       alg1Wimproved=calculateTotal(improved);
       tsp1Improved=improved;
      }
    if (AlgMode==ALG2)
      {
       alg2Wimproved=calculateTotal(improved);;
       tsp2Improved=improved;
      }
    if (AlgMode==ALG3)
      {
       alg3Weight=calculateTotal(improved);
       Order=improved;
      }

  }

/*-------------support for iterative algorithm---------*/
  // Generate initial solution
  void generateInitsol() {
     Order = new int[cityNum+1];
     for (int i=0;i<cityNum;i++)
        Order[i]=i;
     Order[cityNum]=0;
  }


/*-------------support for first algorithm---------*/
  void createList(Graphics g,int mode)  {
    preoderList = new int[cityNum+1];
    list_index=0;
    if (mode!=NOPAINT)
      {
       Font f = new Font("Helvitica",Font.BOLD,12);
       g.setFont(f);
      }
    preoderwalk(g,0,mode);
    preoderList[cityNum]=0;
  }

  // O(V^2)
  void preoderwalk(Graphics g,int j,int mode)  {
     preoderList [list_index++]=j;
     if (mode!=NOPAINT)
       g.drawString(""+(list_index-1),xarr[j],yarr[j]);
     for (int i=1;i<cityNum;i++)
     if (parent[i]==j)
        {
        if (mode!=NOPAINT)
          {
           // DrawLine(g,i,j)
           DrawLine(g,j,i,Color.black,BOTTON);
           if (mode!=REPAINT)
             {
              try {Thread.sleep(700);}
              catch (InterruptedException e) {
               System.out.println("interrupted"); }
             }
          }
        preoderwalk(g,i,mode);

        if (mode!=NOPAINT)
          {
           // DrawLine(g,i,j)
           DrawLine(g,i,j,Color.green.darker() ,UPPER);
           if (mode!=REPAINT)
             {
              try {Thread.sleep(700);}
              catch (InterruptedException e) {
              System.out.println("interrupted"); }

             }
          }
        }
  }

/*-------------support for second algorithm---------*/
  // creates representation of graph by lists of neughbors
  // and also updates matric representation of graph
  void createGraph() {
   Graph = new Node[cityNum];
   edgesMatr = new int[cityNum][cityNum];
   numOfEdges=0;
   // initialize Graph structure
   for (int i=0;i<cityNum;i++)
     Graph[i] = new Node(i);
   // initialize Matriz representation
   for (int i=0;i<cityNum;i++)
     for (int j=0;j<cityNum;j++)
        edgesMatr[i][j]=0;

   for (int i=1;i<cityNum;i++)
      {
       int j=parent[i];
       Graph[i].addEdge(j);
       Graph[j].addEdge(i);
       edgesMatr[i][j]=1;
       edgesMatr[j][i]=1;
       numOfEdges++;
      }
  }

  int findOdds()  {
    int j=0;
    odd = new int[cityNum];
    for (int i=0;i<cityNum;i++)
     if ( (Graph[i].degree % 2) != 0)  // check if node's degree is odd
       {
       odd[j]=i;
       j++;
       }
    return j;
  }

  Couple[] randomMatch(int nodes[],int nodesNum ) {
    boolean done[] = new boolean[nodesNum];
    int num,num0,ind1,ind2;
    int halfNum = (int) nodesNum/2;
    Couple match[] = new Couple[halfNum];
    for (int i=0;i<nodesNum;done[i++]=false);
    for (int i=0;i<halfNum;i++)
     {
        num0=i;
        while (done[num0]!=false)
          num0++;
        done[num0]=true;
        num=(int) (Math.random()*(nodesNum));
        while ((done[num]!=false) ||  (num==nodesNum))
          num=(int) (Math.random()*(nodesNum));
        done[num]=true;
        ind1=nodes[num0];
        ind2=nodes[num];
        match[i] = new Couple(ind1,ind2);
     }
    return match; 
  }

  double improve(int numOfMatches,Couple match[]) {
    double weight=0;
    for (int i=0;i<numOfMatches;i++)
      for(int j=i+1;j<numOfMatches;j++)
        {
         boolean chose1=false,chose2=false;
         double sum = match[i].distance + match[j].distance;
         int i1 = match[i].node1;
         int i2 = match[i].node2;
         int j1 = match[j].node1;
         int j2 = match[j].node2;
         double newsum1 = distMatr[i1][j1]+distMatr[i2][j2];
         double newsum2 = distMatr[i1][j2]+distMatr[i2][j1];

         if (newsum1<sum) {
            if (newsum2 < newsum1)
              chose2=true;
            else
              chose1=true; }
         else if (newsum2<sum)
              chose2=true;

         if (chose1)
           {
            match[i].node2=j1;
            match[i].distance=distMatr[i1][j1];
            match[j].node1=i2;
            match[j].distance=distMatr[i2][j2];
           }
         else if(chose2)
            {
            match[i].node2=j2;
            match[i].distance=distMatr[i1][j2];
            match[j].node2=i2;
            match[j].distance=distMatr[i2][j1];
            }
        }
    for (int i=0;i<numOfMatches;i++)
       weight+=match[i].distance;
    return weight;
  }

  double printMatch(Graphics g,Couple match[]) {
    double sum=0;
    int ind1,ind2;
    g.setColor(Color.black);
    for (int i=0;i<match.length;i++)
       {
        sum+=match[i].distance;
        ind1=match[i].node1;
        ind2=match[i].node2;
        if ((Mode!=GLOBALMATCH) && edgesMatr[ind1][ind2]==2)
          DrawLine(g,ind1,ind2,Color.black,UPPER);
        else
          DrawLine(g,ind1,ind2,Color.black,CENTER);
       }
    return sum;   
  }

  
  void updateGraph() {
    int ind1,ind2;
    for (int i=0;i<matchNum;i++)
       {
        ind1=Match[i].node1;
        ind2=Match[i].node2;
        Graph[ind1].addEdge(ind2);
        Graph[ind2].addEdge(ind1);
        edgesMatr[ind1][ind2]+=1;
        edgesMatr[ind2][ind1]+=1;
        numOfEdges++;
       }
  }




/*******************euler*******/

void EulerTour()  {
 boolean stop = false;
 int nextpoint,startpoint=0;
 int circleIndex=0;
 // number of edged in graph that are still not in circle
 int restOfEdges=numOfEdges;
 Circles = new Vector();

 while (! stop)
  {
   circleElement circle[] = new circleElement[restOfEdges+1];
   circle[0] = new circleElement(startpoint);
   Graph[startpoint].circleNum=circleIndex; // mark node for some circle
   nextpoint=startpoint;
   int tempedges=restOfEdges;

   // next loop creates a circle
   for (int i=1; i<=restOfEdges; i++ )
    {
     int ind;
     if ((ind=Graph[nextpoint].getEdge()) == -1)
       {
       if (nextpoint!=startpoint)     // just for check
         System.out.println("not a circle");
       break;
       }
     circle[i] = new circleElement(ind);
     tempedges--;
     nextpoint=ind;
     Graph[nextpoint].circleNum=circleIndex; // mark node for some circle
    }
   if (tempedges==0)  // no more circles to find.
     {
      Circles.addElement(circle);
      stop=true;
      continue;
     }

   int j,k;    // look for node in circle ,which can begin a new circle
   for (j=1;
        (j<restOfEdges) && (circle[j]!=null) && (Graph[circle[j].index].degree==0);
        j++);
   if (circle[j]!=null && j<restOfEdges)
     {
     startpoint=circle[j].index;
     circle[j].another_circle=++circleIndex;
     }
   else
     {
     for (j=0;j<cityNum &&
         ((Graph[j].degree==0) || (Graph[j].circleNum==-1));j++);
     int circlenum=Graph[j].circleNum;
     circleElement oldcircle[]=(circleElement[]) Circles.elementAt(circlenum);
     for (k=0;oldcircle[k]!=null && k<oldcircle.length && (oldcircle[k].index!=j);k++);
     if (oldcircle[k].index==j && oldcircle[k].another_circle==-1)
     {
     oldcircle[k].another_circle=++circleIndex;
     Circles.setElementAt(oldcircle,circlenum);
     startpoint=j;
     }
     else
      System.out.println("trouble in EulerTour");
     }
   Circles.addElement(circle);
   restOfEdges=tempedges;

  } //end ofwhile loop

} // end of EulerTour

 void TraverseCircle(int circle_index)
   {
   circleElement tempcircle[]=(circleElement[]) (Circles.elementAt(circle_index));
   for (int i=0;i<tempcircle.length && tempcircle[i]!=null;i++)
    {
     if (tempcircle[i].another_circle!=-1 && tempcircle[i].another_circle!=circle_index)
        TraverseCircle(tempcircle[i].another_circle);
     else
       {
       eulertour[outerIndex]=tempcircle[i].index;
       outerIndex++;
       }
    }
   }

 void TSPbuild() {
    tspPath = new int[cityNum+1];
    boolean already[] = new boolean[cityNum];
    int ind=0,node;
    for (int i=0;i<cityNum;already[i++]=false);
    for (int i=0;i<= numOfEdges;i++)
      {
       node = eulertour[i];
       if (!already[node])
         {
          tspPath[ind]=node ;
          already[node]=true;
          ind++;
         }
      }
    TSPpath(tspPath,ALG2);
 }



void drawAllBoxes(Graphics g) {
    Dimension d = size();  
    drawbox(g,0,0, d.width-1, d.height-1,false);
    drawbox(g,x00,y00,window_width,window_height,true);
    drawbox(g,x00,y00+window_height+2,window_width,65,true);

}

  void setControls(int mode) {
    // mode=1 =>new start, mode=0 => algorithm choice
    if (mode==1)  
     {
      add(stopButton);
      stopButton.disable();
      remove(lab1);
      remove(algorithms);
     } 
    remove(gostepButton);
    remove(beginButton);
    remove(nextButton);
    remove(pathButton);    
    remove(matchButton);
    remove(bothButton);
    remove(bestButton);
    remove(intersecButton);
    remove(originalButton);
    remove(improveButton);
    remove(iterations);
    remove(lab2);
    remove(lab3);
    remove(lab4);
  }

  void reshapeControls() {

    cleanButton.reshape( 25,y00+0*25,140,20); // insert new points
    stopButton.reshape( 25,y00+1*25, 140,20);    // stop insert points

    lab1.reshape( 25,y00+15+1*25,140,20);       
    algorithms.reshape( 25,y00+10+2*25,140,20); // Choice Alg

    gostepButton.reshape( 25,y00+30+3*25,140,20);       // go step by step
    pathButton.reshape( 25,y00+30+4*25,140,20); //solution      
    matchButton.reshape( 25,y00+40+5*25,140,20); //solution     

    lab2.reshape( 25,y00+30+3*25,140,20);       // go step by step
    beginButton.reshape( 25,y00+30+4*25,140,20); // start alg
    nextButton.reshape( 25,y00+30+5*25,140,20); // next step
    lab3.reshape( 25,y00+30+6*25,140,20);       // skip to solution


    intersecButton.reshape( 25,y00+30+6*25,140,20); 
    originalButton.reshape(25+15,y00+30+7*25,110,20);

    improveButton.reshape(25,y00+30+8*25,140,20);
    lab4.reshape(25,y00+30+9*25,80,20);

    iterations.reshape( 25, y00+30+10*25, 60, 20 );

    bothButton.reshape( 25,y00+window_height+67-45,140,20);
    bestButton.reshape( 25,y00+window_height+67-20,140,20);
}

 
  public void init()
   {
    setLayout(null);
    setBackground(BackGround);
    Font f = new Font("Helvetica",Font.BOLD,12);
    setFont(f);
    
    Dimension d = size();  
    window_width=d.width-220;
    window_height=d.height-120;
    
    cleanButton=new Button("Insert new points");
    add(cleanButton);

    stopButton=new Button("stop insert points");
    add(stopButton);

    lab1 = new Label("Choose Algorithm:",Label.LEFT);
    algorithms = new MyChoice();
    algorithms.Ident="AlgorithmOptions";
    algorithms.addItem("x2 Approx Alg");
    algorithms.addItem("x1.5 Approx Alg");
    algorithms.addItem("Iterative Alg");

    gostepButton = new Button("Go step by step");
    lab2 = new Label("Go step by step:",Label.LEFT);
    beginButton = new Button("Start Algorithm");
    nextButton = new Button("Next Step");
    lab3 = new Label("Skip straight to:",Label.LEFT);
    pathButton = new Button("Algorithm's Solution");
    
    matchButton = new Button("Match points");
 
    intersecButton = new Button("Remove Intersections");
    originalButton = new Button("View Original Tour");
    improveButton = new Button("Run Improve Iterations");
    lab4 = new Label( "Iterations Num           ", Label.CENTER );
    iterations = new MyChoice();
    iterations.Ident="Iterations";
    iterations.addItem("1");
    iterations.addItem("10");
    iterations.addItem("100");
   iterations.addItem("1000");
   iterations.addItem("5000");
   iterations.addItem("10000");
   iterations.addItem("exhaustive");
   iterations.select("1000");

    bothButton = new Button("Show Both Tours");
    bestButton = new Button("Best Solution");

    reshapeControls();
    setControls(1);

   }


  void BeginHendler() {
     if (cityNum==0)
        return ;
     if (parent==null)
        MSTcreate();  // => parent[]!=null
     Mode=MST;
     nextButton.enable();
  }


  void PreoderHendler() {
    Mode=PREODER; // => preoderList!=null
  }

  void tsp1Hendler() {
    if ((xfinal1==null) || (yfinal1==null))
       TSPpath(preoderList,ALG1); // => xfinal1,yfinal1!=null
    if (alg1Weight==MAX)
       alg1Weight=calculateTotal(preoderList); // => alg1Weight!=MAX
    Mode=TSP1;
  }

  void oddsHendler() {
     if ((Graph==null) || edgesMatr==null)
        createGraph();  // => Graph!=null

     if (odd==null)
        {
         oddNum=findOdds();
         matchNum=(int) oddNum/2;
        }
     Mode=ODDS;
  }

  void MatchHendler() {
    double matchWeight,sum;
    if (Match==null)
       {
        Match=randomMatch(odd,oddNum);
        matchWeight=improve(matchNum,Match);
        while (matchWeight!=(sum=improve(matchNum,Match)))
           matchWeight=sum;
        updateGraph();
       }
    Mode=MATCH;
  }

  void GlobalMatchHendler() {
    double matchWeight,sum;
    int nodesNum;
    if ((cityNum%2) == 0)
      nodesNum=cityNum;
    else // odd number of points were inserted => one point will be unmatched
      nodesNum=cityNum-1;
    int numOfMatches = (int) nodesNum/2;  
    
    if (globalMatch==null)
       {
        if (distMatr==null)
          calculateDist();
        int nodes[] = new int[nodesNum];
        for (int i=0;i<nodesNum;nodes[i]=i,i++);
        globalMatch=randomMatch(nodes,nodesNum);
        matchWeight=improve(numOfMatches,globalMatch);
        while (matchWeight!=(sum=improve(numOfMatches,globalMatch)))
           matchWeight=sum;
       }
    Mode=GLOBALMATCH;
  }  
  
  void EulerHendler() {
    if (Circles==null)
       EulerTour();
    if (eulertour==null)
       {
        eulertour = new int[numOfEdges+1];
        outerIndex=0;
        TraverseCircle(0);
       }
    Mode=EULER;
  }

  void tsp2Hendler() {
    if (tspPath==null)
       {
        TSPbuild();
        alg2Weight=calculateTotal(tspPath);
       }
    Mode=TSP2;

  }

  void StartHendler() {
    for(int j=0;j<xarr.length;xarr[j]=yarr[j]=0,j++);
    cityNum=0; oddNum=0; matchNum=0; numOfEdges=0;
    distMatr=null; edgesMatr=null; tsp1Matr=null; tsp2Matr=null;
    parent = null; preoderList = null;
    xfinal1=null; yfinal1=null; xfinal2=null; yfinal2=null;
    alg1Weight=MAX; alg2Weight=MAX; alg3Weight=MAX;
    alg1Wimproved=MAX; alg2Wimproved=MAX;
    alg1WnoIntersec=MAX; alg2WnoIntersec=MAX;
    Graph=null; odd=null; Match=null; globalMatch=null; Circles=null;
    eulertour=null; tspPath=null;Order=null;
    tsp1Improved=null; tsp2Improved=null;
    tsp1NoIntersec=null; tsp2NoIntersec=null;
    outerIndex=0; list_index=0;
    
    setControls(1);
    Mode=0;
  }

  void StopHendler() {
    Mode=STOP;
    remove(stopButton);
    add(algorithms);
    add(lab1);
  }  
  
  void ImproveHendler() {
    // can improve only already existing solution
    if (AlgMode==ALG1 && preoderList==null)
        return ;
    if (AlgMode==ALG2 && tspPath==null)
        return ;
    if (AlgMode==ALG3 && Order==null)  // need first to create initial solution
      {
        Alg3Hendler();
        return;
      }
    solve();
    Mode=IMPROVE;
  }

  void Alg3Hendler() {
    if (distMatr==null)
       calculateDist();
    generateInitsol();
    alg3Weight=calculateTotal(Order);
    Mode=INITSOL;
  }

  void IntersecHendler() {
    if (AlgMode==ALG1 && tsp1NoIntersec==null)
      {
       tsp1NoIntersec=findIntersections();
       alg1WnoIntersec=calculateTotal(tsp1NoIntersec);
      } 
    if (AlgMode==ALG2 && tsp2NoIntersec==null)
      {
       tsp2NoIntersec=findIntersections();
       alg2WnoIntersec=calculateTotal(tsp2NoIntersec);
      } 
    Mode=INTERSEC;
    repaint();
  }  
    
  void reshapeHendler() {
         add(gostepButton);
         remove(beginButton);
         remove(nextButton);
         nextButton.disable();
         remove(lab2);
         remove(lab3);
         remove(matchButton);
         
         pathButton.reshape( 25,y00+30+4*25,140,20); 
         improveButton.reshape(25,y00+30+8*25,140,20);
         lab4.reshape(25,y00+30+9*25,85,20);
         iterations.reshape(25+85,y00+30+9*25,55,20);

         add(originalButton);
         originalButton.disable();

         add(lab4);
         lab4.enable();
         improveButton.enable();
         add(improveButton);
         iterations.enable();
         add(iterations);
         add(intersecButton);
         intersecButton.enable();
         add(bestButton);
         add(bothButton);
  }
  
  public boolean action(Event event, Object obj)
   {
    if (event.target instanceof Button)
      {
      String lbl = (String)obj;
      if (lbl.equals(cleanButton.getLabel()))
        {
         StartHendler();
         repaint();
         return true;
        }
      if (lbl.equals(stopButton.getLabel()))
        {
         StopHendler();
         repaint();
         return true;
        }
      if (lbl.equals(matchButton.getLabel()))
        {
         GlobalMatchHendler();
         repaint();
         return true;
        } 
      if (lbl.equals(gostepButton.getLabel()) && (AlgMode!=ALG3))
        {
         setControls(0);
         add(beginButton);
         add(nextButton);
         nextButton.disable();
         add(lab2);
         add(lab3);
         pathButton.reshape( 25,y00+30+7*25,140,20); 
         add(pathButton);
         Mode=CHANGED;
         repaint();
         return true;
        }
      if (lbl.equals(pathButton.getLabel()) && (AlgMode!=ALG3))
        {
         Mode=SKIP;
         reshapeHendler();
         repaint();
         return true;
        }
      if (lbl.equals(bothButton.getLabel()) && (AlgMode!=ALG3))
        {
        Mode=BOTH;
        repaint();
        return true;
        }
      if (lbl.equals(intersecButton.getLabel()) && (AlgMode!=ALG3))
        {
        improveButton.disable();
        intersecButton.disable();
        iterations.disable();
        lab4.disable();
        originalButton.enable();

        Mode=WAIT;
        repaint();
        return true;
        }

       if (lbl.equals(beginButton.getLabel()) && (AlgMode!=ALG3))
        {
         BeginHendler();
         repaint();
         return true;
        }
      if (lbl.equals(nextButton.getLabel()) && (AlgMode!=ALG3))
        {
         switch(Mode) {
            case STOP:
            case CHANGED:
            case BOTH:
               BeginHendler();
               break;
            case MST:
               if (AlgMode==ALG1)
                  PreoderHendler();
               else if (AlgMode==ALG2)
                  oddsHendler();
               break;
            case PREODER:
               tsp1Hendler();
               break;
            case ODDS:
               MatchHendler();
               break;
            case MATCH:
               EulerHendler();
               break;
            case EULER:
               tsp2Hendler();
               break;
            case TSP1:
            case TSP2:
               reshapeHendler();
               Mode=FINAL;
               break;
         }
         repaint();
         return true;
        }

       if (lbl.equals(improveButton.getLabel()))
        {
         ImproveHendler() ;
         intersecButton.disable();
         originalButton.enable();
         repaint();
         return true;
        }

       if (lbl.equals(originalButton.getLabel()))
        {
         Mode=FINAL;
        improveButton.enable();
        intersecButton.enable();
        iterations.enable();
        repaint();
        return true;
        }    
       if (lbl.equals(bestButton.getLabel()))
        {
         Mode=BEST;
         repaint();
         return true;
        }

      }

    if (event.target instanceof MyChoice)
      {
       if (((MyChoice)event.target).Ident.equals("Iterations"))  
         {
          if (iterations.getSelectedIndex() != 6)
               {
              exhastMode=false;
                Iterations=Integer.parseInt(iterations.getSelectedItem());
               } 
           else
             exhastMode=true;
          }   
       else if (((MyChoice)event.target).Ident.equals("AlgorithmOptions"))  
         {       
          // enable relevant controls
 
          setControls(0);
          int index=algorithms.getSelectedIndex();
          if (index==0 || index==1)
            {
             // enable option of "go step by step"
             add(pathButton);
             add(gostepButton);
             add(matchButton);
             pathButton.reshape( 25,y00+30+4*25,140,20); //solution     
            } 
       
          switch (index) {
            case 0: //alg x2
               Mode=CHANGED;
               AlgMode=ALG1;
               break;
            case 1: // alg x1.5
               Mode=CHANGED;
               AlgMode=ALG2;
               break;
            case 2: // iterative alg
               Mode=CHANGED;
               AlgMode=ALG3;
               improveButton.reshape( 25,y00+30+3*25,140,20);   
               lab4.reshape( 25,y00+30+4*25,85,20);     
               iterations.reshape( 25+85,y00+30+4*25,55,20);
               
               add(lab4);
               add(improveButton);
               add(iterations);
               add(bestButton);
               Alg3Hendler();
               break;
            default:
               AlgMode=ALG1;
       } // end switch     
       repaint();
       return true;
      } // end if 
     }  
    return false;
  }

  public boolean mouseDown(Event event, int x, int y)
   {
     if ( (Mode==0) && (cityNum<100) &&
          (x>x00+2 && (x<x00+window_width-2) &&
           y>y00+2 && (y<y00+window_height-2)))
       {
        xarr[cityNum]=x;
        yarr[cityNum]=y;
        cityNum++;
        if (cityNum==1) stopButton.enable();
        repaint();
        return (true);
       }
     else
       return false;
   }

  void showPoints(Graphics g) {
    g.setColor(Color.black);
    for(int j=0;j < cityNum;j++)
       {
        g.drawOval(xarr[j]-2,yarr[j]-2,4,4);
       }
  }

void showMST(Graphics g,boolean botton_line) {
    // draw cities
    g.setColor(Color.black);
    for(int j=0;j < cityNum;j++)
       {
        g.fillOval(xarr[j]-3,yarr[j]-3,6,6);
       }
    // draw minimum spanning tree
    g.setColor(Color.red);
    for(int j=1;j<cityNum;j++)
      {
      if (!botton_line)
        g.drawLine(xarr[j],yarr[j],xarr[parent[j]],yarr[parent[j]]);
      else if (edgesMatr[j][parent[j]]==2)
        DrawLine(g,j,parent[j],Color.red,BOTTON);
      else
        g.drawLine(xarr[j],yarr[j],xarr[parent[j]],yarr[parent[j]]);

      }
}


void showPreoderWalk(Graphics g,int mode) {
    // draw cities
    g.setColor(Color.black);
    for(int j=0;j < cityNum;j++)
       {
        g.fillOval(xarr[j]-3,yarr[j]-3,6,6);
       }
    // perfom the preoder walk on MST anf show it
    if (Mode==PREODER)
     {
      g.setColor(Color.black);
      g.drawLine(x00+10,y00+window_height+17,x00+40,y00+window_height+17);
      g.setColor(Color.green);
      g.drawLine(x00+10,y00+window_height+17+20,x00+40,y00+window_height+17+20);
      g.setColor(Color.red);
      g.drawLine(x00+10,y00+window_height+17+40,x00+40,y00+window_height+17+40);

      g.setColor(Color.black);
      g.drawString("first walk on edge during preoder tree walk",x00+50,y00+window_height+22);
      g.drawString("second walk on edge during preoder tree walk",x00+50,y00+window_height+22+20);
      g.drawString("edges of Minimum Spanning Tree",x00+50,y00+window_height+22+40);
     }
    createList(g,mode);

}

void showTSP(Graphics g,boolean alg1) {
    if (alg1)
      showPreoderWalk(g,REPAINT);
    else
      {
       showEuler(g,true);
       g.setColor(Color.black);
       g.drawString("Blue line visits nodes in order of their appearance in the",x00+20,y00+window_height+22);
       g.drawString("Euler cycle but without repeatings",x00+20,y00+window_height+22+20);
      } 

    g.setColor(Color.blue);
    if (!alg1)
       g.drawString(""+0,xfinal2[0],yfinal2[0]);
    for(int j=0;j<cityNum;j++)
      {
      if (!alg1 && ((j+1)!=cityNum) )
        g.drawString(""+(j+1),xfinal2[j+1],yfinal2[j+1]);
      if (!alg1)
        g.drawLine(xfinal2[j],yfinal2[j],xfinal2[j+1],yfinal2[j+1]);
      else
        g.drawLine(xfinal1[j],yfinal1[j],xfinal1[j+1],yfinal1[j+1]);

      try {Thread.sleep(700);}
       catch (InterruptedException e) {
         System.out.println("interrupted");}

      }
}

void showFinal(Graphics g,int mode) {
     nextButton.disable();
 
    for(int j=0;j < cityNum;j++)
       {
        g.setColor(Color.black);
        g.fillOval(xarr[j]-3,yarr[j]-3,6,6);
        g.setColor(Color.blue);
        if (mode==ALG1)
           g.drawLine(xfinal1[j],yfinal1[j],xfinal1[j+1],yfinal1[j+1]);
        else if (mode==ALG2)
           g.drawLine(xfinal2[j],yfinal2[j],xfinal2[j+1],yfinal2[j+1]);

       }
     if (mode==ALG1)
       {
        g.drawString("x2 Algorithm   : TOTAL WEIGHT = "+(int)alg1Weight,x00+20,y00+window_height+22);
        if (alg2Weight!=MAX)
          {
           g.setColor(Color.black);
           g.drawString("x1.5 Algorithm: TOTAL WEIGHT = "+(int)alg2Weight,x00+20,y00+window_height+22+20);
          } 
       }    
     else if (mode==ALG2)
       {
        g.drawString("x1.5 Algorithm: TOTAL WEIGHT = "+(int)alg2Weight,x00+20,y00+window_height+22);
        if (alg1Weight!=MAX)
          {
           g.setColor(Color.black);
           g.drawString("x2 Algorithm   : TOTAL WEIGHT = "+(int)alg1Weight,x00+20,y00+window_height+22+20);
          } 
       }    

}

void signOdds(Graphics g,boolean unsign) {
   if (unsign)
     g.setColor(Color.black);
   else
      g.setColor(Color.red);
   if (Mode==ODDS)
      g.drawString("Odd nodes signed in red",x00+20,y00+window_height+22);
   for(int i=0;i<oddNum;i++)
       g.fillOval(xarr[odd[i]]-3,yarr[odd[i]]-3,6,6);
}

void showOdds(Graphics g) {
    showMST(g,true);
    signOdds(g,false);
}
void showMatch(Graphics g,boolean unsign) {
    showMST(g,true);
    signOdds(g,unsign);
    printMatch(g,Match);
    if (Mode==MATCH)
     {
      g.setColor(Color.red);
      g.drawLine(x00+10,y00+window_height+17,x00+40,y00+window_height+17);
      g.setColor(Color.black);
      g.drawLine(x00+10,y00+window_height+17+20,x00+40,y00+window_height+17+20);
      g.drawString("minimum spanning tree edge",x00+50,y00+window_height+22);
      g.drawString("new edge - result of matching",x00+50,y00+window_height+22+20);
     } 
   
}

void showEuler(Graphics g,boolean show_again) {
   g.setColor(Color.black);
   for(int j=0;j < cityNum;j++)
      g.fillOval(xarr[j]-3,yarr[j]-3,6,6);

   if (!show_again) 
     {
      g.drawString("Moving purple line represents creating of the Euler cycle in above",x00+20,y00+window_height+22);
      g.drawString("graph",x00+20,y00+window_height+22+20);
     } 

   g.setColor(Color.magenta);
   int ind1,ind2;
   for (int i=0;i<numOfEdges;i++)
     {
      ind1=eulertour[i];
      ind2=eulertour[i+1];
      // check wether this is a double edge
      if (edgesMatr[ind1][ind2]==2)
         { // first we draw botton line
          DrawLine(g,ind1,ind2,Color.magenta,BOTTON);
          edgesMatr[ind1][ind2]=3;  // sign that was alredy drawn once
          edgesMatr[ind2][ind1]=3;
         }
      else if (edgesMatr[ind1][ind2]==3)
          // then we draw upper line
          DrawLine(g,ind1,ind2,Color.magenta,UPPER);
      else
          g.drawLine(xarr[ind1],yarr[ind1],xarr[ind2],yarr[ind2]);

      if (!show_again) {
      try {Thread.sleep(700);}
      catch (InterruptedException e) {
          System.out.println("interrupted");  }
      }
     }
   for (int i=0;i<cityNum;i++)
    for (int j=0;j<cityNum;j++)
      {
       if (edgesMatr[i][j]==3)
         {
          edgesMatr[i][j]=2;
          edgesMatr[j][i]=2;
         }
      }
}

  void showAllTogether(Graphics g) {
    BeginHendler();
    if (AlgMode==ALG1)
      {
       createList(g,NOPAINT);
       tsp1Hendler();
       Mode=FINAL;
       repaint();
      }
    if (AlgMode==ALG2)
      {
       oddsHendler();
       MatchHendler();
       EulerHendler();
       tsp2Hendler();
       Mode=FINAL;
       repaint();
      }
  }

  void showBoth(Graphics g) {
     int ind1,ind2;
     // perfom all steps
     BeginHendler();
     nextButton.disable();
     createList(g,NOPAINT);
     tsp1Hendler();
     oddsHendler();
     MatchHendler();
     EulerHendler();
     tsp2Hendler();
     Mode=BOTH;
     if (tsp1Matr==null)
      tspMatrRepresent();

     // draw both tours in appropriate color and kind of line
     for(int i=0;i < cityNum;i++)
       {
        g.setColor(Color.black);
        g.fillOval(xarr[i]-3,yarr[i]-3,6,6);

        // draw tsp1
        g.setColor(Color.blue);
        ind1=preoderList[i];
        ind2=preoderList[i+1];
        if (tsp2Matr[ind1][ind2]) // the edge is in both tours
           DrawLine(g,ind1,ind2,Color.blue,BOTTON); // botton line
        else
           g.drawLine(xarr[ind1],yarr[ind1],xarr[ind2],yarr[ind2]);

        // draw tsp2
        g.setColor(Color.red);
        ind1=tspPath[i];
        ind2=tspPath[i+1];
        if (tsp1Matr[ind1][ind2]) // the edge is in both tours
           DrawLine(g,ind1,ind2,Color.red,UPPER); // upper line
        else
           g.drawLine(xarr[ind1],yarr[ind1],xarr[ind2],yarr[ind2]);

       }
     g.setColor(Color.blue);
     g.drawString("x2 Alg: Total Weight = "+(int)alg1Weight,x00+20,y00+window_height+22);
     g.setColor(Color.red);
     g.drawString("x1.5 Alg: Total Weight = "+(int)alg2Weight,x00+205,y00+window_height+22);

  }

  void showTour(Graphics g ,Color color,int source[]) {
     for (int i=0;i<cityNum;i++)
        {
         g.setColor(Color.black);
         g.fillOval(xarr[source[i]]-3,yarr[source[i]]-3,6,6);
         DrawLine(g,source[i],source[i+1],color,CENTER);
        }
  }

  void showImprove(Graphics g ,int source[]) {
     showTour(g,Color.red,source);
     Newcost=calculateTotal(source);
     g.setColor(Color.black);

     if (AlgMode==ALG1)
       {
        g.drawString("Improved x2 Alg  : TOTAL WEIGHT = "+(int)Newcost,x00+10,y00+window_height+22);
        if (alg2Wimproved!=MAX)
           g.drawString("Improved x1.5 Alg: TOTAL WEIGHT = "+(int)alg2Wimproved,x00+10,y00+window_height+22+20);
       }    
     else if (AlgMode==ALG2)
       {
        g.drawString("Improved x1.5 Alg: TOTAL WEIGHT = "+(int)Newcost,x00+10,y00+window_height+22);
        if (alg1Wimproved!=MAX)
           g.drawString("Improved x2 Alg  : TOTAL WEIGHT = "+(int)alg1Wimproved,x00+10,y00+window_height+22+20);
       }    
     else
       g.drawString("TOTAL WEIGHT = "+(int)Newcost,x00+20,y00+window_height+22);

     if (Oldcost>Newcost)
        g.setColor(Color.red);
     g.drawString("Improved "+(int)(Oldcost-Newcost),x00+255,y00+window_height+22);
     Oldcost=Newcost;
  }

  void showInitSol(Graphics g ,int source[]) {
     showTour(g,Color.red,source);
     Oldcost=Newcost=calculateTotal(source);
     g.setColor(Color.black);
     g.drawString("TOTAL WEIGHT = "+(int)Newcost,x00+20,y00+window_height+22);
  }

  void showBestSol(Graphics g) {
     int best=findMinSolution();
     switch (best) {
       case 1:
          showTour(g,Color.red,preoderList); //show weight
          g.drawString("TOTAL WEIGHT = "+(int)alg1Weight+"  Result of x2 Alg",x00+20,y00+window_height+22);
          break;
       case 2:
          showTour(g,Color.red,tspPath);
          g.drawString("TOTAL WEIGHT = "+(int)alg2Weight+"  Result of x1.5 Alg",x00+20,y00+window_height+22);
          break;
       case 3:
          showTour(g,Color.red,Order);
          g.drawString("TOTAL WEIGHT = "+(int)alg3Weight+"  Result of Iterative Alg",x00+20,y00+window_height+22);
          break;
       case 4:
          g.drawString("TOTAL WEIGHT = "+(int)alg1Wimproved+"  Result of Impoved x2 Alg",x00+20,y00+window_height+22);
          showTour(g,Color.red,tsp1Improved);
          break;
       case 5:
          g.drawString("TOTAL WEIGHT = "+(int)alg2Wimproved+"  Result of Improved x1.5 Alg",x00+20,y00+window_height+22);
          showTour(g,Color.red,tsp2Improved);
          break;
       case 6:
          g.drawString("TOTAL WEIGHT = "+(int)alg1WnoIntersec+"   Result of removing intersections in x2 Alg",x00+10,y00+window_height+22);
          showTour(g,Color.red,tsp1NoIntersec);
          break;
       case 7:
          g.drawString("TOTAL WEIGHT = "+(int)alg2WnoIntersec+"   Result of removing intersections in x1.5 Alg",x00+10,y00+window_height+22);
          showTour(g,Color.red,tsp2NoIntersec);
          break;

     }
  }
/***************Graphics***********************************/
  public void drawbox(Graphics g,int x0,int y0,int width,int height,boolean UseBack){
    g.setColor(Color.white);
    if (UseBack)
      g.fillRect(x0,y0,width,height);
    else
      g.drawRect(x0,y0,width,height);
    g.setColor(Color.darkGray);
    g.drawRect(x0,y0,width,height);
    g.drawRect(x0+1,y0+1,width-2,height-2);
    g.setColor(Color.gray);
    g.drawRect(x0+2,y0+2,width-4,height-4);
  }

  void printHeader(Graphics g,String str,int x_offset) {
      g.setColor(BackGround);
      g.fillRect(x00,y00-12,window_width,12);
      g.setColor(Color.red);
      g.drawString(str,x00+x_offset,y00-2);
  }

/******************************************************/
 public void paint(Graphics g) {
    update(g);
 }

  public void update(Graphics g) {
    drawAllBoxes(g);

    switch (Mode) {
      case 0:
        printHeader(g,"Insert up to 100 points",120);
        g.drawString(cityNum+" points",x00+20,y00+window_height+22);
        g.setColor(Color.black);
        for(int j=0;j < cityNum;j++)
           g.drawOval(xarr[j]-2,yarr[j]-2,4,4);
        break;

      case STOP:
        printHeader(g,"",0);
        showPoints(g);
        break;
        
      case CHANGED:
        printHeader(g,"",0);
        showPoints(g);
        break;
        
      case GLOBALMATCH:
        printHeader(g,"Weighted Match of points",120);
        g.setColor(Color.black);
        for(int i=0;i < cityNum;i++)
           g.fillOval(xarr[i]-3,yarr[i]-3,6,6);
        double weight = printMatch(g,globalMatch);
        g.drawString("MATCH WEIGHT = "+(int)weight,x00+20,y00+window_height+22);
        break;
        
      case MST:
        printHeader(g,"Minimum Spanning Tree",130);
        showMST(g,false);
        break;

      case PREODER:
         printHeader(g,"Preoder Walk",150);
         showMST(g,false);
         showPreoderWalk(g,PAINT);
         break;
      case TSP1:
         printHeader(g,"Building TSP tour of x2 Algorithm",110);
         showTSP(g,true);
         break;

      case ODDS:
         printHeader(g,"Odd Nodes",130);
         showOdds(g);
         break;

      case MATCH:
         printHeader(g,"MST + Matching",130);
         showMatch(g,false);
         break;

      case EULER:
         printHeader(g,"Euler Tour",130);
         showMatch(g,true); // all nodes colored black
         showEuler(g,false);
         break;

      case TSP2:
         printHeader(g,"Building TSP Tour of x1.5 Algorithm",100);
         showTSP(g,false);
         break;

      case FINAL:
         printHeader(g,"TSP Tour",160);
         showFinal(g,AlgMode);
         break;

      case SKIP:
         printHeader(g,"TSP Tour",160);
         showAllTogether(g);
         break;

      case BOTH:
         printHeader(g,"Both TSP Tours",110);
         showBoth(g);
         break;

      case IMPROVE:
         if (!exhastMode)
            printHeader(g,"TSP Tour After "+Iterations+" Improve Iterations",50);
         else
            printHeader(g,"TSP Tour After Exhaustive Improve Iterations",50);
         if (AlgMode==ALG1)
           showImprove(g,tsp1Improved);
         if (AlgMode==ALG2)
           showImprove(g,tsp2Improved);
         if (AlgMode==ALG3)
           showImprove(g,Order);
         break;

      case INITSOL:
         printHeader(g,"Initial Solution",120);
         showInitSol(g,Order);
         break;

      case BEST:
         printHeader(g,"Best Solution",120);
         showBestSol(g);
         break;
      
      case WAIT:
         printHeader(g,"",120);
         g.drawString("Please wait, it takes some time",x00+20,y00+window_height+22);
         IntersecHendler();
         break;
         
      case INTERSEC:
         printHeader(g,"After removing the intersections",120);
         if (AlgMode==ALG1)
           {
            showTour(g,Color.red,tsp1NoIntersec);
            g.drawString("Improved = "+(int)(alg1Weight-alg1WnoIntersec),x00+250,y00+window_height+22);
            g.setColor(Color.black);
            g.drawString("TOTAL WEIGHT = "+(int)alg1WnoIntersec,x00+20,y00+window_height+22);
           }
         if (AlgMode==ALG2)
           {
            showTour(g,Color.red,tsp2NoIntersec);
            g.setColor(Color.black);
            g.drawString("TOTAL WEIGHT = "+(int)alg2WnoIntersec,x00+20,y00+window_height+22);
            g.setColor(Color.red);
            g.drawString("Improved = "+(int)(alg2Weight-alg2WnoIntersec),x00+250,y00+window_height+22);
           } 
         break;
    }
  }
}


