grammar Quake2Map;
list     : '[' elements ']' ;        // match bracketed list
elements : element (',' element)* ;  // match comma-separated list
element  : NAME | list ;             // element is name or nested list
NAME     : ('a'..'z'|'A'..'Z')+ ;    // NAME is sequence of >=1 letter

map        : objectlist ;
objectlist : object* ;
object     : brush | entity ;
entity     : subentitylist ;
entitysublist : subentity*;
subentity : key_value_pair | brush ;
brush : face_list ;
face_list : face* ;
face : point point point texture_path texture_coords ;
point : '(' INTEGER INTEGER INTEGER ')' ;
texture_path : ('a'..'z'|'A'..'Z'|'0'..'9'|'/'|'_')+ ;
texture_coords : INTEGER INTEGER INTEGER FLOAT FLOAT INTEGER INTEGER INTEGER ;