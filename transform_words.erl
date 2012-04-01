%%%--------------------------------------------------------------------------------------------------%%%
%%% @author Shahzad Bhatti
%%% @doc Given two words of equal length, write a method to transform one word
%       into another by changing one letter at a time.
%%%     erlc transform_words.erl
%%%     erl -noshell -s transform_words test_transform_words -s init stop
%%% not yet complete
%%%--------------------------------------------------------------------------------------------------%%%

-module(transform_words).
-export([transform_words/2, test_transform_words/0]).


transform_words(Start, Stop) ->
   Visited = sets:new(),
   Visited1 = sets:add_element(Start, Visited),
   do_transform_words(Stop, [[Start]], Visited1, dict:new()).

do_transform_words(_Stop, [], _Visited, _Backtrack) ->
   undefined;
do_transform_words(Stop, [W|T], Visited, Backtrack) ->
   io:format("#################transform_words(~w)\n", [W]),
   Words = one_edit_words(lists:nth(1, W)),
   {Status, ActionList, Visited1, Backtrack1} = iterate_words(Words, Stop, W, Visited, Backtrack, T),
   case Status of
      not_found -> do_transform_words(Stop, ActionList, Visited1, Backtrack1);
      _ ->
         ActionList
   end.
   

iterate_words([], _Stop, _W, Visited, Backtrack, ActionList) ->
   {not_found, ActionList, Visited, Backtrack}; 
iterate_words([Stop|_T], Stop, W, Visited, Backtrack, _ActionList) ->
   %io:format("iterate_words MATCH (~w, ~w)\n", [list_to_atom(Stop), list_to_atom(W)]),
   io:format("iterate_words MATCH (~w, ~w)\n", [Stop, W]),
   List = [Stop],
   List1 = collect_words(W, Backtrack, List),
   io:format("iterate_words COLLECT (~w)\n", [List1]),
   {found, List1, Visited, Backtrack};

iterate_words([V|T], Stop, W, Visited, Backtrack, ActionList) ->
   %io:format(">>>>iterate_words(~w, ~w, ~w)\n", [list_to_atom(V), list_to_atom(Stop), list_to_atom(W)]),
   io:format(">>>>iterate_words(~w, ~w, ~w)\n", [V, Stop, W]),
   case sets:is_element(V, Visited) of
      true -> 
         iterate_words(T, Stop, W, Visited, Backtrack, ActionList);
      false ->
         ActionList1 = V ++ ActionList,
         Visited1 = sets:add_element(V, Visited),
         Backtrack1 = dict:store(V, W, Backtrack),
         iterate_words(T, Stop, W, Visited1, Backtrack1, ActionList1)
    end.

collect_words(undefined, _Backtrack, List) ->
   List;
collect_words(W, Backtrack, List) ->
   List1 = W ++ List,
   case dict:is_key(W, Backtrack) of
      true -> 
         W1 = dict:fetch(W, Backtrack),
         collect_words(W1, Backtrack, List1);
      false -> 
         List1
   end.


one_edit_words(Word) ->
    io:format("************one_edit_words(~w)\n", [Word]),
    Alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
    Len = length(Word),
    Seq = lists:seq(1, Len),
    lists:foldl(fun(I, Output) -> 
                lists:foldl(fun(A, OutputList) -> 
                            Pre = lists:sublist(Word, 1, I-1),
                            Suf = case Len-I of
                                0 ->
                                    "";
                                _ ->
                                    lists:sublist(Word, I+1, Len-I)
                            end,
                            Word1 = Pre ++ [A] ++ Suf,
                            %io:format("Word ~w, Pre ~w, Suf ~w, I ~w, A ~w\n", [list_to_atom(Word1), list_to_atom(Pre), list_to_atom(Suf), I, A]),
                            %[list_to_atom(Word1)] ++ OutputList end, Output, Alpha)
                            OutputList1 = case dict_words:word_exists(Word1) of
                              true ->
                                 [Word1] ++ OutputList;
                              _ ->
                                 OutputList
                            end,
                            OutputList1 
                     end, Output, Alpha)
            end, [], Seq).


test_transform_words() ->
    %N = one_edit_words("LAMP"),
    N = transform_words("DAMP", "LIKE"),
    io:fwrite("test_transform_words ~w\n", [N]).

