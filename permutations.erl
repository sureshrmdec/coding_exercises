%%%--------------------------------------------------------------------------------------------------%%%
%%% @author Shahzad Bhatti
%%% @doc Generate permutations of string
%%%     erlc perms.erl
%%%     erl -noshell -s permutations test_perms -s init stop
%%%--------------------------------------------------------------------------------------------------%%%

-module(permutations).
-export([perms/1, test_perms/0]).

perms([]) ->
    [" "];
perms([H|T]) -> 
    Words = perms(T),
    make_words(H, Words, []).

make_words(First, Words, Output) ->
    lists:foldl(fun(Word, OutputList) -> add_words(First, Word, 1, OutputList) end, Output, Words).

add_words(_C, Word, I, Output) when I == length(Word) + 1 ->
    Output;
add_words(C, [], _I, Output) ->
    Output;
add_words(C, Word, I, Output) ->
    Pre = lists:sublist(Word, 1, I),
    Suf = lists:sublist(Word, I+1, length(Word)-I),
    Word1 = Pre ++ [C|Suf],
    add_words(C, Word, I+1, [Word1|Output]).

test_perms() ->
    List = perms("abc"),
    lists:foreach(fun(Word) -> io:format("~w\n", [list_to_atom(Word)]) end,
        List).

