###### Natalia Dibbern

## ClassWork 7:

1. `T(n) = 8 * T(n / 2) + n`

   $log_2(8) = 3 $  & $d = 1$ , decomposition dominant  $\implies$ $\Theta(n^3)$ 

2. `T(n) = 6 * T(n / 3) + n^2`

   $log_3(6) < 2 $  & $d = 2$ recombination dominant $\implies$ $\Theta(n^2)$ 

3. `T(n) = 3 * T(n / 4) + n^3`

   $log_4(3) < 2 $  & $d = 3$ recombination dominant $ \implies$ $\Theta(n^3)$ 

4. `T(n) = 0.5 * T(n / 2) + n`

   $a<1$ we cannot apply master theorem

5. `T(n) = 4 * T(n / 4) + √n`

   $log_4(4) = 1 $  & $d = 1/2$ decomposition dominant $\implies$ $\Theta(n)$ 

6. `T(n) = T(n) + n/2`

   $b = 1$ we cannot apply master theorem

7. `T(n) = 5 * T(n / 5) + n/5`

   $log_5(5) = 1 $  & $d = 1$   neutral $\implies$ $\Theta(n\text{ log }n)$ 

8. `T(n) = 3 * T(n / 4) + n^0.9`

   $log_4(3) < 0.9 $  & $d = 0.9$ recombination dominant$ \implies$ $\Theta(n^{0.9})$  

9. `T(n) = 64 * T(n / 4) + n^3`

   $log_4(64) = 3​$ & $d = 3\implies​$ $\Theta(n^3\text{log }n)​$ 

10. ` T(n) = 64 * T(n / 8) + n^n`

   $d= n$  asymptoticaly will always be larger (recomposition) $\implies O(n^n)$ 

