graph = {}
graph["you"] = ["alice", "bob", "claire"]
graph["bob"] = ["anuj", "peggy"]
graph["alice"] = ["peggy"]
graph["claire"] = ["thom", "jonny"]
graph["anuj"] = []
graph["peggy"] = []
graph["thom"] = []
graph["jonny"] = []

from collections import deque 

def person_is_seller(name):
    return name[-1] == 'm'


def search(name):
    search_queue = deque()
    search_queue += graph[name]
     
    while search_queue:
        person = search_queue.popleft()
        if person_is_seller(person):
            print person + " is a mango seller!"
            return True
        else:
            search_queue += graph[person]
    return False

search("you")
