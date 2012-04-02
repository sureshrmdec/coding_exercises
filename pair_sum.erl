%%%--------------------------------------------------------------------------------------------------%%%
%%% @author Shahzad Bhatti
%%% @doc Given two words in a large text file, find pair_sum distance (in terms of words) 
%%%     between them in the file.
%%%     erlc pair_sum.erl
%%%     erl -noshell -s pair_sum test_pair_sum -s init stop
%%% not yet complete
%%%--------------------------------------------------------------------------------------------------%%%

-module(pair_sum).
-export([pair_sum/2, test_pair_sum/0]).

pair_sum(SortedList, Sum) ->
   pair_sum(SortedList, Sum, 1, length(SortedList), []).

pair_sum([], _Sum, _First, _Last, Output) ->
   Output;
pair_sum(SortedList, Sum, First, Last, Output) when First < Last ->
   F = lists:nth(First, SortedList),
   L = lists:nth(Last, SortedList),
   S = F + L,
   case S of
      Sum ->
         pair_sum(SortedList, Sum, First+1, Last-1, [[F,L]|Output]);
      _ ->
         if
            S < Sum ->
               pair_sum(SortedList, Sum, First+1, Last, Output);
            true ->
               pair_sum(SortedList, Sum, First, Last-1, Output)
         end
   end;
pair_sum(_SortedList, _Sum, _First, _Last, Output) ->
   Output.
            
test_pair_sum() ->
   [[4,6],[3,7],[2,8],[1,9]] = pair_sum(lists:seq(1, 50), 10).

