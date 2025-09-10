#include "evaluate-postfix.c"
#include <stdio.h>
#include <string.h>
#include <ctype.h>

int isOperator(string token);
int incomingPrio(string token);
float inStackPrio(string token);

void shuntingYard(string infix) {
	stack Operators;
	queue Output;
	char temp[256];
	createStack(&Operators);
	createQueue(&Output);

	int i = 0;
	
	while (infix[i] != '\0'){
		if(isdigit(infix[i])){
			int j = 0;
		while(isdigit(infix[i])){
        	temp[j] = infix[i];
        	j++;
        	i++;
		}
		temp[j] = '\0';
		i--;
		enqueue(&Output, temp);
    	}
    	else{
			temp[0] = infix[i];
		
			if((temp[0] == '<' || temp[0] == '>' || temp[0] == '=' || temp[0] == '!' || temp[0] == '&' || temp[0] == '|') && 
			   (infix[i+1] == '=' || infix[i+1] == '&' || infix[i+1] == '|')){
				temp[1] = infix[i + 1];
				temp[2] = '\0';
			}
			else{
				temp[1] = '\0';
			}
					
			if (isOperator(temp)) {
				while (!stackEmpty(&Operators) && inStackPrio(top(&Operators)) >= incomingPrio(temp)) {
        			enqueue(&Output, pop(&Operators));
    			}
        		push(&Operators, temp);
			}	 
			else if (strcmp(temp, "(") == 0) {
				push(&Operators, temp);
			}
			else if (strcmp(temp, ")") == 0) {
				while (strcmp(top(&Operators), "(") != 0) {
          			enqueue(&Output, pop(&Operators));
				}
        		pop(&Operators);
			}
    	}
		i++;
	}

	while (!(stackEmpty(&Operators))) {
		enqueue(&Output, pop(&Operators));
	}
  
	int evaluatedValue = evaluatePostfix(&Output);

	Output.head = 0;
	
	while(!queueEmpty(&Output)){
		printf("%s ", dequeue(&Output));
	}
	printf("\n");
	
	if(evaluatedValue == -999999999){
  		printf("Division by zero error!");
	}
	else{
		printf("%d", evaluatedValue);
	}
  
	printf("\n\n");
  
}


int isOperator(string token) {
	if (strcmp(token, "!") == 0 || strcmp(token, "^") == 0 ||
    	strcmp(token, "*") == 0 || strcmp(token, "/") == 0 ||
		strcmp(token, "%") == 0 || strcmp(token, "+") == 0 ||
		strcmp(token, "-") == 0 || strcmp(token, ">") == 0 || 
		strcmp(token, ">=") == 0 || strcmp(token, "<") == 0 || 
		strcmp(token, "<=") == 0 || strcmp(token, "(") == 0 || 
		strcmp(token, "==") == 0 || strcmp(token, "!=") == 0 || 
		strcmp(token, "&&") == 0 || strcmp(token, "||") == 0) {

		return 1;
	}

	return 0;
}

int incomingPrio(string token){
	if(strcmp(token,"||") == 0){
    	return 5;
	}
	else if(strcmp(token,"&&") == 0){
		return 6;
	}
	else if(strcmp(token,"!=") == 0 || strcmp(token,"==") == 0){
		return 7;
	}
	else if(strcmp(token,">") == 0 || strcmp(token,">=") == 0 || strcmp(token,"<") == 0 || strcmp(token,"<=") == 0){
		return 8;
	}
	else if(strcmp(token,"+") == 0 || strcmp(token,"-") == 0){
		return 9;
	}
	else if(strcmp(token,"*") == 0 || strcmp(token,"/") == 0 || strcmp(token,"%") == 0){
		return 10;
	}
	else if(strcmp(token,"^") == 0){
		return 12;
	}
	else if(strcmp(token,"!") == 0){
		return 13;
	}
	else if(strcmp(token,"(") == 0){
		return 14;
	}
	else{
		return -1;
	}
}

float inStackPrio(string token){
	if(strcmp(token,"||") == 0 ){
		return 5;
	}
	else if(strcmp(token,"&&") == 0 ){
    	return 6;
	}
	else if(strcmp(token,"!=") == 0 || strcmp(token,"==") == 0){
		return 7;
	}
	else if(strcmp(token,">") == 0 || strcmp(token,">=") == 0 || strcmp(token,"<") == 0 || strcmp(token,"<=") == 0){
		return 8;
	}
	else if(strcmp(token,"+") == 0 || strcmp(token,"-") == 0){
    	return 9;
	}
	else if(strcmp(token,"*") == 0 || strcmp(token,"/") == 0 || strcmp(token,"%") == 0){
		return 10;
	}
	else if(strcmp(token,"^") == 0 ){
    	return 11;
	}
	else if(strcmp(token,"!") == 0){
    	return 12.5;
	}
	else if(strcmp(token,"(") == 0 ){
		return 0;
	}
	else{
		return -1;
	}
}

/* Hierarchy of Operators
============================
|| operator || icp || isp ||
============================
||    (     ||  14 ||  0  ||
||    !     ||  13 || 12.5||
||    ^     ||  12 ||  11 ||
||    *     ||  10 ||  10 ||
||    /     ||  10 ||  10 ||
||    %     ||  10 ||  10 ||
||    +     ||  9  ||  9  ||
||    -     ||  9  ||  9  ||
||  > or >= ||  8  ||  8  ||
||  < or <= ||  8  ||  8  ||
|| == or != ||  7  ||  7  ||
||    &&    ||  6  ||  6  ||
||    ||    ||  5  ||  5  ||
============================
*/
