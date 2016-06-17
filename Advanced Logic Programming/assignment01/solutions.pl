% ==================== Task 1 =====================
%% path(?Start , ?End)
% get wheter there is a path between Start and the End.
path(Start,End):-arc(Start,End).
path(Start,End):-arc(Start,X),path(X,End).

% the arc predicate
% this is the graph givin by the task 1
arc(a,d).
arc(d,f).
arc(e,f).
arc(e,c).
arc(i,g).
arc(j,h).
arc(h,k).

% you can run it like this
% path(Start,End) it will give all the paths.
% path(a,X). it will give all the nodes reachable from node a
% path(X,b). ith will give all the nodes that have a apth to node b

% ==================== Task 2 =====================
%person(FirstName,LastName,Age).
%% search(+ World) 
% Search for the solution
% successed iff the World 
search(World):-
	World=[person('Tick',_TickLastName,TickAge),
		person('Trick',_TrickLastName,_TrickAge),
		person('Track',_TrackLastName,_TrackAge)],
	member(person(_,_,15),World),
	member(person(_,_,17),World),
	member(person(_,_,18),World),
	member(person(_,'Chang',_),World),
	member(person(_,'Thatcher',17),World),
	member(person(_,'Yang',YangAge),World),
	YangAge is TickAge + 3.
	
% you can run it like this
% search(W) it will give all the possabiliteis of the names.