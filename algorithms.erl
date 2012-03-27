%%%--------------------------------------------------------------------------------------------------%%%
%%% @author Shahzad Bhatti
%%% @doc Common Algorithms for Coding Interviews
%%%     erlc algorithms.erl
%%%     erl -noshell -s algorithms test_compute_square_root -s init stop
%%%     erl -noshell -s algorithms test_binary_search -s init stop
%%%--------------------------------------------------------------------------------------------------%%%

-module(algorithms).
-export([compute_square_root/1, test_compute_square_root/0]).
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


compute_square_root(N) ->
    compute_square_root(N, 0, 65536).

compute_square_root(_, Begin, End) when Begin + 1 >= End ->
    Begin;
compute_square_root(N, Begin, End) ->
    %io:fwrite("compute_square_root(~w, ~w, ~w)\n", [N, Begin, End]),
    Mid = Begin + (End - Begin) / 2,
    MidSquare = Mid * Mid,
    if 
        MidSquare == N ->
            Mid;
        MidSquare > N ->
            compute_square_root(N, Begin, Mid);
        true ->
            compute_square_root(N, Mid, End)
    end.

test_compute_square_root() ->
    4.0 = compute_square_root(16).

