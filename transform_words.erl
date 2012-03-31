%%%--------------------------------------------------------------------------------------------------%%%
%%% @author Shahzad Bhatti
%%% @doc Given two words of equal length, write a method to transform one word
%       into another by changing one letter at a time.
%%%     erlc transform_words.erl
%%%     erl -noshell -s transform_words test_transform_words -s init stop
%%%--------------------------------------------------------------------------------------------------%%%

-module(transform_words).
-export([transform_words/2, test_transform_words/0]).


transform_words(N, Y) ->
    1.

one_edit_words(Word) ->
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
                            [list_to_atom(Word1)] ++ OutputList end, Output, Alpha)
            end, [], Seq).


test_transform_words() ->
    N = one_edit_words("LAMP"),
    io:fwrite("test_transform_words ~w\n", [N]).

