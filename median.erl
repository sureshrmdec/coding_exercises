%%%--------------------------------------------------------------------------------------------------%%%
%%% @author Shahzad Bhatti
%%% @doc Write a method to find and maintain the median values as new values are
%       generated randomly.
%%%     erlc median .erl
%%%     erl -noshell -s median test_median -s init stop
%%% not yet complete
%%%--------------------------------------------------------------------------------------------------%%%

-module(median).
-export([add_number/3, median/2, test_median/0]).

add_number(N, [], []) ->
    {[], [N]};
add_number(N, [Min|MinT], [Max|MaxT]) when length(MinT) == length(MaxT), N > MinT ->
    io:format("add_number1 ~w \n", [N]),
    MaxHeap1 = [Min,Max|MaxT],
    MinHeap1 = [N|MinT],
    {MinHeap1, MaxHeap1};
add_number(N, [Min|MinT], [Max|MaxT]) when length(MinT) == length(MaxT) ->
    io:format("add_number2 ~w\n", [N]),
    MaxHeap1 = [N,Max|MaxT],
    {[Min|MinT], MaxHeap1};
add_number(N, [Min|MinT], [Max|MaxT]) when N < Max ->
    io:format("add_number3 ~w\n", [N]),
    MinHeap1 = [Max, Min|MinT],
    MaxHeap1 = [N|MaxT],
    {MinHeap1, MaxHeap1};
add_number(N, MinHeap, MaxHeap) ->
    io:format("add_number4 ~w, ~w, ~w\n", [N, MinHeap, MaxHeap]),
    MinHeap1 = [N|MinHeap],
    {MinHeap1, MaxHeap}.

median([Min|_MinT], []) ->
    Min;
median([], [Max|_MaxT]) ->
    Max;
median([Min|MinT], [Max|MaxT]) when length(MinT) == length(MaxT) ->
    (Min + Max) / 2;
median([_Min|MinT], [Max|MaxT]) when length(MaxT) > length(MinT) ->
    Max;
median([Min|_MinT], _) ->
    Min.

test_median() ->
    {Min1, Max1} = add_number(3, [], []),
    {Min2, Max2} = add_number(5, Min1, Max1),
    {Min3, Max3} = add_number(7, Min2, Max2),
    {Min4, Max4} = add_number(12, Min3, Max3),
    {Min5, Max5} = add_number(13, Min4, Max4),
    {Min6, Max6} = add_number(14, Min5, Max5),
    {Min7, Max7} = add_number(21, Min6, Max6),
    {Min8, Max8} = add_number(23, Min7, Max7),
    {Min9, Max9} = add_number(23, Min8, Max8),
    {Min10, Max10} = add_number(23, Min9, Max9),
    {Min11, Max11} = add_number(23, Min10, Max10),
    {Min12, Max12} = add_number(29, Min11, Max11),
    {Min13, Max13} = add_number(39, Min12, Max12),
    {Min14, Max14} = add_number(40, Min13, Max13),
    {Min15, Max15} = add_number(56, Min14, Max14),
    io:format("~w\n~w\n", [Min15,Max15]),
    Median = median(Min15, Max15),
    io:format("~w\n", [Median]).

