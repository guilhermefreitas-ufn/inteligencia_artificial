

!start.

+!start : true
    <- .print("guardo peças médias").

+peca(med) : true
    <- .print("percebi uma peça media e vou guardá-la.");
        guardar(med).

+!vamosGuardar(grd)[source(Agt)] : true
    <- .print(Agt, " me chamou para guardar a peça grande");
        guardar(grd).