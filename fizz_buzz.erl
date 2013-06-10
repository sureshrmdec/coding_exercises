%%%--------------------------------------------------------------------------------------------------%%%
%%% @author Shahzad Bhatti
%%% @doc FizzBuzz
%%%     erlc fizz_buzz.erl
%%%     erl -noshell -s fizz_buzz fizz_buzz -s init stop
%%%--------------------------------------------------------------------------------------------------%%%

-module(fizz_buzz).
-export([fizz_buzz/0]).

fizz_buzz(101) ->
   false;
fizz_buzz(N) when N rem 3 =:= 0, N rem 5 =:= 0->
   io:format("FizzBuzz\n"),
   fizz_buzz(N+1);
fizz_buzz(N) when N rem 3 =:= 0 ->
   io:format("Fizz\n"),
   fizz_buzz(N+1);
fizz_buzz(N) when N rem 5 =:= 0->
   io:format("Buzz\n"),
   fizz_buzz(N+1);
fizz_buzz(N) ->
   io:format("~w \n", [N]),
   fizz_buzz(N+1).


fizz_buzz() ->
   fizz_buzz(1).
