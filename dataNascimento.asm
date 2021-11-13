	.data
	
entradaDD: .asciiz "Entre com o dia (DD): \n"
invalidoDD: .asciiz "Dia invalido.\n"

entradaMM: .asciiz "Entre com o mes (MM): \n"
invalidoMM: .asciiz "Mes invalido.\n"

entradaAAAA: .asciiz "Entre com o ano (AAAA): \n"
invalidoAAAA: .asciiz "Ano invalido.\n"

barra: .asciiz "/"
saida: .asciiz "\nData de Nascimento: "
	
.text
.globl main
main:
	
	li $a1 31

	li $v0, 4
	la $a0, entradaDD
	syscall

	li $v0, 5
	syscall
	move $s0, $v0
	
	whileDD:
		slti $t1, $s0, 1
		slt $t2, $a1, $s0
	
		or $t3, $t1, $t2
		beq $t3, $zero, saidaDD
	
		li $v0, 4
		la $a0, invalidoDD
		syscall
		
		li $v0, 4
		la $a0, entradaDD
		syscall
		
		li $v0, 5 
		syscall
		move $s0, $v0 
		
		j whileDD
	
	saidaDD:
		
		move $s1, $s0		
		
		li $a1 12

		li $v0, 4
		la $a0, entradaMM
		syscall

		li $v0, 5
		syscall
		move $s0, $v0
	
		whileMM:
			slti $t1, $s0, 1
			slt $t2, $a1, $s0
	
			or $t3, $t1, $t2
			beq $t3, $zero, saidaMM
	
			li $v0, 4
			la $a0, invalidoMM
			syscall
		
			li $v0, 4
			la $a0, entradaMM
			syscall
		
			li $v0, 5 
			syscall
			move $s0, $v0 
		
			j whileMM
	
	
		saidaMM:
		
			move $s2, $s0
			
			li $a1 2021

			li $v0, 4
			la $a0, entradaAAAA
			syscall

			li $v0, 5
			syscall
			move $s0, $v0
	
			whileAAAA:
				slt $t1, $s0, 1900
				slt $t2, $a1, $s0
	
				or $t3, $t1, $t2
				beq $t3, $zero, saidaAAAA
	
				li $v0, 4
				la $a0, invalidoAAAA
				syscall
		
				li $v0, 4
				la $a0, entradaAAAA
				syscall
			
				li $v0, 5 
				syscall
				move $s0, $v0 
		
				j whileAAAA
	
	
			saidaAAAA:
		
				move $s3, $s0
				
				
				li $v0, 4 
				la $a0, saida
				syscall
				
				li $v0, 1
				la $a0, ($s1)
				syscall
				
				li $v0, 4 
				la $a0, barra
				syscall
				
				li $v0, 1
				la $a0, ($s2)
				syscall
				
				li $v0, 4 
				la $a0, barra
				syscall
				
				li $v0, 1
				la $a0, ($s3)
				syscall