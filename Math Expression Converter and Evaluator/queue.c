#include <stdio.h>
#include <string.h>
#include "queue.h"

void createQueue(queue *Q) {
	Q->head = 0;
	Q->tail = 0;
}

void enqueue(queue *Q, string token) {
	if(queueFull(Q)){
		return;
	}
	Q->tokens[Q->tail] = strdup(token);
	Q->tail++;
}

char* dequeue(queue *Q) {
	if(queueEmpty(Q)){
		return NULL;
	}
	char *temp = Q->tokens[Q->head];
	Q->head++;
	return temp;
}

char* queueHead(queue *Q) { 
	if(queueEmpty(Q)){
		return NULL;
	}
	return Q->tokens[Q->head];  
}

char* queueTail(queue *Q) {
	if (queueEmpty(Q)) {
    	return NULL;
	}
	return Q->tokens[Q->tail-1];
}

int queueEmpty(queue *Q) {
	if (Q->tail == Q->head) {
    	return 1;
	}
	return 0;
}

int queueFull(queue *Q) {
	if (Q->tail == 256) {
		return 1;
	} 
	return 0;
}
