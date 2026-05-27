
!start.

+!start : true
    <- .print("guardo peças pequenas.").

+peca(Tamanho) : Tamanho = peq
    <- .print("percebi uma peça ", Tamanho, " e vou guardá-la");
    guardar(Tamanho).

+peca(Tamanho) : Tamanho = grd
    <- .print("percebi uma peça ", Tamanho, " e vou chamar o r2 para ajudar a guarda-la");
    .send(r2,achieve,vamosGuardar(Tamanho)).