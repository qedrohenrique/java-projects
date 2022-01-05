.data

  string: .space 1000

  input: .asciiz "Insira a mensagem: "
  numChar: .asciiz "Numero total de caracteres: "
  msgCripto: .asciiz "Mensagem Criptografada: "

  quebraLinha: .asciiz "\n"

.text
.globl main

main:

  li $v0, 4         # Imprime a mensagem
  la $a0, input     # reservada em "input".
  syscall           

  li $v0, 8        
  la $a0, string    
  li $a1, 1000       
  syscall
  
  move $s0, $a0     
  move $t0, $a0  

loop:

  lb $t2, 0($t0)
  beqz $t2, done    
  
  beq $t2, ' ', case_space
  beq $t2, 'A', case_A
  beq $t2, 'a', case_A
  beq $t2, 'R', case_R
  beq $t2, 'r', case_R
  beq $t2, 'E', case_E
  beq $t2, 'e', case_E
  beq $t2, 'O', case_O
  beq $t2, 'o', case_O
  beq $t2, 'M', case_M
  beq $t2, 'm', case_M
  beq $t2, 'H', case_H
  beq $t2, 'h', case_H
  beq $t2, 'L', case_L
  beq $t2, 'l', case_L
  beq $t2, 'S', case_S
  beq $t2, 's', case_S
  beq $t2, 'P', case_P
  beq $t2, 'p', case_P

  beq $t2, '0', case_0
  beq $t2, '1', case_1
  beq $t2, '2', case_2
  beq $t2, '3', case_3
  beq $t2, '4', case_4
  beq $t2, '5', case_5
  beq $t2, '6', case_6
  beq $t2, '7', case_7
  beq $t2, '8', case_8
  beq $t2, '9', case_9

continue:

  addi $t0, $t0, 1
  addi $t1, $t1, 1
  j loop

case_space:
  li $t4, '0'
  sb $t4, ($t0)
  j continue

case_A:
  li $t4, '1'
  sb $t4, ($t0)
  j continue


case_R: 
  li $t4, '2'
  sb $t4, ($t0)
  j continue


case_E: 
  li $t4, '3'
  sb $t4, ($t0)
  j continue


case_O: 
  li $t4, '4'
  sb $t4, ($t0)
  j continue

case_M: 
  li $t4, '5'
  sb $t4, ($t0)
  j continue

case_H: 
  li $t4, '6'
  sb $t4, ($t0)
  j continue

case_L: 
  li $t4, '7'
  sb $t4, ($t0)
  j continue

case_S: 
  li $t4, '8'
  sb $t4, ($t0)
  j continue
case_P: 
  li $t4, '9'
  sb $t4, ($t0)
  j continue

case_0: 
  li $t4, ' '
  sb $t4, ($t0)
  j continue
case_1: 
  li $t4, 'A'
  sb $t4, ($t0)
  j continue
case_2: 
  li $t4, 'R'
  sb $t4, ($t0)
  j continue
case_3: 
  li $t4, 'E'
  sb $t4, ($t0)
  j continue
case_4: 
  li $t4, 'O'
  sb $t4, ($t0)
  j continue
case_5: 
  li $t4, 'M'
  sb $t4, ($t0)
  j continue
case_6: 
  li $t4, 'H'
  sb $t4, ($t0)
  j continue
case_7: 
  li $t4, 'L'
  sb $t4, ($t0)
  j continue
case_8: 
  li $t4, 'S'
  sb $t4, ($t0)
  j continue
case_9: 
  li $t4, 'P'
  sb $t4, ($t0)
  j continue
done:

  li $v0, 4
  la $a0, numChar
  syscall

  addi $t1, $t1, -1
  li $v0, 1
  move $a0, $t1
  syscall

  li $v0, 4
  la $a0, quebraLinha
  syscall

  li $v0, 4
  la $a0, msgCripto
  syscall
  
  li $v0, 4
  la $a0, string
  syscall
    
  li $v0, 10
  syscall