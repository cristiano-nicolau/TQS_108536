The project passed.

Quality Gate Status - Passed

Measures - Overall Code:
- Security A - 0 issues
- Reliability A - 0 issues
- Maintainability A - 22 issues
- Accepted Issues: 0
- Coverage 75%

Duplication 0.0% 
Security Hotspot 1

O meu projeto não tem bugs nem vulnerabiblidades, obtendo A tanto em fiabilidade como segurança. Contudo, contem 22 code smells, o que é um valor elevado. A cobertura do projeto é de 75%, o que é um valor aceitável.


| File                               | Issue      | Level | Problem description                                                                                                        | How to solve                                                                            |   |   |
|------------------------------------|------------|-------|----------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------|---|---|
| euromillions/EuromillionsDraw.java | Code Smell | Minor | The return type of method findMatchesFor should be an interface such as "List" rather than the implementation "ArrayList". | Make the function return a List rather than an ArrayList implementation.                |   |   |
| euromillions/Dip.java              | Code Smell | Major | For loop at line 68 is testing a variant stop condition.                                                                   | Refactor the code in order to not assign to the loop counter from within the loop body. | 
