#include "stack.h"
#include <stdio.h>
#include <string.h>

void createStack(stack *S) {
	S->top = -1;
}

void push(stack *S, string token) {
	if (stackFull(S)) {
    	return;
	}
	S->top++;
	S->tokens[S->top] = strdup(token);
}

char* pop(stack *S) {
	if (stackEmpty(S)) {
    	return NULL;
	}
	char *temp = S->tokens[S->top];
	S->top--;
	return temp;
}

char* top(stack *S) {
	if (stackEmpty(S)) {
    	return NULL;
	}
	return S->tokens[S->top];
}

int stackEmpty(stack *S) {
	if (S->top == -1) {
    	return 1;
	}
	return 0;
}

int stackFull(stack *S) {
	if (S->top == 255) {
    	return 1;
	}
	else {
		return 0;
	}
}
