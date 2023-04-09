def mul[x: Int, y: Int]: Int <- x * y;
var x: Int <- 2;
var y: Int;
y <- x + 2 * x;
mul[x, y+2];