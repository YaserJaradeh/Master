%%%%%%%%%%%%  1  %%%%%%%%%%%%%%%

shift([H1|T1],L2):- append(T1,[H1],L2).

%%%%%%%%%%%%  2  %%%%%%%%%%%%%%%

word(1,one).
word(2,two).
word(3,three).
word(4,four).
word(5,five).
word(6,six).
word(7,seven).
word(8,eight).
word(9,nine).

%digit(X) :- number(X), X>=0, X<10.

translate([],[]).
translate([HN|TN],[HW|T]):- word(HN,HW),translate(TN,T).

%%%%%%%%%%%%  3  %%%%%%%%%%%%%%%

%%linear(+L1,?L2) is det
% this predicate succed iff the L1 is a list
% if it was invoked in a (+,+) mode then it will match the two lists and return true or false
% otherwise it will return the flattend list L2 that represent the nested list L1 
linear([],[]).
linear([H|T], [H|TFlat]):- not(is_list(H)),linear(T,TFlat).
linear([H|T], Flat):- linear(H,HFlat),linear(T,TFlat),append(HFlat,TFlat,Flat).

%%%%%%%%%%%%  4  %%%%%%%%%%%%%%%
group([], []).
group([Elem], [[Elem]]).
group([Head, Tail|TS], [[Head]|TR]):- Head \= Tail,group([Tail|TS], TR). 
group([Head, Head|HS], [[Head|TFR]|TR]):- group([Head|HS], [TFR|TR]).

%%%%%%%%%%%%  5  %%%%%%%%%%%%%%%
% this have some mistake
group2([], []).
group2([Elem], [Elem]).
group2([Head, Tail|TS], [[Head]|TR]):- Head \= Tail,group2([Tail|TS], TR). 
group2([Head, Head|HS], [[Head|TFR]|TR]):- group2([Head|HS], [TFR|TR]).
