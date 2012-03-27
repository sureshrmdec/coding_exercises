%%%--------------------------------------------------------------------------------------------------%%%
%%% @author Shahzad Bhatti
%%% @doc Given a dictionary, return pair of words that are anagrams of each other.
%%%     erlc anagrams.erl
%%%     erl -noshell -s anagrams test_anagrams -s init stop
%%%--------------------------------------------------------------------------------------------------%%%

-module(anagrams).
-export([anagrams/1, test_anagrams/0]).

anagrams(DictionaryArray) ->
   Dict = load_map(DictionaryArray, dict:new()),
   %dict:fold(fun(_K,V,Accum) -> lists:atom_to_list(V)++Accum end, [], Dict).
   dict:fold(fun(_K,V,Accum) -> 
         case V of
            [_H|_T] ->
               [V|Accum];
            _ -> 
               Accum
         end
      end, [], Dict).

load_map([], Dict) ->
   Dict;
load_map([H|T], Dict) ->
   SortedWord = lists:sort(H),
   Dict1 = case dict:is_key(SortedWord, Dict) of
      true ->
         Old = dict:fetch(SortedWord, Dict),
         dict:store(SortedWord, [list_to_atom(H)] ++ Old, Dict);
      false ->
         dict:store(SortedWord, list_to_atom(H), Dict)
   end,
   load_map(T, Dict1).

test_anagrams() ->
   [[dog|god],[logarithm|algorithm]] = anagrams(["algorithm", "god", "logarithm", "dog", "cat"]),
   [[teals,tales,steal,stale,slate,least,tesla|stael],[spear,spare,reaps,rapes,pears,parse|pares],[traces,recast,reacts,crates,caters,caster|carets]] = anagrams(["stael", "tesla", "least", "slate", "stale", "steal", "tales", "teals", "carets", "caster", "caters", "crates", "reacts", "recast", "traces", "pares", "parse", "pears", "rapes", "reaps", "spare", "spear"]).

