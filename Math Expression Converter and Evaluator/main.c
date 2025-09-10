#include "infix-to-postfix.c"
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main() {

	char infix[256];
	while(1){
    
    	scanf("%s", infix);
  
    	if(strcmp(infix,"QUIT") == 0){
			exit(0);
		}

		shuntingYard(infix);
	}
	return 0;
}

