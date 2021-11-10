.data
msg1: .asciiz "Enter the number: "
.align 4			#word boundary
mem: .space 4			#reserva 4 bytes (para um inteiro)


.text
.globl main

main: li $v0, 4		#4 no registrador v0 printa uma string	
la $a0, msg1
syscall

li $v0, 5		#5 no registrador v0 le um inteiro
syscall

move $t0, $v0		#operacoes aritmeticas
add $t1, $t0, $t0
sw $t1, mem($0)

li $v0, 1		#1 no registrador v0 printa um inteiro
move $a0, $t1
syscall

li $v0, 10		#10 no registrador v0 termina o programa
syscall