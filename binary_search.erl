%%%--------------------------------------------------------------------------------------------------%%%
%%% @author Shahzad Bhatti
%%% @doc Binary Search an ordered list
%%%     erlc binary_search.erl
%%%     erl -noshell -s binary_search test_binary_search -s init stop
%%%--------------------------------------------------------------------------------------------------%%%

-module(binary_search).
-export([binary_search/2, test_binary_search/0]).


binary_search(Array, Item) ->
    % Note that Erlang arrays start with 1..N
    binary_search(Array, Item, 1, length(Array)). 

binary_search(_, _, Low, High) when Low > High ->
    -1;
binary_search(Array, Item, Low, High) ->
    M = round((Low + High) / 2),
    %io:fwrite("binary_search(~w, ~w, ~w, ~w)\n", [Item, Low, High, M]),
    Current = lists:nth(M, Array),
    if 
        Current < Item ->
            binary_search(Array, Item, M+1, High);
        Current > Item ->
            binary_search(Array, Item, Low, M-1);
        true ->
            M
    end.


test_binary_search() ->
    6 = binary_search([1, 3, 8, 12, 15, 20, 23], 20),
    7 = binary_search([1, 3, 8, 12, 15, 20, 23], 23),
    -1 = binary_search([1, 3, 8, 12, 15, 20, 23], 24),
    1 = binary_search([1, 3, 8, 12, 15, 20, 23], 1).


