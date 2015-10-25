#Here is a high-level outline of how to move a tower from the starting pole, to the goal pole, using an intermediate pole:
#Move a tower of height-1 to an intermediate pole, using the final pole.
#Move the remaining disk to the final pole.
#Move the tower of height-1 from the intermediate pole to the final pole using the original pole.
#http://interactivepython.org/runestone/static/pythonds/Recursion/recursioncomplex.html
def moveTower(height,fromPole, toPole, withPole):
    if height >= 1:
        moveTower(height-1,fromPole,withPole,toPole)
        moveDisk(fromPole,toPole)
        moveTower(height-1,withPole,toPole,fromPole)

def moveDisk(fp,tp):
    print("moving disk from",fp,"to",tp)

moveTower(3,"A","B","C")

