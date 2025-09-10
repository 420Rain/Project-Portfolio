#include "graph.c"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
Resets visited vertices right after using BFS/DFS
@param graph - graph structure pointer
*/
void resetVisited(graph* graph){
	int i;
	
	// Loops through the visited array and sets all values to 0
	for(i = 0; i < graph->numVertices; i++){
		graph->visited[i] = 0;
	}
}

/*
Checks if all the vertices on the graph are visited
@param graph - graph structure pointer
@return 1 - if all vertices have been visited
@return 0 - if not all vertices have been visited
*/
int checkVisits(graph graph) {
	int i;
	int count = 0;
	
	//Checks through a loop if all vertices have been visited
	for (i = 0; i < graph.numVertices; i++) {
		if (graph.visited[i] == 1) {
			count++;
		}
	}
	
	//Checks if all vertices are visited
	if (count == graph.numVertices) {
		return 1;
	} 
	else {
		return 0;
	}
}

/*
A recursive function that queues vertices starting with the
starting/root vertex in a breadth-first manner to an empty
queue
@param graph - graph structure pointer
@param vertexIndex - index of starting/root vertex
@param Q - queue structure pointer
*/
void queueBFS(graph *graph, int vertexIndex, queue *Q, int index[], int *count) {
	int i;
	
	//Checks if the starting/root vertex has been visited
	if(graph->visited[vertexIndex] == 0){
		enqueue(Q,graph->vertices[vertexIndex]);
		graph->visited[vertexIndex] = 1;
	}
	
	//Loops through the adjacent vertices of the starting/root vertex
	for(i = 0; i < graph->numVertices; i++){
		//Checks if the adjacent vertex has been visited
		if(graph->adjMatrix[vertexIndex][i] == 1 && graph->visited[i] == 0){
			enqueue(Q,graph->vertices[i]); //Queues the adjacent vertex and marks as visited
			graph->visited[i] = 1; 
			index[*count] = i; //Places the index of the adjacent vertex in the index array
			(*count)++; //increments the count of visited vertices in total
		}
	}
}

/*
Sets up the needed variables for the recursiveBFS
@param graph - graph structure pointer
@param startVertex - starting/root vertex
@param Q - queue structure pointer
*/
void BFS(graph *graph, String startVertex, queue *Q) {
	int startIndex;
	int index[MAX_VERTICES]; // Stores the indeces of the vertices that have been visited
	int i = 0;
	int num = 0; // Contains the count of visited vertices
	int *count = &num;
	
	// Searches the index of the starting/root vertex
	startIndex = getIndex(graph, startVertex);
	
	// Queues the starting vertex
	queueBFS(graph, startIndex, Q, index, count);
	
	// Continously visits adjacent vertices and queues them until all vertices have been visited
	while(!checkVisits(*graph)){
		queueBFS(graph, index[i], Q, index, count);
		i++;
	}
	
	// Resets the visited array after the recursion
	resetVisited(graph);
}

/*
A recursive function that queues vertices starting with a given 
vertex in a depth-first manner, to an empty queue
@param graph - graph structure pointer
@param vertexIndex - index of starting/root vertex
@param Q - queue structure pointer
*/
void recursiveDFS(graph *graph, int vertexIndex, queue *Q) {
	int i;
	
	// Replaces 0 to 1 to indicate that the vertex has been visited
	graph->visited[vertexIndex] = 1;
	// Queues the vertex
	enqueue(Q, graph->vertices[vertexIndex]);
	
	//Loops through the adjacent vertices of the vertex via the adjacency matrix
	for(i = 0; i < graph->numVertices; i++){
		// Checks if the adjacent vertex is not visited
		if(graph->adjMatrix[vertexIndex][i] == 1 && graph->visited[i] == 0){
			//recursive call with the adjacent vertex as the starting vertex to check other adjacent vertices
			recursiveDFS(graph, i, Q);
		}
	}
}

/*
Sets up the needed variables for the recursiveBFS
@param graph - graph structure pointer
@param startVertex - starting/root vertex
@param Q - queue structure pointer
*/
void DFS(graph* graph, String startVertex, queue *Q){
	int startIndex;
	
	// Searches the index of the starting/root vertex
	startIndex = getIndex(graph, startVertex);
	
	// Calls the recursiveDFS functon
	recursiveDFS(graph, startIndex, Q);
	
	// Resets the visited array after the recursion
	resetVisited(graph);
}

/*
Counts the degrees of a vertex in the graph
@param graph - graph structure pointer
@param vertex - starting vertex
@return degree - the degree of the vertex
*/
int degreeCount(graph graph, String vertex){
	int degree = 0;
	int i;
	
	// Searches the index of the starting vertex
	int vertexIndex = getIndex(&graph, vertex);
	
	//Loops through a row in the adjacency matrix and counts the adjacent vertices
	for(i = 0; i < graph.numVertices; i++){
		if(graph.adjMatrix[vertexIndex][i] == 1){
			degree++;
    	}
	}
	
	//returns the amount of adjacent vertices to the starting vertex
	return degree;
}

/*
Outputs the necessary information to a text file named TRAVERSALS
@param graph - graph structure pointer
@param BFS - queue structure pointer containing the vertices in BFS
@param DFS - queue structure pointer containing the vertices in DFS
@param vertices - array of vertices
*/
void fileOutput(graph graph, queue *BFS, queue *DFS, String vertices[]){
	FILE *fp;
	fp = fopen("TRAVERSALS.txt","w");
	
	int i;
	// Prints the vertices and its degrees
	for(i = 0; i < graph.numVertices; i++){
		fprintf(fp, "%s\t%d\n", vertices[i], degreeCount(graph, vertices[i]));
	}
	fprintf(fp,"\n");
	
	// Prints the vertices in BFS order to the text file
	while(queueEmpty(BFS) == 0){
		fprintf(fp, "%s ", dequeue(BFS));
	}
	fprintf(fp,"\n\n");
	
	// Prints the vertices in DFS order to the text file
	while(queueEmpty(DFS) == 0){
		fprintf(fp, "%s ", dequeue(DFS));
	}
	
	fclose(fp);
}
