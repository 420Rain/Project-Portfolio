//Used from MCO1 CCDSALG SY 2023 - 2024 under Mr. Florante Salvador
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "queue.h"

void createQueue(queue *Q) {
	Q = (queue*) malloc(sizeof(queue));
	Q->head = 0;
	Q->tail = 0;
}

void enqueue(queue *Q, String token) {
	if(queueFull(Q)){
		return;
	}
	strcpy(Q->tokens[Q->tail], token);
	Q->tail++;
}

char* dequeue(queue *Q) {
	if(queueEmpty(Q)){
    	return NULL;
	}
	Q->head++;
	return Q->tokens[Q->head-1];
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
	if (Q->tail == MAX_VERTICES - 1) {
		return 1;
	} 
	return 0;
}
