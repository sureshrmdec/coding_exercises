%%%--------------------------------------------------------------------------------------------------%%%
%%% @author Shahzad Bhatti
%%% @doc Binary Search Tree
%%%     erlc bst.erl
%%%     erl -noshell -s bst test_bst_search -s init stop
%%%--------------------------------------------------------------------------------------------------%%%

-module(bst).
-export([bst_search/2, test_bst_search/0]).

-record(node, {data, left, right}).


bst_search(undefined, _Key) ->
   undefined;
bst_search(Node, Key) when Node#node.data == Key ->
   Node;
bst_search(Node, Key) when Node#node.data > Key ->
   bst_search(Node#node.left, Key);
bst_search(Node, Key) ->
   bst_search(Node#node.right, Key).

make_node(Data) -> 
   #node{data = Data}.
make_node(Data, Left, Right) when is_integer(Left), is_integer(Right) -> 
   #node{data = Data, left = make_node(Left), right = make_node(Right)};
make_node(Data, Left, Right) when is_integer(Left), not is_integer(Right) -> 
   #node{data = Data, left = make_node(Left), right = Right};
make_node(Data, Left, Right) when not is_integer(Left), is_integer(Right) -> 
   #node{data = Data, left = Left, right = make_node(Right)};
make_node(Data, Left, Right) ->
   #node{data = Data, left = Left, right = Right}.


%%%%
%         8
%    3       10
% 1    6        14
%    4        13
%%%%
create_test_data() ->
   N6 = make_node(6, 4, 7),   
   N14 = make_node(14, 13, undefined),
   N3 = make_node(3, 1, N6),
   N10 = make_node(10, undefined, N14),
   make_node(8, N3, N10).


print_node(undefined, _Indent) ->
   undefined;
print_node(Node, _Indent) when Node#node.data == undefined ->
   undefined;
print_node(Node, Indent) ->
    Spaces = string:chars($ , Indent),
    io:fwrite("~s~w\n", [Spaces, Node#node.data]),
    print_node(Node#node.left, Indent+1),
    print_node(Node#node.right, Indent+1).
   
test_bst_search() ->
    Root = create_test_data(),
    %print_node(Root, 0),
    Node1 = bst_search(Root, 6),
    6 = Node1#node.data,
    Node2 = bst_search(Root, 14),
    14 = Node2#node.data,
    undefined = bst_search(Root, 61).

