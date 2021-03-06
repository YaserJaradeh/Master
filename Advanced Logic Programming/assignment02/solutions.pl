% task 3 
% we need to consult the shared.pl file with the file that contains this code
nested_loop(Outer,Inner):-
	loop(Outer,_,_,_),
	loop(Inner,_,_,_),
	ast_ancestor(Inner,Outer).
	
% task 4	
before_in_block(StatementId1,StatementId2,BlockId):-
	blockT(BlockId, _, _, List),
	nth1(Index1, List, StatementId1),
	nth1(Index2, List, StatementId2),
	Index1 < Index2 .

% task 5
% 5(b)
binary_tree(-).
binary_tree(t(L,_,R)) :- binary_tree(L), binary_tree(R).

%5(c)

symmetric(-).
symmetric(t(L,_,R)) :- mirror(L,R).

mirror(-,-).
mirror(t(L1,_,R1),t(L2,_,R2)) :- mirror(L1,R2), mirror(R1,L2).


%5(d)
size(t(-,_,-),1).
size(t(L,_,-),N) :- L = t(_,_,_), size(L,N).
size(t(_,-,R),N) :- R = t(_,_,_), size(R,N).
size(t(L,_,R),N) :- L = t(_,_,_), R = t(_,_,_),
	          size(L,NL), size(R,NR), N is NL + NR.
