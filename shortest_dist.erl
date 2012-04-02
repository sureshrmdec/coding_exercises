%%%--------------------------------------------------------------------------------------------------%%%
%%% @author Shahzad Bhatti
%%% @doc Given two words in a large text file, find shortest distance (in terms of words) 
%%%     between them in the file.
%%%     erlc shortest_dist.erl
%%%     erl -noshell -s shortest_dist test_shortest -s init stop
%%% not yet complete
%%%--------------------------------------------------------------------------------------------------%%%

-module(shortest_dist).
-export([shortest/3, test_shortest/0]).

shortest(Words, Word1, Word2) ->
   shortest(Words, Word1, Word2, 0, -99999, -99999, 99999).

shortest([], _Word, _Word2, _Pos, _WordPos1, _WordPos2, Min) ->
   Min;
shortest([Word1|Words], Word1, Word2, Pos, _WordPos1, WordPos2, Min) ->
      WordPos1A = Pos,
      Dist = WordPos1A - WordPos2,
      Min1 = min(Min, Dist),
      shortest(Words, Word1, Word2, Pos+1,WordPos1A,WordPos2,Min1);
shortest([Word2|Words], Word1, Word2, Pos, WordPos1, _WordPos2, Min) ->
      WordPos2A = Pos,
      Dist = WordPos2A - WordPos1,
      Min1 = min(Min, Dist),
      shortest(Words, Word1, Word2, Pos+1,WordPos1,WordPos2A,Min1);
shortest([_Word|Words], Word1, Word2, Pos, WordPos1, WordPos2, Min) ->
      shortest(Words, Word1, Word2, Pos+1,WordPos1,WordPos2,Min).


test_shortest() ->
   4 = shortest(["One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"], "Four", "Eight"),
   9 = shortest(["One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"], "One", "Ten").

