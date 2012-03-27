%%%--------------------------------------------------------------------------------------------------%%%
%%% @author Shahzad Bhatti
%%% @doc Compute square root for 16-bit integer numbers
%%%     erlc intersect_sorted_lists.erl
%%%     erl -noshell -s intersect_sorted_lists test_intersect_sorted_lists -s init stop
%%%--------------------------------------------------------------------------------------------------%%%

-module(intersect_sorted_lists).
-export([intersect_sorted_lists/2, test_intersect_sorted_lists/0]).


intersect_sorted_lists(Arr1, Arr2) ->
    intersect_sorted_lists(Arr1, Arr2, []).

intersect_sorted_lists([], [], Output) ->
    Output;
intersect_sorted_lists([_H|T], [], Output) ->
   intersect_sorted_lists(T, [], Output);
intersect_sorted_lists([], [_H|T], Output) ->
   intersect_sorted_lists([], T, Output);
intersect_sorted_lists([H|T1], [H|T2], Output) ->
   intersect_sorted_lists(T1, T2, [H|Output]);
intersect_sorted_lists([H1|T1], [H2|T2], Output) when H1 < H2 ->
   intersect_sorted_lists(T1, [H2|T2], Output);
intersect_sorted_lists([H1|T1], [H2|T2], Output) when H1 > H2 ->
   intersect_sorted_lists([H1|T1], T2, Output).

test_intersect_sorted_lists() ->
   [10,5,3] = intersect_sorted_lists([1, 3, 5, 8, 10, 12, 13], [3, 5, 10, 14]),
   [18,16,14,12] = intersect_sorted_lists([11, 12, 13, 14, 15, 16, 17, 18, 19, 21], [10, 12, 14, 16, 18, 20]),
   [18,16,14,12] = intersect_sorted_lists([10, 12, 14, 16, 18, 20], [11, 12, 13, 14, 15, 16, 17, 18, 19, 21]).

