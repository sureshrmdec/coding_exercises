%%%--------------------------------------------------------------------------------------------------%%%
%%% @author Shahzad Bhatti
%%% @doc Given two words in a large text file, find make_change distance (in terms of words) 
%%%     between them in the file.
%%%     erlc make_change.erl
%%%     erl -noshell -s make_change test_make_change -s init stop
%%%--------------------------------------------------------------------------------------------------%%%

-module(make_change).
-export([make_change/2, test_make_change/0]).

make_change(0, _Denom) ->
    0;
make_change(N, Denom) ->
    NextDenom = case Denom of
        25 ->
            10;
        10 ->
            5;
        5 ->
            1;
        1 -> 
            1;
        _ ->
            0
    end,
    if 
        NextDenom =:= 1 ->
            1;
        true ->
            do_make_change(N, Denom, NextDenom, 0, 0)
    end.

do_make_change(N, Denom, NextDenom, Ways, I) when I * Denom =< N ->
    Ways1 = Ways + make_change(N - I * Denom, NextDenom),
    do_make_change(N, Denom, NextDenom, Ways1, I+1);
do_make_change(_N, _Denom, _NextDenom, Ways, _I) ->
    Ways.

test_make_change() ->
   N = make_change(100, 25),
   io:format("~w\n", [N]).

