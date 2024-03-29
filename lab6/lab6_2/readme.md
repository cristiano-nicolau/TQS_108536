Neste projeto, tenho 46min de technical debt e 21 code smells.

Technical debt é uma metáfora que se refere ao custo de manter um código mau, que não tem de ser mau pode apenas ter sido uma ma escolha da parte do desenvolvedor. O código mau é aquele que é difícil de entender, modificar e manter. O technical debt é o custo de corrigir esse código mau.

Code smells são sintomas de problemas no código. Eles são indicadores de que o código pode ser difícil de entender, modificar e manter. Code smells são sinais de que o código pode ter problemas de design.


O tão baixo valor de coverage é devido ao fato de que no CarsModel temos métodos que sao testatos, o toString, hashcode e o equals. Sem estes metodos temos um coverage de 47%.
```