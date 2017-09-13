;Daniel Breitigan
;Dr. Briggs - Artificial Intelligence - CSC 462
;Project 1 - N Queens Problem

;HOW TO RUN
;go to file in terminal, enter clisp, and type (load "proj1.lisp")
;Program is ran with the BFS function. Input the initial state you want to use
;ex: (BFS '((1 0)(2 0)(3 0)(4 0)(5 0)(6 0)(7 0)(8 0))
;if you want to find out how long it ran too,
;use: (time (BFS '((1 0)(2 0)(3 0)(4 0)(5 0)(6 0)(7 0)(8 0))))


;MODIFY
;Finds things in a list and changes them to whatever you want
;x: what to search for
;y: list it is searching
;z: what to replace the x with in the y
(defun MODIFY(x y z)
	(if (not (atom y)) (setf (car y) (MODIFY x (car y) z)))
	(if (not (atom y)) (setf (cdr y) (MODIFY x (cdr y) z)))
	(cond ((equal x y)(setq y z)))
	y
) ; end MODIFY

;THREAT?
;Checks if the piece in will hit any piece in y
;x : 1 piece's coordinates
;y : list of all other piece's coordinates
(defun THREAT? (x y)
	(dotimes (i (length y) i)
		(cond ((= (car x) (caar y))(return-from THREAT? t))
			((= (cadr x) (cadar y))(return-from THREAT? t))
			((= (abs (- (car x) (caar y)))(abs (- (cadr x) (cadar y))))(return-from THREAT? t))
		) ; end conditions
		(setf y (cdr y))
	) ;end dotimes loop
	nil
) ;end THREAT?

;VALID-BOARD?
;Check's if the board is valid
;x: Coordinates for all Queens
(defun VALID-BOARD? (x)
	(dotimes (i (- (length x) 1) i)
		(cond ((THREAT? (car x) (cdr x)) (return-from VALID-BOARD? NIL))
			((= (cadadr x) 0) (return-from VALID-BOARD? NIL)) ;Incomplete Board if not all values are > 0
		) ; end conditions
		(setf x (cdr x))
	) ; end dotimes loop
	t
) ; end VALID-BOARD?

;EXPAND
;Takes in the current state and finds all possible next states
;x: curent state
(defun EXPAND (x)
	(setf neededqueen x)
	(setf alreadyfound nil)
	;Find which queen needs to be placed next 
	(loop while (and (not (eq neededqueen nil))(not (= (cadar neededqueen) 0))) do
		(setq alreadyfound (nconc alreadyfound (list (car neededqueen))))
		(setf neededqueen (cdr neededqueen))
	) ; end loop
	(unless (eq neededqueen nil)
		(setq queenNum (caar neededqueen))
		(dotimes (i (length x) i)
			(setq testPos (list queennum (+ i 1)))
			(when (not (THREAT? testPos alreadyfound))
				(setq next (nconc next (list (append alreadyfound (list testPos) (cdr neededqueen)))))
				(setq numnodes (+ numnodes 1))
			) ; end when
		) ; end dotimes loop
	) ; end if (ensures there is a queen to work with)
	next
) ; end Expand

;BFS
;Breadth-First-Search: Searches for the solution breadth-First
;x : Initial State
(defun BFS (x)
	(setq next nil) ;List that holds list of Queens
	(setq numnodes 1)
	(setq explorednodes 0)
	(setq next (nconc next (list x))) ;Put initial item in next
	(setq foundSolution NIL)
	; While the next list isn't empty & a solution wasn't found,
	; EXPAND the first item in next and check if it's a solution.
	; if Not a solution, move next to the next possible list and continue loop
	(loop while (and (not (eq next nil)) (eq foundSolution NIL)) do
		(EXPAND (car next))
		(setq explorednodes (+ explorednodes 1))
		(when (VALID-BOARD?(car next))
			(setq foundSolution t)
			(setq solution (car next))
		) ; end when
		(setq next (cdr next))
	) ; end while

	(when (eq foundSolution NIL)
		(format t "No Solution was found! ~%Number of Nodes Explored/Generated: ~S" numnodes)
		(setq solution NIL)
	) ; End When
	(when (eq FoundSolution t)
		(format t "A Solution was found! ~%Number of Nodes Generated: ~S~%Number of Nodes Explored: ~S~%Solution: ~S"
		numnodes explorednodes Solution)
		;(print numnodes)
	) ; End when
	foundSolution
) ; End BFS