%%%--------------------------------------------------------------------------------------------------%%%
%%% @author Shahzad Bhatti
%%% @doc Compute square root for 16-bit integer numbers
%%%     erlc compute_square_root.erl
%%%     erl -noshell -s compute_square_root test_compute_square_root -s init stop
%%%--------------------------------------------------------------------------------------------------%%%

-module(compute_square_root).
-export([compute_square_root/1, test_compute_square_root/0]).


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

