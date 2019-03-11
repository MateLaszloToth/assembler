# The Pep/9 Assembler Project
The Pep/9 virtual machine was created by Dr. J. Stanley Warford, computer science professor at Pepperdine University, to teach the basic concepts of the classic von Neumann machine. The Pep/9 assembler project is a school project that translates a subset of the Pep/9 assembly language to Pep/9 machine language.

The assembler project translates instructions from the following subset of the Pep/9 assembly language:

Instructions:

- BR - Branch unconditional
- BRLT - Branch if less then
- BREQ - Branch if equal
- CPWA - Compare word(two bytes) with the accumulator
- DECI - Decimal input trap instruction
- DECO - Decimal output trap instruction
- ADDA - Add accumulator
- SUBA - Subtract accumulator
- STWA  - Store word from the accumulator
- LDWA - Load word from accumulator

- STOP - Stop execution
- ASLA - Arithmetic shift left accumulator
- ASRA - Arithmetic shift right accumulator

Dot commands:

- .BLOCK - Allocate x bytes of zeros
- .END - End of program

Valid Addressing Modes:
	
-	i - Immediate Addressing
-	d - Direct Addressing
-	n - Indirect Addressing
-	s - Stack-relative Addressing
-	sf - Stack-relative Deferred Addressing
-	x - Indexed Addressing
- sx - Stack-indexed Addressing
- sfx - Stack-deferred Indexed Addressing

Valid program:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Each line has to start with an instruction or dot command. The operand following the instruction and separated with spaces can be decimal or hexadecimal constants. After the operand a comma is required followed by the addressing mode. Unary instructions do not have operand or addressing mode. The last instruction must be STOP to terminate the program. The last line must be the .END pseudo operation to terminate the source program listing.

Correct sample program and output:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Input:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ldwa		0x9999,d  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.block		7  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ldwa		0x9999,d  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.block		0xB  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ldwa		0x9999,d  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;stop  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.end  

Output:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Object code:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C1 99 99  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;00 00 00 00 00 00 00  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C1 99 99  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;00 00 00 00 00 00 00 00 00 00 00  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;C1 99 99  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;00  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;zz  

Program listing:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;LDWA    	0x9999,d  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.BLOCK  	7  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;LDWA    	0x9999,d  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.BLOCK  	0x000B  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;LDWA    	0x9999,d   
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;STOP  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.END  

Incorrect sample program and output:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Input:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;stwa 		0x0000,i  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ldwa 		0x0000  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ldwa 		0x12345,d  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;br 		  0x0000,d  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;br 		  0x0000,n  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;br 		  0x0000,s  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;br 		  0x0000,sf  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;br 		  0x0000,sx  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;br 		  0x0000,sxf  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;brlt 		0x0000,d  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;brlt 		0x0000,n  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;brlt 		0x0000,s  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;brlt 		0x0000,sf  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;brlt 		0x0000,sx  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;brlt 		0x0000,sxf  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;deci 		0x0000,i  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;stwa		6,i  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;asla 		6,i  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;asra 		6,i  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;stwa  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ldwa  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;suba  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;cpa  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;stop 		0x0000  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;stop 		0x0000,d  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.end  

Output:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;25 errors were detected.  

Program listing:  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Invalid addressing mode.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Mnemonic needs an address.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Illegal integer or hexadecimal value.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Invalid addressing mode.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Invalid addressing mode.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Invalid addressing mode.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Invalid addressing mode.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Invalid addressing mode.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Invalid addressing mode.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Invalid addressing mode.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Invalid addressing mode.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Invalid addressing mode.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Invalid addressing mode.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Invalid addressing mode.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Invalid addressing mode.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Invalid addressing mode.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Invalid addressing mode.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Illegal trailing character.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Illegal trailing character.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Illegal integer or hexadecimal value.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Illegal integer or hexadecimal value.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Illegal integer or hexadecimal value.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Illegal mnemonic.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Illegal trailing character.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ERROR: Illegal trailing character.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;.END  
