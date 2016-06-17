% Task 2
beat(tom,jim).
beat(ann,tom).
beat(pat,jim).


category(Player,Category):-
	Category=winner,beat(Player,_),not(beat(_,Player));
	Category=loser,beat(_,Player),not(beat(Player,_));
	Category=fighter,beat(Player,_),beat(_,Player).

% Task 3
p(a,4).
p(a,6).
p(a,22).
p(a,7).
p(a,97).
p(a,46).
p(a,2).
p(a,8).
p(a,0).
p(d,66).
p(v,75).
p(b,20).

r(X) :- p(a,X).
s(X) :- not(not(p(a,X))).
