#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "stack.c"
#include "queue.c"

int checkOperator(string token);
int performNotOperation(int operand);
int performOperation(string operation, int operand2, int operand1);

int evaluatePostfix(queue *Q) {
	stack Operands;

	createStack(&Operands);

	string token;
	char temp[256];
	int value;

	string operand1;
	string operand2;

	while(!queueEmpty(Q)) {
    	token = dequeue(Q);

    if (!checkOperator(token)) {
    	push(&Operands, token);
    } 
    else {
		if(strcmp(token,"!") == 0){
        	operand1 = pop(&Operands);
        	value = performNotOperation(atoi(operand1));
    }
		else{
        	operand2 = pop(&Operands);
        	operand1 = pop(&Operands);
        	value = performOperation(token, atoi(operand2), atoi(operand1));
        	if(value == -999999999){
        		return -999999999;
			}
		}
    	sprintf(temp,"%d",value);
		push(&Operands, temp);
    }
  }
	return atoi(pop(&Operands));
}

int checkOperator(string token) {
	if (strcmp(token, "!") == 0 || strcmp(token, "^") == 0 ||
        strcmp(token, "*") == 0 || strcmp(token, "/") == 0 ||
        strcmp(token, "%") == 0 || strcmp(token, "+") == 0 ||
        strcmp(token, "-") == 0 || strcmp(token, ">") == 0 ||
        strcmp(token, ">=") == 0 || strcmp(token, "<") == 0 ||
        strcmp(token, "<=") == 0 || strcmp(token, "!=") == 0 ||
        strcmp(token, "==") == 0 || strcmp(token, "&&") == 0 ||
        strcmp(token, "||") == 0) {

		return 1;
	}

	return 0;
}

int performNotOperation(int operand){
	return !operand;
}

int performOperation(string operation, int operand2, int operand1) {
	if (strcmp(operation, "^") == 0) {
    	return pow(operand1, operand2);
	}
	else if (strcmp(operation, "*") == 0) {
    	return operand1 * operand2;
	} 
	else if (strcmp(operation, "/") == 0) {
		if(operand2 == 0){
			return -999999999;
		}
    return operand1 / operand2;
	}
	else if (strcmp(operation, "%") == 0) {
		if(operand2 == 0){
			return -999999999;
		}
    	return operand1 % operand2;
	}
	else if (strcmp(operation, "+") == 0) {
    	return operand1 + operand2;
	}
	else if (strcmp(operation, "-") == 0) {
    	return operand1 - operand2;
	}
	else if (strcmp(operation, ">") == 0) {
    	return operand1 > operand2;
	}
	else if (strcmp(operation, ">=") == 0) {
    	return operand1 >= operand2;
	}else if (strcmp(operation, "<") == 0) {
    	return operand1 < operand2;
	}
	else if (strcmp(operation, "<=") == 0) {
    	return operand1 <= operand2;
	}
	else if (strcmp(operation, "==") == 0) {
    	return operand1 == operand2;
	}
	else if (strcmp(operation, "!=") == 0) {
    	return operand1 != operand2;
	}
	else if (strcmp(operation, "&&") == 0) {
    	return operand1 && operand2;
	}
	else if (strcmp(operation, "||") == 0) {
    	return operand1 || operand2;
	}
	else {
    	return -1;
  }
}
