package com.aluracursos.screenmatch.principal;
/*Es solo para guardar información y obtener informaciñibn de ahí, colocar datos y verlos de una forma rapida
* Coloaremos strng en vez de int porqeu gson los toma como string
* Ya yiene getterse seter y to string*/

public record TituloOMDb(String title, String year, String runtime ) {
/*Ahora nos hemos dado cuenta que al tener title, year con minuscula, gson no lo coge, pero las buenas ptacticas indican
* que tenemos que tener esas cariables con minusculas, podríamos hacerlo sería mas facil pero no, Gson tiene n sistema
* para esto. por ello tenemos que ir a la doc, FielNamingPolicy, con un patron biulder le diremos que todos empezaran con mayuscual con un
* UOPPERCAMELCASE*/

}
